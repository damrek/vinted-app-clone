package com.dmareca.vintedmvvm.service.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.network.ApiAdapter
import com.dmareca.vintedmvvm.service.model.Usuario
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioRepositoryImpl : UsuarioRepository {

    private var usuario = MutableLiveData<Usuario>()

    override fun callUsuariosAPI(user: Usuario) {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.userLogin(user)

        call.enqueue(object : Callback<Usuario> {
            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<Usuario>,
                response: Response<Usuario>
            ) {
                var userLogin = response.body()
                if(userLogin == null){
                    userLogin = Usuario(JsonObject())
                }
                usuario.value = userLogin

            }
        })
    }

    override fun registerUsuariosAPI(user: Usuario) {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.crearUsuario(user)

        call.enqueue(object : Callback<Usuario> {
            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<Usuario>,
                response: Response<Usuario>
            ) {
                var userRegister = response.body()
                if(userRegister == null){
                    userRegister = Usuario(JsonObject())
                }
                usuario.value = userRegister

            }
        })
    }


    override fun getUsuario(): MutableLiveData<Usuario> {
        return usuario;
    }

}