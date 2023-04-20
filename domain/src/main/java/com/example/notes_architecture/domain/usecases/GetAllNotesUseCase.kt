package com.example.notes_architecture.domain.usecases

import com.example.notes_architecture.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

   operator fun invoke() = noteRepository.getAllNotes()
}