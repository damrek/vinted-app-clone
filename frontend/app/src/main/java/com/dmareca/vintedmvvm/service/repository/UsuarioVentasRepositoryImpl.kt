package com.dmareca.vintedmvvm.service.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.network.ApiAdapter
import com.dmareca.vintedmvvm.service.model.UsuarioVentas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioVentasRepositoryImpl : UsuarioVentasRepository {

    private var usuarios = MutableLiveData<List<UsuarioVentas>>()

    override fun getUsuarios(): MutableLiveData<List<UsuarioVentas>> {
        return usuarios;
    }

    override fun callUsuariosVentasAPI() {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getUsuariosMasVentas()

        call.enqueue(object : Callback<ArrayList<UsuarioVentas>> {
            override fun onFailure(call: Call<ArrayList<UsuarioVentas>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<ArrayList<UsuarioVentas>>,
                response: Response<ArrayList<UsuarioVentas>>
            ) {
                val lstUsuarios = response.body()
                if (lstUsuarios != null) {
                    usuarios.value = lstUsuarios
                }
            }


        })
    }


}