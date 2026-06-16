package com.example.chijeu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chijeu.R
import datos

class GuitarraAdapter(private val lista: List<datos>) :
    RecyclerView.Adapter<GuitarraAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNombre: TextView = view.findViewById(R.id.txtNombre)
        val txtMarca: TextView = view.findViewById(R.id.txtMarca)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val g = lista[position]
        holder.txtNombre.text = g.nombre
        holder.txtMarca.text = g.marca

        holder.txtNombre.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetalleGuitarra::class.java)
            intent.putExtra("pos", position)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = lista.size
}
