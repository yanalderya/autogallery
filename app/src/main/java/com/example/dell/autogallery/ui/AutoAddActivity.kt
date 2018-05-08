package com.example.dell.autogallery.ui

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.TextInputEditText
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.dell.autogallery.R
import com.example.dell.autogallery.dialog.AutoPhotoFragment
import com.example.dell.autogallery.dto.AutoDTO
import com.example.dell.autogallery.dto.CrashDTO
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
import java.util.*


class AutoAddActivity : AppCompatActivity(), AutoPhotoFragment.onAutoPhotoListener, CompoundButton.OnCheckedChangeListener {

    //Auto Info
    private val btnAutoPhoto by lazy { findViewById<ImageView>(R.id.activity_auto_img_auto_photo) }
    private val spinnerBrand by lazy { findViewById<Spinner>(R.id.activity_auto_add_spinner_brand) }
    private val spinnerModel by lazy { findViewById<Spinner>(R.id.activity_auto_add_spinner_model) }
    private val inputModelYear by lazy { findViewById<TextInputEditText>(R.id.activity_auto_add_model_year) }
    private val inputPrice by lazy { findViewById<TextInputEditText>(R.id.activity_auto_add_price) }
    private val inputKm by lazy { findViewById<TextInputEditText>(R.id.activity_autoo_add_km) }

    //Technicial Specifications
    private val spinnerGear by lazy { findViewById<Spinner>(R.id.activity_auto_add_spinner_gear) }
    private val spinnerFuelType by lazy { findViewById<Spinner>(R.id.activity_auto_add_spinner_fuel_type) }
    private val spinnerEngineCapacity by lazy { findViewById<Spinner>(R.id.activity_auto_add_spinner_engine_capacity) }
    private val spinnerFuelComsumption by lazy { findViewById<Spinner>(R.id.activity_auto_add_spinner_fuel_comsumption) }
    private val spinnerEnginePower by lazy { findViewById<Spinner>(R.id.activity_auto_add_spinner_engine_power) }

    //Additional Features
    private val inputAddDescription by lazy { findViewById<TextInputEditText>(R.id.activity_auto_add_description) }

    //Accident Report
//    private val switchAutoCrash by lazy { findViewById<Switch>(R.id.activity_auto_add_auto_crash) }
//    private val inputCarCrash by lazy { findViewById<TextInputEditText>(R.id.activity_auto_car_crash) }

    //Button Save
    private val btnAdvertise by lazy { findViewById<Button>(R.id.activity_auto_add_btnAdvertise) }

    //Checkbox Changing Piece
    private val switchChangingPiece by lazy { findViewById<Switch>(R.id.activity_auto_add_auto_changing_piece) }
    private val checkBoxFrontRightFender by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_front_right_fender) }
    private val checkBoxRearRightFender by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_rear_right_fender) }
    private val checkBoxFrontLeftFender by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_front_left_fender) }
    private val checkBoxRearLeftFender by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_rear_left_fender) }
    private val checkBoxFrontBumper by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_front_bumper) }
    private val checkBoxRearBumper by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_rear_bumper) }
    private val checkBoxRightFrontDoor by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_right_front_door) }
    private val checkBoxLeftFrontDoor by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_left_front_door) }
    private val checkBoxRightRearDoor by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_right_rear_door) }
    private val checkBoxLeftRearDoor by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_left_rear_door) }
    private val checkBoxLuggage by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_luggage) }
    private val checkBoxCeiling by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_ceiling) }
    private val checkBoxBonnet by lazy { findViewById<CheckBox>(R.id.activity_auto_checkbox_bonnet) }


    var flags = false

    //fotoğrafları uygulamada göstermek
    var fromToGalleryURI: Uri? = null
    var fromToCameraBitmap: Bitmap? = null
    var MEGABYTE = 1000000.toDouble()

    var ref = FirebaseDatabase.getInstance().reference
    var arabalarID = ref.child("arabalar").push().key


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_add)

        initEvent()
        firebase()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 150) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                val dialog = AutoPhotoFragment()
                dialog.show(supportFragmentManager, "photoSelect")
            } else {
                Toast.makeText(this, "Tüm izinleri veriniz.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getPhotoPath(photoPath: Uri?) {

        fromToGalleryURI = photoPath
        Picasso.with(this).load(fromToGalleryURI).resize(300, 100).into(btnAutoPhoto)
    }

    override fun getPhotoBitmap(bitmap: Bitmap) {

        fromToCameraBitmap = bitmap
        btnAutoPhoto.setImageBitmap(bitmap)

    }

    private fun initEvent() {

        spinnerBrand.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                val name = spinnerBrand.selectedItem.toString()
                val idSpinner = resources.getIdentifier(name, "array", packageName)

                val spinnerArrayAdapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(idSpinner))
                spinnerModel.adapter = spinnerArrayAdapter
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        btnAutoPhoto.setOnClickListener {

            if (flags) {
                val dialog = AutoPhotoFragment()
                dialog.show(supportFragmentManager, "fotoSeç")
            } else {
                requestPermissions()
            }

        }

//        switchAutoCrash.setOnCheckedChangeListener { _, isChecked ->
//
//            inputCarCrash.visibility=View.VISIBLE
//
//            if (isChecked==false){
//                inputCarCrash.visibility=View.GONE
//            }
//
//        }
        switchChangingPiece.setOnCheckedChangeListener(this)

    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        if (p1) {
            checkBoxFrontRightFender.visibility = View.VISIBLE
            checkBoxRearRightFender.visibility = View.VISIBLE
            checkBoxFrontLeftFender.visibility = View.VISIBLE
            checkBoxRearLeftFender.visibility = View.VISIBLE
            checkBoxFrontBumper.visibility = View.VISIBLE
            checkBoxRearBumper.visibility = View.VISIBLE
            checkBoxRightFrontDoor.visibility = View.VISIBLE
            checkBoxLeftFrontDoor.visibility = View.VISIBLE
            checkBoxRightRearDoor.visibility = View.VISIBLE
            checkBoxLeftRearDoor.visibility = View.VISIBLE
            checkBoxLuggage.visibility = View.VISIBLE
            checkBoxCeiling.visibility = View.VISIBLE
            checkBoxBonnet.visibility = View.VISIBLE
        } else {
            checkBoxFrontRightFender.visibility = View.GONE
            checkBoxRearRightFender.visibility = View.GONE
            checkBoxFrontLeftFender.visibility = View.GONE
            checkBoxRearLeftFender.visibility = View.GONE
            checkBoxFrontBumper.visibility = View.GONE
            checkBoxRearBumper.visibility = View.GONE
            checkBoxRightFrontDoor.visibility = View.GONE
            checkBoxLeftFrontDoor.visibility = View.GONE
            checkBoxRightRearDoor.visibility = View.GONE
            checkBoxLeftRearDoor.visibility = View.GONE
            checkBoxLuggage.visibility = View.GONE
            checkBoxCeiling.visibility = View.GONE
            checkBoxBonnet.visibility = View.GONE
        }
    }


    private fun firebase() {

        btnAdvertise.setOnClickListener {

            addAuto()
            addChangingPiece()

            if (fromToGalleryURI != null) {

                fotografCompressed(fromToGalleryURI!!)

            } else if (fromToCameraBitmap != null) {

                fotografCompressed(fromToCameraBitmap!!)

            }
        }
    }

    private fun addChangingPiece() {

        val veritabaninaEklenecekKullanici = CrashDTO()

        if (checkBoxFrontRightFender.isChecked) {
            veritabaninaEklenecekKullanici.frontRightFender = checkBoxFrontRightFender.text.toString()
        } else{}
        if (checkBoxRearRightFender.isChecked) {
            veritabaninaEklenecekKullanici.rearRightFender = checkBoxRearRightFender.text.toString()
        } else{}
        if (checkBoxFrontLeftFender.isChecked) {
            veritabaninaEklenecekKullanici.frontLeftFender = checkBoxFrontLeftFender.text.toString()
        } else{}
        if (checkBoxRearLeftFender.isChecked) {
            veritabaninaEklenecekKullanici.rearLeftFender = checkBoxRearLeftFender.text.toString()
        } else{}
        if (checkBoxFrontBumper.isChecked) {
            veritabaninaEklenecekKullanici.frontBumper = checkBoxFrontBumper.text.toString()
        } else{}
        if (checkBoxRearBumper.isChecked) {
            veritabaninaEklenecekKullanici.rearBumper = checkBoxRearBumper.text.toString()
        } else{}
        if (checkBoxRightFrontDoor.isChecked) {
            veritabaninaEklenecekKullanici.rightFrontDoor = checkBoxRightFrontDoor.text.toString()
        } else{}
        if (checkBoxLeftFrontDoor.isChecked) {
            veritabaninaEklenecekKullanici.leftFrontDoor = checkBoxLeftFrontDoor.text.toString()
        } else{}
        if (checkBoxRightRearDoor.isChecked) {
            veritabaninaEklenecekKullanici.rightRearDoor = checkBoxRightRearDoor.text.toString()
        } else{}
        if (checkBoxLeftRearDoor.isChecked) {
            veritabaninaEklenecekKullanici.leftRearDoor = checkBoxLeftRearDoor.text.toString()
        } else{}
        if (checkBoxLuggage.isChecked) {
            veritabaninaEklenecekKullanici.luggage = checkBoxLuggage.text.toString()
        } else{}
        if (checkBoxCeiling.isChecked) {
            veritabaninaEklenecekKullanici.ceiling = checkBoxCeiling.text.toString()
        } else{}
        if (checkBoxBonnet.isChecked) {
            veritabaninaEklenecekKullanici.bonnet = checkBoxBonnet.text.toString()
        }else{

        }

        ref.child("kazaRaporu").child("degisenParca").child(arabalarID).setValue(veritabaninaEklenecekKullanici)


    }


    private fun addAuto() {

        val veritabaninaEklenecekAraba = AutoDTO()
        veritabaninaEklenecekAraba.id = arabalarID.toString()
        veritabaninaEklenecekAraba.brand = spinnerBrand.selectedItem.toString()
        veritabaninaEklenecekAraba.model = spinnerModel.selectedItem.toString()
        veritabaninaEklenecekAraba.modelYear = inputModelYear.text.toString()
        veritabaninaEklenecekAraba.price = inputPrice.text.toString() //+"TL"
        veritabaninaEklenecekAraba.km = inputKm.text.toString()
        veritabaninaEklenecekAraba.gear = spinnerGear.selectedItem.toString()
        veritabaninaEklenecekAraba.fuelType = spinnerFuelType.selectedItem.toString()
        veritabaninaEklenecekAraba.engineCapacity = spinnerEngineCapacity.selectedItem.toString()
        veritabaninaEklenecekAraba.fuelComsumption = spinnerFuelComsumption.selectedItem.toString()
        veritabaninaEklenecekAraba.enginePower = spinnerEnginePower.selectedItem.toString()
        veritabaninaEklenecekAraba.description = inputAddDescription.text.toString()
        //   veritabaninaEklenecekAraba.carCrash=inputCarCrash.text.toString()


        ref.child("arabalar").child(arabalarID).setValue(veritabaninaEklenecekAraba)
    }

    private fun requestPermissions() {

        val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA)
        if (ContextCompat.checkSelfPermission(this, permission[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, permission[1]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, permission[2]) == PackageManager.PERMISSION_GRANTED) {
            flags = true

        } else {
            ActivityCompat.requestPermissions(this, permission, 150)
        }
    }

    private fun fotografCompressed(fromToGalleryURI: Uri) {

        //nesne yaratıyoruz.
        val compressed = BackgroundResimCompress()
        //execute dediğimiz an backgroundResimde ki doInBackground methodu çalışmaya başlıyor.Worker Thread de
        compressed.execute(fromToGalleryURI)

    }

    private fun fotografCompressed(fromToCameraBitmap: Bitmap) {

        val compressed = BackgroundResimCompress(fromToCameraBitmap)
        val uri: Uri? = null
        //Url bilgimiz olmadıgı için null geçmemiz gerek bu yüzden değişken oluşturuyoruz.
        compressed.execute(uri)
    }

    private fun uploadResimToFirebase(result: ByteArray?) {

        val storageReferans = FirebaseStorage.getInstance().getReference().child("images/" + UUID.randomUUID().toString())
        storageReferans.putBytes(result!!).addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot> {
            override fun onSuccess(p0: UploadTask.TaskSnapshot?) {

                val firebaseURI = p0?.downloadUrl
                Toast.makeText(this@AutoAddActivity, "Firebase resmin yolu: " + firebaseURI.toString(), Toast.LENGTH_SHORT).show()

                ref.child("arabalar").child(arabalarID).child("profilePhoto").setValue(firebaseURI.toString())

                Toast.makeText(this@AutoAddActivity, "Yükleme tamamlandı.", Toast.LENGTH_SHORT).show()

            }


        }).addOnFailureListener(object : OnFailureListener {
            override fun onFailure(p0: Exception) {
                Toast.makeText(this@AutoAddActivity, "Resim yüklenirken hata oluştu", Toast.LENGTH_SHORT).show()
            }

        })


    }

    inner class BackgroundResimCompress : AsyncTask<Uri, Double, ByteArray?> {

        var myBitmap: Bitmap? = null

        constructor() {}

        constructor(bm: Bitmap) {

            if (true) {
                myBitmap = bm
            }


        }

        override fun onPreExecute() {
            super.onPreExecute()
        }


        override fun doInBackground(vararg params: Uri?): ByteArray? {

            //galeriden resim seçilmiş
            if (myBitmap == null) {
                myBitmap = MediaStore.Images.Media.getBitmap(this@AutoAddActivity.contentResolver, params[0])

            }

            var resimBytes: ByteArray? = null

            for (i in 1..2) {
                resimBytes = convertBitmaptoByte(myBitmap, 100 / i)
                publishProgress(resimBytes!!.size.toDouble())
            }

            return resimBytes

        }


        override fun onProgressUpdate(vararg values: Double?) {
            super.onProgressUpdate(*values)
            Toast.makeText(this@AutoAddActivity, "Suanki byte:" + values[0]!! / MEGABYTE + " MB", Toast.LENGTH_SHORT).show()
        }

        private fun convertBitmaptoByte(myBitmap: Bitmap?, i: Int): ByteArray? {

            val stream = ByteArrayOutputStream()
            myBitmap?.compress(Bitmap.CompressFormat.JPEG, i, stream)
            return stream.toByteArray()

        }

        override fun onPostExecute(result: ByteArray?) {
            super.onPostExecute(result)
            uploadResimToFirebase(result)
        }

    }


}