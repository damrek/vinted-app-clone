package com.dmareca.vintedmvvm.service.model

import java.lang.Exception
import java.util.HashMap

class Categoria {

    companion object {
        fun getLstCategorias(): HashMap<String, Integer> {
            var lstCategorias = HashMap<String, Integer>()
            lstCategorias["Hombre"] = 1 as Integer
            lstCategorias["Mujer"] = 2 as Integer
            lstCategorias["Niños"] = 3 as Integer
            return lstCategorias
        }
    }
}