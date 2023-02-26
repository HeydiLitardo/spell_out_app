package com.example.spellout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spellout.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class Alumnos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumnos)

        val db = FirebaseFirestore.getInstance()
        val refUsers = db.collection("users")

        val usuarios = mutableListOf<User>()

        refUsers.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("Documento", "${document.id} => ${document.data}")
                    val user = document.toObject<User>()
                    usuarios.add(user)
                    Log.d("Documento", "${user.correo}")
                }
                showUserList(usuarios)
            }
            .addOnFailureListener { exception ->
                Log.w("Documento", exception)
            }
    }

    fun showUserList(userList: List<User>) {
        val recyclerView = findViewById<RecyclerView>(R.id.listausuarios)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = UsersAdapter(userList)
    }
}