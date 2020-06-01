package com.example.ttruserver2.BottomTab.Coupon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ttruserver2.BottomTab.*
import com.example.ttruserver2.BottomTab.FavoriteRestaurant.BottomFavoriteRestaurantActivity
import com.example.ttruserver2.MainActivity
import com.example.ttruserver2.R
import com.example.ttruserver2.Retrofit.IMyService
import com.example.ttruserver2.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_bottom_coupon.*
import kotlinx.android.synthetic.main.bottom.*
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BottomCouponActivity : AppCompatActivity() {

    lateinit var iMyService: IMyService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_coupon)

        //init retrofit api
        val retrofit = RetrofitClient.getInstance()
        iMyService = retrofit.create(IMyService::class.java)

        val retro_couponlist = arrayListOf<BottomCouponActivity>(

        )

        iMyService.getMenuListOfRestaurant("5ed3d06ba0db991f9c01f18a").enqueue(object : Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                println("실패@@")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                var result = response.body()?.string()
                println(result)

                var jsonArray = JSONArray(result)
                Log.d("restaurant_menu", jsonArray.toString())
            }

        })


        val coupon_list_array = arrayListOf<BottomCouponContentsListModel>(
            BottomCouponContentsListModel(
                R.drawable.list1,
                "안성찜닭",
                10000,
                "내찜닭"
            ),
            BottomCouponContentsListModel(
                R.drawable.list2,
                "제육볶음",
                6000,
                "미스터쉐프"
            ),
            BottomCouponContentsListModel(
                R.drawable.list3,
                "치킨",
                18000,
                "비비큐"
            ),
            BottomCouponContentsListModel(
                R.drawable.list4,
                "우동",
                4500,
                "역전우동"
            ),
            BottomCouponContentsListModel(
                R.drawable.list5,
                "피자",
                23000,
                "미스터피자"
            ),
            BottomCouponContentsListModel(
                R.drawable.list6,
                "도넛츠",
                6000,
                "크리스피도넛"
            ),
            BottomCouponContentsListModel(
                R.drawable.list7,
                "짜장면",
                7000,
                "홍콩반점"
            )
        )

        val bottomCouponAdapter =
            BottomCouponListViewAdapter(
                this,
                coupon_list_array
            )
        bottom_coupon_activity_listview.adapter = bottomCouponAdapter

        bottom_coupon_activity_listview.setOnItemClickListener { parent, view, position, id ->
            val item = bottomCouponAdapter.getItem(position)
            Toast.makeText(this, item.toString(), Toast.LENGTH_LONG).show()
            val intent = Intent(this, BottomCouponInfoActivity::class.java)
            intent.putExtra("coupon_info", item.toString())
            startActivity(intent)
        }



        bottom_tab_home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        bottom_tab_favorite_restaurant.setOnClickListener {
            val intent = Intent(this, BottomFavoriteRestaurantActivity::class.java)
            startActivity(intent)
        }

        bottom_tab_coupon.setOnClickListener {
            val intent = Intent(this, BottomCouponActivity::class.java)
            startActivity(intent)
        }
        bottom_tab_my_info.setOnClickListener {
            val intent = Intent(this, BottomMyInfoActivity::class.java)
            startActivity(intent)
        }

    }
}
