package com.example.chijeu

data class datos(
    var nombre: String,       // Modelo
    var marca: String,        // Marca
    var empresa: String,      // Fabricante
    var precio: Double,
    var pais: String,         // Origen
    var telefono: String,
    var tipoleche: String,    // Cuerpo
    var proceso: String,      // Pastillas
    var presentacion: String, // Puente
    var sabores: String,      // Maderas
    var peso: String          // Cuerdas
)

object Guitarra {
    val listaGuitarras = mutableListOf<datos>()
}
