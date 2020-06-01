package com.example.ttruserver2.UseFirebaseDB

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ttruserver2.R
import com.google.firebase.auth.FirebaseAuth



class FBSignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f_b_sign_up)

        auth = FirebaseAuth.getInstance()



    }
}
