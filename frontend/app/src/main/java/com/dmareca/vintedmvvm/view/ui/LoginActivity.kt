package com.dmareca.vintedmvvm.view.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.dmareca.vintedmvvm.R
import com.dmareca.vintedmvvm.databinding.ActivityLoginBinding
import com.dmareca.vintedmvvm.service.model.Usuario
import com.dmareca.vintedmvvm.viewmodel.UsuarioViewModel

class LoginActivity : AppCompatActivity() {

    @BindView(R.id.txtCrearCuenta)
    @JvmField
    var createAccount: TextView? = null

    private var usuarioViewModel: UsuarioViewModel? = null

    companion object {
        var USUARIO: Usuario  ?= null;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupBindings(savedInstanceState)

        usuarioViewModel?.getUsuario()?.observe(this, Observer<Usuario>(){
                user: Usuario ->

                if(user.username != null){
                    USUARIO = user;
                    val intent = Intent(this, ContainerActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Usuario no existe", Toast.LENGTH_LONG).show()
                }
        })

        ButterKnife.bind(this)
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        var activityLoginBinding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        usuarioViewModel = ViewModelProviders.of(this).get(UsuarioViewModel::class.java)
        activityLoginBinding.setModel(usuarioViewModel)

    }

    @OnClick(R.id.txtCrearCuenta)
    fun createAccount(){
        val intent = Intent(this, CreateAccountActivity::class.java)
        ContextCompat.startActivity(this, intent, null)
    }

}