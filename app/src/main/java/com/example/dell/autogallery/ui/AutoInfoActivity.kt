package com.example.dell.autogallery.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.dell.autogallery.R
import com.example.dell.autogallery.dto.AutoDTO
import com.example.dell.autogallery.dto.CrashDTO
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
    private val tvDescription by lazy { findViewById<TextView>(R.id.activity_auto_info_description) }

    //changing_piece
    private val tvChangingPiece by lazy { findViewById<TextView>(R.id.activity_auto_info_changing_piece) }
    private val tvFrontRightFender by lazy { findViewById<TextView>(R.id.activity_auto_info_front_right_fender) }
    private val tvRearRightFender by lazy { findViewById<TextView>(R.id.activity_auto_info_rear_right_fender) }
    private val tvFrontLeftFender by lazy { findViewById<TextView>(R.id.activity_auto_info_front_left_fender) }
    private val tvRearLeftFender by lazy { findViewById<TextView>(R.id.activity_auto_info_rear_left_fender) }
    private val tvFrontBumper by lazy { findViewById<TextView>(R.id.activity_auto_info_front_bumper) }
    private val tvRearBumper by lazy { findViewById<TextView>(R.id.activity_auto_info_rear_bumper) }
    private val tvRightFrontDoor by lazy { findViewById<TextView>(R.id.activity_auto_info_right_front_door) }
    private val tvLeftFrontDoor by lazy { findViewById<TextView>(R.id.activity_auto_info_left_front_door) }
    private val tvRightRearDoor by lazy { findViewById<TextView>(R.id.activity_auto_info_right_rear_door) }
    private val tvLeftRearDoor by lazy { findViewById<TextView>(R.id.activity_auto_info_left_rear_door) }
    private val tvLuggage by lazy { findViewById<TextView>(R.id.activity_auto_info_luggage) }
    private val tvCeiling by lazy { findViewById<TextView>(R.id.activity_auto_info_ceiling) }
    private val tvBonnet by lazy { findViewById<TextView>(R.id.activity_auto_info_bonnet) }

    //painted piece
    private val tvPaintedPiece by lazy { findViewById<TextView>(R.id.activity_auto_info_painted_piece) }
    private val tvFrontRightFender2 by lazy { findViewById<TextView>(R.id.activity_auto_info_front_right_fender2) }
    private val tvRearRightFender2 by lazy { findViewById<TextView>(R.id.activity_auto_info_rear_right_fender2) }
    private val tvFrontLeftFender2 by lazy { findViewById<TextView>(R.id.activity_auto_info_front_left_fender2) }
    private val tvRearLeftFender2 by lazy { findViewById<TextView>(R.id.activity_auto_info_rear_left_fender2) }
    private val tvFrontBumper2 by lazy { findViewById<TextView>(R.id.activity_auto_info_front_bumper2) }
    private val tvRearBumper2 by lazy { findViewById<TextView>(R.id.activity_auto_info_rear_bumper2) }
    private val tvRightFrontDoor2 by lazy { findViewById<TextView>(R.id.activity_auto_info_right_front_door2) }
    private val tvLeftFrontDoor2 by lazy { findViewById<TextView>(R.id.activity_auto_info_left_front_door2) }
    private val tvRightRearDoor2 by lazy { findViewById<TextView>(R.id.activity_auto_info_right_rear_door2) }
    private val tvLeftRearDoor2 by lazy { findViewById<TextView>(R.id.activity_auto_info_left_rear_door2) }
    private val tvLuggage2 by lazy { findViewById<TextView>(R.id.activity_auto_info_luggage2) }
    private val tvCeiling2 by lazy { findViewById<TextView>(R.id.activity_auto_info_ceiling2) }
    private val tvBonnet2 by lazy { findViewById<TextView>(R.id.activity_auto_info_bonnet2) }

    //accidentFree
    private val tvAccientFree by lazy { findViewById<TextView>(R.id.activity_auto_info_accient_free) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_info)

        title = getString(R.string.auto_info_title)
        progressBarGoster()
        readAdvertise()
        readChangingPiece()
        readPaintedPiece()
        readAccientFree()

    }

    private fun readAccientFree() {

        val id = intent?.getStringExtra("Result")

        val referans = FirebaseDatabase.getInstance().reference

        val sorgu3 = referans.child("kazaRaporu").child("kazasiz")
                .orderByKey()
                .equalTo(id.toString())
        sorgu3.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                for (singleSnapshot in p0!!.children) {

                //    val okunanDeger = singleSnapshot.getValue(CrashDTO::class.java)
                    tvAccientFree.visibility=View.VISIBLE

                }

            }

        })

    }

    private fun readPaintedPiece() {

        val id = intent?.getStringExtra("Result")

        val referans = FirebaseDatabase.getInstance().reference

        val sorgu2 = referans.child("kazaRaporu").child("boyaliParca")
                .orderByKey()
                .equalTo(id.toString())
        sorgu2.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                for (singleSnapshot in p0!!.children) {

                    val okunanDeger = singleSnapshot.getValue(CrashDTO::class.java)
                    tvPaintedPiece.visibility=View.VISIBLE

                    if (!okunanDeger?.frontRightFender.isNullOrEmpty()){
                        tvFrontRightFender2.visibility=View.VISIBLE
                        tvFrontRightFender2.text = okunanDeger!!.frontRightFender.toString()
                    }else{

                    }

                    if (!okunanDeger?.rearRightFender.isNullOrEmpty()){
                        tvRearRightFender2.visibility=View.VISIBLE
                        tvRearRightFender2.text = okunanDeger!!.rearRightFender.toString()
                    }else{

                    }

                    if (!okunanDeger?.frontLeftFender.isNullOrEmpty()){
                        tvFrontLeftFender2.visibility=View.VISIBLE
                        tvFrontLeftFender2.text = okunanDeger!!.frontLeftFender.toString()
                    }else{

                    }

                    if (!okunanDeger?.rearLeftFender.isNullOrEmpty()){
                        tvRearLeftFender2.visibility=View.VISIBLE
                        tvRearLeftFender2.text = okunanDeger!!.rearLeftFender.toString()
                    }else{

                    }

                    if (!okunanDeger?.rearLeftFender.isNullOrEmpty()){
                        tvRearLeftFender2.visibility=View.VISIBLE
                        tvRearLeftFender2.text = okunanDeger!!.rearLeftFender.toString()
                    }else{

                    }

                    if (!okunanDeger?.frontBumper.isNullOrEmpty()){
                        tvFrontBumper2.visibility=View.VISIBLE
                        tvFrontBumper2.text = okunanDeger!!.frontBumper.toString()
                    }else{

                    }

                    if (!okunanDeger?.rearBumper.isNullOrEmpty()){
                        tvRearBumper2.visibility=View.VISIBLE
                        tvRearBumper2.text = okunanDeger!!.rearBumper.toString()
                    }else{
                    }

                    if (!okunanDeger?.rightFrontDoor.isNullOrEmpty()){
                        tvRightFrontDoor2.visibility=View.VISIBLE
                        tvRightFrontDoor2.text = okunanDeger!!.rightFrontDoor.toString()
                    }else{

                    }

                    if (!okunanDeger?.leftFrontDoor.isNullOrEmpty()){
                        tvLeftFrontDoor2.visibility=View.VISIBLE
                        tvLeftFrontDoor2.text = okunanDeger!!.leftFrontDoor.toString()
                    }else{

                    }

                    if (!okunanDeger?.rightRearDoor.isNullOrEmpty()){
                        tvRightRearDoor2.visibility=View.VISIBLE
                        tvRightRearDoor2.text = okunanDeger!!.rightRearDoor.toString()
                    }else{

                    }

                    if (!okunanDeger?.leftRearDoor.isNullOrEmpty()){
                        tvLeftRearDoor2.visibility=View.VISIBLE
                        tvLeftRearDoor2.text = okunanDeger!!.leftRearDoor.toString()
                    }else{

                    }

                    if (!okunanDeger?.luggage.isNullOrEmpty()){
                        tvLuggage2.visibility=View.VISIBLE
                        tvLuggage2.text = okunanDeger!!.luggage.toString()
                    }else{

                    }

                    if (!okunanDeger?.ceiling.isNullOrEmpty()){
                        tvCeiling2.visibility=View.VISIBLE
                        tvCeiling2.text = okunanDeger!!.ceiling.toString()
                    }else{

                    }

                    if (!okunanDeger?.bonnet.isNullOrEmpty()){
                        tvBonnet2.visibility=View.VISIBLE
                        tvBonnet2.text = okunanDeger!!.bonnet.toString()
                    }else{

                    }
                }
            }
        })

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
                    tvPrice.text = "Fiyat: " + okunanDeger.price + " TL"
                    tvKM.text = "Km: " + okunanDeger.km
                    tvGear.text = "Vites: " + okunanDeger.gear
                    tvFuelType.text = "Yakıt Tipi: " + okunanDeger.fuelType
                    tvEngineCapacity.text = "Motor Hacmi: " + okunanDeger.engineCapacity
                    tvFuelComsuption.text = "Yakıt Tüketimi: " + okunanDeger.fuelComsumption
                    tvEnginePower.text = "Motor Gücü: " + okunanDeger.enginePower
                    tvDescription.text=okunanDeger.description

                    if (okunanDeger.profilePhoto.isNullOrEmpty()) {
                        Picasso.with(this@AutoInfoActivity).load(R.drawable.icon).resize(100, 100).into(imgAuto)
                    } else {
                        Picasso.with(this@AutoInfoActivity).load(okunanDeger.profilePhoto).resize(300, 170).into(imgAuto)
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

    private fun readChangingPiece() {

        val id = intent?.getStringExtra("Result")

        val referans = FirebaseDatabase.getInstance().reference

        val sorgu2 = referans.child("kazaRaporu").child("degisenParca")
                .orderByKey()
                .equalTo(id.toString())
        sorgu2.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                for (singleSnapshot in p0!!.children) {

                    val okunanDeger = singleSnapshot.getValue(CrashDTO::class.java)
                    tvChangingPiece.visibility=View.VISIBLE

                    if (!okunanDeger?.frontRightFender.isNullOrEmpty()){
                        tvFrontRightFender.visibility=View.VISIBLE
                        tvFrontRightFender.text = okunanDeger!!.frontRightFender.toString()
                    }else{

                    }

                    if (!okunanDeger?.rearRightFender.isNullOrEmpty()){
                        tvRearRightFender.visibility=View.VISIBLE
                        tvRearRightFender.text = okunanDeger!!.rearRightFender.toString()
                    }else{

                    }

                    if (!okunanDeger?.frontLeftFender.isNullOrEmpty()){
                        tvFrontLeftFender.visibility=View.VISIBLE
                        tvFrontLeftFender.text = okunanDeger!!.frontLeftFender.toString()
                    }else{

                    }

                    if (!okunanDeger?.rearLeftFender.isNullOrEmpty()){
                        tvRearLeftFender.visibility=View.VISIBLE
                        tvRearLeftFender.text = okunanDeger!!.rearLeftFender.toString()
                    }else{

                    }

                    if (!okunanDeger?.rearLeftFender.isNullOrEmpty()){
                        tvRearLeftFender.visibility=View.VISIBLE
                        tvRearLeftFender.text = okunanDeger!!.rearLeftFender.toString()
                    }else{

                    }

                    if (!okunanDeger?.frontBumper.isNullOrEmpty()){
                        tvFrontBumper.visibility=View.VISIBLE
                        tvFrontBumper.text = okunanDeger!!.frontBumper.toString()
                    }else{

                    }

                    if (!okunanDeger?.rearBumper.isNullOrEmpty()){
                        tvRearBumper.visibility=View.VISIBLE
                        tvRearBumper.text = okunanDeger!!.rearBumper.toString()
                    }else{
                    }

                    if (!okunanDeger?.rightFrontDoor.isNullOrEmpty()){
                        tvRightFrontDoor.visibility=View.VISIBLE
                        tvRightFrontDoor.text = okunanDeger!!.rightFrontDoor.toString()
                    }else{

                    }

                    if (!okunanDeger?.leftFrontDoor.isNullOrEmpty()){
                        tvLeftFrontDoor.visibility=View.VISIBLE
                        tvLeftFrontDoor.text = okunanDeger!!.leftFrontDoor.toString()
                    }else{

                    }

                    if (!okunanDeger?.rightRearDoor.isNullOrEmpty()){
                        tvRightRearDoor.visibility=View.VISIBLE
                        tvRightRearDoor.text = okunanDeger!!.rightRearDoor.toString()
                    }else{

                    }

                    if (!okunanDeger?.leftRearDoor.isNullOrEmpty()){
                        tvLeftRearDoor.visibility=View.VISIBLE
                        tvLeftRearDoor.text = okunanDeger!!.leftRearDoor.toString()
                    }else{

                    }

                    if (!okunanDeger?.luggage.isNullOrEmpty()){
                        tvLuggage.visibility=View.VISIBLE
                        tvLuggage.text = okunanDeger!!.luggage.toString()
                    }else{

                    }

                    if (!okunanDeger?.ceiling.isNullOrEmpty()){
                        tvCeiling.visibility=View.VISIBLE
                        tvCeiling.text = okunanDeger!!.ceiling.toString()
                    }else{

                    }

                    if (!okunanDeger?.bonnet.isNullOrEmpty()){
                        tvBonnet.visibility=View.VISIBLE
                        tvBonnet.text = okunanDeger!!.bonnet.toString()
                    }else{

                    }
                }
            }
        })
    }

}
