package com.cx.edu.entity.university;

import com.cx.edu.base.entity.BaseEntity;
import com.cx.edu.entity.university.enums.EducationEnum;
import com.cx.edu.entity.university.enums.SubjectEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Table(name = "t_university")
public class University extends BaseEntity {

	@Column(name = "base_id")
	private Long baseId;

	@Column(name = "school_no")
	private String schoolNo;

	@Column(name = "school_name")
	private String schoolName;

	@Column(name = "plan_number")
	private Integer planNumber;

	@Column(name = "scale_low_score")
	private Integer scaleLowScore;

	@Column(name = "low_score")
	private Integer lowScore;

	@Column(name = "submit_number")
	private Integer submitNumber;

	@Column(name = "sign_number")
	private Integer sign_number;

	@Column(name = "lack_number")
	private Integer lackNumber;

	@Column(name = "education")
	private EducationEnum education;

	@Column(name = "arts_sciences")
	private SubjectEnum artsSciences;

	@Column(name = "exam_at")
	private LocalDate examAt;

}
