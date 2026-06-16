package com.example.chijeu

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import Guitarra.listaGuitarras

class eliminar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eliminar)

        val etEliminar = findViewById<EditText>(R.id.etNombreEliminar)
        val btnEliminar = findViewById<Button>(R.id.btnConfirmarEliminar)

        btnEliminar.setOnClickListener {
            val nombre = etEliminar.text.toString()
            val eliminada = listaGuitarras.removeIf { it.nombre.equals(nombre, true) }

            if (eliminada) {
                Toast.makeText(this, "Guitarra eliminada del catálogo", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "No se encontró el modelo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}