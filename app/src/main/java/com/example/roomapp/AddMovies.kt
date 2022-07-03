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
import java.net.HttpURLConnection
import java.net.URL

class AddMovies : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movies)

        val tv = findViewById<TextView>(R.id.text)

        tv.setText("")

        // create the database
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase").build()
        val userDao = db.userDao()

        runBlocking {
            launch {
                val user = User(1, "The Shawshank Redemption", "1994","R","14 Oct 1994","142 min","Drama","Frank Darabont","Stephen King, Frank Darabont","Tim Robbins, Morgan Freeman, Bob Gunton","Two imprisoned men bond over a number of years, finding solace\n" +
                        "and eventual redemption through acts of common decency.")
                val user2 = User(2, "Batman: The Dark Knight Returns, Part 1", "2012","PG-13","25 Sep 2012","76 min","Animation, Action, Crime, Drama, Thriller","Jay Oliva","Bob Kane (character created by: Batman), Frank Miller (comic book), Klaus Janson (comic book), Bob\n" +
                        "Goodman","Peter Weller, Ariel Winter, David Selby, Wade Williams","Batman has not been seen for ten years. A new breed\n" +
                        "of criminal ravages Gotham City, forcing 55-year-old Bruce Wayne back\n" +
                        "into the cape and cowl. But, does he still have what it takes to fight\n" +
                        "crime in a new era?")
                val user3 = User(3, "The Lord of the Rings: The Return of the King", "2003","PG-13","17 Dec 2003","201 min","Action, Adventure, Drama","Peter Jackson","J.R.R. Tolkien, Fran Walsh, Philippa Boyens","Elijah Wood, Viggo Mortensen, Ian McKellen","Gandalf and Aragorn lead the World of Men against Sauron's\n" +
                        "army to draw his gaze from Frodo and Sam as they approach Mount Doom\n" +
                        "with the One Ring.")
                val user4 = User(4, "Inception", "2010","PG-13","16 Jul 2010","148 min","Action, Adventure, Sci-Fi","Christopher Nolan","Christopher Nolan","Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page","A thief who steals corporate secrets through the use of\n" +
                        "dream-sharing technology is given the inverse task of planting an idea\n" +
                        "into the mind of a C.E.O., but his tragic past may doom the project\n" +
                        "and his team to disaster.")
                val user5 = User(5, "The Matrix", "1999","R","31 Mar 1999","136 min","Action, Sci-Fi","Lana Wachowski, Lilly Wachowski","Lilly Wachowski, Lana Wachowski","Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss","When a beautiful stranger leads computer hacker Neo to a\n" +
                        "forbidding underworld, he discovers the shocking truth--the life he\n" +
                        "knows is the elaborate deception of an evil cyber-intelligence")

                userDao.insertUsers(user,user2,user3,user4,user5)
                val users: List<User> = userDao.getAll()


                for (u in users) {


                    tv.append("(${u.id}) \n Title: ${u.title} \n Year: ${u.year} \n Rated: ${u.rated} \n Released: ${u.released} \n Runtime: ${u.runTime} \n Genre: ${u.Genre} \n Director: ${u.director} \n Writer: ${u.writer} \n Actors: ${u.actors} \n plot: ${u.plot} \n\n\n ")

                }
            }
        }

    }
}