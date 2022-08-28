package com.example.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.module.Tasks;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * author ye
 * createDate 2022/8/17  20:37
 */
@Mapper
public interface TaskMapper extends BaseMapper<Tasks> {
    void updateLoveCount(@Param("taskId") String taskId,@Param("count") int count);
    void updateStatus(@Param("taskId") String taskId, @Param("status") Integer status);
}
