package com.example.ttruserver2.BottomTab.Coupon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.ttruserver2.R

class BottomCouponListViewAdapter (val context: Context, val list: ArrayList<BottomCouponContentsListModel>):BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : ViewHolder

        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.coupon_food_info_listviev, null)
            holder = ViewHolder()
            holder.coupon_food_img = view.findViewById(R.id.bottom_coupon_food_img)
            holder.coupon_food_title = view.findViewById(R.id.bottom_coupon_food_name)
            holder.coupon_food_price = view.findViewById(R.id.bottom_coupon_food_price)
            holder.coupon_food_category = view.findViewById(R.id.bottom_coupon_restaurant_name)

            view.tag = holder
        }else{
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        val item = list[position]
        holder.coupon_food_img?.setImageResource(item.image)
        holder.coupon_food_title?.text = item.menuName
        holder.coupon_food_price?.text = item.totalPrice.toString()
        holder.coupon_food_category?.text = item.address

        return view
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

    private class ViewHolder{
        var coupon_food_img : ImageView? = null
        var coupon_food_title : TextView? = null
        var coupon_food_price : TextView? = null
        var coupon_food_category : TextView? = null
    }

}