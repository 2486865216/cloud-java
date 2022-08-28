package com.example.mapperServer.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.module.User;
import com.example.common.module.UserInfo;
import com.example.common.result.ResultCode;
import com.example.common.result.ResultVo;
import com.example.common.utils.MyJWTUtil;
import com.example.common.utils.PasswordUtils;
import com.example.common.utils.RandomId;
import com.example.common.utils.UpLoadAliyunOss;
import com.example.mapperServer.mapper.UserInfoMapper;
import com.example.mapperServer.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * author ye
 * createDate 2022/8/17  19:12
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    public ResultVo login(String username, String password){
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (user != null) {
            //校验密码
            if (PasswordUtils.verify(password, user.getPassword())){
                String token = MyJWTUtil.createToken(user.getUserId());
                return new ResultVo(new HashMap<String, String>(){{put("token", token);}});
            }
            return new ResultVo(401, "密码错误", null);
        }
        return new ResultVo(ResultCode.FAILED, null);
    }

    public ResultVo register(User user) {
        //看看用户名是否重复
        User isHas = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (isHas != null) {
            return new ResultVo(400, "用户名已存在", null);
        }
        //密码加密
        user.setPassword(PasswordUtils.generate(user.getPassword()));
        long nextId = RandomId.getNextId();
        user.setUserId(String.valueOf(nextId));
        userMapper.insert(user);

        UserInfo userInfo = new UserInfo();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());

        userInfo.setUserId(user.getUserId());
        userInfo.setUsername(user.getUsername());
        userInfo.setCreateTime(Timestamp.valueOf(format));
        userInfoMapper.insert(userInfo);

        return new ResultVo(new HashMap<String, String>(){{put("token", MyJWTUtil.createToken(String.valueOf(nextId)));}});
    }

    public ResultVo getInfo(String token){
        if (MyJWTUtil.isExpire(token)) {
            return new ResultVo(402, "身份信息已过期，请重新登录!", null);
        }
        String userId = MyJWTUtil.getUserId(token);
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(userId);
        if (userInfo == null) {
            userInfo = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("user_id", userId));
            if (userInfo == null) {
                return new ResultVo(ResultCode.FAILED, "通过token获取信息失败!");
            }
            redisTemplate.opsForValue().set(userId, userInfo);
        }
        return new ResultVo(ResultCode.SUCCESS, userInfo);
    }

    public ResultVo getOtherInfo(String userId) {
        Map<String, UserInfo> userMap = redisTemplate.opsForHash().entries(userId + "userInfo");
        UserInfo userInfo = userMap.get("userInfo");
        if (userInfo == null) {
            userInfo = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("user_id", userId));
            if (userInfo == null) {
                return new ResultVo(ResultCode.FAILED, "获取信息失败!");
            }
            Map<String, UserInfo> map = new HashMap<>();
            map.put("userInfo", userInfo);
            redisTemplate.opsForHash().putAll(userId + "userInfo", map);
        }
        return new ResultVo(ResultCode.SUCCESS, userInfo);
    }

    //编辑信息
    public ResultVo editUserInfo(UserInfo userInfo) {
        int update = userInfoMapper.update(userInfo, new QueryWrapper<UserInfo>().eq("user_id", userInfo.getUserId()));
        redisTemplate.opsForHash().delete(userInfo.getUserId() + "userInfo", "userInfo");
        redisTemplate.opsForValue().getAndDelete(userInfo.getUserId());
        return new ResultVo(ResultCode.SUCCESS, null);
    }

    public ResultVo userAvatarUpload(MultipartFile avatar, String token) {
        if (MyJWTUtil.isExpire(token)) {
            return new ResultVo(402, "身份信息已过期，请重新登录!", null);
        }
        String userId = MyJWTUtil.getUserId(token);
        String upload = UpLoadAliyunOss.upload(avatar);
        userInfoMapper.updateAvatar(userId, upload);
        redisTemplate.opsForHash().delete(userId + "userInfo", "userInfo");
        redisTemplate.opsForValue().getAndDelete(userId);
        return new ResultVo(ResultCode.SUCCESS, upload);
    }
}
