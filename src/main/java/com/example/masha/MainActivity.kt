package com.example.masha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val create = findViewById<Button>(R.id.button)

        create.setOnClickListener {
         val intent = Intent(this@MainActivity, SignInActivity::class.java)
           startActivity(intent)
            finish()
        }
    }
}