package com.example.ttruserver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.ttruserver2.Fragment.TimeSearchListModel
import com.example.ttruserver2.Retrofit.IMyService
import com.example.ttruserver2.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.searched_menu_detail.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchedMenuDetailActivity : AppCompatActivity() {

//    lateinit var iMyService: IMyService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searched_menu_detail)

//        val retrofit = RetrofitClient.getInstance()
//        iMyService = retrofit.create(IMyService::class.java)

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
        var type = intent.getStringExtra("type")
        var method = intent.getStringExtra("method")
        var quantity = intent.getStringExtra("quantity")

        val menuTypeToIcons = mapOf("치킨&피자" to R.drawable.menu_chickenpizza, "족발&보쌈" to R.drawable.menu_jokbal,
            "돈까스&일식" to R.drawable.menu_japan, "세계음식" to R.drawable.menu_nation, "햄버거" to R.drawable.menu_hambur,
            "밥류" to R.drawable.menu_rice, "카페&빵&디저트" to R.drawable.menu_cafe, "육고기" to R.drawable.menu_meat,
            "면" to R.drawable.menu_noodle, "분식&야식" to R.drawable.menu_snack, "찜&탕&찌개" to R.drawable.menu_soup,
            "반찬&과일" to R.drawable.menu_fruit, "떡&기타" to R.drawable.menu_ricecake,
            "샐러드&다이어트" to R.drawable.menu_salad, "편의점" to R.drawable.menu_convstore)
        val pciture = menuTypeToIcons.get(type)
        val discount_menu_id = menuId
//        val discount_user_id = "5ed66c5324eca172f73395e4"
        val discount_user_id = UserData.getOid().toString()

        val discount_method = "forhere"
        val discount_bosstalk = "boss message test"

//        test_textView1.setText(menuId)
//        test_textView2.setText(restaurantId)
//        test_textView3.setText(menuTitle)
//        test_textView4.setText(restaurantTitle)
//        test_textView5.setText(quantity)
        if (pciture != null) {
            searched_menu_pic.setImageResource(pciture)
        }
        tv_restaurantTitleInMenu.setText(restaurantTitle)
        tv_titleInMenu.setText(menuTitle.split("/")[0])
        tv_originPriceInMenu.setText(originPrice)
        tv_discountPriceInMenu.setText(discountedPrice)
        tv_discountInMenu.setText(discount)

//        val asdf = intent.getSerializableExtra("timesearch_list")
//        println(asdf)
        btn_toPayment.setOnClickListener {
            val intent = Intent(this, CreateTicketActivity::class.java)

            intent.putExtra("restaurantTitle", restaurantTitle)
            intent.putExtra("menuTitle", menuTitle)
            intent.putExtra("originPrice", originPrice)
            intent.putExtra("discountedPrice", discountedPrice)
            intent.putExtra("discount", discount)
            intent.putExtra("type", type)
            intent.putExtra("menuId", menuId)
            intent.putExtra("method", method)
            intent.putExtra("quantity", quantity)

            startActivity(intent)
            finish()
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
