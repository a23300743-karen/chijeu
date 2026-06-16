package com.example.chijeu

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import Guitarra.listaGuitarras

class actualizar : AppCompatActivity() {
    lateinit var etBuscar: EditText
    lateinit var btnBuscar: Button
    lateinit var etPrecio: EditText
    lateinit var etTelefono: EditText
    lateinit var spMaderas: Spinner
    lateinit var btnActualizar: Button

    var posicionEncontrada: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar)

        etBuscar = findViewById(R.id.etNombreBuscar)
        btnBuscar = findViewById(R.id.btnBuscar)
        etPrecio = findViewById(R.id.etNuevoPrecio)
        etTelefono = findViewById(R.id.etNuevoTelefono)
        spMaderas = findViewById(R.id.spNuevosSabores)
        btnActualizar = findViewById(R.id.btnActualizar)

        // Adapter para el spinner de maderas (ejemplo de actualización)
        val opcionesMaderas = arrayOf("Caoba", "Arce", "Palosanto", "Aliso")
        spMaderas.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opcionesMaderas)

        btnBuscar.setOnClickListener {
            val nombreBusqueda = etBuscar.text.toString()
            posicionEncontrada = listaGuitarras.indexOfFirst { it.nombre.equals(nombreBusqueda, true) }

            if (posicionEncontrada != -1) {
                val g = listaGuitarras[posicionEncontrada]
                etPrecio.setText(g.precio.toString())
                etTelefono.setText(g.telefono)
                Toast.makeText(this, "Guitarra encontrada: ${g.marca}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No se encontró esa guitarra", Toast.LENGTH_SHORT).show()
            }
        }

        btnActualizar.setOnClickListener {
            if (posicionEncontrada != -1) {
                listaGuitarras[posicionEncontrada].precio = etPrecio.text.toString().toDouble()
                listaGuitarras[posicionEncontrada].telefono = etTelefono.text.toString()
                listaGuitarras[posicionEncontrada].sabores = spMaderas.selectedItem.toString()

                Toast.makeText(this, "Datos actualizados", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}