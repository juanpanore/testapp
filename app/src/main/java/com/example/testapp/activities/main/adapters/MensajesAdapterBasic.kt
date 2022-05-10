package com.example.testapp.activities.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testapp.R
import com.example.testapp.model.Mensaje
import com.example.testapp.utils.UtilsDate

import kotlin.collections.ArrayList

class MensajesAdapterBasic(
    private val dataset: ArrayList<Mensaje>,
    val context: Context
) :
    RecyclerView.Adapter<MensajesAdapterBasic.ViewHolder>() {

    private lateinit var mlistener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mlistener = listener
    }

    class ViewHolder(view: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {

        val iv_mensaje: ImageView
        val tv_from: TextView
        val tv_title: TextView
        val tv_body: TextView
        val tv_time: TextView

        init {
            iv_mensaje = view.findViewById(R.id.iv_mensaje)
            tv_from = view.findViewById(R.id.tv_from)
            tv_title = view.findViewById(R.id.tv_title)
            tv_body = view.findViewById(R.id.tv_body)
            tv_time = view.findViewById(R.id.tv_time)
            view.setOnClickListener { listener.onItemClick(bindingAdapterPosition) }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.one_line_mensajes, parent, false
        )
        return ViewHolder(view, mlistener)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(this.context).load(dataset[position].imageContactUrl).diskCacheStrategy(
            DiskCacheStrategy.NONE
        ).into(holder.iv_mensaje)
        holder.tv_body.text = dataset[position].body
        holder.tv_from.text = dataset[position].emailFrom
        holder.tv_title.text = dataset[position].title
        holder.tv_time.text = dataset[position].time?.let { UtilsDate(it).dateToString("hh:mm a E dd-MMM") }

    }

    override fun getItemCount(): Int = dataset.size




}


