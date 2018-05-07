package com.example.dell.autogallery.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.dell.autogallery.R
import com.example.dell.autogallery.dto.AutoDTO
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_auto_info.*

class AutoInfoActivity : AppCompatActivity() {


    private val imgAuto by lazy { findViewById<ImageView>(R.id.activity_auto_info_photo) }
    private val tvBrand by lazy { findViewById<TextView>(R.id.activity_auto_info_brand) }
    private val tvModel by lazy { findViewById<TextView>(R.id.activity_auto_info_model) }
    private val tvModelYear by lazy { findViewById<TextView>(R.id.activity_auto_info_model_year) }
    private val tvPrice by lazy { findViewById<TextView>(R.id.activity_auto_info_price) }
    private val tvKM by lazy { findViewById<TextView>(R.id.activity_auto_info_km) }
    private val tvGear by lazy { findViewById<TextView>(R.id.activity_auto_info_gear) }
    private val tvFuelType by lazy { findViewById<TextView>(R.id.activity_auto_info_fuel_type) }
    private val tvEngineCapacity by lazy { findViewById<TextView>(R.id.activity_auto_info_engine_capacity) }
    private val tvFuelComsuption by lazy { findViewById<TextView>(R.id.activity_auto_info_fuel_comsuption) }
    private val tvEnginePower by lazy { findViewById<TextView>(R.id.activity_auto_info_engine_power) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_info)

        progressBarGoster()

        readAdvertise()

    }

    private fun readAdvertise() {
        val id = intent?.getStringExtra("Result")


        val referans = FirebaseDatabase.getInstance().reference
        val sorgu = referans.child("arabalar")
                .orderByKey()
                .equalTo(id.toString())
        sorgu.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot?) {
                for (singleSnapshot in p0!!.children) {

                    progressBarGizle()
                    val okunanDeger = singleSnapshot.getValue(AutoDTO::class.java)
                    tvBrand.text = "Marka: " + okunanDeger!!.brand
                    tvModel.text = "Model: " + okunanDeger.model
                    tvModelYear.text = "Model Yılı: " + okunanDeger.modelYear
                    tvPrice.text ="Fiyat: " + okunanDeger.price + " TL"
                    tvKM.text ="Km: " + okunanDeger.km
                    tvGear.text = "Vites: " +okunanDeger.gear
                    tvFuelType.text ="Yakıt Tipi: " + okunanDeger.fuelType
                    tvEngineCapacity.text ="Motor Hacmi: " + okunanDeger.engineCapacity
                    tvFuelComsuption.text ="Yakıt Tüketimi: " + okunanDeger.fuelComsumption
                    tvEnginePower.text ="Motor Gücü: " + okunanDeger.enginePower
                    if(okunanDeger.profilePhoto.isNullOrEmpty()){
                        Picasso.with(this@AutoInfoActivity).load(R.drawable.icon).resize(100,100).into(imgAuto)
                    }
                    else{
                        Picasso.with(this@AutoInfoActivity).load(okunanDeger.profilePhoto).resize(300,170).into(imgAuto)
                    }


                }

            }

        })

    }

    private fun progressBarGoster() {
        activity_info_progressBar.visibility = View.VISIBLE
    }

    private fun progressBarGizle() {
        activity_info_progressBar.visibility = View.GONE
    }
}
