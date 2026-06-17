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
import guitarra.listaGuitarras

class actualizar : AppCompatActivity() {
    lateinit var Modelo: EditText
    lateinit var Marca: EditText
    lateinit var Fabricante: EditText
    lateinit var Precio: EditText
    lateinit var Pais: EditText
    lateinit var Telefono: EditText
    lateinit var TipoCuerpo: Spinner
    lateinit var TipoPastillas: Spinner
    lateinit var Puente: Spinner
    lateinit var Madera: Spinner
    lateinit var Cuerdas: Spinner
    lateinit var BtnAnterior: Button
    lateinit var BtnActualizar: Button
    lateinit var BtnSiguiente: Button

    var posicionActual = 0

    val tipocuerpo = arrayOf("Sólido", "Hueco", "Semi-hueco")
    val tipopastillas = arrayOf("Single Coil", "Humbucker", "P90", "Activas")
    val puente = arrayOf("Fijo", "Trémolo", "Floyd Rose")
    val madera = arrayOf("Caoba", "Arce", "Palosanto", "Aliso", "Fresno", "Ebano", "Nogal")
    val cuerdas = arrayOf("6 cuerdas", "7 cuerdas", "8 cuerdas", "12 cuerdas", "4 cuerdas", "5 cuerdas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actualizar)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        Modelo = findViewById(R.id.etNombreG)
        Marca = findViewById(R.id.etMarcaG)
        Fabricante = findViewById(R.id.etEmpresaG)
        Precio = findViewById(R.id.etPrecioG)
        Pais = findViewById(R.id.etPaisG)
        Telefono = findViewById(R.id.etTelefonoG)
        TipoCuerpo = findViewById(R.id.spTipoCuerpo)
        TipoPastillas = findViewById(R.id.spTipoPastillas)
        Puente = findViewById(R.id.spPuente)
        Madera = findViewById(R.id.spMadera)
        Cuerdas = findViewById(R.id.spCuerdas)
        BtnAnterior = findViewById(R.id.btnQuesoAnt)
        BtnActualizar = findViewById(R.id.btnActualizarQueso)
        BtnSiguiente = findViewById(R.id.btnQuesoSig)

        val tipoCuerpoAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.textoSpinner, tipocuerpo)
        tipoCuerpoAdapter.setDropDownViewResource(R.layout.item_spinner)
        TipoCuerpo.adapter = tipoCuerpoAdapter

        val tipoPastillasAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.textoSpinner, tipopastillas)
        tipoPastillasAdapter.setDropDownViewResource(R.layout.item_spinner)
        TipoPastillas.adapter = tipoPastillasAdapter

        val puenteAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.textoSpinner, puente)
        puenteAdapter.setDropDownViewResource(R.layout.item_spinner)
        Puente.adapter = puenteAdapter

        val maderaAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.textoSpinner, madera)
        maderaAdapter.setDropDownViewResource(R.layout.item_spinner)
        Madera.adapter = maderaAdapter

        val cuerdasAdapter = ArrayAdapter(this, R.layout.item_spinner, R.id.textoSpinner, cuerdas)
        cuerdasAdapter.setDropDownViewResource(R.layout.item_spinner)
        Cuerdas.adapter = cuerdasAdapter

        //Carga inicialmente la primera guitarra
        cargarGuitarra(posicionActual)

        BtnActualizar.setOnClickListener {
            actualizarGuitarra()
        }

        BtnAnterior.setOnClickListener {
            posicionActual = if (posicionActual == 0) {
                //resta total - 1 para ir al ultimo
                listaGuitarras.size - 1
            } else {
                posicionActual - 1
            }
            cargarGuitarra(posicionActual)
        }

        BtnSiguiente.setOnClickListener {
            posicionActual = if (posicionActual == listaGuitarras.size - 1) {
                0
            } else {
                posicionActual + 1
            }
            cargarGuitarra(posicionActual)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun cargarGuitarra(pos: Int) {
        val item = listaGuitarras[pos]
        Modelo.setText(item.modelo)
        Marca.setText(item.marca)
        Fabricante.setText(item.fabricante)
        Precio.setText(item.precio.toString())
        Pais.setText(item.pais)
        Telefono.setText(item.telefono)
        TipoCuerpo.setSelection(tipocuerpo.indexOf(item.tipocuerpo))
        TipoPastillas.setSelection(tipopastillas.indexOf(item.tipopastillas))
        Puente.setSelection(puente.indexOf(item.puente))
        Madera.setSelection(madera.indexOf(item.madera))
        Cuerdas.setSelection(cuerdas.indexOf(item.cuerdas))
    }

    private fun actualizarGuitarra() {
        val telefono = Telefono.text.toString()

        if (Modelo.text.isEmpty() || Marca.text.isEmpty() || Fabricante.text.isEmpty() ||
            Precio.text.isEmpty() || Pais.text.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        if (telefono.length != 10) {
            Toast.makeText(this, "Número telefónico inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val guitarraEditada = datos(
            modelo = Modelo.text.toString(),
            marca = Marca.text.toString(),
            fabricante = Fabricante.text.toString(),
            precio = Precio.text.toString().toDouble(),
            pais = Pais.text.toString(),
            telefono = telefono,
            tipocuerpo = TipoCuerpo.selectedItem.toString(),
            tipopastillas = TipoPastillas.selectedItem.toString(),
            puente = Puente.selectedItem.toString(),
            madera = Madera.selectedItem.toString(),
            cuerdas = Cuerdas.selectedItem.toString()
        )
        listaGuitarras[posicionActual] = guitarraEditada
        Toast.makeText(this, "Guitarra actualizada correctamente", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.opc1){
            val cambio= Intent(this, MainActivity::class.java)
            startActivity(cambio)
        }
        if (item.itemId == R.id.opc2) {
            if (listaGuitarras.isEmpty()) {
                Toast.makeText(this, "No hay guitarras registradas", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, ver::class.java))
            }
        }
        if (item.itemId == R.id.opc3) {
            Toast.makeText(this,"Ya estas aqui", Toast.LENGTH_SHORT).show()
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
            } else if (listaGuitarras.isEmpty()) {
                Toast.makeText(this, "No hay ninguna guitarra registrada", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, eliminar::class.java))
            }
        }

        if (item.itemId == R.id.iconCerrarSesion) {
            val prefs = getSharedPreferences("sesion", MODE_PRIVATE)
            prefs.edit().clear().apply()
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
        return true
    }
}
