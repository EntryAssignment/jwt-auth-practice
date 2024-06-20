package org.example.jwtauthpractice.domain.note.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jwtauthpractice.domain.note.presentation.dto.request.NoteRequest;
import org.example.jwtauthpractice.domain.note.presentation.dto.response.NoteResponse;
import org.example.jwtauthpractice.domain.note.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/note")
public class NoteController {
    private final NoteService noteService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNote(@RequestBody @Valid NoteRequest noteRequests) {
        noteService.createNote(noteRequests);
    }

    @GetMapping
    public List<NoteResponse> getNotes() {
        return noteService.getNotes();
    }

    @PutMapping("{noteId}")
    public void updateNote(@PathVariable Long noteId, @RequestBody @Valid NoteRequest noteRequests) {
        noteService.updateNoteById(noteId, noteRequests);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{noteId}")
    public void deleteNoteById(@PathVariable Long noteId) {
        noteService.deleteNoteById(noteId);
    }
}
