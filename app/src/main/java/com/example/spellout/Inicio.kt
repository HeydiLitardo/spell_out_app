package com.example.spellout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.bottomappbar.BottomAppBar

class Inicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        title = "Opciones del Profesor"

        val alumnos = findViewById<ImageView>(R.id.alumnos)
        val crearActividad = findViewById<ImageView>(R.id.crear_actividad)
        val notas = findViewById<ImageView>(R.id.notas)
        val asignarActividad = findViewById<ImageView>(R.id.asignar_actividad)
        val btnRegistrar = findViewById<Button>(R.id.btn_registrar_alumnos)
        val btnAtras = findViewById<Button>(R.id.btn_atras)

        alumnos.setOnClickListener {
            val intent = Intent(this, Alumnos::class.java)
            startActivity(intent)
        }

        crearActividad.setOnClickListener {

        }

        notas.setOnClickListener {

        }

        asignarActividad.setOnClickListener {

        }

        btnRegistrar.setOnClickListener {

        }

        btnAtras.setOnClickListener {

        }
    }

}