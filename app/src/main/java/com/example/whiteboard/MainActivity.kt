package com.example.whiteboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

var isIntroEnded = false

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val introPageIntent = Intent(this, IntroductionPageActivity::class.java)

        if (!isIntroEnded) {
            isIntroEnded = true

            startActivity(introPageIntent)
        }

        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.MainPageButton)

        button.setOnClickListener {
            startActivity(introPageIntent)
        }
    }
}

