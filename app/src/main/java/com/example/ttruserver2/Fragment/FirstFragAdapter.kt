package com.example.ttruserver2.Fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.ttruserver2.R

class FirstFragAdapter(val context: Context, val list: ArrayList<TimeSearchListModel>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : ViewHolder

        if(convertView==null){
            view = LayoutInflater.from(context).inflate(R.layout.minseock_menu_list_item, null)
            holder = ViewHolder()
            holder.menuPicture = view.findViewById(R.id.iv_menuPicture)
            holder.menuTitle = view.findViewById(R.id.tv_menuTitle)
            holder.startTime = view.findViewById(R.id.tv_startTime)
            holder.endTime = view.findViewById(R.id.tv_endTime)
            holder.menuDistance = view.findViewById(R.id.tv_menuDistance)
            holder.quantity = view.findViewById(R.id.tv_quantity)
            holder.discount = view.findViewById(R.id.tv_discount)
            holder.originPrice = view.findViewById(R.id.tv_originPrice)
            holder.discountedPrice = view.findViewById(R.id.tv_discountedPrice)

            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        val item = list[position]
        val menuTypeToIcons = mapOf("치킨&피자" to R.drawable.menu_chickenpizza, "족발&보쌈" to R.drawable.menu_jokbal,
            "돈까스&일식" to R.drawable.menu_japan, "세계음식" to R.drawable.menu_nation, "햄버거" to R.drawable.menu_hambur,
            "밥류" to R.drawable.menu_rice, "카페&빵&디저트" to R.drawable.menu_cafe, "육고기" to R.drawable.menu_meat,
            "면" to R.drawable.menu_noodle, "분식&야식" to R.drawable.menu_snack, "찜&탕&찌개" to R.drawable.menu_soup,
            "반찬&과일" to R.drawable.menu_fruit, "떡&기타" to R.drawable.menu_ricecake,
            "샐러드&다이어트" to R.drawable.menu_salad, "편의점" to R.drawable.menu_convstore)
        val image_test = menuTypeToIcons[item.type]
        holder.menuPicture?.setImageResource(item.menuPicture)
        holder.menuTitle?.text = item.menuTitle
        holder.startTime?.text = item.startTime
        holder.endTime?.text = item.endTime
        holder.menuDistance?.text = item.menuDistance.toString()
        holder.quantity?.text = item.quantity.toString()
        holder.discount?.text = item.discount.toString()
        holder.originPrice?.text = item.originPrice.toString()
        holder.discountedPrice?.text = item.discountedPrice.toString()

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
        var menuPicture : ImageView? = null
        var menuTitle : TextView? = null
        var startTime : TextView? = null
        var endTime : TextView? = null
        var menuDistance : TextView? = null
        var quantity : TextView? = null
        var discount : TextView? = null
        var originPrice : TextView? = null
        var discountedPrice : TextView? = null
    }
}