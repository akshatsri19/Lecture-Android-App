package com.example.project_g05.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.project_g05.R
import com.example.project_g05.model.Lessons

class LessonsAdapter(context: Context, lessons:List<Lessons>): ArrayAdapter<Lessons>(context,0,lessons) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.lessons_list, parent, false)

        val lessonNumber = view.findViewById<TextView>(R.id.lessonNumber)
        val lessonName = view.findViewById<TextView>(R.id.lessonName)
        val videoLength = view.findViewById<TextView>(R.id.videoLength)
        val status = view.findViewById<TextView>(R.id.status)
        val lesson = getItem(position)
        lesson?.let {
            lessonNumber.text = it.lessonNumber.toString()
            lessonName.text = it.lessonName
            videoLength.text = it.videoLength.toString()
            status.text= it.state.toString()
        }
        return view
    }
}