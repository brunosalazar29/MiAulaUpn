package com.example.miaulaupn.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miaulaupn.R
import com.example.miaulaupn.adapters.AulaAdapter
import com.example.miaulaupn.adapters.RutaDetalleAdapter
import com.example.miaulaupn.network.ApiService
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListarAulasActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_aulas)

        recycler = findViewById(R.id.recyclerAulas)
        recycler.layoutManager = LinearLayoutManager(this)

        apiService = ApiService(this)

        apiService.obtenerAulas(
            { lista ->
                recycler.adapter = AulaAdapter(lista, this)
            },
            { errorMsg ->
                Toast.makeText(this, "Error: $errorMsg", Toast.LENGTH_LONG).show()
            }
        )

        val fab = findViewById<FloatingActionButton>(R.id.btnAgregarAula)
        fab.setOnClickListener {
            val intent = Intent(this, RegistrarAulaActivity::class.java)
            startActivity(intent)
        }
    }
}


