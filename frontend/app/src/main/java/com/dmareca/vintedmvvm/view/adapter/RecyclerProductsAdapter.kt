package com.dmareca.vintedmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dmareca.vintedmvvm.BR
import com.dmareca.vintedmvvm.service.model.Producto
import com.dmareca.vintedmvvm.viewmodel.ProductoViewModel
import android.widget.AdapterView.OnItemClickListener



class RecyclerProductsAdapter (var productViewModel: ProductoViewModel, var resource: Int) :
    androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerProductsAdapter.CardProductHolder>(){
    var products: List<Producto>? = null

    fun setProductsList(products: List<Producto>?){
        this.products = products;
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardProductHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    override fun onBindViewHolder(
        p0: CardProductHolder, p1: Int
    ) {
        p0.setDataCard(productViewModel, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int{
        return resource
    }
    class CardProductHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(productoViewModel: ProductoViewModel, position: Int){
            binding?.setVariable(BR.model, productoViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }


    }

}