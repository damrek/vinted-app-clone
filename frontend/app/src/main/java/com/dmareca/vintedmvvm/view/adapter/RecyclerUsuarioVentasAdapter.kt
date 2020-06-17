package com.dmareca.vintedmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dmareca.vintedmvvm.BR
import com.dmareca.vintedmvvm.service.model.UsuarioVentas
import com.dmareca.vintedmvvm.viewmodel.UsuarioVentasViewModel


class RecyclerUsuarioVentasAdapter (var usuarioVentasViewModel: UsuarioVentasViewModel, var resource: Int) :
    androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerUsuarioVentasAdapter.CardUsuarioVentasHolder>(){
    var usuariosVentas: List<UsuarioVentas>? = null

    fun setUsuariosVentasList(usuariosVentas: List<UsuarioVentas>?){
        this.usuariosVentas = usuariosVentas;
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardUsuarioVentasHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardUsuarioVentasHolder(binding)
    }

    override fun getItemCount(): Int {
        return usuariosVentas?.size ?: 0
    }

    override fun onBindViewHolder(
        p0: CardUsuarioVentasHolder, p1: Int
    ) {
        p0.setDataCard(usuarioVentasViewModel, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int{
        return resource
    }
    class CardUsuarioVentasHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(UsuarioVentasViewModel: UsuarioVentasViewModel, position: Int){
            binding?.setVariable(BR.model, UsuarioVentasViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }


    }

}