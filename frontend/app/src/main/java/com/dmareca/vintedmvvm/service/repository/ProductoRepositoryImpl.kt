package com.dmareca.vintedmvvm.service.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.network.ApiAdapter
import com.dmareca.vintedmvvm.service.model.Producto
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductoRepositoryImpl : ProductoRepository {
    private var products = MutableLiveData<List<Producto>>()
    private var product = MutableLiveData<Producto>()

    override fun getProduct(): MutableLiveData<Producto> {
        return product;
    }

    override fun getProducts(): MutableLiveData<List<Producto>> {
        return products;
    }

    override fun callProductsAPI() {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getProducts()

        call.enqueue(object : Callback<ArrayList<Producto>> {
            override fun onFailure(call: Call<ArrayList<Producto>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<ArrayList<Producto>>,
                response: Response<ArrayList<Producto>>
            ) {
                val lstProductos = response.body()
                if (lstProductos != null) {
                    products.value = lstProductos
                }
            }


        })
    }

    override fun callProductsCoincCategoriaAPI(texto: String, categoriaId: Integer) {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getProductsCoincidenciaYCategoria(texto, categoriaId)

        call.enqueue(object : Callback<ArrayList<Producto>> {
            override fun onFailure(call: Call<ArrayList<Producto>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<ArrayList<Producto>>,
                response: Response<ArrayList<Producto>>
            ) {
                val lstProductos = response.body()
                if (lstProductos != null) {
                    products.value = lstProductos
                }
            }


        })
    }

    override fun callProductsPorCategoriaAPI(categoriaId: Integer) {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getProductsCategoria(categoriaId)

        call.enqueue(object : Callback<ArrayList<Producto>> {
            override fun onFailure(call: Call<ArrayList<Producto>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<ArrayList<Producto>>,
                response: Response<ArrayList<Producto>>
            ) {
                val lstProductos = response.body()
                if (lstProductos != null) {
                    products.value = lstProductos
                }
            }


        })
    }

    override fun addProductAPI(categoriaId: Integer, usuarioId: Integer, producto: Producto) {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.addProducto(categoriaId, usuarioId, producto)

        call.enqueue(object : Callback<Producto> {
            override fun onFailure(call: Call<Producto>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<Producto>,
                response: Response<Producto>
            ) {
                var prod = response.body()

                if (prod == null) {
                    prod = Producto(JsonObject())
                }

                product.value = prod

            }


        })
    }


}