package org.example.jwtauthpractice.domain.auth.exception;

import org.example.jwtauthpractice.global.err.exception.ErrorCode;
import org.example.jwtauthpractice.global.err.exception.NoteException;

public class PasswordMismatchException extends NoteException {
    public static final NoteException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
