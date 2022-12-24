package com.hari.colorit.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.hari.colorit.databinding.ActivityLoginBinding
import com.hari.colorit.ui.utils.Extensions.isValidEmail

class Login: AppCompatActivity() {

    private lateinit var view: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(view.root)

        view.okBtnLogin.setOnClickListener{

            login(view.emailEtLogin.text.toString(), view.passwordEtLogin.text.toString())
        }

        view.registerTxtLogin.setOnClickListener {
//            Intent(this@Login, Register::class.java).also { startActivity(intent)}
            startActivity(Intent(this, Register::class.java))
        }
    }

    private fun login(email: String, password: String){

        view.progressBarLogin.visibility = View.VISIBLE
        if(!email.isValidEmail()){
            Snackbar.make(view.root, "Enter valid email to login..", Snackbar.LENGTH_SHORT).show()
        }else if (password.isEmpty()){
            Snackbar.make(view.root, "Enter your password to login..", Snackbar.LENGTH_SHORT).show()
        }else{
            fireBaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                task -> if(task.isSuccessful){
                startActivity(Intent(this, Dashboard::class.java))
                finish()
                }else{
                    Snackbar.make(view.root, "Provided email not exist or incorrect password. Please check", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
        view.progressBarLogin.visibility = View.GONE
    }

    private val fireBaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
}