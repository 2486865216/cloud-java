package com.example.api.service;

import com.example.common.module.User;
import com.example.common.module.UserInfo;
import com.example.common.result.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * author ye
 * createDate 2022/8/11  17:33
 */
@Service
@FeignClient(value = "TASK-USER")
public interface UserService {
    @GetMapping("/login")
    ResultVo login(@RequestParam("username") String username,@RequestParam("password") String password);

    @PostMapping("/register")
    ResultVo register(@RequestBody User user);

    @GetMapping("/getInfo")
    ResultVo getInfo(@RequestHeader(value = "Authorization") String token);

    @GetMapping("/getOtherInfo")
    ResultVo getOtherInfo(@RequestParam("userId") String userId);

    @PostMapping("/editUserInfo")
    ResultVo editUserInfo(@RequestBody UserInfo userInfo);

    @PostMapping(value = "/userAvatarUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ResultVo userAvatarUpload(@RequestPart("avatar") MultipartFile avatar, @RequestHeader(value = "Authorization") String token);
}
