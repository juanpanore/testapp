package com.example.testapp.utils

import java.text.SimpleDateFormat
import java.util.*

class UtilsDate (private val date: Date) {

    fun dateToString(format: String): String {
        //simple date formatter
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

        //return the formatted date string
          return dateFormatter.format(date)
    }
}