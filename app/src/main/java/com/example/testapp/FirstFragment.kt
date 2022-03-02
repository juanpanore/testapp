package com.example.testapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val mensajes =  arrayOf("UCO", "Profesor", "SURA", "Medico", "Amigo", "Propanda", "Bancolombia", "Davivienda","Medico", "Amigo", "Propanda", "Bancolombia", "Davivienda","Medico", "Amigo", "Propanda", "Bancolombia", "Davivienda")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        recyclerViewBasic()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            println("Ingresando al segundo fragmento")

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun recyclerViewBasic() {
        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = MensajesAdapterBasic(mensajes)

        binding.recyclerView.layoutManager = viewManager
        binding.recyclerView.adapter = viewAdapter
    }
}