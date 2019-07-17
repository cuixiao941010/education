package com.cx.edu.test;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationResult {

    private boolean hasErrors;

    private Map<String, String> errorMsg;

}
