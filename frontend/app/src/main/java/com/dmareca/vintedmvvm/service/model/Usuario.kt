package com.dmareca.vintedmvvm.service.model

import com.google.gson.JsonObject
import java.io.Serializable
import java.lang.Exception

class Usuario(usuarioJson: JsonObject?) : Serializable {

    var id: Int? = null
    var username: String? = null
    var password: String? = null
    var email: String? = null
    var fechaRegistro: String? = null
    var activo: Boolean? = null

    init {
        try {
            id = usuarioJson!!.get("id").asInt
            username = usuarioJson!!.get("username").asString
            password = usuarioJson!!.get("password").asString
            email = usuarioJson!!.get("email").asString
            fechaRegistro = usuarioJson!!.get("fechaRegistro").asString
            activo = usuarioJson!!.get("activo").asBoolean
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

}