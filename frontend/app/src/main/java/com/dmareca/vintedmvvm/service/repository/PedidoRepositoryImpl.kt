package com.dmareca.vintedmvvm.service.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.network.ApiAdapter
import com.dmareca.vintedmvvm.service.model.Pedido
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PedidoRepositoryImpl : PedidoRepository {
    private var pedido = MutableLiveData<Pedido>()
    private var pedidos = MutableLiveData<List<Pedido>>()

    override fun getPedido(): MutableLiveData<Pedido> {
        return pedido;
    }

    override fun getPedidos(): MutableLiveData<List<Pedido>> {
        return pedidos;
    }

    override fun getPedidoConfirmado(): MutableLiveData<Pedido> {
        return pedido;
    }

    override fun callPedidosCompraAPI(productoId: Int, usuarioId: Int) {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.addPedido(productoId, usuarioId)

        call.enqueue(object : Callback<Pedido> {
            override fun onFailure(call: Call<Pedido>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<Pedido>,
                response: Response<Pedido>
            ) {
                var ped = response.body()

                if (ped == null) {
                    ped = Pedido(JsonObject())
                }

                pedido.value = ped

            }


        })
    }

    override fun callPedidosConfirmaAPI(ped: Pedido) {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.confirmarPedido(ped)

        call.enqueue(object : Callback<Pedido> {
            override fun onFailure(call: Call<Pedido>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<Pedido>,
                response: Response<Pedido>
            ) {
                var ped = response.body()

                if (ped == null) {
                    ped = Pedido(JsonObject())
                }

                pedido.value = ped

            }


        })
    }

    override fun callPedidosHistoricoAPI(usuarioId: Int) {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getHistoricoPedidos(usuarioId)

        call.enqueue(object : Callback<ArrayList<Pedido>> {
            override fun onFailure(call: Call<ArrayList<Pedido>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<ArrayList<Pedido>>,
                response: Response<ArrayList<Pedido>>
            ) {
                var peds = response.body()

                pedidos.value = peds

            }


        })
    }


}