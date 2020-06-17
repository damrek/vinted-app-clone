package com.dmareca.vintedmvvm.service.model

import com.google.gson.JsonObject
import java.io.Serializable
import java.lang.Exception

class Pedido(pedidoJson: JsonObject?) : Serializable {
    var id: Int? = null
    var fecha: String? = null
    var cantidad: Int? = null
    var precio: Float? = null
    var finalizado: Boolean? = null
    var producto: Producto? = null

    init {
        try {
            id = pedidoJson!!.get("id").asInt
            fecha = pedidoJson!!.get("fecha").asString
            cantidad = pedidoJson!!.get("cantidad").asInt
            precio = pedidoJson!!.get("precio").asFloat
            finalizado = pedidoJson!!.get("finalizado").asBoolean
            producto = pedidoJson!!.get("producto") as Producto
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}