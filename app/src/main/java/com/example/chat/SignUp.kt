package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private  lateinit var edtName: EditText
    private  lateinit var edtEmail: EditText
    private  lateinit var edtPassword: EditText
    private  lateinit var btnSignup: Button
    private  lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignup = findViewById(R.id.btnSignup)

        btnSignup.setOnClickListener{
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signup(email, password)
        }
    }

    private fun signup(email:String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@SignUp, MainActivity:: class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignUp, "Some Error Occurred!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}