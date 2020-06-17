package com.dmareca.vintedmvvm.service.repository

import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.service.model.Usuario

interface UsuarioRepository {
    fun callUsuariosAPI(user:Usuario)
    fun registerUsuariosAPI(user:Usuario)
    fun getUsuario(): MutableLiveData<Usuario>
}