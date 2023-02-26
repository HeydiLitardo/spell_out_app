package com.example.spellout.model

import com.google.firebase.Timestamp
import java.time.LocalDate

class User (
    val nombre: String,
    val apellido: String,
    val correo: String,
    val institucion: String,
    val nacimiento: Timestamp,
) {
    constructor() : this("", "", "", "", Timestamp.now())
}