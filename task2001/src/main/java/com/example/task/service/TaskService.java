package com.example.task.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.module.Love;
import com.example.common.module.Tasks;
import com.example.common.result.ResultCode;
import com.example.common.result.ResultVo;
import com.example.common.utils.RandomId;
import com.example.task.mapper.TaskMapper;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author ye
 * createDate 2022/8/17  20:39
 */
@Service
public class TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TTLService ttlService;

    public ResultVo newTask(Tasks task) {
        String taskId = String.valueOf(RandomId.getNextId());
        task.setTaskId(taskId);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());

        task.setCreateTime(Timestamp.valueOf(format));
        int insert = taskMapper.insert(task);
        if (insert == 1) {
            //删除redis，保持缓存数据库一致性
            Long size = redisTemplate.opsForList().size(task.getCreateUserId() + "list");
            redisTemplate.opsForList().leftPop(task.getCreateUserId() + "list", size);
            //放入rabbit延迟队列
            ttlService.sendDelayedMessage(taskId, task.getEndTime());
            return new ResultVo(ResultCode.SUCCESS, task);
        }
        return new ResultVo(ResultCode.FAILED, null);
    }

    public ResultVo getMyTasks(String userId) {
        //redis
        List<Tasks> tasksList = (List<Tasks>) redisTemplate.opsForList().range(userId + "list", 0, -1);
        if (tasksList.size() == 0) {
            tasksList = taskMapper.selectList(new QueryWrapper<Tasks>().eq("create_user_id", userId));
            if (tasksList.size() == 0) {
                return new ResultVo(ResultCode.SUCCESS, null);
            }
            for (Tasks tasks : tasksList) {
                redisTemplate.opsForList().rightPush(userId + "list", tasks);
            }
        }
        return new ResultVo(ResultCode.SUCCESS, tasksList);
    }

    public ResultVo getAllTasks(Integer pageId)  {
        Page<Tasks> page = new Page<>(pageId, 10);
        Page<Tasks> is_show = taskMapper.selectPage(page, new QueryWrapper<Tasks>().eq("is_show", 1).eq("status", 0));
        Map<String, Object> map = new HashMap<>();
        map.put("tasks", is_show.getRecords());
        map.put("total", is_show.getTotal());
        return new ResultVo(ResultCode.SUCCESS, map);
    }
}
