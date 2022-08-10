package com.example.testapp.activities.main.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import com.example.testapp.activities.detallemensaje.DetalleMensajeActivity
import com.example.testapp.activities.main.adapters.MensajesAdapterBasic
import com.example.testapp.model.Mensaje
import com.example.testapp.databinding.FragmentMensajesBinding
import com.example.testapp.integration.UserImpl
import com.example.testapp.persistence.database.AppDatabase
import com.example.testapp.utils.UtilsArray
import com.example.testapp.utils.UtilsDate
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MensajesFragment : Fragment() {

    private var _binding: FragmentMensajesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val mensajes = arrayListOf<Mensaje>()
    private lateinit var db: AppDatabase
    private lateinit var userImpl: UserImpl
    private val dbFireBase = Firebase.firestore
    private val mensajes_collection = "mensajes_data"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMensajesBinding.inflate(inflater, container, false)
        println("estoy creando la vista")

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        db = AppDatabase.getInstance(context)
        userImpl = UserImpl()
        fillListaMensajes(mensajes)
        println("los mensajes de db consultados son $mensajes")
        recyclerViewBasic()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setOnClickListener {
            println("Ingresando al segundo fragmento")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun recyclerViewBasic() {
        val viewManager = LinearLayoutManager(this.context)
        mensajes.forEach {
            println("mensajes en db $it")
        }
        val viewAdapter = context?.let { MensajesAdapterBasic(mensajes, it) }
        binding.recyclerView.layoutManager = viewManager
        binding.recyclerView.adapter = viewAdapter

        viewAdapter?.setOnItemClickListener(object : MensajesAdapterBasic.OnItemClickListener {
            override fun onItemClick(position: Int) {
                try {
                    Intent(context, DetalleMensajeActivity::class.java).apply {
                        putExtra("from", mensajes[position].emailFrom)
                        putExtra("title", mensajes[position].title)
                        putExtra("body", mensajes[position].body)
                        putExtra("imageContactUrl", mensajes[position].imageContactUrl)
                        putExtra(
                            "time",
                            mensajes[position].time?.let { UtilsDate(it).dateToString("hh:mm a E dd-MMM") }
                        )
                    }.apply {
                        startActivity(this)
                    }
                } catch (e: IntentSender.SendIntentException) {
                    println("Error enviando intent $e")
                }


            }


        })
    }

    fun fillListaMensajes(mensajesList: ArrayList<Mensaje>) {
        println("Ingreso a llenar lista de mensajes")
        doAsync {
            val user = userImpl.searchUser("C", "123")
            uiThread {
                db.mensajeDAO().delete()
                var mensajes = emptyArray<Mensaje>()
                dbFireBase.collection(mensajes_collection)
                    .whereEqualTo("emailTo", user.email)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            Log.d(TAG, "#####FIREBASE####${document.id} => ${document.data}")
                            val mensaje = document.toObject<Mensaje>()
                            mensajes = UtilsArray().append(mensajes, mensaje)
                        }
                        mensajes.forEach { println("mensajes from #####FIREBASE#### $it") }

                        db.mensajeDAO().insertAll(*mensajes)
                        mensajesList.addAll(db.mensajeDAO().getAll())
                        mensajesList.forEach { println("mensajes en db $it") }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents: ", exception)
                    }

            }
        }

    }


}