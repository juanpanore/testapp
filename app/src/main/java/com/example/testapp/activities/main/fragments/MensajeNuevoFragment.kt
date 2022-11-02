package com.example.testapp.activities.main.fragments


import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testapp.activities.detallemensaje.DetalleMensajeActivity
import com.example.testapp.databinding.FragmentRespuestaMensajesBinding
import com.example.testapp.model.Mensaje
import com.example.testapp.utils.UtilsDate
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MensajeNuevoFragment : Fragment() {

    private var _binding: FragmentRespuestaMensajesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    private val mensajes_collection = "mensajes_data"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRespuestaMensajesBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            val email = binding.editTextTo.text as CharSequence
            if (email.isValidEmail()) {
                if (binding.editTextSubject.text.isEmpty()) {
                    binding.editTextSubject.requestFocus()
                    binding.editTextSubject.setError("El Asunto no puede estar vacío")

                } else {
                    if (binding.editTextMessage.text.isEmpty()) {
                        binding.editTextMessage.requestFocus()
                        binding.editTextMessage.setError("El Mensaje no puede estar vacío")
                    } else {
                        Snackbar.make(view, "Enviando correo a $email ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()

                        val mensaje = Mensaje(

                            binding.editTextTo.text.toString(),
                            binding.editTextSubject.text.toString(),
                            binding.editTextMessage.text.toString(),
                            "juanpanore@gmail.com",
                            Date(),
                            "https://upload.wikimedia.org/wikipedia/commons/b/bd/Antonio_Banderas_2014.jpg"
                        )


                        db.collection(mensajes_collection)
                            .add(mensaje)
                            .addOnSuccessListener { documentReference ->
                                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                                try {
                                        Intent(context, DetalleMensajeActivity::class.java).apply {
                                        putExtra("from", mensaje.emailFrom)
                                        putExtra("title", mensaje.title)
                                        putExtra("body", mensaje.body)
                                        putExtra("imageContactUrl", mensaje.imageContactUrl)
                                        putExtra(
                                            "time",
                                            mensaje.time?.let { UtilsDate(it).dateToString("hh:mm a E dd-MMM") }
                                        )
                                    }.apply {
                                        startActivity(this)
                                    }
                                } catch (e: IntentSender.SendIntentException) {
                                    println("Error enviando intent $e")
                                }
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }

                    }
                }

            } else {
                binding.editTextTo.requestFocus()
                binding.editTextTo.setError("Correo Incorrecto")
            }
        }

    }

    private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}