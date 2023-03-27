package com.example.project_g05.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project_g05.R


class NameActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var nameEditText: EditText
    lateinit var spName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        nameEditText = findViewById(R.id.ed_Name)

        val saveButton = findViewById<Button>(R.id.btn_Continue)
        saveButton.setOnClickListener {
            spName = nameEditText.text.toString().trim()

            if (spName.isNotEmpty()) {
                // Save the user name in SharedPreferences
                sharedPreferences.edit().putString("name", spName).apply()

                // Go to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Show an error message if the user did not enter a name
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}