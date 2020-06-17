package com.dmareca.vintedmvvm.view.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dmareca.vintedmvvm.databinding.FragmentProductosBinding
import com.dmareca.vintedmvvm.service.model.Producto
import com.dmareca.vintedmvvm.viewmodel.ProductoViewModel
import android.content.Intent
import android.text.TextUtils
import androidx.appcompat.widget.SearchView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dmareca.vintedmvvm.service.model.Categoria
import com.dmareca.vintedmvvm.view.ui.ProductoDetailActivity
import com.dmareca.vintedmvvm.view.ui.ProductoNuevoActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.ProgressBar
import com.dmareca.vintedmvvm.R


class ProductosFragment() : Fragment() {
    var simpleSearchView: SearchView? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var categoriaFiltroSeleccionada: ChipGroup? = null
    var categoriaSel: Integer? = null
    var txtFiltro: String? = " "
    var fabVenderProducto: FloatingActionButton? = null

    private var productoViewModel: ProductoViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentProductosBinding
                : FragmentProductosBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_productos, container, false)

        productoViewModel = activity?.run {
            ViewModelProviders.of(this)[ProductoViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        fragmentProductosBinding.setModel(productoViewModel)

        return fragmentProductosBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBindings(savedInstanceState)
        showToolbar("Catálogo", false, view)


        simpleSearchView = view.findViewById(R.id.search_view)
        categoriaFiltroSeleccionada = view.findViewById(R.id.chipGroupFilterCategoria)
        fabVenderProducto = view.findViewById(R.id.fab_Add)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        simpleSearchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val chip =
                    categoriaFiltroSeleccionada?.findViewById<Chip>(categoriaFiltroSeleccionada!!.checkedChipId)
                if (chip != null) {
                    categoriaSel = Categoria.getLstCategorias().get(chip.text.toString())
                    productoViewModel?.callProductsPorCoincidenciaCategoria(query, categoriaSel!!);
                } else {
                    productoViewModel?.callProductsPorCoincidenciaCategoria(query, 0 as Integer);
                }

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (TextUtils.isEmpty(newText)) {
                    val chip =
                        categoriaFiltroSeleccionada?.findViewById<Chip>(categoriaFiltroSeleccionada!!.checkedChipId)
                    if (chip != null) {
                        categoriaSel = Categoria.getLstCategorias().get(chip.text.toString())
                        productoViewModel?.callProductsPorCoincidenciaCategoria(
                            newText,
                            categoriaSel!!
                        );
                    } else {
                        productoViewModel?.callProductsPorCoincidenciaCategoria(
                            newText,
                            0 as Integer
                        );
                    }

                }
                if(txtFiltro == ""){
                    txtFiltro = " "
                }else{
                    txtFiltro = newText;
                }
                return false
            }
        })

        swipeRefreshLayout?.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            val chip =
                categoriaFiltroSeleccionada?.findViewById<Chip>(categoriaFiltroSeleccionada!!.checkedChipId)

            if (chip != null) {
                categoriaSel = Categoria.getLstCategorias().get(chip!!.text.toString())

                if (txtFiltro.equals(" ")) {
                    productoViewModel?.callProductsPorCategoria(categoriaSel!!);
                } else {
                    if(txtFiltro == ""){
                        txtFiltro = " "
                    }
                    productoViewModel?.callProductsPorCoincidenciaCategoria(
                        txtFiltro!!,
                        categoriaSel!!
                    );

                }


            } else {
                productoViewModel?.callProductsPorCoincidenciaCategoria("%", 0 as Integer);
            }


        })

        fabVenderProducto?.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, ProductoNuevoActivity::class.java)
            startActivity(intent)
        })
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        setupListUpdate()
    }

    fun setupListUpdate() {
        productoViewModel?.callProducts()

        productoViewModel?.getProducts()?.observe(this, Observer { products: List<Producto> ->

            productoViewModel?.setProductsInRecyclerAdapter(products)
            swipeRefreshLayout?.setRefreshing(false)
        })
    }


    fun showToolbar(title: String, backButton: Boolean, view: View) {
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setTitle(title)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(backButton)
    }
}