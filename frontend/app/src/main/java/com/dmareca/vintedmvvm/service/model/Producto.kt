package com.dmareca.vintedmvvm.service.model

import com.google.gson.JsonObject
import java.io.Serializable
import java.lang.Exception

class Producto(productoJson: JsonObject?) : Serializable {

    var id: Int? = null
    var nombre: String? = null
    var descripcion: String? = null
    var precio: Float? = null
    var imagen: String? = null
    var color: String? = null
    var talla: String? = null
    var puntuacion: Int? = null
    var activo: Boolean? = null

    init {
        try {
            id = productoJson!!.get("id").asInt
            nombre = productoJson!!.get("nombre").asString
            descripcion = productoJson!!.get("descripcion").asString
            precio = productoJson!!.get("precio").asFloat
            imagen = productoJson!!.get("imagen").asString
            color = productoJson!!.get("color").asString
            talla = productoJson!!.get("talla").asString
            puntuacion = productoJson!!.get("puntuacion").asInt
            activo = productoJson!!.get("activo").asBoolean
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}