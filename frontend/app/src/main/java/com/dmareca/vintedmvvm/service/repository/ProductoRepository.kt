package com.dmareca.vintedmvvm.service.repository

import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.service.model.Producto

interface ProductoRepository {
    fun callProductsAPI()
    fun callProductsCoincCategoriaAPI(texto:String, categoriaId: Integer)
    fun callProductsPorCategoriaAPI(categoriaId: Integer)
    fun addProductAPI(categoriaId: Integer, usuarioId:Integer, producto: Producto)
    fun getProducts(): MutableLiveData<List<Producto>>
    fun getProduct(): MutableLiveData<Producto>
}