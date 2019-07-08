package com.cx.edu.university.service.impl;

import com.cx.edu.base.service.impl.BaseServiceImpl;
import com.cx.edu.entity.university.University;
import com.cx.edu.entity.university.enums.EducationEnum;
import com.cx.edu.entity.university.enums.ScoreEnum;
import com.cx.edu.entity.university.enums.SubjectEnum;
import com.cx.edu.jwt.UserContext;
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
public class UniversityServiceImpl extends BaseServiceImpl<University, Long> implements UniversityService {

	@Autowired
	private UniversityMapper universityMapper;

	@Override
	protected Mapper<University> getMapper() {
		return universityMapper;
	}

	private static final Integer SCORE_LIMIT = 30;

	private static final Integer SCORE_COMPUTE = 10;

	@Override
	public List<UniversityListDTO> getUniversityList(EducationEnum education, SubjectEnum artsSciences, String examAt, Integer score, Integer page, Integer pageSize, String schoolName) {
		Long currentUserId = UserContext.getCurrentUser().getId();
		PageHelper.startPage(page, pageSize);
		Integer minScore = 0;
		Integer maxScore = 0;
		if (score != null) {
			minScore = score - SCORE_LIMIT;
			maxScore = score + SCORE_LIMIT;
		}
		List<UniversityListDTO> universityListDTOS = universityMapper.getUniversityList(education, artsSciences, examAt, minScore, maxScore, schoolName);
		if (CollectionUtils.isEmpty(universityListDTOS)) {
			return universityListDTOS;
		}
		if (score == null) {
			return universityListDTOS;
		}
		return universityListDTOS.stream().map(universityListDTO -> {
			if (universityListDTO.getLowScore() < score - SCORE_COMPUTE) {
				universityListDTO.setScoreType(ScoreEnum.STABLE);
			} else if (universityListDTO.getLowScore() >= score - SCORE_COMPUTE && universityListDTO.getLowScore() < score + SCORE_COMPUTE) {
				universityListDTO.setScoreType(ScoreEnum.PROTECT);
			} else {
				universityListDTO.setScoreType(ScoreEnum.PUNCHING);
			}
			return universityListDTO;
		}).collect(Collectors.toList());
	}
}
