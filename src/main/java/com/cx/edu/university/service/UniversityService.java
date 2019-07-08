package com.cx.edu.university.service;


import com.cx.edu.base.service.BaseService;
import com.cx.edu.entity.university.University;
import com.cx.edu.entity.university.enums.EducationEnum;
import com.cx.edu.entity.university.enums.SubjectEnum;
import com.cx.edu.university.model.UniversityListDTO;
import java.util.List;

public interface UniversityService extends BaseService<University, Long> {


    List<UniversityListDTO> getUniversityList(EducationEnum education, SubjectEnum artsSciences, String examAt, Integer score, Integer page, Integer pageSize, String schoolName);
}
