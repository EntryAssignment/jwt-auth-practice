package org.example.jwtauthpractice.domain.user.exception;

import org.example.jwtauthpractice.global.err.exception.ErrorCode;
import org.example.jwtauthpractice.global.err.exception.NoteException;

public class UserNotAccessible extends NoteException {
    public static final NoteException EXCEPTION = new UserNotAccessible();

    public UserNotAccessible() {
        super(ErrorCode.USER_NOT_ACCESSIBLE);
    }
}
