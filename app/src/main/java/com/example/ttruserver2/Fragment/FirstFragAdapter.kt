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