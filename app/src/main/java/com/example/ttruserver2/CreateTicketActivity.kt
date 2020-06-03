package com.example.ttruserver2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ttruserver2.Retrofit.IMyService
import com.example.ttruserver2.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_create_ticket.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateTicketActivity : AppCompatActivity() {
    lateinit var iMyService: IMyService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ticket)

        val retrofit = RetrofitClient.getInstance()
        iMyService = retrofit.create(IMyService::class.java)

        var restaurantTitle = intent.getStringExtra("restaurantTitle")
        var menuTitle = intent.getStringExtra("menuTitle")
        var originPrice = intent.getStringExtra("originPrice").toInt()
        var discountedPrice = intent.getStringExtra("discountedPrice").toInt()
        var discount = intent.getStringExtra("discount").toInt()
        var menuId = intent.getStringExtra("menuId")
        var type = intent.getStringExtra("type")
        var method = intent.getStringExtra("method")
        var quantity = intent.getStringExtra("quantity").toInt()


        if (method == "forhere"){
            rb_takeout.visibility = View.INVISIBLE
        }else if(method == "takeout"){
            rb_forhere.visibility = View.INVISIBLE
        }

        val availableQuantity = quantity.toInt()
        var want_quantity = 1

        tv_menuTitleInPay.setText(menuTitle)
        tv_totalPriceInPay.setText(discountedPrice.toString())

        btn_plusQuantity.setOnClickListener{
            tv_ticketQuantity.text = (want_quantity + 1).toString()
            want_quantity += 1
            if (want_quantity == availableQuantity){
                btn_plusQuantity.isEnabled = false
            }else if(want_quantity > 1){
                btn_minusQuantity.isEnabled = true
            }
            tv_totalPriceInPay.text = (discountedPrice.toInt() * want_quantity).toString()
        }
        btn_minusQuantity.setOnClickListener {
            tv_ticketQuantity.text = (want_quantity - 1).toString()
            want_quantity -= 1
            if (want_quantity == 1){
                btn_minusQuantity.isEnabled = false
            }else if(want_quantity < availableQuantity){
                btn_plusQuantity.isEnabled = true
            }
            tv_totalPriceInPay.text = (discountedPrice * want_quantity).toString()
        }



        btn_createTicket.setOnClickListener {
            if(tv_messageForBoss.text.toString() == null){
                tv_messageForBoss.setText("특이사항 없음")
            }
            iMyService.createTicket(menuId, want_quantity, UserData.getOid().toString(), tv_totalPriceInPay.text.toString().toInt(),
                method, tv_messageForBoss.text.toString(), restaurantTitle).enqueue(object : Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d("create_ticket:  ", "create_ticket is fail!!")
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    var result = response.body()?.string()
                    Log.d("create_ticket_result:  ", result)
                    Toast.makeText(this@CreateTicketActivity, "결제 성공!", Toast.LENGTH_SHORT).show()
                    finish()                }

            })

        }

//        btn_toPayment.setOnClickListener {
            //            discount_quantity = textView25.text.toString().toInt()
//            discount_totalPrice = discountedPrice.toInt() * discount_quantity
//            iMyService.createTicket(discount_menu_id, discount_quantity, discount_user_id,
//                discount_totalPrice, discount_method, discount_bosstalk).enqueue(object :
//                Callback<ResponseBody> {
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    Log.d("create_ticket:  ", "create_ticket is fail!!")
//                }
//
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    var result = response.body()?.string()
//                    Log.d("create_ticket_result:  ", result)
//                    Toast.makeText(this@SearchedMenuDetailActivity, "결제 성공!", Toast.LENGTH_SHORT).show()
//                    finish()
//                }
//            })
//        }


    }
}
