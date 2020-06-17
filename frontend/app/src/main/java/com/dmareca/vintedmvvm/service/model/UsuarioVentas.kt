package com.dmareca.vintedmvvm.service.model

import com.google.gson.JsonObject
import java.io.Serializable
import java.lang.Exception

class UsuarioVentas(usuarioVentasJson: JsonObject?) : Serializable {

    var id: Int? = null
    var username: String? = null
    var fechaRegistro: String? = null
    var totalVentas: Int? = null

    init {
        try {
            id = usuarioVentasJson!!.get("id").asInt
            username = usuarioVentasJson!!.get("username").asString
            fechaRegistro = usuarioVentasJson!!.get("fechaRegistro").asString
            totalVentas = usuarioVentasJson!!.get("id").asInt
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

}