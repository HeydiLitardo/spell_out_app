package com.example.spellout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRegistrar = findViewById<Button>(R.id.btn_iniciar)

        btnRegistrar.setOnClickListener {
            var itent = Intent(this, Inicio::class.java)
            startActivity(itent)
        }
    }

}