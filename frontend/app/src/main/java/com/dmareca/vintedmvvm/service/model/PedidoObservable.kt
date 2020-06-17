package com.dmareca.vintedmvvm.service.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.service.repository.PedidoRepository
import com.dmareca.vintedmvvm.service.repository.PedidoRepositoryImpl

class PedidoObservable  : BaseObservable() {
    private var pedidoRepository: PedidoRepository = PedidoRepositoryImpl()

    fun getPedido(): MutableLiveData<Pedido> {
        return pedidoRepository.getPedido()
    }

    fun getPedidos(): MutableLiveData<List<Pedido>> {
        return pedidoRepository.getPedidos()
    }


    fun getPedidoConfirmado(): MutableLiveData<Pedido> {
        return pedidoRepository.getPedidoConfirmado()
    }

    fun callPedidosCompra(productoId: Int, usuarioId: Int){
        pedidoRepository.callPedidosCompraAPI(productoId, usuarioId)
    }

    fun callPedidosHistorico(usuarioId: Int){
        pedidoRepository.callPedidosHistoricoAPI(usuarioId)
    }

    fun callPedidosConfirma(ped:Pedido){
        pedidoRepository.callPedidosConfirmaAPI(ped)
    }
}