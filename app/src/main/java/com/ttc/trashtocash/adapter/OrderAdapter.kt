package com.ttc.trashtocash.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.ttc.trashtocash.R
import com.ttc.trashtocash.register.Order

class OrderAdapter (private val context: Context, private val orderList : ArrayList<Order>) : RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.order_item,
            parent,false)
        return MyViewHolder(itemView,mListener)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = orderList[position]

        holder.orderName.text = currentitem.jenis
        holder.berat.text = currentitem.jumlah
        Glide.with(context)
            .load(currentitem.img_link)
            .into(holder.image)

    }

    override fun getItemCount(): Int {

        return orderList.size
    }


    class MyViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val orderName : TextView = itemView.findViewById(R.id.order_name)
        val berat : TextView = itemView.findViewById(R.id.order_weight)
        val image: ShapeableImageView = itemView.findViewById(R.id.order_img)

        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

}