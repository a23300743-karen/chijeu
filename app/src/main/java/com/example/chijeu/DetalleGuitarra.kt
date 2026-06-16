package com.example.chijeu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast

class DetalleGuitarra : AppCompatActivity() {
    private val REQUEST_CALL = 1
    lateinit var nomb: TextView
    lateinit var marc: TextView
    lateinit var pai: TextView
    lateinit var emp: TextView
    lateinit var prec: TextView
    lateinit var cuerpo: TextView
    lateinit var maderas: TextView
    lateinit var pastillas: TextView
    lateinit var puente: TextView
    lateinit var cuerdas: TextView
    lateinit var tel: TextView
    lateinit var llamar: Button
    lateinit var regresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tarjeta)

        regresar = findViewById<Button>(R.id.btnRegresar)
        nomb = findViewById<TextView>(R.id.txtNom)
        marc = findViewById<TextView>(R.id.txtMarc)
        pai = findViewById<TextView>(R.id.txtPais)
        emp = findViewById<TextView>(R.id.txtEmpresa)
        prec = findViewById<TextView>(R.id.txtPrecio)
        cuerpo = findViewById<TextView>(R.id.txtTipoLeche)
        maderas = findViewById<TextView>(R.id.txtSabor)
        pastillas = findViewById<TextView>(R.id.txtProceso)
        puente = findViewById<TextView>(R.id.txtPresentacion)
        cuerdas = findViewById<TextView>(R.id.txtPeso)
        tel = findViewById<TextView>(R.id.txtTelefono)
        llamar = findViewById<Button>(R.id.btnLlamar)

        regresar.setOnClickListener { finish() }
        llamar.setOnClickListener { llamar() }

        val posicion = intent.getIntExtra("pos", -1)
        if (posicion != -1 && posicion < Guitarra.listaGuitarras.size) {
            val item = Guitarra.listaGuitarras[posicion]

            nomb.text = "Modelo: ${item.nombre}"
            marc.text = "Marca: ${item.marca}"
            emp.text = "Fabricante: ${item.empresa}"
            prec.text = "Precio: $${item.precio}"
            pai.text = "Origen: ${item.pais}"
            tel.text = item.telefono
            cuerpo.text = "Cuerpo: ${item.tipoleche}"
            pastillas.text = "Pastillas: ${item.proceso}"
            puente.text = "Puente: ${item.presentacion}"
            maderas.text = "Maderas: ${item.sabores}"
            cuerdas.text = "Cuerdas: ${item.peso}"
        } else {
            Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun llamar(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_CALL)
        } else {
            val intent= Intent(Intent.ACTION_CALL)
            intent.data= Uri.parse("tel:"+tel.text.toString())
            startActivity(intent)
        }
    }
}
