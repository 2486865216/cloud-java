package com.example.api.service;

import com.example.common.module.Love;
import com.example.common.module.TaskReceive;
import com.example.common.result.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * author ye
 * createDate 2022/8/17  20:47
 */
@FeignClient(value = "TASK-TASK")
public interface TaskService {
    @PostMapping("/newTask")
    ResultVo newTask(@RequestBody TaskReceive taskReceive);

    @GetMapping("/getMyTasks")
    ResultVo getMyTasks(@RequestParam("userId") String userId);

    @GetMapping("/getAllTasks")
    ResultVo getAllTasks(@RequestParam("pageId") Integer pageId);

    @PostMapping("/love")
    ResultVo love(@RequestBody Love love);

    @PostMapping("/unLove")
    ResultVo unLove(@RequestBody Love love);

    @GetMapping("/getMyLoveList")
    ResultVo getMyLoveList(@RequestParam("userId") String userId);

    @GetMapping("/getMyLove")
    ResultVo getMyLove(@RequestParam("userId") String userId);
}
