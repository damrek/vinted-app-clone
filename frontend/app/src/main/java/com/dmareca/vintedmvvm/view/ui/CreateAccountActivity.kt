package com.dmareca.vintedmvvm.view.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dmareca.vintedmvvm.R
import com.dmareca.vintedmvvm.databinding.ActivityCreateAccountBinding
import com.dmareca.vintedmvvm.databinding.ActivityLoginBinding
import com.dmareca.vintedmvvm.service.model.Usuario
import com.dmareca.vintedmvvm.viewmodel.RegisterViewModel
import com.dmareca.vintedmvvm.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.actionbar_toolbar.*

class CreateAccountActivity : AppCompatActivity() {

    private var registerViewModel: RegisterViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        setupBindings(savedInstanceState)
        showToolbar(resources.getString(R.string.toolbar_tittle_createaccount), true)

        registerViewModel?.getUsuario()?.observe(this, Observer<Usuario>(){
                user: Usuario ->

            if(user.username != null){
                Toast.makeText(this, "Cuenta creada con éxito", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Error al crear cuenta", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        var activityCreateAccountBinding: ActivityCreateAccountBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_create_account)

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        activityCreateAccountBinding.setModel(registerViewModel)
    }

    fun showToolbar(title: String, backButton: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle(title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(backButton)
    }
}