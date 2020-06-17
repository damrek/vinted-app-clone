package com.dmareca.vintedmvvm.view.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.dmareca.vintedmvvm.R
import com.dmareca.vintedmvvm.view.ui.fragment.MisPedidosFragment
import com.dmareca.vintedmvvm.view.ui.fragment.ProductosFragment
import com.dmareca.vintedmvvm.view.ui.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ContainerActivity : AppCompatActivity() {

    var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        bottomNavigationView = findViewById(R.id.navigationBarContainer)
        bottomNavigationView?.setSelectedItemId(R.id.navigation_products)

        val productosFragment = ProductosFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container, productosFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .addToBackStack(null).commit()

        bottomNavigationView?.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_products -> {
                    val productosFragment = ProductosFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, productosFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null).commit()
                }
                R.id.navigation_shopping -> {
                    val pedidosFragment = MisPedidosFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, pedidosFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null).commit()
                }
                R.id.navigation_profile -> {
                    val profileFragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, profileFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null).commit()
                }
            }
            true
        })

    }
}