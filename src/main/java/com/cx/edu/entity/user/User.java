package com.cx.edu.entity.user;

import com.cx.edu.base.entity.BaseEntity;
import com.cx.edu.entity.university.enums.EducationEnum;
import com.cx.edu.entity.university.enums.SubjectEnum;
import com.cx.edu.entity.user.enums.RoleEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Table(name = "t_user")
public class User extends BaseEntity {

	@Column(name = "user_name")
	private String userName;

	@Column(name = "pass_word")
	private String passWord;

	@Column(name = "salt")
	private String salt;

	@Column(name = "role")
	private RoleEnum role;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile")
	private String mobile;

}
