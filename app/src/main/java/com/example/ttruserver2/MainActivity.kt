package com.example.ttruserver2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.ttruserver.ViewPagerAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom.*
import kotlinx.android.synthetic.main.content_main.*

//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar //toolbar is androidx.appcompat.widget
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    internal lateinit var viewpager : ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//navigation
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
//navigation
        val img = arrayOf(
            R.drawable.ai,
            R.drawable.css,
            R.drawable.id,
            R.drawable.jpg,
            R.drawable.js,
            R.drawable.mp4,
            R.drawable.pdf,
            R.drawable.php,
            R.drawable.png,
            R.drawable.psd,
            R.drawable.tiff
        )
        val img2 = arrayOf(
            R.drawable.tiff,
            R.drawable.psd,
            R.drawable.png,
            R.drawable.php,
            R.drawable.pdf,
            R.drawable.mp4,
            R.drawable.js,
            R.drawable.jpg,
            R.drawable.id,
            R.drawable.css,
            R.drawable.ai
        )
        val text = arrayOf(
            "ai",
            "css",
            "html",
            "id",
            "jpg",
            "js",
            "mp4",
            "pdf",
            "php",
            "png",
            "psd",
            "tiff"
        )
        val text2 = arrayOf(//grid contents
            "tiff",
            "psd",
            "png",
            "php",
            "pdf",
            "mp4",
            "js",
            "jpg",
            "id",
            "html",
            "css",
            "ai"
        )

//        val

        val gridviewAdapter = GridViewAdapter(this, img, text)//conveying img and text to gridviewadapter
        main_gridview.adapter = gridviewAdapter

        discount_button.setOnClickListener {
            val gridviewAdapter = GridViewAdapter(this, img, text)//conveying img and text to gridviewadapter
            main_gridview.adapter = gridviewAdapter
        }
        restaurant_button.setOnClickListener {
            val gridviewAdapter = GridViewAdapter(this, img2, text2)//conveying img and text to gridviewadapter
            main_gridview.adapter = gridviewAdapter
        }

        main_gridview.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, GridInfoActivity::class.java)
            startActivity(intent)
        }

        bottom_tab_home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        bottom_tab_map.setOnClickListener {
            val intent = Intent(this, BottomMapActivity::class.java)
            startActivity(intent)
        }

        bottom_tab_heart.setOnClickListener {
            val intent = Intent(this, BottomHeartActivity::class.java)
            startActivity(intent)
        }
        bottom_tab_coupon.setOnClickListener {
            val intent = Intent(this, BottomCouponActivity::class.java)
            startActivity(intent)
        }

        viewpager = findViewById(R.id.main_ad_viewpager) as ViewPager

        val adapter = ViewPagerAdapter(this)
        viewpager.adapter = adapter

//        user_profile.setOnClickListener {
//            Toast.makeText(this, "user_profile clicked", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, LogInActivity::class.java)
//            startActivity(intent)
//        }

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.user_profile-> {
                Toast.makeText(this, "user_profile clicked", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, LogInActivity::class.java)
//                startActivity(intent)
            }
            R.id.navigation_home-> {
                Toast.makeText(this, "navigation_home clicked!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LogInActivity::class.java)
                val intent2 = Intent(this, TestActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_bike -> {
                Toast.makeText(this, "navigation_bike clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_bus -> {
                Toast.makeText(this, "navigation_bus clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_airplane -> {
                Toast.makeText(this, "navigation_airplane clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_login -> {
                Toast.makeText(this, "navigation_login clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_profile -> {
                Toast.makeText(this, "navigation_profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_logout -> {
                Toast.makeText(this, "navigation_logout clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_cloud -> {
                Toast.makeText(this, "navigation_cloud clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.navigation_wifi -> {
                Toast.makeText(this, "navigation_wifi clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
//drawer 켜진 상태에서 뒤로가기 누르면 메인 화면에서 뒤로가기 누른 처리돼서 앱종료인데 이거 추가하면 네비게이션 드로우만 없어짐
    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }
}
