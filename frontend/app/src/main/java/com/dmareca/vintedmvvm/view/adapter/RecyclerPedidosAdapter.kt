package com.dmareca.vintedmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dmareca.vintedmvvm.BR
import com.dmareca.vintedmvvm.service.model.Pedido
import com.dmareca.vintedmvvm.viewmodel.PedidoViewModel

class RecyclerPedidosAdapter (var pedidoViewModel: PedidoViewModel, var resource: Int) :
    androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerPedidosAdapter.CardPedidoHolder>() {

    var pedidos: List<Pedido>? = null

    fun setPedidosList(pedidos: List<Pedido>?){
        this.pedidos = pedidos;
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardPedidoHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardPedidoHolder(binding)
    }

    override fun getItemCount(): Int {
        return pedidos?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int{
        return resource
    }

    override fun onBindViewHolder(
        p0: CardPedidoHolder, p1: Int
    ) {
        p0.setDataCard(pedidoViewModel, p1)
    }

    class CardPedidoHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(pedidoViewModel: PedidoViewModel, position: Int){
            binding?.setVariable(BR.model, pedidoViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }


    }
}