package com.example.dell.autogallery.dialog


import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.dell.autogallery.R
import com.example.dell.autogallery.dto.AutoDTO
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

class SelectedItemDashboardFragment : DialogFragment() {

    lateinit var tvQrId:TextView
    lateinit var imgQr:ImageView
    lateinit var btnCancel:TextView
    lateinit var btnSubmit:TextView
    lateinit var btnDelete:TextView
    lateinit var text2Qr: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_selected_item_dashboard, container, false)
        tvQrId=view.findViewById(R.id.dialog_qr_code_textId)
        imgQr=view.findViewById(R.id.dialog_qr_code_imageQr)
        btnCancel=view.findViewById(R.id.dialog_tv_cancel)
        btnSubmit=view.findViewById(R.id.dialog_tv_submit)
        btnDelete=view.findViewById(R.id.dialog_tv_deleteAdvertise)

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        initEvent()

        return view
    }

    private fun initEvent() {

        val autooModel: AutoDTO = activity!!.intent?.getSerializableExtra("Data") as AutoDTO
        tvQrId.text=autooModel.id

        text2Qr = tvQrId.getText().toString().trim({ it <= ' ' })
        val multiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE, 200, 200)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            imgQr.setImageBitmap(bitmap)


        } catch (e: WriterException) {
            e.printStackTrace()
        }


        btnSubmit.setOnClickListener {

            imgQr.buildDrawingCache()
            val bm = imgQr.getDrawingCache()

            MediaStore.Images.Media.insertImage(activity!!.getContentResolver(), bm, "autogallery", "autogallery")
            Toast.makeText(activity, "Galeriye başarılı bir şekilde yüklendi.", Toast.LENGTH_SHORT).show()

        }

        btnDelete.setOnClickListener {

            FirebaseDatabase.getInstance().reference.child("arabalar").child(text2Qr).removeValue()
            dialog.dismiss()
            Toast.makeText(activity, "İlan Silindi.", Toast.LENGTH_SHORT).show()

        }

    }


}
