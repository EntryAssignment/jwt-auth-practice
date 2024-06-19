package org.example.jwtauthpractice.global.err.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    EXPIRED_TOKEN(401, "expired token"),
    INVALID_TOKEN(401, "invalid token"),
    USER_NOT_ACCESSIBLE(403, "Don't have permission"),
    REFRESH_TOKEN_NOT_FOUND(404, "refresh token not found"),
    PASSWORD_MISMATCH(401, "password mismatch"),
    USER_NOT_FOUND(404, "user not found"),
    NOTE_NOT_FOUND(404, "note not found"),
    BAD_REQUEST(400, "bad request"),
    INTERNAL_SERVER_ERROR(500, "server error");

    private final int statusCode;
    private final String message;
}
