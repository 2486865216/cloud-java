package com.example.mapperServer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.module.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * author ye
 * createDate 2022/8/10  20:44
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
