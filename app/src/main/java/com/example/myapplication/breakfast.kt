package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class breakfast : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breakfast)

        val btn_breakfast_back = findViewById<Button>(R.id.btn_breakfast_back)
        //返回按鈕觸發 -> 跳回沒有想法的畫面
        btn_breakfast_back.setOnClickListener {
            startActivityForResult(Intent(this, noideal::class.java), 1)
        }

        val breakfurstbutton : Button = findViewById(R.id.breakfurstbutton)
        breakfurstbutton.setOnClickListener{
            breakfurstfoodlotto()
        }
    }



    class foodchoice(val numfoodchoice: Int) {
        fun playfoodlotto(): Int {
            return(0..numfoodchoice-1).random()
        }
    }

    private fun breakfurstfoodlotto() {
        val breakfurstlist = arrayOf("中和豆漿","早安晨之美","7-11便利商店")
        val breakfurstlistsize = breakfurstlist.size
        val foodchoice = foodchoice(breakfurstlistsize)
        val foodchoicenumber = foodchoice.playfoodlotto()
        val resultTextView: TextView = findViewById(R.id.breakfursttextView)
        resultTextView.text = breakfurstlist[foodchoicenumber]
    }
}