package org.example.jwtauthpractice.domain.note.service;

import lombok.RequiredArgsConstructor;
import org.example.jwtauthpractice.domain.note.domain.Note;
import org.example.jwtauthpractice.domain.note.domain.repository.NoteRepository;
import org.example.jwtauthpractice.domain.note.facade.NoteFacade;
import org.example.jwtauthpractice.domain.note.presentation.dto.request.NoteRequest;
import org.example.jwtauthpractice.domain.note.presentation.dto.response.NoteResponse;
import org.example.jwtauthpractice.domain.user.domain.User;
import org.example.jwtauthpractice.domain.user.exception.UserNotAccessible;
import org.example.jwtauthpractice.domain.user.facade.UserFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteFacade noteFacade;
    private final UserFacade userFacade;

    public void createNote(NoteRequest noteRequest) {
        User user = userFacade.getCurrentUser();
        Note note = Note.of(noteRequest, user);
        noteRepository.save(note);
    }

    public List<NoteResponse> getNotes() {
        User user = userFacade.getCurrentUser();
        List<Note> notes = noteRepository.findByUserId(user.getId());
        return notes.stream().map(NoteResponse::of).toList();
    }

    public void updateNoteById(Long noteId, NoteRequest noteRequest) {
        User user = userFacade.getCurrentUser();
        Note note = noteFacade.findById(noteId);

        if (!Objects.equals(user.getUsername(), note.getUser().getUsername())) {
            throw UserNotAccessible.EXCEPTION;
        }

        note.update(noteRequest);
        noteRepository.save(note);
    }

    public void deleteNoteById(Long noteId) {
        User user = userFacade.getCurrentUser();
        Note note = noteFacade.findById(noteId);

        if (!Objects.equals(user.getUsername(), note.getUser().getUsername())) {
            throw UserNotAccessible.EXCEPTION;
        }

        noteRepository.delete(note);
    }
}