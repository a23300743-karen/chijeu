package com.example.chijeu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EliminarAdapter (private val lista:MutableList<datos>):
    RecyclerView.Adapter<EliminarAdapter.ViewHolderClass>() {
        val seleccionados = MutableList(lista.size){false}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_holder_eliminar, parent, false)
        return ViewHolderClass(view)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val item = lista[position]
        holder.nombre.text = "Modelo: ${item.nombre}"
        holder.marca.text = "Marca: ${item.marca}"
        holder.presentacion.text = "Puente: ${item.presentacion}"
        holder.empresa.text = "Fabricante: ${item.empresa}"

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = seleccionados[position]

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            seleccionados[position] = isChecked
        }
    }

    override fun getItemCount(): Int = lista.size

    class ViewHolderClass(view: View) : RecyclerView.ViewHolder(view) {
        val nombre       = view.findViewById<TextView>(R.id.tvNombreE)
        val marca        = view.findViewById<TextView>(R.id.tvMarcaE)
        val presentacion = view.findViewById<TextView>(R.id.tvPresentacionE)
        val empresa      = view.findViewById<TextView>(R.id.tvEmpresaE)
        val checkBox     = view.findViewById<CheckBox>(R.id.checkboxEliminar)
    }
}
