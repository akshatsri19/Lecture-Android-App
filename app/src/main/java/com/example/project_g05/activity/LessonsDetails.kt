package com.example.project_g05.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.project_g05.R
import com.example.project_g05.dataSource.LessonsDatastore

class LessonsDetails : AppCompatActivity() {

    private var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons_details)

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

        }
    }
}