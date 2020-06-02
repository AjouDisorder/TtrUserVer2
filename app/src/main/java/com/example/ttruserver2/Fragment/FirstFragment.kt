package com.example.ttruserver2.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ttruserver2.Fragment.RestaurantInfo.RestaurantInfoActivity
import com.example.ttruserver2.R
import com.example.ttruserver2.Retrofit.IMyService
import com.example.ttruserver2.Retrofit.RetrofitClient
import com.example.ttruserver2.TestActivity
import kotlinx.android.synthetic.main.fragment_first.*
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

        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_first, container, false)

        val timesearch_list = arrayListOf<TimeSearchListModel>(

        )

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
                    var _originMenu = jsonObject.getString("originMenu")
                    var _originMenu_jsonObj = JSONObject(_originMenu)
                    var _menuId = jsonObject.getString("_id")
                    var _restaurantId = _originMenu_jsonObj.getString("restaurant_id")
                    var _menuTitle = jsonObject.getString("title")
                    var _startTime = jsonObject.getString("startDateObject").substring(5, 16).replace("T", " ")
                    var _endTime = jsonObject.getString("endDateObject").substring(5, 16).replace("T", " ")
                    var _menuDistance = jsonObject.getString("distance").toDouble()
                    var _menuDistance2 = String.format("%.2f", _menuDistance)
                    var _quantity = jsonObject.getString("quantity").toInt()
                    var _discount = jsonObject.getString("discount").toInt()
                    var _originPrice = _originMenu_jsonObj.getString("originPrice").toInt()
                    var _discountedPrice = (_originPrice*_discount/100).toInt()
//                    var _discountedPrice = jsonObject.getString("title")
//                    var _originPrice = jsonObject.getString("title")
                    var _restaurantTitle = jsonObject.getString("restaurantTitle")
                    var _method = jsonObject.getString("method")
                    var _title = _menuTitle + "/" + _restaurantTitle

//                    println(title + type + discount)
                    timesearch_list.add(TimeSearchListModel(R.drawable.list1, _title, _startTime, _endTime, _menuDistance2.toDouble(), _quantity, _discount, _originPrice, _discountedPrice, _menuId, _restaurantId, _restaurantTitle))
                }
                view.listview_first_fragment.adapter= list_adapter
            }

        })
//        view.listview_first_fragment.adapter= list_adapter

        view.listview_first_fragment.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), TestActivity::class.java)
            var dataTimeMenu = timesearch_list[position]

//            Toast.makeText(activity, (parent.toString() + "//"+ view.toString() + "//"+ position.toString() + "//"+ id.toString()).toString(), Toast.LENGTH_LONG).show()
//            Log.d("qwerqwer", (parent.toString() + "//"+ view.toString() + "//"+ position.toString() + "//"+ id.toString()).toString())
//            Log.d("qwerqwer", qwer.toString())
            Log.d("dataTimeMenu", dataTimeMenu.toString())


//            Toast.makeText(this, (parent.toString() + view.toString() + position.toString() + id.toString()).toString(), Toast.LENGTH_LONG)
//            intent.putExtra("menuId", dataTimeMenu.menuId.toString())
//            intent.putExtra("restaurantId", dataTimeMenu.restaurantId.toString())
//            intent.putExtra("menuTitle", dataTimeMenu.menuTitle.toString())
//            intent.putExtra("restaurantTitle", dataTimeMenu.restaurantTitle.toString())
//            intent.putExtra("quantity", dataTimeMenu.quantity.toString())

            intent.putExtra("restaurantTitle", dataTimeMenu.restaurantTitle.toString())
            intent.putExtra("menuTitle", dataTimeMenu.menuTitle.toString())
            intent.putExtra("originPrice", dataTimeMenu.originPrice.toString())
            intent.putExtra("discountedPrice", dataTimeMenu.discountedPrice.toString())
            intent.putExtra("discount", dataTimeMenu.discount.toString())
            intent.putExtra("menuId", dataTimeMenu.menuId.toString())

            startActivity(intent)
        }
//
        return view
    }
}
