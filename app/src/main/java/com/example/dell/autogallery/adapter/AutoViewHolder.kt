package com.example.dell.autogallery.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.dell.autogallery.R


/**
 * Created by ${DERYA_YANAL} on 30.04.2018.
 */
class AutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val itemViewBrand by lazy { itemView.findViewById<TextView>(R.id.view_item_txtBrand)}
    val itemViewModel by lazy { itemView.findViewById<TextView>(R.id.view_item_txtModel) }
    val itemViewPrice by lazy { itemView.findViewById<TextView>(R.id.view_item_txtPrice) }
}