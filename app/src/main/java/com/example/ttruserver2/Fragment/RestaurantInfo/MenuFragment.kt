package com.example.ttruserver2.Fragment.RestaurantInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ttruserver2.Fragment.ContentsListModel

import com.example.ttruserver2.R
import kotlinx.android.synthetic.main.fragment_menu.view.*

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {

    val menu_list1 = ArrayList<String>()
    val menu_list2 = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_menu, container, false)

        val list_array = arrayListOf<ContentsListModel>(
            ContentsListModel(R.drawable.list9, "제육볶음", 8000, "한식"),
            ContentsListModel(R.drawable.list8, "치킨", 16000, "치킨"),
            ContentsListModel(R.drawable.list6, "우동", 6000, "면"),
            ContentsListModel(R.drawable.list7, "짜장면", 5500, "면")
        )
        menu_list1.add("a1")
        menu_list1.add("b1")
        menu_list1.add("c1")

        menu_list2.add("a2")
        menu_list2.add("b2")
        menu_list2.add("c2")

        val menu_list_adapter = MenuListAdapter(requireContext(), list_array)
//        val menu_list_adapter = MenuListAdapter(requireContext(), menu_list1, menu_list2)
        view.menu_listview.adapter = menu_list_adapter

        return view
    }

}
