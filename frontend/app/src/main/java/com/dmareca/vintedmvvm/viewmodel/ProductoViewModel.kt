package com.dmareca.vintedmvvm.viewmodel

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmareca.vintedmvvm.service.model.Producto
import com.dmareca.vintedmvvm.service.model.ProductoObservable
import com.dmareca.vintedmvvm.view.adapter.RecyclerProductsAdapter
import com.dmareca.vintedmvvm.view.ui.ProductoDetailActivity
import com.google.gson.JsonObject
import java.security.AccessController.getContext

class ProductoViewModel : ViewModel() {
    private var productObservable: ProductoObservable = ProductoObservable()
    private var recyclerProductsAdapter: RecyclerProductsAdapter? = null

    var nombreProducto = MutableLiveData<String>()
    var descrProducto = MutableLiveData<String>()
    var precioProducto = MutableLiveData<String>()
    var colorProducto = MutableLiveData<String>()
    var tallaProducto = MutableLiveData<String>()

    fun callProducts(){
        productObservable.callProducts()
    }

    fun callProductsPorCoincidenciaCategoria(texto:String, categoriaId:Integer){
        productObservable.callProductsPorCoincidenciaCategoria(texto, categoriaId)
    }

    fun callProductsPorCategoria(categoriaId:Integer){
        productObservable.callProductsPorCategoria(categoriaId)
    }

    fun getProducts(): MutableLiveData<List<Producto>> {
        return productObservable.getProducts()
    }

    fun getProduct(): MutableLiveData<Producto> {
        return productObservable.getProduct()
    }

    fun setProductsInRecyclerAdapter(products: List<Producto>){
        recyclerProductsAdapter?.setProductsList(products)
        recyclerProductsAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerProductsAdapter(): RecyclerProductsAdapter?{
        recyclerProductsAdapter = RecyclerProductsAdapter(this, com.dmareca.vintedmvvm.R.layout.cardview_producto)
        return recyclerProductsAdapter
    }

    fun getProductAt(position: Int): Producto?{
        var products: List<Producto>? = productObservable.getProducts().value
        return products?.get(position)
    }

    fun onClickP(v:View, prod:Producto){
        val intent = Intent(v.context, ProductoDetailActivity::class.java)
        intent.putExtra("producto", prod)
        v.getContext().startActivity(intent)
    }

    fun addProducto(categoriaId: Integer, usuarioId: Integer){
        val nuevoProducto = Producto(JsonObject())
        nuevoProducto.nombre = nombreProducto.value
        nuevoProducto.descripcion = descrProducto.value
        var precio = precioProducto.value
        nuevoProducto.precio = precio?.toFloat()
        nuevoProducto.color = colorProducto.value
        nuevoProducto.talla = tallaProducto.value
        nuevoProducto.puntuacion = 0

        productObservable.addProducto(categoriaId, usuarioId, nuevoProducto)
    }
}