package com.example.ttruserver2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.ttruserver2.Fragment.FragmentAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_grid_info.*
import kotlinx.android.synthetic.main.custom_grid_info_tab.view.*

class GridInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_info)

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        grid_info_viewpager.adapter = fragmentAdapter

        grid_info_tab_layout.addTab(grid_info_tab_layout.newTab().setCustomView(createTabView("시간검색")))
        grid_info_tab_layout.addTab(grid_info_tab_layout.newTab().setCustomView(createTabView("치킨&피자")))
        grid_info_tab_layout.addTab(grid_info_tab_layout.newTab().setCustomView(createTabView("족발&보쌈")))
        grid_info_tab_layout.addTab(grid_info_tab_layout.newTab().setCustomView(createTabView("돈까스&일식")))
//        grid_info_tab_layout.addTab(grid_info_tab_layout.newTab().setCustomView(createTabView("세계음식")))
//        grid_info_tab_layout.addTab(grid_info_tab_layout.newTab().setCustomView(createTabView("햄버거")))
//        grid_info_tab_layout.addTab(grid_info_tab_layout.newTab().setCustomView(createTabView("밥류")))
//        grid_info_tab_layout.addTab(grid_info_tab_layout.newTab().setCustomView(createTabView("카페&빵&디저")))
//        grid_info_tab_layout.addTab(grid_info_tab_layout.newTab().setCustomView(createTabView("육고기")))
//        Toast.makeText(this, "asdfasdf", Toast.LENGTH_LONG).show()
        grid_info_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(grid_info_tab_layout))//when move page change tab
        val gridnum = intent.getStringExtra("num")
        grid_info_viewpager.currentItem = gridnum.toInt()
//        val couponInfo = intent.getStringExtra("coupon_info")
        grid_info_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{//when click tab move page
        override fun onTabReselected(p0: TabLayout.Tab?) {

        }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null){
                    grid_info_viewpager.currentItem = p0.position
//                    Toast.makeText(this@GridInfoActivity, p0.position.toString(), Toast.LENGTH_LONG).show()
//                    Toast.makeText(this, "p0.toString()", Toast.LENGTH_LONG).show()

                }
            }

        })
    }

    private fun createTabView(tabName: String) : View {
        val gridInfoTabView = LayoutInflater.from(baseContext).inflate(R.layout.custom_grid_info_tab, null)
        gridInfoTabView.custom_grid_info_tab_txt_name.text = tabName

        return gridInfoTabView
    }



}
