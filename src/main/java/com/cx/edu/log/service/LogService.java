package com.cx.edu.log.service;


import com.cx.edu.base.service.BaseService;
import com.cx.edu.entity.log.Log;
import com.cx.edu.log.model.LogSearchDTO;

import java.util.List;

public interface LogService extends BaseService<Log, Long> {

    void saveLog(Log log);

    List<LogSearchDTO> getLog(String userName, String operation, String createAt, Integer page, Integer pageSize);

}
