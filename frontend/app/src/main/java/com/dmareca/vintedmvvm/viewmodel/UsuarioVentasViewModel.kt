package com.dmareca.vintedmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmareca.vintedmvvm.service.model.UsuarioVentas
import com.dmareca.vintedmvvm.service.model.UsuarioVentasObservable
import com.dmareca.vintedmvvm.view.adapter.RecyclerUsuarioVentasAdapter

class UsuarioVentasViewModel : ViewModel() {

    private var usuarioVentasObservable: UsuarioVentasObservable = UsuarioVentasObservable()
    private var recyclerUsuarioVentasAdapter: RecyclerUsuarioVentasAdapter? = null

    fun setRecyclerUsuarioVentasAdapter(usuarios: List<UsuarioVentas>){
        recyclerUsuarioVentasAdapter?.setUsuariosVentasList(usuarios)
        recyclerUsuarioVentasAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerUsuarioVentasAdapter(): RecyclerUsuarioVentasAdapter?{
        recyclerUsuarioVentasAdapter = RecyclerUsuarioVentasAdapter(this, com.dmareca.vintedmvvm.R.layout.cardview_usuarioventas)
        return recyclerUsuarioVentasAdapter
    }

    fun callUsuariosMasVentas(){
        usuarioVentasObservable.callUsuariosVentas()
    }

    fun getUsuarios(): MutableLiveData<List<UsuarioVentas>> {
        return usuarioVentasObservable.getUsuarios()
    }

    fun getUsuarioAt(position: Int): UsuarioVentas?{
        var usuarios: List<UsuarioVentas>? = usuarioVentasObservable.getUsuarios().value
        return usuarios?.get(position)
    }

}