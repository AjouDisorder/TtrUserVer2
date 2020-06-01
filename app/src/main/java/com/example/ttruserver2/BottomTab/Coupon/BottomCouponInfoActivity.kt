package com.example.ttruserver2.BottomTab.Coupon

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ttruserver2.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_bottom_coupon_info.*

class BottomCouponInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_coupon_info)

        val couponInfo = intent.getStringExtra("coupon_info")

        Toast.makeText(this, "넘어온 정보: " + couponInfo, Toast.LENGTH_LONG).show()
        val asd = couponInfo.split(',')
        val qwe = asd[3].split("=",")")
        Log.d("asd", qwe[1].toString())
//        bottom_coupon_info_lecture_text.setText()
        bottom_coupon_info_lecture_text.setText(qwe[1])


        val multiFormatWriter = MultiFormatWriter()
        val bitMatrix: BitMatrix = multiFormatWriter.encode(couponInfo, BarcodeFormat.QR_CODE, 600, 600)
//        val bitMatrix: BitMatrix = multiFormatWriter.encode("UserInputText", BarcodeFormat.QR_CODE, 600, 600)
        val barcodeEncoder = BarcodeEncoder()
        val bitmap : Bitmap = barcodeEncoder.createBitmap(bitMatrix)

        bottom_coupon_info_qrcode.setImageBitmap(bitmap)
    }
}
