package com.dm.berxley.notesapp.utils

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dm.berxley.notesapp.data.NotesDB
import com.dm.berxley.notesapp.data.repositories.NoteRepository

object Graph {
    lateinit var database: NotesDB

    val noteRepository by lazy {
        NoteRepository(database.noteDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, NotesDB::class.java, "my_notes_db").build()
    }

}