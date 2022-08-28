package com.example.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.module.Love;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author ye
 * createDate 2022/8/20  19:26
 */
@Mapper
public interface LoveMapper extends BaseMapper<Love> {
    List<String> getMyLoveList(String userId);
}
