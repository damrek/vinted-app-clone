package com.dmareca.vintedmvvm.view.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.dmareca.vintedmvvm.R
import com.dmareca.vintedmvvm.view.ui.LoginActivity
import com.dmareca.vintedmvvm.view.ui.PropietariosVentasActivity

class ProfileFragment() : Fragment() {
    @BindView(R.id.modUsername)
    @JvmField
    var username: EditText? = null
    @BindView(R.id.modEmail)
    @JvmField
    var email: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view!!)

        username?.setText(LoginActivity.USUARIO?.username.toString())
        email?.setText(LoginActivity.USUARIO?.email.toString())

        showToolbar("Mi cuenta", false, view)
    }

    @OnClick(R.id.txtUsuariosVentas)
    fun consultarUsuariosMasVentas() {
        val intent = Intent(activity, PropietariosVentasActivity::class.java)
        startActivity(intent)
    }

    fun showToolbar(title: String, backButton: Boolean, view: View) {
        val toolbar : Toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setTitle(title)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(backButton)
    }

    @OnClick(R.id.cerrarSesion)
    fun disconnect(){
        LoginActivity.USUARIO = null
        val intent = Intent(getContext(), LoginActivity::class.java)
        startActivity(intent)
    }

}