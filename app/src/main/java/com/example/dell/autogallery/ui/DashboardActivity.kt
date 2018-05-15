package com.example.dell.autogallery.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.dell.autogallery.R
import com.example.dell.autogallery.adapter.AutoRecyclerViewAdapter
import com.example.dell.autogallery.dialog.SelectedItemDashboardFragment
import com.example.dell.autogallery.dto.AutoDTO
import com.example.dell.autogallery.interfaces.AutoOnItemClickListener
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.io.Serializable

class DashboardActivity : AppCompatActivity(),ValueEventListener {

    private val autoRecyclerView by lazy { findViewById<RecyclerView>(R.id.activity_dashboard_recycAdvertiseList) }
    private val autoList by lazy { ArrayList<AutoDTO>() }

    private lateinit var databaseEventDetail : DatabaseReference
    private lateinit var autoOnItemClickListener: AutoOnItemClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)

        progressBarGoster()

        fab.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    //.setAction("Action", null).show()

            val intent=Intent(this,AutoAddActivity::class.java)
            startActivity(intent)

        }

        autoOnItemClickListener=object : AutoOnItemClickListener{
            override fun onItemClick(item: AutoDTO) {
               // Toast.makeText(this@DashboardActivity,"Aferin",Toast.LENGTH_SHORT).show()
                val bundle = Bundle()
                val dialog = SelectedItemDashboardFragment()
                dialog.arguments=bundle
                intent.putExtra("Data", item as Serializable)
                dialog.show(supportFragmentManager, "photoSelect")

            }

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
            val modelk=singleSnapshot.getValue(AutoDTO::class.java)
            autoList.add(modelk!!)
        }
        fillList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.munu_activity_dashboard,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId){
            R.id.menu_activity_dashboard_exit->{

                val intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fillList() {
        val autoRecyclerAdapter= AutoRecyclerViewAdapter(this,autoList,autoOnItemClickListener)
        val linearLayoutManager= LinearLayoutManager(this)
        autoRecyclerView.layoutManager= linearLayoutManager as LinearLayoutManager
        autoRecyclerView.adapter=autoRecyclerAdapter
        progressBarGizle()
    }

    private fun progressBarGoster() {
        activity_dashboard_progressBar.visibility = View.VISIBLE
    }

    private fun progressBarGizle() {
        activity_dashboard_progressBar.visibility = View.GONE
    }




}

