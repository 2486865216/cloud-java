package com.example.task.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.module.Love;
import com.example.common.module.Tasks;
import com.example.common.result.ResultCode;
import com.example.common.result.ResultVo;
import com.example.task.service.LoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author ye
 * createDate 2022/8/20  19:45
 */
@RestController
public class LoveController {
    @Autowired
    private LoveService loveService;

    //收藏
    @PostMapping("/love")
    public ResultVo love(@RequestBody Love love) {
        return loveService.love(love);
    }

    //取消收藏
    @PostMapping("/unLove")
    public ResultVo unLove(@RequestBody Love love) {
        return loveService.unLove(love);
    }

    @GetMapping("/getMyLoveList")
    public ResultVo getMyLoveList(@RequestParam("userId") String userId) {
        return loveService.getMyLoveList(userId);
    }

    @GetMapping("/getMyLove")
    public ResultVo getMyLove(@RequestParam("userId") String userId) {
        return loveService.getMyLove(userId);
    }
}
