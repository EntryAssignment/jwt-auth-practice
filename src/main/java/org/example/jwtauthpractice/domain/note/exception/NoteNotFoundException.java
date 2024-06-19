package org.example.jwtauthpractice.domain.note.exception;

import org.example.jwtauthpractice.global.err.exception.ErrorCode;
import org.example.jwtauthpractice.global.err.exception.NoteException;

public class NoteNotFoundException extends NoteException {
    public static final NoteException EXCEPTION = new NoteNotFoundException();

    private NoteNotFoundException() {
        super(ErrorCode.NOTE_NOT_FOUND);
    }
}