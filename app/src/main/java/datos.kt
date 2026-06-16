// Archivo: datos.kt
data class datos(
    var nombre: String,       // Modelo de guitarra
    var marca: String,        // Marca
    var empresa: String,      // Fabricante
    var precio: Double,       // Precio
    var pais: String,         // País de origen
    var telefono: String,     // Teléfono de tienda
    var tipoleche: String,    // Spinner 1 (Cuerpo)
    var proceso: String,      // Spinner 2 (Pastillas)
    var presentacion: String, // Spinner 3 (Puente)
    var sabores: String,      // Spinner 4 (Maderas)
    var peso: String          // Spinner 5 (Cuerdas)
)

object Guitarra {
    val listaGuitarras = mutableListOf<datos>()
}

