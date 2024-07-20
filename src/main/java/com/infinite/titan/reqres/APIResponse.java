package com.infinite.titan.reqres;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse<T> {

    // Status of the response (e.g., success or error)
    private String status;

    // Message providing additional details about the response
    private String message;

    // The data to be returned (can be of any type)
    private T data;

    // Additional information if required (e.g., pagination details)
    private Object additionalInfo;

    // Method to create a success response
    public static <T> APIResponse<T> success(T data, String message) {
        return APIResponse.<T>builder()
                .status("success")
                .message(message)
                .data(data)
                .build();
    }

    // Method to create an error response
    public static <T> APIResponse<T> error(String message, T data) {
        return APIResponse.<T>builder()
                .status("error")
                .message(message)
                .data(data)
                .build();
    }
}