package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class dinner : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dinner)

        val btn_dinner_back = findViewById<Button>(R.id.btn_dinner_back)

        //返回按鈕觸發 -> 跳回沒有想法的畫面
        btn_dinner_back.setOnClickListener {
            startActivityForResult(Intent(this, noideal::class.java), 1)
        }

        val dinnerbutton : Button = findViewById(R.id.dinnerbutton)
        dinnerbutton.setOnClickListener{
            dinnerfoodlotto()
        }

    }

    class foodchoice(val numfoodchoice: Int) {
        fun playfoodlotto(): Int {
            return (0..numfoodchoice - 1).random()
        }
    }

    private fun dinnerfoodlotto() {
        val dinnerlist = arrayOf("湯神牛肉麵","韓老六滷味","麥當勞","蛋包飯","章魚燒","提拉米蘇")
        val dinnerlistsize = dinnerlist.size
        val foodchoice = foodchoice(dinnerlistsize)
        val foodchoicenumber = foodchoice.playfoodlotto()
        val resultTextView: TextView = findViewById(R.id.dinnertextView)
        resultTextView.text = dinnerlist[foodchoicenumber]
    }
}