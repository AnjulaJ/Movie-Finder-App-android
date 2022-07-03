package com.example.roomapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PreviewDatabase : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_database)

        val text = findViewById<TextView>(R.id.text)
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase").build()
        val userDao = db.userDao()

        runBlocking {
            launch {
                val users: List<User> = userDao.getAll()


                for (u in users) {


                    text.append("(${u.id}) \n Title: ${u.title} \n Year: ${u.year} \n Rated: ${u.rated} \n Released: ${u.released} \n Runtime: ${u.runTime} \n Genre: ${u.Genre} \n Director: ${u.director} \n Writer: ${u.writer} \n Actors: ${u.actors} \n plot: ${u.plot} \n\n\n ")

                }
            }
        }

    }
}