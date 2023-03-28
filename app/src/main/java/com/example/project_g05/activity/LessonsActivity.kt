package com.example.project_g05.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import com.example.project_g05.R
import com.example.project_g05.adapter.LessonsAdapter
import com.example.project_g05.dataSource.LessonsDatastore


class LessonsActivity : AppCompatActivity() {

    private lateinit var lessonsAdapter:LessonsAdapter
    private var navigation =  true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)


    }

    override fun onStart() {
        super.onStart()

        val lessonList = LessonsDatastore.getInstance().lessonsList

        lessonsAdapter = LessonsAdapter(this,lessonList)

        val lessonListView = findViewById<ListView>(R.id.lessonListView)
        lessonListView.adapter = lessonsAdapter

        val switchSeqProgression = findViewById<SwitchCompat>(R.id.switchSeqProgression)
        switchSeqProgression.setOnCheckedChangeListener { _, isChecked ->
            lessonsAdapter.notifyDataSetChanged()
            lessonListView.invalidateViews()
        }

        lessonListView.setOnItemClickListener { adapterView, view, i, l ->
            val prevLessonCompleted = if (i > 0) {
                val prevLesson = lessonList[i - 1]
                val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
                sharedPreferences.getBoolean("lesson_${prevLesson.lessonNumber}_completed", false)
            } else {
                true // first lesson is always considered completed
            }

            if (switchSeqProgression.isChecked && !prevLessonCompleted) {
                // force sequential progression is on and previous lesson is not completed
                Toast.makeText(this, "Complete the previous lesson first", Toast.LENGTH_SHORT).show()
            } else {
                // allow navigation to selected lesson
                val intent = Intent(this, LessonsDetails::class.java)
                intent.putExtra("EXTRA_LESSON_POS", i)
                startActivity(intent)
            }
        }

    }
}