package com.example.project_g05.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
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

        lessonListView.setOnItemClickListener { adapterView,view,i,l->
            switchSeqProgression.setOnClickListener{
                navigation = !switchSeqProgression.isChecked
            }
            if(navigation){
                val intent = Intent(this,LessonsDetails::class.java)
                intent.putExtra("EXTRA_LESSON_POS",i)
                startActivity(intent)
            }
            else{
                Log.d("My-App","Do nothing")
            }
        }
    }
}