package com.example.spellout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val btn_iniciar = findViewById<Button>(R.id.btn_iniciar)

        btn_iniciar.setOnClickListener {
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Inicio::class.java))
            finish()
        }
    }


}