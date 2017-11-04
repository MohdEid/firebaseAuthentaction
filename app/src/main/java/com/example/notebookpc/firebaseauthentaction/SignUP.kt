package com.example.notebookpc.firebaseauthentaction

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class SignUP : AppCompatActivity() {

    lateinit var btnSignUP: Button
    lateinit var btnLogin:TextView
    lateinit var btnForgotPass:TextView
    lateinit var input_email:EditText
    lateinit var input_pass:EditText

    lateinit var activity_sign_up:RelativeLayout

    private lateinit var auth:FirebaseAuth
    lateinit var snackBar:Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSignUP = findViewById(R.id.signUp_btn_register)
        btnLogin=findViewById(R.id.signUp_btn_Login)
        btnForgotPass=findViewById(R.id.signUp_btn_forgot_password)
        input_email=findViewById(R.id.signUp_input_email)
        input_pass=findViewById(R.id.signUp_input_pass)
        activity_sign_up=findViewById(R.id.signUp_activity)

        btnSignUP.setOnClickListener {
            signUpUser(input_email.toString(),input_pass.toString())
        }
        btnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        btnForgotPass.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
            finish()
        }

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            startActivity(Intent(this, DashBoard::class.java))

        }
    }
    private fun SignUpUser(email:String,password:String){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,OnCompleteListener <AuthResult>?() {
            public fun onComplete (task:Task<AuthResult>?){
                if (!task!!.isSuccessful){
                    
                }
            }
        })
    }
}
