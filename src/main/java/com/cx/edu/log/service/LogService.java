package com.cx.edu.log.service;


import com.cx.edu.base.service.BaseService;
import com.cx.edu.entity.log.Log;

public interface LogService extends BaseService<Log, Long> {

    void saveLog(Log log);

}
