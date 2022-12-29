package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class lunch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)

        val btn_lunch_back = findViewById<Button>(R.id.btn_lunch_back)
        val lunchbutton : Button = findViewById(R.id.lunchbutton)

        //返回按鈕觸發 -> 跳回沒有想法的畫面
        btn_lunch_back.setOnClickListener {
            startActivityForResult(Intent(this, noideal::class.java), 1)
        }

        //午餐抽抽樂按鈕觸發 -> 啟動lunchfoodlotto
        lunchbutton.setOnClickListener{
            lunchfoodlotto()
        }

    }

    class foodchoice(val numfoodchoice: Int) {
        fun playfoodlotto(): Int {
            return(0..numfoodchoice-1).random()
        }
    }

    private fun lunchfoodlotto() {
        val lunchlist = arrayOf("北科學餐","義大利麵","拉麵")
        val lunchlistsize = lunchlist.size
        val foodchoice = foodchoice(lunchlistsize)
        val foodchoicenumber = foodchoice.playfoodlotto()
        val resultTextView: TextView = findViewById(R.id.lunchtextView)
        resultTextView.text = lunchlist[foodchoicenumber]
    }

}