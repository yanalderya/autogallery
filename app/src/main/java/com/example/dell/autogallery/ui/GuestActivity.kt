package com.example.dell.autogallery.ui


import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.dell.autogallery.R
import com.example.dell.autogallery.dialog.AutoPhotoFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class GuestActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val tvScanCode by lazy { findViewById<TextView>(R.id.activity_guest_scan_qrCode) }
    private val builder by lazy { AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert) }
    private var zXingScannerView:ZXingScannerView? = null
    private val lnrMasterView by lazy { findViewById<LinearLayout>(R.id.activity_guest_lnrMasterView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

        initEvent()
    }

    override fun onPause() {
        super.onPause()

        zXingScannerView?.visibility = View.GONE
        lnrMasterView.visibility = View.VISIBLE
    }

    override fun handleResult(result: Result?) {
        val test = result!!.getText().split("-")

        builder.setTitle("Uyarı")
        builder.setMessage("İlan Detaylarına Bakmak İstiyor Musunuz?")
        builder.setPositiveButton("Evet") { dialog, which ->

            zXingScannerView?.visibility = View.GONE
            lnrMasterView.visibility = View.VISIBLE

            val intent = Intent(this, AutoInfoActivity::class.java)
            intent.putExtra("Result", result.getText().toString())
            startActivity(intent)
        }
        builder.setNegativeButton("Hayır") { dialog, which ->
            dialog.cancel()
//            val intent=Intent(this,GuestActivity::class.java)
//            startActivity(intent)
        }
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.show()

        Toast.makeText(this, result.getText(), Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.munu_activity_dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.menu_activity_dashboard_exit -> {

                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initEvent() {

        tvScanCode.setOnClickListener {

            zXingScannerView = findViewById<ZXingScannerView>(R.id.activity_guest_zxingScannerView)
            zXingScannerView?.setResultHandler(this)

            Toast.makeText(this, "Harikasın", Toast.LENGTH_SHORT).show()

            zXingScannerView?.visibility = View.VISIBLE
            lnrMasterView.visibility = View.GONE
            zXingScannerView?.startCamera()
        }
    }

}
