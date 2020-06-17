package com.dmareca.vintedmvvm.viewmodel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmareca.vintedmvvm.service.model.Usuario
import com.dmareca.vintedmvvm.service.model.UsuarioObservable
import com.google.gson.JsonObject

class RegisterViewModel : ViewModel() {
    private var usuarioObservable: UsuarioObservable = UsuarioObservable()

    var txtUsername = MutableLiveData<String>()
    var txtEmail = MutableLiveData<String>()
    var txtPw = MutableLiveData<String>()
    var txtConfirmaPw = MutableLiveData<String>()

    fun registerUsuario(user: Usuario){
        usuarioObservable.registerUsuarios(user)
    }

    fun getUsuario(): MutableLiveData<Usuario> {
        return usuarioObservable.getUsuario()
    }

    fun onRegister(view: View){
        val registerUser = Usuario(JsonObject())
        registerUser.username = txtUsername.value
        registerUser.email = txtEmail.value
        registerUser.password = txtPw.value

        if(registerUser.username == null || registerUser.password == null || registerUser.email == null){
            Toast.makeText(view.context, "Campos vacios", Toast.LENGTH_LONG).show()
        }
        else if(registerUser.username.equals("") || registerUser.email.equals("")){
            Toast.makeText(view.context, "Email o username no pueden estar vacios", Toast.LENGTH_LONG).show()
        }
        else if (registerUser.username.equals("")  || txtConfirmaPw.value.toString().equals("")   ){
            Toast.makeText(view.context, "La contraseña no puede estar vacía", Toast.LENGTH_LONG).show()
        }
        else if(!registerUser.password.equals(txtConfirmaPw.value)){
            Toast.makeText(view.context, "Contraseñas no coinciden", Toast.LENGTH_LONG).show()
        }else{
            val regUser = Usuario(JsonObject())
            regUser.username = txtUsername.value
            regUser.email = txtEmail.value
            regUser.password = txtPw.value

            registerUsuario(registerUser)
        }


    }
}