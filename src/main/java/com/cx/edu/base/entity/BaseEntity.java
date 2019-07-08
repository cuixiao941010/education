package com.cx.edu.base.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -5785859031130509619L;

	/**
	 * 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "JDBC")
	protected Long id;

//	/**
//	 * 创建时间
//	 */
//	@Column(name = "create_at")
//	private LocalDateTime createAt;
//
//	/**
//	 * 修改时间
//	 */
//	@Column(name = "update_at")
//	private LocalDateTime updateAt;
}
