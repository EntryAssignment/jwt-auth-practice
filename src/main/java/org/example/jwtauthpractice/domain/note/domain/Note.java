package org.example.jwtauthpractice.domain.note.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.jwtauthpractice.domain.note.presentation.dto.request.NoteRequest;
import org.example.jwtauthpractice.domain.user.domain.User;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void updateNote(NoteRequest noteRequest) {
        this.title = noteRequest.getTitle();
        this.content = noteRequest.getContent();
    }

    public static Note of(NoteRequest noteRequest, User user) {
        return Note.builder().title(noteRequest.getTitle()).content(noteRequest.getContent()).user(user).build();
    }
}