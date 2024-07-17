package com.dm.berxley.notesapp.views

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object AddNoteScreen: Screen("add_note")
}