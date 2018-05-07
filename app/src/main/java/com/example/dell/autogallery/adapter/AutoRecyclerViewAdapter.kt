package com.example.dell.autogallery.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dell.autogallery.R
import com.example.dell.autogallery.dto.AutoDTO
import com.example.dell.autogallery.interfaces.AutoOnItemClickListener

/**
 * Created by ${DERYA_YANAL} on 30.04.2018.
 */
class AutoRecyclerViewAdapter(private val context: Context,autoDataList:ArrayList<AutoDTO>,clickListener:AutoOnItemClickListener):RecyclerView.Adapter<AutoViewHolder>(){

    var autoList=autoDataList
    var autoOnItemClickListener=clickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.view_item,parent,false)
        return AutoViewHolder(itemView)
    }

    override fun getItemCount(): Int =autoList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AutoViewHolder, position: Int) {

        holder.itemViewBrand.text=autoList[position].brand
        holder.itemViewModel.text=autoList[position].model
        holder.itemViewPrice.text=autoList[position].price +" " + "TL"

        holder.itemView.setOnClickListener ({
            autoOnItemClickListener.onItemClick(autoList[position])

        })
    }

}