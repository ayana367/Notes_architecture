package com.example.notes_architecture.data.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes_architecture.data.lokal.NoteDao


@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
 abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}