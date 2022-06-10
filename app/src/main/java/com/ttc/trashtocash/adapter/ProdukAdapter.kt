package com.ttc.trashtocash.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.ttc.trashtocash.R
import com.ttc.trashtocash.register.Produk

class ProdukAdapter(private val context: Context, private val produkList: ArrayList<Produk>) : RecyclerView.Adapter<ProdukAdapter.MyViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.produk_item,
            parent,false)
        return MyViewHolder(itemView, mListener)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = produkList[position]

        holder.orderName.text = currentitem.jenis
        holder.berat.text = currentitem.jumlah
        Glide.with(context)
            .load(currentitem.img_link)
            .into(holder.image)

    }

    override fun getItemCount(): Int {

        return produkList.size
    }


    class MyViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val orderName : TextView = itemView.findViewById(R.id.produk_name)
        val berat : TextView = itemView.findViewById(R.id.produk_weight)
        val image: ShapeableImageView = itemView.findViewById(R.id.produk_img)
        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}