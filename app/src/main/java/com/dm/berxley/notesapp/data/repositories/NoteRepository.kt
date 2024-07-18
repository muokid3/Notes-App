package com.dm.berxley.notesapp.data.repositories

import com.dm.berxley.notesapp.data.daos.NoteDao
import com.dm.berxley.notesapp.data.models.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val notesDao: NoteDao) {

    suspend fun upsertNote(note: Note){
        notesDao.upsertNote(note)
    }

    suspend fun deleteNote(note: Note){
        notesDao.deleteNote(note)
    }

    fun getNoteById(id: Int): Flow<Note>{
        return notesDao.getNoteById(id)
    }

    fun getNotesOrderedByDateAdded() = notesDao.getNotesOrderedByDateAdded()
    fun getNotesOrderedByTitle() = notesDao.getNotesOrderedByTitle()



}