package com.example.spellout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.SignInButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val btn_iniciar = findViewById<Button>(R.id.btn_iniciar)
        val btn_registrar = findViewById<Button>(R.id.btn_registrar)
        val input_correo = findViewById<TextInputEditText>(R.id.input_correo)
        val input_password = findViewById<TextInputEditText>(R.id.input_password)

        btn_iniciar.setOnClickListener {
            val correo = input_correo.text.toString().trim()
            val password = input_password.text.toString().trim()

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Ingresa el correo y contraseña", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(correo, password)
            }
        }

        btn_registrar.setOnClickListener {
            startActivity(Intent(this, RegistrarUsuario::class.java))
        }

    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // El inicio de sesión fue exitoso, puedes redirigir al usuario a la actividad principal
                    Toast.makeText(this, "El inicio de sesión fue exitoso", Toast.LENGTH_LONG).show()
                    Log.d("LOGIN", "El inicio de sesión fue exitoso")
                    startActivity(Intent(this, Inicio::class.java))
                    finish()
                } else {
                    // Si el inicio de sesión falló, muestra un mensaje al usuario.
                    Toast.makeText(this, "Inicio de sesión falló", Toast.LENGTH_LONG).show()
                    Log.d("LOGIN", "Inicio de sesión falló")
                }
            }
    }


}