package com.example.testapp.activities.main.fragments

import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
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
import com.example.testapp.utils.UtilsDate
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
                        putExtra("id", mensajes[position].idMessage)
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
                println("he llamado el servicio de usuario y obtenido los datos $user")
                if (db.mensajeDAO().getAll().isEmpty()) {
                    val mensaje1 = Mensaje(
                        0,
                        "Problema con aplicación",
                        "Buenos dias estoy presentando inconveniente",
                        user.email,
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/f/f9/Diego_Luna_Cannes_2016.jpg"
                    )
                    println("se crea mensaje1 $mensaje1")
                    val mensaje2 = Mensaje(
                        1,
                        "Problema con aplicación",
                        "Buenos dias, la factura pago",
                        "javierbarden@gmail.com",
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/f/fd/JavierBardemHWOFNov2012.jpg"
                    )
                    val mensaje3 = Mensaje(
                        2,
                        "Problema con aplicación",
                        "Buenos dias error en pago",
                        "antonio_aanderas@uco.net.com",
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/b/bd/Antonio_Banderas_2014.jpg"
                    )
                    val mensaje4 = Mensaje(
                        3,
                        "Problema con aplicación",
                        "Buenos dias estoy presentando inconveniente",
                        "juanpanore@gmail.com",
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/7/79/Pepemujica2.jpg"
                    )
                    val mensaje5 = Mensaje(
                        4,
                        "Problema con aplicación",
                        "Buenos dias estoy presentando inconveniente",
                        "juanpanore@gmail.com",
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/4/4d/Gandhi_smiling.jpg"
                    )
                    val mensaje6 = Mensaje(
                        5,
                        "Problema con aplicación",
                        "Buenos dias estoy presentando inconveniente",
                        "juanpanore@gmail.com",
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/6/6a/Shakira%2C_2011.jpg"
                    )
                    val mensaje7 = Mensaje(
                        6,
                        "Problema con aplicación",
                        "Buenos dias estoy presentando inconveniente",
                        "juanpanore@gmail.com",
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/1/19/Kim_Kardashian_Tribeca_portrait_2009.jpg"
                    )
                    val mensaje8 = Mensaje(
                        7,
                        "Problema con aplicación",
                        "Buenos dias estoy presentando inconveniente",
                        "juanpanore@gmail.com",
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/8/8c/Olivia_Wilde_at_2011_Tribeca_Film_Festival.jpg"
                    )
                    val mensaje9 = Mensaje(
                        8,
                        "Problema con aplicación",
                        "Buenos dias estoy presentando inconveniente",
                        "juanpanore@gmail.com",
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/f/f4/Aishwarya_Rai_Cannes_2014.jpg"
                    )
                    val mensaje10 = Mensaje(
                        9,
                        "Problema con aplicación",
                        "Buenos dias estoy presentando inconveniente",
                        "juanpanore@gmail.com",
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/7/77/Simon_Baker_2013_1.jpg"
                    )
                    val mensaje11 = Mensaje(
                        10,
                        "Problema con aplicación",
                        "Buenos dias estoy presentando inconveniente",
                        "juanpanore@gmail.com",
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/6/60/Scarlett_Johansson_by_Gage_Skidmore_2_%28cropped%29.jpg"
                    )
                    val mensaje12 = Mensaje(
                        11,
                        "Problema con aplicación",
                        "Buenos dias estoy presentando inconveniente",
                        "juanpanore@gmail.com",
                        Date(),
                        "https://upload.wikimedia.org/wikipedia/commons/0/01/Demi_Moore_by_David_Shankbone.jpg"
                    )
                    mensajesList.clear()

                    val mensajes = arrayOf(
                        mensaje1,
                        mensaje2,
                        mensaje3,
                        mensaje4,
                        mensaje5,
                        mensaje6,
                        mensaje7,
                        mensaje8,
                        mensaje9,
                        mensaje10,
                        mensaje11,
                        mensaje12
                    )
                    mensajes.forEach {
                        println("se agregan los siguientes mensajes $it")
                    }
                    db.mensajeDAO().insertAll(*mensajes)

                    println("Termino de llenar lista de mensajes")
                }




            }
        }
        mensajesList.addAll(db.mensajeDAO().getAll())
        mensajesList.forEach { println("mensajes en db $it") }
    }


}