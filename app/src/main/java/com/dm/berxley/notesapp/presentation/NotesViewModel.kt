package com.dm.berxley.notesapp.presentation

import androidx.lifecycle.ViewModel
import com.dm.berxley.notesapp.data.daos.NoteDao
import com.dm.berxley.notesapp.data.repositories.NoteRepository
import com.dm.berxley.notesapp.utils.Graph

class NotesViewModel(
    private val noteRepository: NoteRepository = Graph.noteRepository
) : ViewModel() {


}