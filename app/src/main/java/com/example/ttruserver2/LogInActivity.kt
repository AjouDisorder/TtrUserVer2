package com.example.ttruserver2

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.example.ttruserver2.Retrofit.IMyService
import com.example.ttruserver2.Retrofit.ResponseDTO
import com.example.ttruserver2.Retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.main_menu_header.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest

class LogInActivity : AppCompatActivity() {

    lateinit var iMyService: IMyService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val retrofit = RetrofitClient.getInstance()
        iMyService = retrofit.create(IMyService::class.java)

        //event
        login_button.setOnClickListener{
            loginUser(email_area.text.toString(),password_area.text.toString())

        }

//        kakao_login_button.setOnClickListener {
//            Toast.makeText(this,"Kakao", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, KakaoInfoActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        signup_button.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(id: String, password: String) {

        //Check empty
        if(TextUtils.isEmpty(id)){
            Toast.makeText(this,"Email can not be null or empty",Toast.LENGTH_SHORT).show()
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Password can not be null or empty",Toast.LENGTH_SHORT).show()
            return;
        }

        iMyService.loginUser(id, password).enqueue(object :
            Callback<ResponseDTO> {
            override fun onFailure(call: Call<ResponseDTO>?, t: Throwable?) {
                Toast.makeText(this@LogInActivity, "login fail!", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<ResponseDTO>?,
                response: Response<ResponseDTO>?
            ) {
                Toast.makeText(this@LogInActivity,response?.body().toString(), Toast.LENGTH_LONG).show()
                println(response?.body()?.userId.toString())

//                val userid = response?.body()?.userId.toString()
//                userid.get().
//                user_id.setText(response?.body()?.userId.toString())

                val intent = Intent(this@LogInActivity, MainActivity::class.java)
                startActivity(intent)

            }
        })
    }

    // 앱의 해쉬 키 얻는 함수 kakao api
    private fun getAppKeyHash() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.e("Hash key", something)
            }
        } catch (e: Exception) {
            Log.e("name not found", e.toString())
        }

    }
}
