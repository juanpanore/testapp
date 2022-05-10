package com.example.testapp.activities.main.fragments


import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testapp.databinding.FragmentRespuestaMensajesBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MensajeNuevoFragment : Fragment() {

    private var _binding: FragmentRespuestaMensajesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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