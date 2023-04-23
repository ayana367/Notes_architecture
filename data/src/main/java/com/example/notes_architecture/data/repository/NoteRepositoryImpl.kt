package com.example.notes_architecture.data.repository

import com.example.notes_architecture.data.base.BaseRepository
import com.example.notes_architecture.data.lokal.NoteDao
import com.example.notes_architecture.data.mappers.toEntity
import com.example.notes_architecture.data.mappers.toNote
import com.example.notes_architecture.domain.model.Note
import com.example.notes_architecture.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
): NoteRepository, BaseRepository() {
    override fun createNote(note: Note) = doRequest {
        noteDao.createNote(note.toEntity())
    }

    override fun getAllNotes() = doRequest {
        noteDao.getAllNotes().map { it.toNote() }
    }

    override fun editNote(note: Note) = doRequest{
        noteDao.editNote(note.toEntity())
    }

    override fun delete(note: Note) = doRequest {
        noteDao.deleteNote(note.toEntity())
    }

}