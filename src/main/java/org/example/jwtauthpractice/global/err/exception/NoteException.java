package org.example.jwtauthpractice.global.err.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoteException extends RuntimeException {
    private final ErrorCode errorCode;
}
