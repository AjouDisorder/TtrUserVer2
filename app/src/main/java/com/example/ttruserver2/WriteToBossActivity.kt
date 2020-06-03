package com.example.ttruserver2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ttruserver2.Retrofit.IMyService
import com.example.ttruserver2.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_write_to_boss.*
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WriteToBossActivity : AppCompatActivity() {
    lateinit var iMyService: IMyService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_to_boss)

        val retrofit = RetrofitClient.getInstance()
        iMyService = retrofit.create(IMyService::class.java)

        val discount_menu_id = "5ed4f3ecaf3e974c5b6c5c4a"
        val discount_quantity = 1
        val discount_user_id = "5ed4c6fb1ee7260a21652817"
        val discount_totalPrice = 2000
        val discount_method = "forhere"
        val discount_bosstalk = "boss message test cham 2 seul"

//        write_to_boss_writing_button.setOnClickListener {
//            iMyService.createTicket(discount_menu_id, discount_quantity, discount_user_id,
//                discount_totalPrice, discount_method, discount_bosstalk).enqueue(object : Callback<ResponseBody>{
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    Log.d("create_ticket:  ", "create_ticket is fail!!")
//                }
//
//                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                    var result = response.body()?.string()
//                    Log.d("create_ticket_result:  ", result)
////                    var jsonArray = JSONArray(result)
////                    println(jsonArray)
////                for (i in 0..(jsonArray.length()-1)){
////                    var jsonObject: JSONObject = jsonArray.getJSONObject(i)
////                    var _id = jsonObject.getString("_id")
////                    var title = jsonObject.getString("title")
////                    var type = jsonObject.getString("type")
////                    var discount = jsonObject.getString("discount")
////                    println(title + type + discount)
////                }
//                }
//
//            })
//        }
    }
}