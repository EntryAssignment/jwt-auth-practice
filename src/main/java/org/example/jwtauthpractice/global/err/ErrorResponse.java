package org.example.jwtauthpractice.global.err;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.jwtauthpractice.global.err.exception.ErrorCode;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Integer status;
    private LocalDateTime timestamp;
    private String description;

    public static ErrorResponse of(ErrorCode errorCode) {
        return ErrorResponse.builder().message(errorCode.getMessage()).status(errorCode.getStatusCode()).timestamp(LocalDateTime.now()).description(errorCode.getMessage()).build();
    }

    public static ErrorResponse of(int statusCode, String description) {
        return ErrorResponse.builder().message(description).status(statusCode).timestamp(LocalDateTime.now()).description(description).build();
    }
}
