package com.dm.berxley.notesapp.utils

import android.app.Application

class NotesApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}