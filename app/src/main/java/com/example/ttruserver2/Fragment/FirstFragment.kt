package com.example.ttruserver2.Fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ttruserver2.R
import com.example.ttruserver2.Retrofit.IMyService
import com.example.ttruserver2.Retrofit.RetrofitClient
import com.example.ttruserver2.SearchedMenuDetailActivity
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

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

        val menuTypeToIcons = mapOf("치킨&피자" to R.drawable.menu_chickenpizza, "족발&보쌈" to R.drawable.menu_jokbal,
            "돈까스&일식" to R.drawable.menu_japan, "세계음식" to R.drawable.menu_nation, "햄버거" to R.drawable.menu_hambur,
            "밥류" to R.drawable.menu_rice, "카페&빵&디저트" to R.drawable.menu_cafe, "육고기" to R.drawable.menu_meat,
            "면" to R.drawable.menu_noodle, "분식&야식" to R.drawable.menu_snack, "찜&탕&찌개" to R.drawable.menu_soup,
            "반찬&과일" to R.drawable.menu_fruit, "떡&기타" to R.drawable.menu_ricecake,
            "샐러드&다이어트" to R.drawable.menu_salad, "편의점" to R.drawable.menu_convstore)



        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_first, container, false)

        var cal:Calendar = Calendar.getInstance()
        var y = cal.get(Calendar.YEAR)
        var m = cal.get(Calendar.MONTH)
        var d = cal.get(Calendar.DAY_OF_MONTH)
        var c:Calendar= Calendar.getInstance()
        var hh=c.get(Calendar.HOUR_OF_DAY)
        var mm=c.get(Calendar.MINUTE)

        view.dateTv.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                dateTv.setText(""+year+"/"+(month+1)+"/" +dayOfMonth)
            }, y, m, d)
            dpd.show()
        }
        view.timeTv.setOnClickListener {
            val timePickerDialog: TimePickerDialog =TimePickerDialog(requireContext(),TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeTv.setText( ""+hourOfDay + ":" + minute);
            },hh,mm,true)
            timePickerDialog.show()
        }

        var timesearch_list = arrayListOf<TimeSearchListModel>(

        )

        val list_adapter = FirstFragAdapter(requireContext(), timesearch_list)

        iMyService.getMenuByTime(2020, 11, 5, 23, 23, 37.2768, 127.042314).enqueue(object : Callback<ResponseBody> {
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
                    var _type = jsonObject.getString("type")

//                    println(title + type + discount)
                    val pciture =menuTypeToIcons.get(_type)
                    if (pciture != null) {
                        timesearch_list.add(TimeSearchListModel(pciture, _title, _startTime, _endTime, _menuDistance2.toDouble(), _quantity, _discount, _originPrice, _discountedPrice, _menuId, _restaurantId, _restaurantTitle, _type, _method))
                    }
//                    timesearch_list.add(TimeSearchListModel(R.drawable.menu_chickenpizza, _title, _startTime, _endTime, _menuDistance2.toDouble(), _quantity, _discount, _originPrice, _discountedPrice, _menuId, _restaurantId, _restaurantTitle, _type))
                }
                view.listview_first_fragment.adapter= list_adapter
            }

        })

        view.time_search_refresh.setOnClickListener {
            var date_data = dateTv.text.toString().split("/")
            var time_data = timeTv.text.toString().split(":")
            Log.d("qwert", date_data.toString()+time_data.toString())
            var _year = date_data[0]
            var _month = date_data[1]
            var _day = date_data[2]
            var _hour = time_data[0]
            var _minute = time_data[1]
            Toast.makeText(requireContext(), ""+ _year+"/"+_month+"/" +_day+"..."+""+_hour + ":" + _minute+"",Toast.LENGTH_SHORT).show()
            val timesearch_list2 = arrayListOf<TimeSearchListModel>(

            )

            val list_adapter = FirstFragAdapter(requireContext(), timesearch_list2)

            iMyService.getMenuByTime(_year.toInt(), _month.toInt(), _day.toInt(), _hour.toInt(), _minute.toInt(), 37.2768, 127.042314).enqueue(object : Callback<ResponseBody> {
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
                        var _type = jsonObject.getString("type")

//                    println(title + type + discount)
                        val pciture =menuTypeToIcons.get(_type)
                        if (pciture != null) {
                            timesearch_list2.add(TimeSearchListModel(pciture, _title, _startTime, _endTime, _menuDistance2.toDouble(), _quantity, _discount, _originPrice, _discountedPrice, _menuId, _restaurantId, _restaurantTitle, _type, _method))
                        }
//                    timesearch_list.add(TimeSearchListModel(R.drawable.menu_chickenpizza, _title, _startTime, _endTime, _menuDistance2.toDouble(), _quantity, _discount, _originPrice, _discountedPrice, _menuId, _restaurantId, _restaurantTitle, _type))
                    }
                    timesearch_list = timesearch_list2
                    view.listview_first_fragment.adapter= list_adapter
                }

            })
        }
//        view.listview_first_fragment.adapter= list_adapter
        view.listview_first_fragment.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), SearchedMenuDetailActivity::class.java)
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

            intent.putExtra("type", dataTimeMenu.type.toString())
            intent.putExtra("menuId", dataTimeMenu.menuId.toString())
            intent.putExtra("method", dataTimeMenu.method.toString())
            intent.putExtra("quantity", dataTimeMenu.quantity.toString())



//            intent.putExtra("timesearch_list", timesearch_list)

            startActivity(intent)
        }
//
        return view
    }
}
