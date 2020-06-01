package com.example.ttruserver2.UseFirebaseDB

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ttruserver2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_f_b_sign_up_info.*

class FBSignUpInfoActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val fbdb = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f_b_sign_up_info)

        auth = FirebaseAuth.getInstance()

        fb_signup_complete_button.setOnClickListener {
            val user = hashMapOf(
                "nickname" to fb_signup_nickname_area.text.toString()
            )

            fbdb.collection("users")
                .document(auth.currentUser?.uid.toString())
                .set(user)
                .addOnSuccessListener {
                    Log.d("FBSignUpActivity", "DocumentSnapshot successfully written!")
                    val intent = Intent(this, FBLoginActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { e -> Log.w("FBSignUpActivity", "Error writing document", e) }
        }
    }
}
