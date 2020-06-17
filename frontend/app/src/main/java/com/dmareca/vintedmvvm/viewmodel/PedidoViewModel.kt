package com.dmareca.vintedmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmareca.vintedmvvm.service.model.Pedido
import com.dmareca.vintedmvvm.service.model.PedidoObservable
import com.dmareca.vintedmvvm.service.model.Producto
import com.dmareca.vintedmvvm.view.adapter.RecyclerPedidosAdapter
import com.dmareca.vintedmvvm.view.adapter.RecyclerProductsAdapter

class PedidoViewModel : ViewModel() {
    private var pedidoObservable: PedidoObservable = PedidoObservable()
    private var recyclerPedidosAdapter: RecyclerPedidosAdapter? = null

    fun callPedidosCompra(productoId: Int, usuarioId: Int){
        pedidoObservable.callPedidosCompra(productoId, usuarioId)
    }

    fun callPedidosHistorico(usuarioId: Int){
        pedidoObservable.callPedidosHistorico(usuarioId)
    }

    fun getPedido(): MutableLiveData<Pedido> {
        return pedidoObservable.getPedido()
    }

    fun getPedidos(): MutableLiveData<List<Pedido>> {
        return pedidoObservable.getPedidos()
    }

    fun setPedidosInRecyclerAdapter(pedidos: List<Pedido>){
        recyclerPedidosAdapter?.setPedidosList(pedidos)
        recyclerPedidosAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerPedidosAdapter(): RecyclerPedidosAdapter?{
        recyclerPedidosAdapter = RecyclerPedidosAdapter(this, com.dmareca.vintedmvvm.R.layout.cardview_pedido)
        return recyclerPedidosAdapter
    }

    fun getPedidoAt(position: Int): Pedido?{
        var pedidos: List<Pedido>? = pedidoObservable.getPedidos().value
        return pedidos?.get(position)
    }

    fun getPedidoConfirmado(): MutableLiveData<Pedido> {
        return pedidoObservable.getPedidoConfirmado()
    }

    fun confirmarPedido(ped: Pedido) {
        pedidoObservable.callPedidosConfirma(ped)
    }

    fun comprar(productoId: Int, usuarioId: Int) {
        callPedidosCompra(productoId, usuarioId)
    }
}