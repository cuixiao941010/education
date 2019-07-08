package com.cx.edu.entity.basics;

import com.cx.edu.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "t_school_base")
public class Basics extends BaseEntity {

	@Column(name = "school_no")
	private String school_no;

	@Column(name = "school_name")
	private String school_name;

}
