package com.example.miaulaupn.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miaulaupn.R
import com.example.miaulaupn.models.RutaDetalle

class RutaDetalleAdapter(private val lista: List<RutaDetalle>) :
    RecyclerView.Adapter<RutaDetalleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtIdRutaRegistro: TextView = view.findViewById(R.id.txtIdRutaRegistro)
        val txtNroSalon: TextView = view.findViewById(R.id.txtNroSalon)
        val txtPaso: TextView = view.findViewById(R.id.txtPaso)
        val txtDescripcion: TextView = view.findViewById(R.id.txtDescripcion)
        val txtImagen: TextView = view.findViewById(R.id.txtImagen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ruta_detalle, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ruta = lista[position]
        holder.txtIdRutaRegistro.text = "ID Ruta: ${ruta.idRutaRegistro}"
        holder.txtNroSalon.text = "Nro. Salón: ${ruta.nroSalon}"
        holder.txtPaso.text = "Paso: ${ruta.nroPaso}"
        holder.txtDescripcion.text = "Descripción: ${ruta.descripcionRuta}"
        holder.txtImagen.text = "Imagen: ${ruta.imagen}"
    }

    override fun getItemCount() = lista.size
}
