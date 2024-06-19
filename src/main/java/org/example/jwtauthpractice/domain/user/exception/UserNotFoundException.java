package org.example.jwtauthpractice.domain.user.exception;

import org.example.jwtauthpractice.global.err.exception.ErrorCode;
import org.example.jwtauthpractice.global.err.exception.NoteException;

public class UserNotFoundException extends NoteException {
    public static final NoteException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
