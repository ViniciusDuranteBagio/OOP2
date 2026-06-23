package com.aula.oop.app.exceptions;

import java.time.OffsetDateTime;
import java.util.Map;

public record ApiError(
        Integer status,
        String error,
        String message,
        Map<String, String> validationErrors,
        OffsetDateTime timeStamp) {
}