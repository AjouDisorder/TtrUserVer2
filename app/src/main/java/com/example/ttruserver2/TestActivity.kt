package com.example.ttruserver2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.ttruserver2.Retrofit.IMyService
import com.example.ttruserver2.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_test.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestActivity : AppCompatActivity() {

    var count = 1
    lateinit var iMyService: IMyService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val retrofit = RetrofitClient.getInstance()
        iMyService = retrofit.create(IMyService::class.java)

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
        var menuId = intent.getStringExtra("menuId")

        val discount_menu_id = menuId
        var discount_quantity = textView25.text.toString().toInt()
        val discount_user_id = "5ed4c6fb1ee7260a21652817"
        var discount_totalPrice = discountedPrice.toInt() * discount_quantity
        val discount_method = "forhere"
        val discount_bosstalk = "boss message test cham 2 seul"

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
        pay_button_test.setOnClickListener {
            discount_quantity = textView25.text.toString().toInt()
            discount_totalPrice = discountedPrice.toInt() * discount_quantity
            iMyService.createTicket(discount_menu_id, discount_quantity, discount_user_id,
                discount_totalPrice, discount_method, discount_bosstalk).enqueue(object :
                Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("create_ticket:  ", "create_ticket is fail!!")
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    var result = response.body()?.string()
                    Log.d("create_ticket_result:  ", result)
                    Toast.makeText(this@TestActivity, "결제 성공!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
        }
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
