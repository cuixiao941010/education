package com.cx.edu.log.service.impl;

import com.cx.edu.base.service.impl.BaseServiceImpl;
import com.cx.edu.entity.log.Log;
import com.cx.edu.entity.university.University;
import com.cx.edu.entity.university.enums.EducationEnum;
import com.cx.edu.entity.university.enums.ScoreEnum;
import com.cx.edu.entity.university.enums.SubjectEnum;
import com.cx.edu.jwt.UserContext;
import com.cx.edu.log.model.LogSearchDTO;
import com.cx.edu.log.repository.LogMapper;
import com.cx.edu.log.service.LogService;
import com.cx.edu.university.model.UniversityListDTO;
import com.cx.edu.university.repository.UniversityMapper;
import com.cx.edu.university.service.UniversityService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LogServiceImpl extends BaseServiceImpl<Log, Long> implements LogService {

	@Autowired
	private LogMapper logMapper;

	@Override
	protected Mapper<Log> getMapper() {
		return logMapper;
	}

	@Override
	public void saveLog(Log log) {
		save(log);
	}

	@Override
	public List<LogSearchDTO> getLog(String userName, String operation, String createAt, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<LogSearchDTO> logSearchDTOS = logMapper.getUsers(userName, operation, createAt);
		return logSearchDTOS;
	}
}
