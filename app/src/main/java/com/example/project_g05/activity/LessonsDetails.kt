package com.example.project_g05.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.project_g05.R
import com.example.project_g05.dataSource.LessonsDatastore

class LessonsDetails : AppCompatActivity() {

    private var pos = -1
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons_details)

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)


        if(intent != null){
            this.pos = intent.getIntExtra("EXTRA_LESSON_POS",-1)
            val dataSource = LessonsDatastore.getInstance()
            val currLesson = dataSource.lessonsList[this.pos]

            val etLessonNumber = findViewById<TextView>(R.id.lessonNumber)
            val etLessonName = findViewById<TextView>(R.id.lessonName)
            val etVideoLength = findViewById<TextView>(R.id.videoLength)
            val etLessonDescription = findViewById<TextView>(R.id.lessonDescription)

            etLessonNumber.text = currLesson.lessonNumber.toString()
            etLessonName.text = currLesson.lessonName
            etVideoLength.text = currLesson.videoLength.toString()
            etLessonDescription.text = currLesson.lessonDescription

            val watchLesson = findViewById<Button>(R.id.watchLesson)
            watchLesson.setOnClickListener{
                val lessonAddress = currLesson.lessonLink
                val urlToOpenAsUri = Uri.parse(lessonAddress)
                val intent = Intent(Intent.ACTION_VIEW,urlToOpenAsUri)
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                else {
                    val toast = Toast.makeText(this, "Cannot open website", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            val btnComplete = findViewById<Button>(R.id.btnComplete)
            val isCompleted = sharedPreferences.getBoolean("lesson_${currLesson.lessonNumber}_completed", false)
            if (isCompleted) {
                btnComplete.text = "Completed"
            }

            btnComplete.setOnClickListener {
                val editor = sharedPreferences.edit()
                editor.putBoolean("lesson_${currLesson.lessonNumber}_completed", true)
                // TODO: Add code to update the UI to reflect completion status
                if(btnComplete.text == "Completed"){
                    Toast.makeText(this,"You already completed this lesson",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                else{
                    var noOfCourseDone = sharedPreferences.getInt("noOfCourseDone",0)
                    noOfCourseDone++
                    editor.putInt("noOfCourseDone" , noOfCourseDone)
                }
                editor.apply()
                btnComplete.text = "Completed"
            }

            val etNotes = findViewById<EditText>(R.id.etNotes)
            val isNotes = sharedPreferences.getString("notes_${currLesson.lessonNumber}", "")
            if (isNotes != "") {
                etNotes.setText(isNotes)
            }
            val btnSaveNotes = findViewById<Button>(R.id.btnSaveNotes)
            btnSaveNotes.setOnClickListener{
                val notes = etNotes.text.toString()
                sharedPreferences.edit().putString("notes_${currLesson.lessonNumber}", notes).apply()
                Toast.makeText(this, "Notes Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}