package com.dm.berxley.notesapp.views

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dm.berxley.notesapp.data.models.Note
import com.dm.berxley.notesapp.presentation.NotesViewModel
import kotlin.coroutines.coroutineContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteView(
    id: Int,
    viewModel: NotesViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    if (id != 0) {
        val editingNote = viewModel.getNoteById(id).collectAsState(
            initial = Note(
                id = 0,
                title = "",
                description = "",
                createdAt = System.currentTimeMillis()
            )
        )
        viewModel.onNewNoteTitle(editingNote.value.title)
        viewModel.onNewNoteDescription(editingNote.value.description)
        viewModel.onNewCreatedAt(editingNote.value.createdAt)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = if (id != 0) "Edit A  Note" else "Add a Note") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        Modifier.clickable {
                            navController.navigate(Screen.HomeScreen.route)
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        },

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .fillMaxSize()
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.noteTitle.value,
                onValueChange = { viewModel.onNewNoteTitle(it) },
                label = { Text(text = "Title") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.noteDescription.value,
                onValueChange = { viewModel.onNewNoteDescription(it) },
                label = { Text(text = "Notes") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )


            Spacer(modifier = Modifier.height(16.dp))

            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (viewModel.noteTitle.value.isNotEmpty() && viewModel.noteDescription.value.isNotEmpty()) {

                        val noteToAdd: Note = if (id != 0) {
                            Note(
                                id = id,
                                title = viewModel.noteTitle.value.trim(),
                                description = viewModel.noteDescription.value.trim(),
                                createdAt = viewModel.createdAt.value
                            )
                        } else {
                            Note(
                                title = viewModel.noteTitle.value,
                                description = viewModel.noteDescription.value,
                                createdAt = System.currentTimeMillis()
                            )
                        }
                        viewModel.upsertNote(noteToAdd)
                        navController.navigate(Screen.HomeScreen.route)
                    } else {
                        Toast.makeText(
                            context,
                            "Please fill all the fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                Text(text = "Save")
            }

        }
    }
}
