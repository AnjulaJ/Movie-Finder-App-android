package com.example.roomapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class SearchTitle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_title)

        val search = findViewById<Button>(R.id.search)
        val text = findViewById<TextView>(R.id.display)
        val input = findViewById<EditText>(R.id.userInput)

        text.setText("")
        search.setOnClickListener {
            retrieve(input,text)
        }

    }

    private fun retrieve(userInput: EditText, text: TextView){
        var stb = StringBuilder()

        val url_string = "https://www.omdbapi.com/?t=${userInput.getText()}&apikey=d7db2f49";
        val url = URL(url_string)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        runBlocking {
            launch {
// run the code of the coroutine in a new thread
                withContext(Dispatchers.IO) {
                    var bf = BufferedReader(InputStreamReader(con.inputStream))
                    var line: String? = bf.readLine()
                    while (line != null) {
                        stb.append(line + "\n")
                        line = bf.readLine()
                    }

                }
                val json = JSONObject(stb.toString())

                val title = json.getString("Title")
                val year = json.getString("Year")
                val rated = json.getString("Rated")
                val released = json.getString("Released")
                val runtime = json.getString("Runtime")
                val genre = json.getString("Genre")
                val director = json.getString("Director")
                val writer = json.getString("Writer")
                val actors = json.getString("Actors")
                val plot = json.getString("Plot")
                text.text="Title: $title \n Year: $year \n Rated: $rated \n Released: $released \n Runtime: $runtime \n Genre: $genre \n Director: $director \n Writer: $writer \n Actors: $actors \n\n Plot: $plot"

            }
        }
    }
}