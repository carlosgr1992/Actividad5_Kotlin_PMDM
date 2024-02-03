package com.carlos.practica5_pmdm_cgr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val userInputText = intent.getStringExtra("userInput")
        val textViewUserInput: TextView = findViewById(R.id.textViewUserInput)
        textViewUserInput.text = userInputText
    }
}