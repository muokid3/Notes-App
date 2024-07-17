package com.dm.berxley.notesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dm.berxley.notesapp.data.daos.NoteDao
import com.dm.berxley.notesapp.data.models.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NotesDB : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}