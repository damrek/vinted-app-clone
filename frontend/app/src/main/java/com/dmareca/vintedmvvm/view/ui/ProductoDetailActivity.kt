package com.dmareca.vintedmvvm.view.ui

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.dmareca.vintedmvvm.R
import com.dmareca.vintedmvvm.databinding.ActivityLoginBinding
import com.dmareca.vintedmvvm.databinding.ActivityProductoDetailBinding
import com.dmareca.vintedmvvm.network.ApiAdapter
import com.dmareca.vintedmvvm.service.model.Pedido
import com.dmareca.vintedmvvm.service.model.Producto
import com.dmareca.vintedmvvm.viewmodel.PedidoViewModel
import com.dmareca.vintedmvvm.viewmodel.UsuarioViewModel
import com.squareup.picasso.Picasso

class ProductoDetailActivity : AppCompatActivity() {

    @BindView(R.id.btnComprar)
    @JvmField
    var btnComprar: Button? = null

    @BindView(R.id.imgProducto)
    @JvmField
    var imagenDetalle: ImageView? = null

    @BindView(R.id.detalleNombreProducto)
    @JvmField
    var nombreProducto: TextView? = null

    @BindView(R.id.detallePuntuacionProducto)
    @JvmField
    var puntuacionProducto: TextView? = null

    @BindView(R.id.detalleDescripcionProducto)
    @JvmField
    var descripcionProducto: TextView? = null

    @BindView(R.id.detallePrecioProducto)
    @JvmField
    var precioProducto: TextView? = null

    var producto: Producto? = null
    var pedido: Pedido? = null;

    private var pedidoViewModel: PedidoViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto_detail)
        setupBindings(savedInstanceState)

        pedidoViewModel?.getPedido()?.observe(this, Observer<Pedido>(){
                ped: Pedido ->

            if(ped.id != null){
                pedido = ped
            }
        })

        pedidoViewModel?.getPedidoConfirmado()?.observe(this, Observer<Pedido>(){
                ped: Pedido ->

            if(ped.finalizado == true){
                Toast.makeText(this, "Compra efectuada con éxito", Toast.LENGTH_LONG).show()
            }
        })

        ButterKnife.bind(this)

        cargarDatosProducto(savedInstanceState)

        showToolbar("Volver al catálogo", true)
    }

    private fun cargarDatosProducto(savedInstanceState: Bundle?) {
        val mIntent = intent
        producto = mIntent.extras!!.get("producto") as Producto?


        Picasso.get()
            .load("http://"+ ApiAdapter.LOCALHOST_IP+":59273/productos/images/" +producto?.imagen + ".jpg")
            .placeholder(R.drawable.logo)
            .into(imagenDetalle)

        nombreProducto?.setText(producto?.nombre)
        descripcionProducto?.setText(producto?.descripcion)
        puntuacionProducto?.setText(producto?.puntuacion.toString())
        precioProducto?.setText("Precio: " + producto?.precio + " €")
    }

    fun setupBindings(savedInstanceState: Bundle?) {

        var activityProductoDetailBinding: ActivityProductoDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_producto_detail)

        pedidoViewModel = ViewModelProviders.of(this).get(PedidoViewModel::class.java)
        activityProductoDetailBinding.setModel(pedidoViewModel)
    }

    @OnClick(R.id.btnComprar)
    fun comprarCarrito() {
        pedidoViewModel?.comprar(producto?.id as Int, LoginActivity.USUARIO?.id as Int);

        val alertDialog =
            AlertDialog.Builder(this@ProductoDetailActivity, R.style.AlertDialogCustom).create()
        alertDialog.setTitle(producto?.nombre)
        alertDialog.setMessage("Efectuar la compra de " + producto?.nombre + " a un precio de " + producto?.precio + " €")
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "Confirmar"
        ) { dialog, which ->
            pedidoViewModel?.confirmarPedido(pedido!!)
        }
        alertDialog.show()
    }

    fun showToolbar(tittle: String, backButton: Boolean) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = tittle
        supportActionBar!!.setDisplayHomeAsUpEnabled(backButton)
    }
}