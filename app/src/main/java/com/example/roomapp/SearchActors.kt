package com.example.roomapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_search_movies.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

class SearchActors : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_actors)

        var searchActors = findViewById<Button>(R.id.searchActors)
        var input = findViewById<EditText>(R.id.actorInput)
        var text = findViewById<TextView>(R.id.display)
        text.text=""


        searchActors.setOnClickListener {
            val db = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase").build()
            val userDao = db.userDao()

            runBlocking {
                launch {
                    val users: List<User> = userDao.getAll()


                    for (u in users) {
                        if (u.actors.contains(input.text)){
                            text.setText("Title: ${u.title} \n Year: ${u.year} \n Rated: ${u.rated} \n Released: ${u.released} \n Runtime: ${u.runTime} \n Genre: ${u.Genre} \n Director: ${u.director} \n Writer: ${u.writer} \n Actors: ${u.actors} \n plot: ${u.plot} \n\n\n ")

                        }

                    }
                }
            }
        }
    }
}