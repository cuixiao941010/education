package com.cx.edu.log.repository;

import com.cx.edu.entity.log.Log;
import com.cx.edu.log.model.LogSearchDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

public interface LogMapper extends Mapper<Log> {

    List<LogSearchDTO> getUsers(@Param("userName") String userName, @Param("operation") String operation, @Param("createAt") String createAt);
}
