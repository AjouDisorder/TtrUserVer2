package com.example.ttruserver2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import com.example.ttruserver.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    internal lateinit var viewpager : ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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




        viewpager = findViewById(R.id.main_ad_viewpager) as ViewPager

        val adapter = ViewPagerAdapter(this)
        viewpager.adapter = adapter
    }
}
