package com.example.chijeu

import EliminarAdapter
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import guitarra.listaGuitarras
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class eliminar : AppCompatActivity() {
    lateinit var recy: RecyclerView
    lateinit var btnEliminar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_eliminar)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        recy = findViewById(R.id.rvEliminar)
        btnEliminar = findViewById(R.id.btnEliminar)

        recy.layoutManager = LinearLayoutManager(this)
        val adapter = EliminarAdapter(guitarra.listaGuitarras)
        recy.adapter = adapter
        adapter.notifyDataSetChanged()

        btnEliminar.setOnClickListener {
            // Recorre los índices de la lista desde el último hasta el primero
            //reversed función que invierte el orden de una colección para evitar errores de posiciones
            for (i in guitarra.listaGuitarras.indices.reversed()) {
                if (adapter.seleccionados[i]) {
                    guitarra.listaGuitarras.removeAt(i)       //elimina objeto
                    adapter.seleccionados.removeAt(i)   //eliminar su estado de checkbox
                }
            }
            Toast.makeText(this, "Guitarras eliminadas correctamente", Toast.LENGTH_SHORT).show()

            //notifica al RecyclerView que los datos han cambiado y debe redibujar la lista
            adapter.notifyDataSetChanged()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.opc1) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        if (item.itemId == R.id.opc2) {
            if (guitarra.listaGuitarras.isEmpty()) {
                Toast.makeText(this, "No hay ninguna guitarra registrada", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, ver::class.java))
            }
        }
        if (item.itemId == R.id.opc3) {
            if (guitarra.listaGuitarras.isEmpty()) {
                Toast.makeText(this, "No hay ninguna guitarra registrada", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "Ya estás aquí", Toast.LENGTH_SHORT).show()
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