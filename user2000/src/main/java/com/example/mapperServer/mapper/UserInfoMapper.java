package com.example.mapperServer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.module.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * author ye
 * createDate 2022/8/12  20:42
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    void updateAvatar(@Param("userId") String userId, @Param("url") String url);
}
