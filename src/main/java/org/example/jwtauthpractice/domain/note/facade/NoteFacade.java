package org.example.jwtauthpractice.domain.note.facade;

import lombok.RequiredArgsConstructor;
import org.example.jwtauthpractice.domain.note.domain.Note;
import org.example.jwtauthpractice.domain.note.domain.repository.NoteRepository;
import org.example.jwtauthpractice.domain.note.exception.NoteNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NoteFacade {
    private final NoteRepository noteRepository;

    public Note findById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> NoteNotFoundException.EXCEPTION);
    }
}

