package com.dmareca.vintedmvvm.service.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.service.repository.UsuarioRepository
import com.dmareca.vintedmvvm.service.repository.UsuarioRepositoryImpl

class UsuarioObservable: BaseObservable() {
    private var usuarioRepository: UsuarioRepository = UsuarioRepositoryImpl()

    fun callUsuarios(user:Usuario){
       usuarioRepository.callUsuariosAPI(user)
    }

    fun registerUsuarios(user:Usuario){
        usuarioRepository.registerUsuariosAPI(user)
    }

    fun getUsuario(): MutableLiveData<Usuario> {
        return usuarioRepository.getUsuario()
    }
}