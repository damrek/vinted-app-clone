package com.dmareca.vintedmvvm.service.repository

import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.service.model.UsuarioVentas

interface UsuarioVentasRepository {
    fun getUsuarios(): MutableLiveData<List<UsuarioVentas>>
    fun callUsuariosVentasAPI()
}