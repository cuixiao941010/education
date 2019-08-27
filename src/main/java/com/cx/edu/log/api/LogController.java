package com.cx.edu.log.api;

import com.cx.edu.base.model.PageDTO;
import com.cx.edu.entity.university.enums.EducationEnum;
import com.cx.edu.entity.university.enums.SubjectEnum;
import com.cx.edu.log.model.LogSearchDTO;
import com.cx.edu.log.service.LogService;
import com.cx.edu.university.model.UniversityListDTO;
import com.cx.edu.university.service.UniversityService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/log")
public class LogController {

	@Autowired
	LogService logService;

	@GetMapping
	public PageDTO executeActivation(@RequestParam(value = "userName", required = false) String userName,
									 @RequestParam(value = "operation", required = false) String operation,
									 @RequestParam(value = "createAt", required = false) String createAt,
									 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
									 @RequestParam(value = "limit", required = false, defaultValue = "10") Integer pageSize) {

		List<LogSearchDTO> logSearchDTOS = logService.getLog(userName, operation, createAt, page, pageSize);
		return new PageDTO(new PageInfo(logSearchDTOS), logSearchDTOS);
	}

}
