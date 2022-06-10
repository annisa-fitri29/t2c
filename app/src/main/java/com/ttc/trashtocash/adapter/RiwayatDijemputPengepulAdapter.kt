package com.ttc.trashtocash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ttc.trashtocash.R
import com.ttc.trashtocash.pengepul2.RiwayatDikemasPengepul

class RiwayatDijemputPengepulAdapter (private val dikemasList: ArrayList<RiwayatDikemasPengepul>) : RecyclerView.Adapter<RiwayatDijemputPengepulAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.riwayat_dijemput_pengepul_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = dikemasList[position]

        holder.nama.text = currentitem.nama
        holder.berat.text = currentitem.jumlah
        holder.jenis.text = currentitem.jenis
        holder.harga.text = currentitem.harga
        holder.lokasi.text = currentitem.alamat
        holder.phone.text = currentitem.noTelp
        holder.waktu_jemput.text = currentitem.waktu_jemput
        holder.tgl_jemput.text = currentitem.tgl_jemput

    }

    override fun getItemCount(): Int {

        return dikemasList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val nama : TextView = itemView.findViewById(R.id.Pdijemputpenjemputanname)
        val berat : TextView = itemView.findViewById(R.id.Pdijemput_itemweight)
        val jenis : TextView = itemView.findViewById(R.id.Pdijemput_itemname)
        val harga : TextView = itemView.findViewById(R.id.Pdijemputitem_price)
        val lokasi: TextView = itemView.findViewById(R.id.djP_loctxt)
        val phone : TextView = itemView.findViewById(R.id.djP_phonetxt)
        val waktu_jemput : TextView = itemView.findViewById(R.id.djP_clocktxt)
        val tgl_jemput : TextView = itemView.findViewById(R.id.djP_calendartxt)

    }

}