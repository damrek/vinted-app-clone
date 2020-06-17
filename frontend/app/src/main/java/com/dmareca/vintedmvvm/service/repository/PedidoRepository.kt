package com.dmareca.vintedmvvm.service.repository

import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.service.model.Pedido

interface PedidoRepository {
    fun getPedido(): MutableLiveData<Pedido>
    fun getPedidos(): MutableLiveData<List<Pedido>>
    fun getPedidoConfirmado(): MutableLiveData<Pedido>
    fun callPedidosCompraAPI(productoId: Int, usuarioId: Int)
    fun callPedidosConfirmaAPI(ped:Pedido)
    fun callPedidosHistoricoAPI(usuarioId: Int)
}