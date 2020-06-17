package com.dmareca.vintedmvvm.view.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.dmareca.vintedmvvm.R
import com.dmareca.vintedmvvm.databinding.ActivityAddProductoBinding
import com.dmareca.vintedmvvm.databinding.ActivityLoginBinding
import com.dmareca.vintedmvvm.service.model.Categoria
import com.dmareca.vintedmvvm.service.model.Producto
import com.dmareca.vintedmvvm.viewmodel.ProductoViewModel
import com.dmareca.vintedmvvm.viewmodel.UsuarioViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.JsonObject

class ProductoNuevoActivity : AppCompatActivity() {

    private var productoViewModel: ProductoViewModel? = null

    @BindView(R.id.txtNombreProducto)
    @JvmField
    var nombreProducto: EditText? = null

    @BindView(R.id.txtDescProducto)
    @JvmField
    var descrProducto: EditText? = null

    @BindView(R.id.txtPrecioProducto)
    @JvmField
    var precioProducto: EditText? = null

    @BindView(R.id.txtColorProducto)
    @JvmField
    var colorProducto: EditText? = null

    @BindView(R.id.txtTallaProducto)
    @JvmField
    var tallaProducto: EditText? = null

    @BindView(R.id.chipGroupCategoria)
    @JvmField
    var categoriaSeleccionada: ChipGroup? = null

    @BindView(R.id.toolbar)
    @JvmField
    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_producto)
        setupBindings(savedInstanceState)

        productoViewModel?.getProduct()?.observe(this, Observer<Producto>(){
                prod: Producto ->

            if(prod.id != null){
                Toast.makeText(this, "Producto añadido correctamente", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Error al añadir producto", Toast.LENGTH_LONG).show()
            }
        })

        ButterKnife.bind(this)

        showToolbar("Vender producto", true)
    }

    @OnClick(R.id.btnAddProducto)
     fun addProducto() {
        val chip =
            categoriaSeleccionada?.findViewById<Chip>(categoriaSeleccionada?.checkedChipId!!)

        val categoriaSel = Categoria.getLstCategorias().get(chip!!.text.toString())

        productoViewModel?.addProducto(categoriaSel!!, LoginActivity.USUARIO?.id as Integer)
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        var activityAddProductoBinding: ActivityAddProductoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_producto)

        productoViewModel = ViewModelProviders.of(this).get(ProductoViewModel::class.java)
        activityAddProductoBinding.setModel(productoViewModel)

    }

    fun showToolbar(tittle: String, backButton: Boolean) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = tittle
        supportActionBar!!.setDisplayHomeAsUpEnabled(backButton)
    }
}