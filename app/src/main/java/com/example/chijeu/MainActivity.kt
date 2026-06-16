package com.example.chijeu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import datos
import Guitarra.listaGuitarras // Cambiado de listaQuesos a listaGuitarras
import Guitarra

class MainActivity : AppCompatActivity() {

    // 6 EditTexts (Misma cantidad que antes)
    lateinit var Modelo: EditText
    lateinit var Marca: EditText
    lateinit var Fabricante: EditText
    lateinit var Precio: EditText
    lateinit var PaisOrigen: EditText
    lateinit var TelefonoTienda: EditText

    // 5 Spinners (Misma cantidad que antes)
    lateinit var TipoCuerpo: Spinner
    lateinit var TipoPastillas: Spinner
    lateinit var Puente: Spinner
    lateinit var Maderas: Spinner
    lateinit var NumeroCuerdas: Spinner

    lateinit var Guardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Inicialización de los 6 EditTexts usando los IDs existentes en tu XML
        Modelo = findViewById<EditText>(R.id.etNombre)
        Marca = findViewById<EditText>(R.id.etMarca)
        Fabricante = findViewById<EditText>(R.id.etEmpresa)
        Precio = findViewById<EditText>(R.id.etPrecio)
        PaisOrigen = findViewById<EditText>(R.id.etPais)
        TelefonoTienda = findViewById<EditText>(R.id.etTelefono)

        // Inicialización de los 5 Spinners usando los IDs existentes en tu XML
        TipoCuerpo = findViewById<Spinner>(R.id.spTipoLeche)
        TipoPastillas = findViewById<Spinner>(R.id.spProceso)
        Puente = findViewById<Spinner>(R.id.spPresentacion)
        Maderas = findViewById<Spinner>(R.id.spSabores)
        NumeroCuerdas = findViewById<Spinner>(R.id.spPeso)

        Guardar = findViewById<Button>(R.id.btnGuardar)

        // Arreglos adaptados (Misma cantidad de opciones aproximada)
        val opcionesCuerpo = arrayOf("Sólido", "Hueco", "Semi-hueco")
        val opcionesPastillas = arrayOf("Single Coil", "Humbucker", "P90", "Activas")
        val opcionesPuente = arrayOf("Fijo", "Trémolo", "Floyd Rose")
        val opcionesMaderas = arrayOf("Caoba", "Arce", "Palosanto", "Aliso", "Fresno", "Ebano", "Nogal")
        val opcionesCuerdas = arrayOf("6 cuerdas", "7 cuerdas", "8 cuerdas", "12 cuerdas", "4 cuerdas", "5 cuerdas")

        Guardar.setOnClickListener {
            guardar()
        }

        // Adaptadores usando los mismos IDs de Spinner y Layouts
        val cuerpoAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.textoSpinner, opcionesCuerpo)
        cuerpoAdapter.setDropDownViewResource(R.layout.item_spinner)
        TipoCuerpo.adapter = cuerpoAdapter

        val pastillasAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.textoSpinner, opcionesPastillas)
        pastillasAdapter.setDropDownViewResource(R.layout.item_spinner)
        TipoPastillas.adapter = pastillasAdapter

        val puenteAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.textoSpinner, opcionesPuente)
        puenteAdapter.setDropDownViewResource(R.layout.item_spinner)
        Puente.adapter = puenteAdapter

        val maderasAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.textoSpinner, opcionesMaderas)
        maderasAdapter.setDropDownViewResource(R.layout.item_spinner)
        Maderas.adapter = maderasAdapter

        val cuerdasAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.textoSpinner, opcionesCuerdas)
        cuerdasAdapter.setDropDownViewResource(R.layout.item_spinner)
        NumeroCuerdas.adapter = cuerdasAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun guardar() {
        val telefono = TelefonoTienda.text.toString()

        if (Modelo.text.isEmpty() || Marca.text.isEmpty() || Fabricante.text.isEmpty() ||
            Precio.text.isEmpty() || PaisOrigen.text.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos de la guitarra", Toast.LENGTH_SHORT).show()
            return
        }
        if (telefono.length != 10) {
            Toast.makeText(this, "Número telefónico inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val info = datos(
            nombre = Modelo.text.toString(),
            marca = Marca.text.toString(),
            empresa = Fabricante.text.toString(),
            precio = Precio.text.toString().toDouble(),
            pais = PaisOrigen.text.toString(),
            telefono = telefono,
            tipoleche = TipoCuerpo.selectedItem.toString(),
            proceso = TipoPastillas.selectedItem.toString(),
            presentacion = Puente.selectedItem.toString(),
            sabores = Maderas.selectedItem.toString(),
            peso = NumeroCuerdas.selectedItem.toString(),
        )
        listaGuitarras.add(info)
        Toast.makeText(this, "La guitarra se ha guardado correctamente", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val prefs = getSharedPreferences("sesion", MODE_PRIVATE)
        val rol = prefs.getString("rol", "")
        if (rol == "Trabajador") {
            menu?.findItem(R.id.opc1)?.isVisible = false
            menu?.findItem(R.id.opc3)?.isVisible = false
            menu?.findItem(R.id.iconEliminar)?.isVisible = false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.opc1){
            Toast.makeText(this, "Ya estas aquí", Toast.LENGTH_SHORT).show()
        }
        if (item.itemId == R.id.opc2) {
            if (Guitarra.listaGuitarras.isEmpty()) {
                Toast.makeText(this, "No hay guitarras registradas", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, ver::class.java))
            }
        }
        if (item.itemId == R.id.opc3) {
            if (Guitarra.listaGuitarras.isEmpty()) {
                Toast.makeText(this, "No hay guitarras para actualizar", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, actualizar::class.java))
            }
        }
        if (item.itemId == R.id.opc4) {
            startActivity(Intent(this, creador::class.java))
        }
        if (item.itemId == R.id.opc5) {
            startActivity(Intent(this, contacto::class.java))
        }

        if (item.itemId == R.id.iconEliminar) {
            val prefs = getSharedPreferences("sesion", MODE_PRIVATE)
            val rol = prefs.getString("rol", "")
            if (rol == "trabajador") {
                Toast.makeText(this, "No tienes permisos para esta acción", Toast.LENGTH_SHORT).show()
            } else if (Guitarra.listaGuitarras.isEmpty()) {
                Toast.makeText(this, "No hay ninguna guitarra para eliminar", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, eliminar::class.java))
            }
        }
        if (item.itemId == R.id.iconCerrarSesion) {
            val prefs = getSharedPreferences("sesion", MODE_PRIVATE)
            prefs.edit().clear().apply()
            startActivity(Intent(this, login::class.java))
            finish()
        }
        return true
    }
}