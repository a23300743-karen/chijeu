package com.example.chijeu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isEmpty

class login : AppCompatActivity() {
    lateinit var etUsuario: EditText
    lateinit var etPassword: EditText
    lateinit var btnIniciarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        crearUsuarios()

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        btnIniciarSesion = findViewById(R.id.btnLogin)

        btnIniciarSesion.setOnClickListener {
            iniciarSesion()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun crearUsuarios() {
        val prefs = getSharedPreferences("sesion", MODE_PRIVATE)

        if (!prefs.contains("usuarioAdmin")) {
            prefs.edit()
                .putString("usuarioAdmin", "admin_musica") // Cambiado para el contexto
                .putString("passAdmin", "guitarra123")    // Cambiado para el contexto
                .putString("usuarioTrabajador", "vendedor")
                .putString("passTrabajador", "musica2024")
                .apply()
        }
    }

    private fun iniciarSesion() {
        val usuario = etUsuario.text.toString()
        val password = etPassword.text.toString()

        if (usuario.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tus credenciales", Toast.LENGTH_SHORT).show()
            return
        }

        val prefs = getSharedPreferences("sesion", MODE_PRIVATE)
        val usuarioAdmin = prefs.getString("usuarioAdmin", "")
        val passAdmin = prefs.getString("passAdmin", "")
        val usuarioTrabajador = prefs.getString("usuarioTrabajador", "")
        val passTrabajador = prefs.getString("passTrabajador", "")

        if (usuario == usuarioAdmin && password == passAdmin) {
            prefs.edit().putString("rol", "Administrador").apply()
            // Ir al panel de administración de la tienda
            startActivity(Intent(this, MainActivity::class.java))

        } else if (usuario == usuarioTrabajador && password == passTrabajador) {
            prefs.edit().putString("rol", "Vendedor").apply()

            // CAMBIO CLAVE: Referencia a la nueva clase Guitarra y su lista
            // Asumiendo que renombrarás la clase 'queso' a 'Guitarra'
            if (Guitarra.listaGuitarras.isEmpty()) {
                Toast.makeText(this, "No hay guitarras en el inventario",
                    Toast.LENGTH_SHORT).show()
            } else {
                // Ir a la actividad de ver catálogo
                startActivity(Intent(this, ver::class.java))
            }

        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos",
                Toast.LENGTH_SHORT).show()
        }
    }
}