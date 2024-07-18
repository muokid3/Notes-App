package com.dm.berxley.notesapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.berxley.notesapp.data.models.Note
import com.dm.berxley.notesapp.data.repositories.NoteRepository
import com.dm.berxley.notesapp.utils.Graph
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val noteRepository: NoteRepository = Graph.noteRepository
) : ViewModel() {

    private val _noteTitle = mutableStateOf("")
    val noteTitle: State<String> = _noteTitle

    private val _noteDescription = mutableStateOf("")
    val noteDescription: State<String> = _noteDescription

    private val _createdAt = mutableStateOf(System.currentTimeMillis())
    val createdAt: State<Long> = _createdAt

    lateinit var getNotesOrderedByDateAdded: Flow<List<Note>>
    lateinit var getNotesOrderedByTitle: Flow<List<Note>>

    init {
        viewModelScope.launch {
            getNotesOrderedByTitle = noteRepository.getNotesOrderedByTitle()
        }

        viewModelScope.launch {
            getNotesOrderedByDateAdded = noteRepository.getNotesOrderedByDateAdded()
        }
    }

    fun onNewNoteTitle(newTitle: String){
        _noteTitle.value = newTitle
    }
    fun onNewNoteDescription(newDesc: String){
        _noteDescription.value = newDesc
    }
    fun onNewCreatedAt(newCreatedAt: Long){
        _createdAt.value = newCreatedAt
    }

    fun upsertNote(note: Note){
        viewModelScope.launch {
            noteRepository.upsertNote(note)
        }
    }

    fun getNoteById(id: Int): Flow<Note>{
        return noteRepository.getNoteById(id)
    }



}