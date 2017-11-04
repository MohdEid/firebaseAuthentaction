package com.example.notebookpc.firebaseauthentaction

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var btnLogin: Button
    lateinit var email_Input: EditText
    lateinit var pass_Input: EditText
    lateinit var btnForgotPassword: TextView
    lateinit var btnSignUP: TextView
    lateinit var activity_Main: RelativeLayout

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.login_btn)
        email_Input = findViewById(R.id.login_input_email)
        pass_Input = findViewById(R.id.login_input_pass)
        btnSignUP = findViewById(R.id.signUp)
        btnForgotPassword = findViewById(R.id.frgt_password)
        activity_Main = findViewById(R.id.activity_main)

        btnSignUP.setOnClickListener {
            startActivity(Intent(this, SignUP::class.java))
            finish()
        }
        btnForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
            finish()
        }
        btnLogin.setOnClickListener {
            loginUser(login_input_email.toString(), login_input_pass.toString())
        }

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            startActivity(Intent(this, DashBoard::class.java))

        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(OnCompleteListener<AuthResult>() {
            fun onComplete(task: Task<AuthResult>)
            {
                if (task.isSuccessful)
                {
                    if (password.length < 6)
                    {
                        var snackBar = Snackbar.make(activity_main, "Password must be over 6", Snackbar.LENGTH_SHORT)
                        snackBar.show()
                    } else {
                        startActivity(Intent(this, DashBoard::class.java))
                    }
                }
            }
        })

    }

}
