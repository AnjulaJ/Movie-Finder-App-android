package com.example.roomapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int,
    val title: String?,
    val year: String,
    val rated: String,
    val released: String,
    val runTime: String,
    val Genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String

)