package com.example.roomapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addMovies = findViewById<Button>(R.id.addMovies)
        val searchMovies = findViewById<Button>(R.id.searchMovies)
        val searchActors = findViewById<Button>(R.id.searchActors)
        val previewDatabase = findViewById<Button>(R.id.database)
        val searchTitle = findViewById<Button>(R.id.searchTitle)


        addMovies.setOnClickListener {
            val intent = Intent(this, AddMovies::class.java)
            startActivity(intent)
        }


        searchMovies.setOnClickListener {
            val intent = Intent(this, SearchMovies::class.java)
            startActivity(intent)
        }

        searchActors.setOnClickListener {
            val intent = Intent(this, SearchActors::class.java)
            startActivity(intent)
        }

        previewDatabase.setOnClickListener {
            val intent = Intent(this, PreviewDatabase::class.java)
            startActivity(intent)
        }
        searchTitle.setOnClickListener {
            val intent = Intent(this, SearchTitle::class.java)
            startActivity(intent)
        }


    }
}