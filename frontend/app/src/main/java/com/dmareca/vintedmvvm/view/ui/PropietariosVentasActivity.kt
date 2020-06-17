package com.dmareca.vintedmvvm.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dmareca.vintedmvvm.R
import com.dmareca.vintedmvvm.databinding.ActivityPropietariosVentasBinding
import com.dmareca.vintedmvvm.service.model.UsuarioVentas
import com.dmareca.vintedmvvm.viewmodel.UsuarioVentasViewModel
import kotlinx.android.synthetic.main.actionbar_toolbar.*

class PropietariosVentasActivity : AppCompatActivity()  {

    private var usuarioVentasViewModel: UsuarioVentasViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_propietarios_ventas)
        setupBindings(savedInstanceState)
        showToolbar("Los 10 usuarios con más ventas", true)
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        var activityPropietariosVentasBinding: ActivityPropietariosVentasBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_propietarios_ventas)

        usuarioVentasViewModel = ViewModelProviders.of(this).get(UsuarioVentasViewModel::class.java)
        activityPropietariosVentasBinding.setModel(usuarioVentasViewModel)

        setupListUpdate()
    }

    fun setupListUpdate() {
        usuarioVentasViewModel?.callUsuariosMasVentas()

        usuarioVentasViewModel?.getUsuarios()?.observe(this, Observer { usuarios: List<UsuarioVentas> ->

            usuarioVentasViewModel?.setRecyclerUsuarioVentasAdapter(usuarios)
        })
    }

    fun showToolbar(title: String, backButton: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(backButton)
    }
}