package com.infinite.titan.reqres;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    // Timestamp of when the error occurred
    private LocalDateTime timestamp;

    // HTTP status code of the error
    private int status;

    // Error message describing what went wrong
    private String error;

    // Detailed error message for debugging purposes
    private String message;

    // URL or endpoint where the error occurred (optional)
    private String path;
}