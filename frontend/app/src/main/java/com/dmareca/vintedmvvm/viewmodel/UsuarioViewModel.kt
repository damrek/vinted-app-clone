package com.dmareca.vintedmvvm.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmareca.vintedmvvm.service.model.Usuario
import com.dmareca.vintedmvvm.service.model.UsuarioObservable
import com.dmareca.vintedmvvm.view.ui.ContainerActivity
import com.dmareca.vintedmvvm.view.ui.CreateAccountActivity
import com.google.gson.JsonObject


class UsuarioViewModel : ViewModel(){
    private var usuarioObservable: UsuarioObservable = UsuarioObservable()
    var EmailAddress = MutableLiveData<String>()
    var Password = MutableLiveData<String>()

    fun callUsuarios(user: Usuario){
        usuarioObservable.callUsuarios(user)
    }

    fun getUsuario(): MutableLiveData<Usuario> {
        return usuarioObservable.getUsuario()
    }

    fun onClick(view: View) {
        val loginUser = Usuario(JsonObject())
        loginUser.email = "-"
        loginUser.username = EmailAddress.value
        loginUser.password = Password.value

        callUsuarios(loginUser)
    }


}