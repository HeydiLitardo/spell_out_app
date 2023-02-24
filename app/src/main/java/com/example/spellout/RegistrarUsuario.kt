package com.example.spellout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.spellout.databinding.ActivityRegistrarUsuarioBinding
import com.example.spellout.model.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrarUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarUsuarioBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        val db = FirebaseFirestore.getInstance()
        val refUsuario = db.collection("usuarios")

        setContentView(binding.root)

        Log.d("REGISTRAR", "Inicio registra usuario")

        mAuth = FirebaseAuth.getInstance()

        binding.btnRegistrar.setOnClickListener {
            Log.d("Registrar", "Probando el binding")
            val nombre = binding.inputNombre.text.toString().trim()
            val apellido = binding.inputApellido.text.toString().trim()
            val correo = binding.inputCorreo.text.toString().trim()
            val password = binding.inputPassword.text.toString().trim()

            mAuth.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@RegistrarUsuario,
                            "Usuario registrado correctamente",
                            Toast.LENGTH_LONG
                        ).show()

                        val user = task.result?.user
                        val uid = user?.uid

                        val usuario = User(
                            nombre,
                            apellido
                        )
                        refUsuario
                            .document(uid.toString())
                            .set(usuario)
                            .addOnSuccessListener { documentReference ->
                                Log.d("Registrar", "El usuario fue insertado con ID: ${uid.toString()}")
                            }
                            .addOnFailureListener { e ->
                                Log.w("Registrar", "Error al insertar documento", e)
                            }


                        finish()
                    } else {
                        Toast.makeText(
                            this@RegistrarUsuario,
                            "Error al registrar usuario: ${task.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }


    }
}