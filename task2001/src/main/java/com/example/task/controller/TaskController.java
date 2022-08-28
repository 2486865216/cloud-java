package com.example.task.controller;

import com.example.common.module.Tasks;
import com.example.common.module.TaskReceive;
import com.example.common.result.ResultVo;
import com.example.task.mapper.TaskMapper;
import com.example.task.service.TTLService;
import com.example.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * author ye
 * createDate 2022/8/17  20:45
 */
@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/newTask")
    public ResultVo newTask(@RequestBody TaskReceive taskReceive) {
        Tasks task = new Tasks();
        task.setTaskName(taskReceive.getTaskName());
        task.setTaskCommand(taskReceive.getTaskCommand());
        task.setCreateUserId(taskReceive.getCreateUserId());
        task.setIsShow(taskReceive.getIsShow());
        task.setEndTime(Timestamp.valueOf(taskReceive.getEndTime()));
        return taskService.newTask(task);
    }

    @GetMapping("/getMyTasks")
    public ResultVo getMyTasks(@RequestParam("userId") String userId) {
        return taskService.getMyTasks(userId);
    }

    @GetMapping("/getAllTasks")
    public ResultVo getAllTasks(@RequestParam("pageId") Integer pageId) {
        return taskService.getAllTasks(pageId);
    }
}
