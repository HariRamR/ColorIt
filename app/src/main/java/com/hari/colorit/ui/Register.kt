package com.hari.colorit.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.hari.colorit.databinding.ActivityRegisterBinding
import com.hari.colorit.ui.utils.Extensions.isValidEmail

class Register: AppCompatActivity() {

    private lateinit var view: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(view.root)

        view.toolbarRegister.setNavigationOnClickListener {
            finish()
        }

        view.registerBtnRegister.setOnClickListener{

            register(view.emailEtRegister.text.toString(), view.passwordEtRegister.text.toString())
        }
    }

    private fun register(email: String, password: String){

        view.progressBarRegister.visibility = View.VISIBLE
        if(!email.isValidEmail()){
            Snackbar.make(view.root, "Enter valid email to login..", Snackbar.LENGTH_SHORT).show()
        }else if (password.isEmpty()){
            Snackbar.make(view.root, "Enter your password to login..", Snackbar.LENGTH_SHORT).show()
        }else{
            fireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                task -> if(task.isSuccessful){
                startActivity(Intent(this, Dashboard::class.java))
                finish()
                }else{
                    Snackbar.make(view.root, "Something went wrong. Please try again later", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
        view.progressBarRegister.visibility = View.GONE
    }

    private val fireBaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
}