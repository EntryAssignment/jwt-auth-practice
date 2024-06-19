package org.example.jwtauthpractice.domain.note.presentation.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.jwtauthpractice.domain.note.domain.Note;

@Builder
@Data
public class NoteResponse {
    private Long id;
    private String title;
    private String content;

    public static NoteResponse of(Note note) {
        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .build();
    }
}
