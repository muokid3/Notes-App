package com.dm.berxley.notesapp.data.repositories

import com.dm.berxley.notesapp.data.daos.NoteDao
import com.dm.berxley.notesapp.data.models.Note

class NoteRepository(private val notesDao: NoteDao) {

    suspend fun upsertNote(note: Note){
        notesDao.upsertNote(note)
    }

    suspend fun deleteNote(note: Note){
        notesDao.deleteNote(note)
    }

    fun getNoteById(id: Int){
        notesDao.getNoteById(id)
    }

    suspend fun getNotesOrderedByDateAdded(){
        notesDao.getNotesOrderedByDateAdded()
    }
    suspend fun getNotesOrderedByTitle(){
        notesDao.getNotesOrderedByTitle()
    }



}