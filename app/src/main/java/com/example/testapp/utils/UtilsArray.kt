package com.example.testapp.utils

import com.example.testapp.model.Mensaje

class UtilsArray  {

    fun append(arr: Array<Mensaje>, mensaje: Mensaje): Array<Mensaje> {
        val list: MutableList<Mensaje> = arr.toMutableList()
        list.add(mensaje)
        return list.toTypedArray()
    }
}