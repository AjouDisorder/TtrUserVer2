package com.example.ttruserver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.bottom.*

class BottomHeartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_heart)

        bottom_tab_home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        bottom_tab_map.setOnClickListener {
            val intent = Intent(this, BottomMapActivity::class.java)
            startActivity(intent)
        }

        bottom_tab_heart.setOnClickListener {
            val intent = Intent(this, BottomHeartActivity::class.java)
            startActivity(intent)
        }
        bottom_tab_coupon.setOnClickListener {
            val intent = Intent(this, BottomCouponActivity::class.java)
            startActivity(intent)
        }

    }
}
