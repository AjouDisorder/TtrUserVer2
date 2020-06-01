package com.example.ttruserver2.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ttruserver2.Fragment.RestaurantInfo.RestaurantInfoActivity
import com.example.ttruserver2.R
import com.example.ttruserver2.Retrofit.IMyService
import com.example.ttruserver2.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.fragment_first.view.*
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {
    lateinit var iMyService: IMyService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init retrofit api
        val retrofit = RetrofitClient.getInstance()
        iMyService = retrofit.create(IMyService::class.java)

//        val timesearch_list = arrayListOf<TimeSearchListModel>(
//
//        )
//        val timesearch_list = arrayListOf<ContentsListModel>(
//
//        )
//
//        iMyService.getMenuByTime(2020, 7, 3, 13, 30, 37.2768, 127.042314).enqueue(object : Callback<ResponseBody> {
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.d("timesearch", "time search is fail!!")
//            }
//
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                var result = response.body()?.string()
//                Log.d("timesearch", result)
//                var jsonArray = JSONArray(result)
//                println(jsonArray)
//                for (i in 0..(jsonArray.length()-1)){
//                    var jsonObject: JSONObject = jsonArray.getJSONObject(i)
//                    var jsonObject_ori_menu:JSONObject = jsonObject.getJSONObject("orginMenu")
//
//                    var _id = jsonObject.getString("_id")
//                    var title = jsonObject.getString("title")
//                    var type = jsonObject.getString("type")
//                    var discount = jsonObject.getString("discount")
//                    var oriPrice = jsonObject_ori_menu.getString("originPrice")
//                    var price = (oriPrice.toDouble() * discount.toDouble() / 100).toInt()
//
//
//                    timesearch_list.add(i, ContentsListModel(R.drawable.list1, title, price, type))
//                    //martListView.layoutManager = GridLayoutManager(this, 2)
//                }
//            }
//
//        })

        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_first, container, false)

//        val list_array = arrayListOf<ContentsListModel>(
//            ContentsListModel(R.drawable.list1, "안성찜닭", 1, "c1"),
//            ContentsListModel(R.drawable.list2, "제육볶음", 2, "c2"),
//            ContentsListModel(R.drawable.list3, "치킨", 3, "c3"),
//            ContentsListModel(R.drawable.list4, "우동", 4, "c4"),
//            ContentsListModel(R.drawable.list5, "피자", 5, "c5"),
//            ContentsListModel(R.drawable.list6, "도넛츠", 6, "c6"),
//            ContentsListModel(R.drawable.list7, "짜장면" , 7, "c7")
//        )

//        val list_adapter = FirstFragAdapter(requireContext(), list_array)
        val timesearch_list = arrayListOf<ContentsListModel>(
//            ContentsListModel(R.drawable.list1, "안성찜닭", 1, "c1")
        )
//        timesearch_list.add(ContentsListModel(R.drawable.list1, "안성찜닭", 1, "c1"))
//        val timesearch_list = ArrayList<ContentsListModel>(
//
//        )
        val list_adapter = FirstFragAdapter(requireContext(), timesearch_list)

        iMyService.getMenuByTime(2020, 7, 3, 13, 30, 37.2768, 127.042314).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("timesearch", "time search is fail!!")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                var result = response.body()?.string()
                Log.d("timesearch", result)
                var jsonArray = JSONArray(result)
                println(jsonArray)
                for (i in 0..(jsonArray.length()-1)){
                    var jsonObject: JSONObject = jsonArray.getJSONObject(i)
//                    var jsonObject_ori_menu:JSONObject = jsonObject.getJSONObject("orginMenu")

                    var _id = jsonObject.getString("_id")
                    var title = jsonObject.getString("title")
                    var type = jsonObject.getString("type")
                    var discount = jsonObject.getString("discount")
                    println(title + type + discount)
//                    var oriPrice = jsonObject_ori_menu.getString("originPrice")
//                    var price = (oriPrice.toDouble() * discount.toDouble() / 100).toInt()
//                    timesearch_list.add(ContentsListModel(R.drawable.list1, "ㅁㄴㅇㅁㄴㅇ성찜닭", 1, "c1"))
//                    timesearch_list.add(ContentsListModel(R.drawable.list1, "오레오닭", 1, "c1"))
                    timesearch_list.add(ContentsListModel(R.drawable.list1, title.toString(), discount.toInt(), type.toString()))

//                    timesearch_list.addAll(i, ContentsListModel(R.drawable.list7, title, discount.toInt(), type))

//                    timesearch_list.add(ContentsListModel(R.drawable.list1, title, discount.toInt(), type))
                    //martListView.layoutManager = GridLayoutManager(this, 2)
                }
                view.listview_first_fragment.adapter= list_adapter
            }

        })


//        view.listview_first_fragment.adapter= list_adapter

        view.listview_first_fragment.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), RestaurantInfoActivity::class.java)
            startActivity(intent)
        }
//
        return view
    }
}
