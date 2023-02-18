package com.example.spellout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MainActivity : AppCompatActivity() {
    companion object {
        private const val RC_SIGN_IN = 423
    }

    private val authUser: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Firebase.initialize(this)
        googleLogin()

        val btnIniciar = findViewById<Button>(R.id.btn_iniciar_sesion)
        btnIniciar.setOnClickListener {
            startActivity(Intent(this, Inicio::class.java))
        }

        val btnRegistrar = findViewById<Button>(R.id.btn_registrar)
        btnRegistrar.setOnClickListener {
            startActivity(Intent(this, RegistrarUsuario::class.java))
        }

    }

    fun googleLogin() {
        val btnLogin = findViewById<SignInButton>(R.id.btn_login_google)
        val providers: ArrayList<AuthUI.IdpConfig> = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        btnLogin.setOnClickListener {
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build(),
                RC_SIGN_IN
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                var user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this, "Bienvenido ${user!!.displayName}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Inicio::class.java))
                finish()
            } else {
                Toast.makeText(this, "Ocurrio un herror ${response!!.error!!.errorCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }


}