package com.example.roomapp

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_search_movies.*
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

class SearchMovies : AppCompatActivity(){

    lateinit var useInput:EditText

    var moviesAll = mutableListOf<String>("","","","","","","","","","")
    var idNum:Int = 7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movies)

        var text = findViewById<TextView>(R.id.display)

        text.setText("")
        var retrieve = findViewById<TextView>(R.id.button1)
        var save = findViewById<TextView>(R.id.button2)

        retrieve.setOnClickListener {
            retrieve(userInput,text)

        }

        save.setOnClickListener {
            saveMovie()
        }
    }

    private fun retrieve(userInput:EditText,text: TextView){
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

                    parseJSON(stb,text)

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
    private fun parseJSON(stb: java.lang.StringBuilder,text: TextView) {

// this contains the full JSON returned by the Web Service
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

        moviesAll.add(0,title)
        moviesAll.add(1,year)
        moviesAll.add(2,rated)
        moviesAll.add(3,released)
        moviesAll.add(4,runtime)
        moviesAll.add(5,genre)
        moviesAll.add(6,director)
        moviesAll.add(7,writer)
        moviesAll.add(8,actors)
        moviesAll.add(9,plot)

        //text.append("Title: $title \n Year: $year \n Rated: $rated \n Released: $released \n Runtime: $runtime \n Genre: $genre \n Director \n Writer: $writer \n Actors: $actors \n\n plot: $plot")

    }

    private fun saveMovie(){

        // create the database
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase").build()
        val userDao = db.userDao()


        runBlocking {
            launch {
                val user6 = User(idNum, moviesAll[0], moviesAll[1],moviesAll[2],moviesAll[3],moviesAll[4],moviesAll[5],moviesAll[6],moviesAll[7],moviesAll[8],moviesAll[9])

                userDao.insertUsers(user6)
                val users: List<User> = userDao.getAll()
                idNum+=1
                for (u in users) {

                   // text.append("(${u.id}) \n Title: ${u.title} \n Year: ${u.year} \n Rated: ${u.rated} \n Released: ${u.released} \n Runtime: ${u.runTime} \n Genre: ${u.Genre} \n Director: ${u.director} \n Writer: ${u.writer} \n Actors: ${u.actors} \n plot: ${u.plot} \n\n\n ")
                    //text.append(moviesAll[0])

                }
            }
        }
    }
}