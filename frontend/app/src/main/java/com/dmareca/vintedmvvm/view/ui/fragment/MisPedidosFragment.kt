package com.dmareca.vintedmvvm.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dmareca.vintedmvvm.R
import com.dmareca.vintedmvvm.databinding.FragmentMispedidosBinding
import com.dmareca.vintedmvvm.service.model.Pedido
import com.dmareca.vintedmvvm.view.ui.LoginActivity
import com.dmareca.vintedmvvm.viewmodel.PedidoViewModel

class MisPedidosFragment()  : Fragment() {

    private var pedidoViewModel: PedidoViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var fragmentProductosBinding
                : FragmentMispedidosBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mispedidos, container, false)

        pedidoViewModel = activity?.run {
            ViewModelProviders.of(this)[PedidoViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        fragmentProductosBinding.setModel(pedidoViewModel)

        return fragmentProductosBinding.root

//        return inflater.inflate(R.layout.fragment_mispedidos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBindings(savedInstanceState)
        showToolbar("Mis pedidos", false, view)
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        setupListUpdate()
    }

    fun setupListUpdate() {

        pedidoViewModel?.callPedidosHistorico(LoginActivity.USUARIO?.id!!)

        pedidoViewModel?.getPedidos()?.observe(this, Observer { pedidos: List<Pedido> ->

            pedidoViewModel?.setPedidosInRecyclerAdapter(pedidos)
        })
    }

    fun showToolbar(title: String, backButton: Boolean, view: View) {
        val toolbar : Toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setTitle(title)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(backButton)
    }
}