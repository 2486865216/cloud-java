package com.example.task.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.module.Tasks;
import com.example.task.config.RabbitConfig;
import com.example.task.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * author ye
 * createDate 2022/8/27  19:16
 */
@Slf4j
@Service
public class TTLService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public void sendDelayedMessage(String taskId, Timestamp endTime) {
        long time = endTime.getTime() - new Date().getTime();
        log.info("当前时间:{}, 发送一条时长为:{}ms,信息给了一个delayed队列:{}",new Date(), time, taskId);
        rabbitTemplate.convertAndSend(RabbitConfig.DELAYED_EXCHANGE, RabbitConfig.DELAYED_ROUTINGKEY, taskId , msg -> {
            //设置TTL
            msg.getMessageProperties().setDelay(Math.toIntExact(time));
            return msg;
        });
    }

    //监听消息
    @RabbitListener(queues = RabbitConfig.DELAYED_QUEUE)
    public void receiveDelayedMessage(Message message){
        String taskId = new String(message.getBody());
        log.info("当前时间：{}，接受到delayed死信队列消息:{}",new Date(), taskId);
        Tasks task = taskMapper.selectOne(new QueryWrapper<Tasks>().eq("task_id", taskId));
        taskMapper.updateStatus(taskId, task.getStatus());
        Long size = redisTemplate.opsForList().size(task.getCreateUserId() + "list");
        redisTemplate.opsForList().leftPop(task.getCreateUserId() + "list", size);
    }
}
