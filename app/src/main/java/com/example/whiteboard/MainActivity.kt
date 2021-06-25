package com.example.whiteboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var isIntroEnded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, IntroductionPageActivity::class.java)

        if (!isIntroEnded) {
            startActivity(intent)
        }

        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.MainPageButton)

        button.setOnClickListener {
            startActivity(intent)
        }
    }
}

