package com.cx.edu.log.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class LogSearchDTO implements Serializable {

    private static final long serialVersionUID = -5779280403067394058L;

    private Long id;

    private String userName;

    private String operation;

    private String ip;

    private LocalDateTime createAt;
}
