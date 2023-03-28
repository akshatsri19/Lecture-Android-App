package com.example.project_g05.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import com.example.project_g05.R
import com.example.project_g05.model.Lessons

class LessonsAdapter(context: Context, lessons:List<Lessons>): ArrayAdapter<Lessons>(context,0,lessons) {

    lateinit var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.lessons_list, parent, false)

        val lessonNumber = view.findViewById<TextView>(R.id.lessonNumber)
        val lessonName = view.findViewById<TextView>(R.id.lessonName)
        val videoLength = view.findViewById<TextView>(R.id.videoLength)
        val completedIcon = view.findViewById<ImageView>(R.id.checkmark)
        val LessonLayout = LayoutInflater.from(context).inflate(R.layout.activity_lessons, null)
        val switchSeqProgression = LessonLayout.findViewById<SwitchCompat>(R.id.switchSeqProgression)
        val lesson = getItem(position)
        lesson?.let {
            lessonNumber.text = it.lessonNumber.toString()
            lessonName.text = it.lessonName
            videoLength.text = it.videoLength.toString()

            // Check if previous lesson is completed
            val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            val isPrevLessonCompleted = if (position > 0) {
                sharedPreferences.getBoolean("lesson_${position}_completed", false)
            } else {
                true // first lesson is always considered completed
            }

            // Check if this lesson is completed
            val isCompleted = sharedPreferences.getBoolean("lesson_${it.lessonNumber}_completed", false)
            if (isCompleted) {
                completedIcon.visibility = View.VISIBLE
            } else {
                completedIcon.visibility = View.GONE
            }

            // Disable the view for this lesson if the previous lesson is not completed
                if (switchSeqProgression.isChecked && !isPrevLessonCompleted) {
                    view.isEnabled = false
                    view.alpha = 0.5f
                } else {
                    view.isEnabled = true
                    view.alpha = 1.0f
                }
            }
        return view
    }

}

