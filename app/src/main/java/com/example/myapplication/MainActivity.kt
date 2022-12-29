package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_hesitate = findViewById<Button>(R.id.btn_hesitate)
        val btn_noideal = findViewById<Button>(R.id.btn_noideal)

        //猶豫不決按鈕觸發 -> 跳到猶豫不決的畫面
        btn_hesitate.setOnClickListener {
            startActivityForResult(Intent(this, hesitate::class.java), 1)
        }

        //沒有想法按鈕觸發 -> 跳到沒有想法的畫面
        btn_noideal.setOnClickListener {
            startActivityForResult(Intent(this, noideal::class.java), 1)
        }

    }
}