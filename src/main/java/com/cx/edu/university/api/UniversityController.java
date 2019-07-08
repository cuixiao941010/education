package com.cx.edu.university.api;

import com.cx.edu.base.model.PageDTO;
import com.cx.edu.entity.university.enums.EducationEnum;
import com.cx.edu.entity.university.enums.SubjectEnum;
import com.cx.edu.exception.ResponseData;
import com.cx.edu.university.model.UniversityListDTO;
import com.cx.edu.university.service.UniversityService;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("api/v1/university")
public class UniversityController {

	@Autowired
	UniversityService universityService;

	@GetMapping
	public PageDTO executeActivation(@RequestParam(value = "education", required = false) EducationEnum eduction,
									 @RequestParam(value = "artsSciences", required = false)SubjectEnum artsSciences,
									 @RequestParam(value = "examAt", required = false) String examAt,
									 @RequestParam(value = "score", required = false) Integer score,
									 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
									 @RequestParam(value = "limit", required = false, defaultValue = "10") Integer pageSize,
									 @RequestParam(value = "schoolName", required = false) String schoolName) {

		List<UniversityListDTO> universityListDTOS = universityService.getUniversityList(eduction, artsSciences, examAt, score, page, pageSize, schoolName);
		return new PageDTO(new PageInfo(universityListDTOS), universityListDTOS);
	}
}
