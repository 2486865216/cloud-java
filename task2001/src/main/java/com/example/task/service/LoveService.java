package com.example.task.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.module.Love;
import com.example.common.module.Tasks;
import com.example.common.result.ResultCode;
import com.example.common.result.ResultVo;
import com.example.task.mapper.LoveMapper;
import com.example.task.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author ye
 * createDate 2022/8/20  19:27
 */
@Service
public class LoveService {
    @Autowired
    private LoveMapper loveMapper;

    @Autowired
    private TaskMapper taskMapper;

    public ResultVo love(Love love) {
        Tasks tasks = taskMapper.selectOne(new QueryWrapper<Tasks>().eq("task_id", love.getTaskId()));
        taskMapper.updateLoveCount(love.getTaskId(), tasks.getLoveCount() + 1);
        loveMapper.insert(love);
        return new ResultVo(ResultCode.SUCCESS, null);
    }

    //取消收藏
    public ResultVo unLove(Love love) {
        Tasks tasks = taskMapper.selectOne(new QueryWrapper<Tasks>().eq("task_id", love.getTaskId()));
        if (tasks.getLoveCount() <= 0) {
            return new ResultVo(ResultCode.FAILED, null);
        }
        taskMapper.updateLoveCount(love.getTaskId(), tasks.getLoveCount() - 1);
        loveMapper.delete(new QueryWrapper<Love>()
                .eq("love_user_id", love.getLoveUserId())
                .eq("task_id", love.getTaskId()));
        return new ResultVo(ResultCode.SUCCESS, null);
    }

    public ResultVo getMyLoveList(String userId) {
        return new ResultVo(ResultCode.SUCCESS, loveMapper.getMyLoveList(userId));
    }
    
    public ResultVo getMyLove(String userId) {
        List<String> myLoveList = loveMapper.getMyLoveList(userId);
        List<Tasks> tasks = new ArrayList<>();
        for (String s : myLoveList) {
            tasks.add(taskMapper.selectOne(new QueryWrapper<Tasks>().eq("task_id", s)));
        }
        return new ResultVo(ResultCode.SUCCESS, tasks);
    }
}
