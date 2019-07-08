package com.cx.edu.university.repository;

import com.cx.edu.entity.university.University;
import com.cx.edu.entity.university.enums.EducationEnum;
import com.cx.edu.entity.university.enums.SubjectEnum;
import com.cx.edu.university.model.UniversityListDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

public interface UniversityMapper extends Mapper<University> {

    List<UniversityListDTO> getUniversityList(@Param("education") EducationEnum education, @Param("artsSciences") SubjectEnum artsSciences, @Param("examAt") String examAt, @Param("minScore") Integer minScore, @Param("maxScore") Integer maxScore, @Param("schoolName") String schoolName);
}
