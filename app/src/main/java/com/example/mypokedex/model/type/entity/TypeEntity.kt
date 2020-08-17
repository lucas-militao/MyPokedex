package com.example.mypokedex.model.type.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type")
data class TypeEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "nome") val name: String
)