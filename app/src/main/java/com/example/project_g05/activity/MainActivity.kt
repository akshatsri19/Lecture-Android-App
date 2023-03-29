package com.example.project_g05.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.project_g05.R
import com.example.project_g05.dataSource.LessonsDatastore

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the SharedPreferences object
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        // Check if the user name is already stored in SharedPreferences
        var spName = sharedPreferences.getString("name", null)
        if (spName == null) {
            // Show the EnterNameActivity if the user name is not stored
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Display the name in the UI
            val nameTextView = findViewById<TextView>(R.id.tv_welcome)
            nameTextView.text = "Welcome Back, $spName"
        }


        val lessonsButton = findViewById<Button>(R.id.btn_Lessons)
        lessonsButton.setOnClickListener{
            val intent = Intent(this, LessonsActivity::class.java)
            startActivity(intent)
        }

        val clearButton = findViewById<Button>(R.id.btn_Clear)
        clearButton.setOnClickListener {
            // Clear the stored name from SharedPreferences
            val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            Toast.makeText(this, "Name cleared", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val noOfCourseDone = sharedPreferences.getInt("noOfCourseDone",0)
        val lessonList = LessonsDatastore.getInstance().lessonsList

        val percentageCompleted = findViewById<TextView>(R.id.tv_completion)
        val lessonsCompleted = findViewById<TextView>(R.id.tv_complete)
        val lessonsRemaining = findViewById<TextView>(R.id.tv_remaining)

        val totalLesson = lessonList.size
        val percentage = String.format("%.2f%%", (noOfCourseDone*100).toDouble()/totalLesson)
        percentageCompleted.text = "You've completed$percentage% of the course!"
        lessonsCompleted.text = "Lessons Completed: $noOfCourseDone"
        lessonsRemaining.text = "Lessons Remaining: ${totalLesson-noOfCourseDone}"
    }
}