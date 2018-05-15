package com.example.dell.autogallery.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dell.autogallery.R
import com.example.dell.autogallery.dto.UserDTO
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val edtMail by lazy { findViewById<EditText>(R.id.activity_register_edtMail) }
    private val edtPassword by lazy { findViewById<EditText>(R.id.activity_register_edtPassword) }
    private val edtComfirmPassword by lazy { findViewById<EditText>(R.id.activity_register_edtConfirmPassword) }
    private val btnSignup by lazy { findViewById<Button>(R.id.activity_register_btnSignup) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initEvent()

    }

    private fun initEvent() {
        btnSignup.setOnClickListener {
            if (edtMail.text.isNotEmpty() && edtPassword.text.isNotEmpty() && edtComfirmPassword.text.isNotEmpty()) {

                if (edtPassword.text.toString().equals(edtComfirmPassword.text.toString())) {

                    //yeni üyenin kayıtlarının yapıldığı bir method oluşturup parametrelerini veriyoruz.
                    newMember(edtMail.text.toString(), edtPassword.text.toString())
                    progressBarGoster()

                } else {
                    Toast.makeText(this, "Şifreler Eşleşmiyor", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Boş Alanları Doldurunuz", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun newMember(mail: String, sifre: String) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail,sifre)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                    override fun onComplete(p0: Task<AuthResult>) {

                        if (p0.isSuccessful){
                            //this yazdıgımızda hata verdi çünkü artık  classımın içinde başka bir inner classtayım.
                            //kullanıcı kayıt yaptıktan sonra onay maili gonderme

                            //kullanıcıya database e kaydetmek için
                            //ilk nesne oluşturulur.
                            var veritabaninaEklenecekKullanici= UserDTO()
                            veritabaninaEklenecekKullanici.isim=edtMail.text.toString().substring(0,edtMail.text.toString().indexOf("@"))
                            veritabaninaEklenecekKullanici.email=FirebaseAuth.getInstance().currentUser?.email
                            veritabaninaEklenecekKullanici.phoneNumber="123"

                            FirebaseDatabase.getInstance().reference
                                    .child("kullanici")
                                    .child(FirebaseAuth.getInstance().currentUser?.uid)
                                    .setValue(veritabaninaEklenecekKullanici).addOnCompleteListener { task ->
                                        if (task.isSuccessful){
                                            FirebaseAuth.getInstance().signOut()
                                            redirectToLoginPage()
                                        }
                                    }

                        }else{
                            Toast.makeText(this@RegisterActivity, "Üye Kaydedilirken Sorun Oluştu."+p0.exception?.message, Toast.LENGTH_SHORT).show()

                        }
                    }

                })

        progressBarGizle()

    }

    private fun progressBarGoster() {
        progressBar2.visibility = View.VISIBLE
    }

    private fun progressBarGizle() {
        progressBar2.visibility = View.GONE
    }

    private fun redirectToLoginPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
