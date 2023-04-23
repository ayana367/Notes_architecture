package com.example.notes_architecture.data.mappers

import com.example.notes_architecture.data.model.NoteEntity
import com.example.notes_architecture.domain.model.Note

fun Note.toEntity() = NoteEntity(
    id, title, descriptions
)

fun NoteEntity.toNote() = Note(
    id, title, descriptions
)