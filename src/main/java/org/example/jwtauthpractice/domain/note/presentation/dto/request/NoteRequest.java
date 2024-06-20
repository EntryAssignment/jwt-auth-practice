package org.example.jwtauthpractice.domain.note.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class NoteRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
