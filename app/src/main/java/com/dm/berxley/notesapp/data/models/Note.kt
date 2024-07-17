package com.dm.berxley.notesapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Long,
    val updatedAt: Long = System.currentTimeMillis()
)
