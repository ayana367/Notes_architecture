package com.example.notes_architecture.domain.usecases

import com.example.notes_architecture.domain.model.Note
import com.example.notes_architecture.domain.repository.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
   operator fun invoke(note: Note) = noteRepository.createNote(note)
}