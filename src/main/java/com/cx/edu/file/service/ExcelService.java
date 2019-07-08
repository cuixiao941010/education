package com.cx.edu.file.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class ExcelService {

    @Value("${education.excelFile}")
    private String excelPath;
}
