package com.example.miaulaupn.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miaulaupn.R
import com.example.miaulaupn.models.Aula
import com.example.miaulaupn.ui.ListarRutasActivity
import com.example.miaulaupn.ui.RegistrarRutaDetalleActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AulaAdapter(
    private val lista: List<Aula>,
    private val context: Context
) : RecyclerView.Adapter<AulaAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtIdRutaRegistro: TextView = itemView.findViewById(R.id.txtIdRutaRegistro)
        val txtSede: TextView = itemView.findViewById(R.id.txtSede)
        val txtPabellon: TextView = itemView.findViewById(R.id.txtPabellon)
        val txtSalon: TextView = itemView.findViewById(R.id.txtSalon)
        val btnVerRuta: FloatingActionButton = itemView.findViewById(R.id.btnVerRuta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_aula, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aula = lista[position]
        holder.txtIdRutaRegistro.text = "ID Ruta: ${aula.idRutaRegistro}"
        holder.txtSede.text = "Sede: ${aula.nombreSede}"
        holder.txtPabellon.text = "Pabellón: ${aula.letraPabellon}"
        holder.txtSalon.text = "Salón: ${aula.nroSalon}"

        holder.btnVerRuta.setOnClickListener {
            val intent = Intent(context, ListarRutasActivity::class.java)
            intent.putExtra("idRutaRegistro", aula.idRutaRegistro)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = lista.size
}


