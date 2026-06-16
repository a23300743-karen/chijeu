package com.example.chijeu

// Mantenemos la clase "datos" con los mismos campos que usabas para los quesos
// para no romper la compatibilidad con tus otros archivos (Adapter, Actualizar, etc.)
data class datos(
    var nombre: String,  // Antes: Nombre del queso / Ahora: Modelo de guitarra
    var tipo: String,    // Antes: Tipo de queso / Ahora: Tipo de guitarra
    var origen: String,  // Antes: Origen del queso / Ahora: Marca de la guitarra
    var precio: Double,  // Se mantiene igual
    var imagen: Int      // Se mantiene igual (ID del recurso drawable)
)

object Guitarra {
    // Cambiamos solo el nombre de la lista de listaQuesos a listaGuitarras
    val listaGuitarras = mutableListOf<datos>()
}