package com.example.testapp.activities.main.adapters

import android.content.Context
import com.example.testapp.model.Mensaje
import junit.framework.TestCase
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MensajesAdapterBasicTest : TestCase() {

    @Mock
    lateinit var dataset: ArrayList<Mensaje>
    @Mock
    lateinit var context: Context

    lateinit var mensajesAdapterBasic: MensajesAdapterBasic


    @Before
    fun onBefore(){
        MockitoAnnotations.initMocks(this)
        mensajesAdapterBasic = MensajesAdapterBasic(dataset,context)
    }
    fun testOnCreateViewHolder() {}
}