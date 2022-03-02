package com.example.testapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MensajesAdapterBasic(private val dataset: Array<String>) :
    RecyclerView.Adapter<MensajesAdapterBasic.ViewHolder>() {
    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(
            android.R.layout.simple_list_item_1, parent,false
        ) as TextView

        return ViewHolder(textView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataset[position]
    }

    override fun getItemCount(): Int = dataset.size





}

