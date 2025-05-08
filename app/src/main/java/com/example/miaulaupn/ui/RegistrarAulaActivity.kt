package com.example.miaulaupn.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miaulaupn.R
import com.example.miaulaupn.models.Aula
import com.example.miaulaupn.network.ApiService

class RegistrarAulaActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_aula)

        apiService = ApiService(this)

        val id = 0
        val edtNombreSede = findViewById<EditText>(R.id.edtNombreSede)
        val edtLetraPabellon = findViewById<EditText>(R.id.edtLetraPabellon)
        val edtNroSalon = findViewById<EditText>(R.id.edtNroSalon)
        val edtImagenReferencial = findViewById<EditText>(R.id.edtImagenReferencial)
        val btnRegistrar = findViewById<Button>(R.id.btnGuardarRuta)

        btnRegistrar.setOnClickListener {
            val aula = Aula(
                id,
                nombreSede = edtNombreSede.text.toString(),
                letraPabellon = edtLetraPabellon.text.toString(),
                nroSalon = edtNroSalon.text.toString(),
                imagenReferencial = edtImagenReferencial.text.toString()
            )

            if (aula.nombreSede.isNotEmpty() && aula.letraPabellon.isNotEmpty() &&
                aula.nroSalon.isNotEmpty() && aula.imagenReferencial.isNotEmpty()) {

                apiService.registrarAula(aula) { success ->
                    if (success) {
                        Toast.makeText(this, "Aula registrada con éxito", Toast.LENGTH_SHORT).show()
                        finish() // Cerramos la actual

                        // 👇 Lanzamos la pantalla de listado
                        val intent = Intent(this, ListarAulasActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "Error al registrar aula", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
