package com.example.miaulaupn.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miaulaupn.R
import com.example.miaulaupn.models.RutaDetalle
import com.example.miaulaupn.network.ApiService

class RegistrarRutaDetalleActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_ruta_detalle)

        apiService = ApiService(this)

        val edtIdRutaRegistro = findViewById<EditText>(R.id.edtIdRutaRegistro) // Cambio de nombre de campo
        val edtNroSalon = findViewById<EditText>(R.id.edtNroSalon)
        val edtNroPaso = findViewById<EditText>(R.id.edtNroPaso)
        val edtDescripcionRuta = findViewById<EditText>(R.id.edtDescripcionRuta)
        val edtImagenRuta = findViewById<EditText>(R.id.edtImagenRuta)
        val btnRegistrarRuta = findViewById<Button>(R.id.btnRegistrarRuta)

        btnRegistrarRuta.setOnClickListener {
            val ruta = RutaDetalle(
                idRutaRegistro = edtIdRutaRegistro.text.toString().toIntOrNull() ?: 0,
                nroSalon = edtNroSalon.text.toString(),
                nroPaso = edtNroPaso.text.toString().toIntOrNull() ?: 0,
                descripcionRuta = edtDescripcionRuta.text.toString(),
                imagen = edtImagenRuta.text.toString()
            )

            if (ruta.idRutaRegistro != 0 && ruta.nroSalon.isNotEmpty() && ruta.nroPaso != 0 && ruta.descripcionRuta.isNotEmpty() && ruta.imagen.isNotEmpty()) {
                apiService.registrarRutaDetalle(ruta) { success ->
                    if (success) {
                        Toast.makeText(this, "Paso registrado", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
