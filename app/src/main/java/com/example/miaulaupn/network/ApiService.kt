package com.example.miaulaupn.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.miaulaupn.models.Aula
import com.example.miaulaupn.models.RutaDetalle
import org.json.JSONArray
import org.json.JSONObject

class ApiService(private val context: Context) {

    fun registrarAula(aula: Aula, callback: (Boolean) -> Unit) {
        val url = "http://10.0.2.2/mi_aula_upn/api/insert_ruta_registro.php"

        val jsonBody = JSONObject().apply {
            put("nombre_sede", aula.nombreSede)
            put("letra_pabellon", aula.letraPabellon)
            put("nro_salon", aula.nroSalon)
            put("imagen_referencial", aula.imagenReferencial)
        }

        // 👇 Log para verificar qué se está enviando
        android.util.Log.d("API_REQUEST", "Body: $jsonBody")

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonBody,
            { response ->
                // 👇 Log de respuesta del servidor
                android.util.Log.d("API_RESPONSE", "Response: $response")

                val status = response.optString("status")
                callback(status == "success")
            },
            { error ->
                // 👇 Log de error si la request falla
                android.util.Log.e("API_ERROR", "Error: ${error.message}")
                callback(false)
            }
        )

        Volley.newRequestQueue(context).add(request)
    }


    fun obtenerAulas(callback: (List<Aula>) -> Unit, onError: (String) -> Unit) {
        val url = "http://10.0.2.2/mi_aula_upn/api/listar_aulas.php"

        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response: JSONArray ->
                val aulas = mutableListOf<Aula>()
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    aulas.add(
                        Aula(
                            idRutaRegistro = item.getInt("id"),
                            nombreSede = item.getString("nombre_sede"),
                            letraPabellon = item.getString("letra_pabellon"),
                            nroSalon = item.getString("nro_salon"),
                            imagenReferencial = item.getString("imagen_referencial")
                        )
                    )
                }
                callback(aulas)
            },
            { error ->
                onError(error.message ?: "Error desconocido")
            }
        )

        Volley.newRequestQueue(context).add(request)
    }



    fun registrarRutaDetalle(ruta: RutaDetalle, callback: (Boolean) -> Unit) {
        val url = "http://10.0.2.2/mi_aula_upn/api/insert_ruta_detalle.php"

        val jsonBody = JSONObject().apply {
            put("id_ruta_registro", ruta.idRutaRegistro)
            put("nro_salon", ruta.nroSalon)
            put("nro_paso", ruta.nroPaso)
            put("descripcion_ruta", ruta.descripcionRuta)
            put("imagen", ruta.imagen)
        }

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonBody,
            { response ->
                val status = response.optString("status")
                callback(status == "success")
            },
            {
                callback(false)
            }
        )

        Volley.newRequestQueue(context).add(request)
    }



    fun listarRutaDetallePorId(
        idRutaRegistro: Int,
        callback: (List<RutaDetalle>) -> Unit
    ) {
        val url = "http://10.0.2.2/mi_aula_upn/api/get_rutas_detalle.php"

        val params = JSONObject().apply {
            put("id_ruta_registro", idRutaRegistro)
        }

        val request = JsonObjectRequest(Request.Method.POST, url, params,
            { response ->
                val lista = mutableListOf<RutaDetalle>()
                val dataArray = response.optJSONArray("data") ?: return@JsonObjectRequest

                for (i in 0 until dataArray.length()) {
                    val item = dataArray.getJSONObject(i)
                    val ruta = RutaDetalle(
                        idRutaRegistro = item.getInt("id_ruta_registro"),
                        nroSalon = item.getString("nro_salon"),
                        nroPaso = item.getInt("nro_paso"),
                        descripcionRuta = item.getString("descripcion_ruta"),
                        imagen = item.getString("imagen")
                    )
                    lista.add(ruta)
                }

                callback(lista)
            },
            { error ->
                error.printStackTrace()
                callback(emptyList())
            })

        Volley.newRequestQueue(context).add(request)
    }






}
