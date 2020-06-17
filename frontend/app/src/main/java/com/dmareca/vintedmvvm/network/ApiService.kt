package com.dmareca.vintedmvvm.network

import com.dmareca.vintedmvvm.service.model.Pedido
import com.dmareca.vintedmvvm.service.model.Producto
import com.dmareca.vintedmvvm.service.model.Usuario
import com.dmareca.vintedmvvm.service.model.UsuarioVentas
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    /***** USUARIOS *****/
    @POST("usuarios/login")
    fun userLogin(@Body usuario: Usuario): Call<Usuario>

    @POST("usuarios/")
    fun crearUsuario(@Body usuario: Usuario): Call<Usuario>

    @GET("usuarios/masventas")
    fun getUsuariosMasVentas(): Call<ArrayList<UsuarioVentas>>


    /***** PRODUCTOS *****/
    @GET("productos/activos")
    fun getProducts(): Call<ArrayList<Producto>>

    @GET("productos/categoria/{categoria}")
    fun getProductsCategoria( @Path("categoria") categoria:Integer): Call<ArrayList<Producto>>

    @GET("productos/contiene/{palabras}/categoria/{categoria}")
    fun getProductsCoincidenciaYCategoria( @Path("palabras") palabras:String,
                                           @Path("categoria") categoriaId:Integer): Call<ArrayList<Producto>>

    @POST("productos/categoria/{categoria}/usuario/{usuario}")
    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json;charset=utf-8",
        "Cache-Control: max-age=640000"
    )
    fun addProducto(@Path("categoria") categoria:Integer,
                    @Path("usuario") usuario:Integer,
                    @Body producto: Producto): Call<Producto>


    /***** PEDIDOS *****/
    @POST("pedidos/nuevo/{producto}/usuario/{usuario}")
    fun addPedido(@Path("producto") productId: Int,
                           @Path("usuario") usuario: Int): Call<Pedido>

    @PUT("pedidos/confirmarpedido")
    fun confirmarPedido(@Body pedido: Pedido): Call<Pedido>

    @GET("pedidos/historico/{usuario}")
    fun getHistoricoPedidos(@Path("usuario") usuario:Int): Call<ArrayList<Pedido>>
}