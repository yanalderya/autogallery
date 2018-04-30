package com.example.dell.autogallery.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dell.autogallery.R
import com.example.dell.autogallery.dto.AutoDTO

/**
 * Created by ${DERYA_YANAL} on 30.04.2018.
 */
class AutoRecyclerViewAdapter(private val context: Context,autoDataList:ArrayList<AutoDTO>):RecyclerView.Adapter<AutoViewHolder>(){

    var autoList=autoDataList;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.view_item,parent,false)
        return AutoViewHolder(itemView)
    }

    override fun getItemCount(): Int =autoList.size

    override fun onBindViewHolder(holder: AutoViewHolder, position: Int) {

        holder.brand.text=autoList[position].brand
        holder.model.text=autoList[position].model
        holder.price.text=autoList[position].price
    }

}