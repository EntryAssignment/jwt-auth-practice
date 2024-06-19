package org.example.jwtauthpractice.domain.note.domain.repository;

import org.example.jwtauthpractice.domain.note.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long userId);
}
