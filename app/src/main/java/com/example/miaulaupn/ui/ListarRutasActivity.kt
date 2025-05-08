package com.example.miaulaupn.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miaulaupn.R
import com.example.miaulaupn.adapters.RutaDetalleAdapter
import com.example.miaulaupn.network.ApiService
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListarRutasActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_rutas_detalle)

        recycler = findViewById(R.id.recyclerRutaDetalle)
        recycler.layoutManager = LinearLayoutManager(this)
        apiService = ApiService(this)

        val idRuta = intent.getIntExtra("idRutaRegistro", -1)

        if (idRuta != -1) {
            apiService.listarRutaDetallePorId(idRuta) { lista ->
                recycler.adapter = RutaDetalleAdapter(lista)
            }
        } else {
            Toast.makeText(this, "ID de ruta no válido", Toast.LENGTH_LONG).show()
        }

        val fab = findViewById<FloatingActionButton>(R.id.btnAgregarRuta)
        fab.setOnClickListener {
            val intent = Intent(this, RegistrarRutaDetalleActivity::class.java)
            startActivity(intent)
        }
    }
}
