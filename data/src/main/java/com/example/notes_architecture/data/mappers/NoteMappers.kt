package com.example.notes_architecture.data.mappers

import com.example.notes_architecture.data.model.NoteEntity
import com.example.notes_architecture.domain.model.Note

fun com.example.notes_architecture.domain.model.Note.toEntity() = NoteEntity(
    id, title, descriptions
)

fun NoteEntity.toNote() = com.example.notes_architecture.domain.model.Note(
    id, title, descriptions
)