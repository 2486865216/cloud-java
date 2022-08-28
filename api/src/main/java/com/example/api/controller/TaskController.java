package com.example.api.controller;

import com.example.api.service.TaskService;
import com.example.common.module.Love;
import com.example.common.module.TaskReceive;
import com.example.common.result.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author ye
 * createDate 2022/8/17  20:48
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/newTask")
    public ResultVo newTask(@RequestBody TaskReceive taskReceive){
        return taskService.newTask(taskReceive);
    }

    @GetMapping("/getMyTasks")
    public ResultVo getMyTasks(@RequestParam("userId") String userId){
        return taskService.getMyTasks(userId);
    }

    @GetMapping("/getAllTasks")
    public ResultVo getAllTasks(@RequestParam("pageId") Integer pageId){
        return taskService.getAllTasks(pageId);
    }

    @PostMapping("/love")
    public ResultVo love(@RequestBody Love love){
        return taskService.love(love);
    }

    @PostMapping("/unLove")
    public ResultVo unLove(@RequestBody Love love){
        return taskService.unLove(love);
    }

    @GetMapping("/getMyLoveList")
    public ResultVo getMyLoveList(@RequestParam("userId") String userId){
        return taskService.getMyLoveList(userId);
    }

    @GetMapping("/getMyLove")
    public ResultVo getMyLove(@RequestParam("userId") String userId){
        return taskService.getMyLove(userId);
    }
}
