package com.cx.edu.entity.log;

import com.cx.edu.base.entity.BaseEntity;
import com.cx.edu.entity.university.enums.EducationEnum;
import com.cx.edu.entity.university.enums.SubjectEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table(name = "t_log")
public class Log extends BaseEntity {

	@Column(name = "user_name")
	private String userName;

	@Column(name = "operation")
	private String operation;

	@Column(name = "method")
	private String method;

	@Column(name = "params")
	private String params;

	@Column(name = "ip")
	private String ip;

	@Column(name = "create_at")
	private LocalDateTime createAt;

}
