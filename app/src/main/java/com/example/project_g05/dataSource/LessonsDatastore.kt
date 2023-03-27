package com.example.project_g05.dataSource

import com.example.project_g05.model.Lessons

class LessonsDatastore {

    var lessonsList:MutableList<Lessons> = mutableListOf(
        Lessons(1,"Web Development",90,true,"Dive into a challenging " +
                "curriculum including JavaScript, HTML, Bootstrap, jQuery & more. Flexible online course allows you to study web " +
                "development in the comfort of your home. No application fee. Enrolling now. Hands-on training. Flexible schedule. " +
                "Job planning support.",""),
        Lessons(2,"Fullstack Development",45,false,"Dive into a challenging " +
                "curriculum including JavaScript, HTML, Bootstrap, jQuery & more. Flexible online course allows you to study web " +
                "development in the comfort of your home. No application fee. Enrolling now. Hands-on training. Flexible schedule. " +
                "Job planning support.",""),
        Lessons(3,"HTML & CSS",60,false,"Dive into a challenging " +
                "curriculum including JavaScript, HTML, Bootstrap, jQuery & more. Flexible online course allows you to study web " +
                "development in the comfort of your home. No application fee. Enrolling now. Hands-on training. Flexible schedule. " +
                "Job planning support.",""),
        Lessons(4,"JAVA",75,false,"Dive into a challenging " +
                "curriculum including JavaScript, HTML, Bootstrap, jQuery & more. Flexible online course allows you to study web " +
                "development in the comfort of your home. No application fee. Enrolling now. Hands-on training. Flexible schedule. " +
                "Job planning support.",""),
        Lessons(5,"Javascript",60,false,"Dive into a challenging " +
                "curriculum including JavaScript, HTML, Bootstrap, jQuery & more. Flexible online course allows you to study web " +
                "development in the comfort of your home. No application fee. Enrolling now. Hands-on training. Flexible schedule. " +
                "Job planning support.",""),
        Lessons(6,"Angular development",60,false,"Dive into a challenging " +
                "curriculum including JavaScript, HTML, Bootstrap, jQuery & more. Flexible online course allows you to study web " +
                "development in the comfort of your home. No application fee. Enrolling now. Hands-on training. Flexible schedule. " +
                "Job planning support.","")
    )

    companion object {
        @Volatile
        private lateinit var instance: LessonsDatastore

        fun getInstance(): LessonsDatastore {
            synchronized(this) {
                if (!Companion::instance.isInitialized) {
                    instance = LessonsDatastore()
                }
                return instance
            }
        }
    }

}