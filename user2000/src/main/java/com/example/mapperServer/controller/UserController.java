package com.example.mapperServer.controller;

import com.example.common.module.User;
import com.example.common.module.UserInfo;
import com.example.common.result.ResultVo;
import com.example.mapperServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * author ye
 * createDate 2022/8/10  20:45
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResultVo login(@RequestParam("username") String username,@RequestParam("password") String password){
        return userService.login(username, password);
    }

    @PostMapping("/register")
    public ResultVo register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/getInfo")
    public ResultVo getInfo(@RequestHeader(value = "Authorization") String token){
        return userService.getInfo(token);
    }

    @GetMapping("/getOtherInfo")
    public ResultVo getOtherInfo(@RequestParam("userId") String userId) {
        return userService.getOtherInfo(userId);
    }

    @PostMapping("/editUserInfo")
    public ResultVo editUserInfo(@RequestBody UserInfo userInfo) {
        return userService.editUserInfo(userInfo);
    }

    @PostMapping(value = "/userAvatarUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ResultVo userAvatarUpload(@RequestPart("avatar") MultipartFile avatar, @RequestHeader(value = "Authorization") String token) {
        return userService.userAvatarUpload(avatar, token);
    }
}
