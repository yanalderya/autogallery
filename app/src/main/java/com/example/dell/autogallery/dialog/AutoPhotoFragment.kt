package com.example.dell.autogallery.dialog


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.dell.autogallery.R

class AutoPhotoFragment : DialogFragment() {

    lateinit var btnGallerySelect:TextView
    lateinit var btnTakePhoto:TextView

    interface onAutoPhotoListener{

        fun getPhotoPath(photoPath:Uri?)
        fun getPhotoBitmap(bitmap: Bitmap)
    }

    lateinit var mAutoPhotoListener: onAutoPhotoListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view =inflater.inflate(R.layout.fragment_auto_photo, container, false)

        btnGallerySelect=view.findViewById(R.id.fragment_auto_btnGallerySelect)
        btnTakePhoto=view.findViewById(R.id.fragment_auto_btnTakePhoto)

        btnGallerySelect.setOnClickListener {

            var intent= Intent(Intent.ACTION_GET_CONTENT)
            intent.type="image/*"
            startActivityForResult(intent,100)
        }

        btnTakePhoto.setOnClickListener {

            var intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,200)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==100 && resultCode==Activity.RESULT_OK && data!=null){

            var galeridenSecilenResimYolu=data.data
            Log.e("Derya","galeridenSecilenResimYolu")
            mAutoPhotoListener.getPhotoPath(galeridenSecilenResimYolu)
            dismiss()

        }else if (requestCode==200 && resultCode==Activity.RESULT_OK && data!=null){
            var kameradanSecilenResim:Bitmap
            kameradanSecilenResim=data.extras.get("data") as Bitmap
            mAutoPhotoListener.getPhotoBitmap(kameradanSecilenResim)
            dialog.dismiss()

        }
    }

    //fragmentimiz akvitiye eklendiği zaman
    override fun onAttach(context: Context?) {

        mAutoPhotoListener=activity as onAutoPhotoListener
        super.onAttach(context)
    }

}
