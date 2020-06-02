package com.example.ttruserver2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

//        var menuId = intent.getStringExtra("menuId")
//        var restaurantId = intent.getStringExtra("restaurantId")
//        var menuTitle = intent.getStringExtra("menuTitle")
//        var restaurantTitle = intent.getStringExtra("restaurantTitle")
//        var quantity = intent.getStringExtra("quantity")

        var restaurantTitle = intent.getStringExtra("restaurantTitle")
        var menuTitle = intent.getStringExtra("menuTitle")
        var originPrice = intent.getStringExtra("originPrice")
        var discountedPrice = intent.getStringExtra("discountedPrice")
        var discount = intent.getStringExtra("discount")

//        val discount_menu_id = "5ed4f3ecaf3e974c5b6c5c4a"
//        val discount_quantity = 1
//        val discount_user_id = "5ed4c6fb1ee7260a21652817"
//        val discount_totalPrice = 2000
//        val discount_method = "forhere"
//        val discount_bosstalk = "boss message test cham 2 seul"

//        test_textView1.setText(menuId)
//        test_textView2.setText(restaurantId)
//        test_textView3.setText(menuTitle)
//        test_textView4.setText(restaurantTitle)
//        test_textView5.setText(quantity)
        detailedMenuName.setText(restaurantTitle)
        searched_menu_title.setText(menuTitle.split("/")[0])
        textView13.setText(originPrice)
        textView21.setText(discountedPrice)
        textView23.setText(discount)

        Toast.makeText(this, count.toString(), Toast.LENGTH_SHORT).show()
    }

    fun plusQ(view: View) {
        count++
        val countPlus = findViewById(R.id.textView25) as TextView
        countPlus.text = "$count"
    }
    fun minusQ(view: View) {
        count--
        val countMinus = findViewById(R.id.textView25) as TextView
        countMinus.text = "$count"
    }
}
