package com.tyro.order.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult {

    private int code;

    private String message;

    private Map<String, Object> details;

    public ErrorResult(int code, String message) {
        this.code = code;
        this.message = message;
        this.details = new HashMap<>();
    }

    public <T> ErrorResult writeDetails(String key, T value) {
        details.put(key, value);
        return this;
    }
}