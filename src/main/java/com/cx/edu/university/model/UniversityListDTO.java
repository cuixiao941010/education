package com.cx.edu.university.model;

import com.cx.edu.entity.university.enums.EducationEnum;
import com.cx.edu.entity.university.enums.ScoreEnum;
import com.cx.edu.entity.university.enums.SubjectEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UniversityListDTO implements Serializable {

    private static final long serialVersionUID = -289762789261077258L;

    private Long id;

    private String schoolNo;

    private String schoolName;

    private Integer planNumber;

    private Integer lowScore;

    private Integer submitNumber;

    private Integer signNumber;

    private EducationEnum education;

    private SubjectEnum artsSciences;

    private LocalDate examAt;

    private ScoreEnum scoreType;

}
