package com.dmareca.vintedmvvm.service.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.service.repository.UsuarioVentasRepository
import com.dmareca.vintedmvvm.service.repository.UsuarioVentasRepositoryImpl

class UsuarioVentasObservable: BaseObservable() {

    private var usuarioVentasRepository: UsuarioVentasRepository = UsuarioVentasRepositoryImpl()

    fun getUsuarios(): MutableLiveData<List<UsuarioVentas>> {
        return usuarioVentasRepository.getUsuarios()
    }

    fun callUsuariosVentas(){
        usuarioVentasRepository.callUsuariosVentasAPI()
    }
}