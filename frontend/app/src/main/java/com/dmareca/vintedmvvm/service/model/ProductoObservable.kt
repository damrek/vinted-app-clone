package com.dmareca.vintedmvvm.service.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.dmareca.vintedmvvm.service.repository.ProductoRepository
import com.dmareca.vintedmvvm.service.repository.ProductoRepositoryImpl

class ProductoObservable : BaseObservable() {

    private var productoRepository: ProductoRepository = ProductoRepositoryImpl()

    fun callProducts(){
        productoRepository.callProductsAPI()
    }

    fun callProductsPorCategoria(categoriaId:Integer){
        productoRepository.callProductsPorCategoriaAPI(categoriaId)
    }

    fun callProductsPorCoincidenciaCategoria(texto:String, categoriaId:Integer){
        productoRepository.callProductsCoincCategoriaAPI(texto, categoriaId)
    }

    fun addProducto(categoriaId: Integer, usuarioId: Integer, producto: Producto){
        productoRepository.addProductAPI(categoriaId, usuarioId, producto)
    }

    fun getProducts(): MutableLiveData<List<Producto>> {
        return productoRepository.getProducts()
    }

    fun getProduct(): MutableLiveData<Producto> {
        return productoRepository.getProduct()
    }
}