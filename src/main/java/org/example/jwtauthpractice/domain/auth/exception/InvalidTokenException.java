package org.example.jwtauthpractice.domain.auth.exception;

import org.example.jwtauthpractice.global.err.exception.ErrorCode;
import org.example.jwtauthpractice.global.err.exception.NoteException;

public class InvalidTokenException extends NoteException {
    public static final NoteException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}