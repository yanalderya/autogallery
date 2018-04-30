package com.example.dell.autogallery.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.dell.autogallery.R
import com.example.dell.autogallery.adapter.AutoRecyclerViewAdapter
import com.example.dell.autogallery.dto.AutoDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_guest.*

class GuestActivity : AppCompatActivity(),ValueEventListener {

    private val autoRecyclerView by lazy { findViewById<RecyclerView>(R.id.activity_guest_recyclerView) }
    private val autoList by lazy { ArrayList<AutoDTO>() }

    private lateinit var databaseEventDetail : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

        btnsignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)



        }
    }

    override fun onStart() {
        super.onStart()
        databaseEventDetail= FirebaseDatabase.getInstance().getReference("arabalar")
        databaseEventDetail.addValueEventListener(this)


    }

    override fun onStop() {
        super.onStop()
        databaseEventDetail.removeEventListener(this)
    }

    override fun onCancelled(p0: DatabaseError?) {

    }

    override fun onDataChange(p0: DataSnapshot?) {
        autoList.clear()
        for (singleSnapshot in p0!!.children){
            val modell=singleSnapshot.getValue(AutoDTO::class.java)
            autoList.add(modell!!)
        }
        fillList()
    }

    private fun fillList() {
        val autoRecyclerAdapter= AutoRecyclerViewAdapter(this,autoList)
        val linearLayoutManager= LinearLayoutManager(this)
        autoRecyclerView.layoutManager=linearLayoutManager
        autoRecyclerView.adapter=autoRecyclerAdapter
    }
}
