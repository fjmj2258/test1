package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class noideal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noideal)

        val btn_noideal_back = findViewById<Button>(R.id.btn_noideal_back)
        val btn_breakfast = findViewById<Button>(R.id.btn_breakfast)
        val btn_btn_lunch = findViewById<Button>(R.id.btn_lunch)
        val btn_btn_dinner = findViewById<Button>(R.id.btn_dinner)

        //返回按鈕觸發 -> 跳回主畫面
        btn_noideal_back.setOnClickListener {
            startActivityForResult(Intent(this, MainActivity::class.java), 1)
        }

        //早餐按鈕觸發 -> 跳到早餐的畫面
        btn_breakfast.setOnClickListener {
            startActivityForResult(Intent(this, breakfast::class.java), 1)
        }

        //午餐按鈕觸發 -> 跳到午餐的畫面
        btn_btn_lunch.setOnClickListener {
            startActivityForResult(Intent(this, lunch::class.java), 1)
        }

        //晚餐按鈕觸發 -> 跳到晚餐的畫面
        btn_btn_dinner.setOnClickListener {
            startActivityForResult(Intent(this, dinner::class.java), 1)
        }

    }
}