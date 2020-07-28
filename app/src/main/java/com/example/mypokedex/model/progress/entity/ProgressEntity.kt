package com.example.mypokedex.model.progress.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progresso")
data class ProgressEntity (
    @PrimaryKey(autoGenerate = false) val id: Int = 1,
    @ColumnInfo(name = "progresso") val progress: Int = 0
)