package com.example.myapplication

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.*

class hesitate : AppCompatActivity() {

    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var dbrw: SQLiteDatabase

    override fun onDestroy() {
        dbrw.close() //關閉資料庫
        super.onDestroy()
    }

    //建立 showToast 方法顯示 Toast 訊息
    private fun showToast(text: String) = Toast.makeText(this,text, Toast.LENGTH_LONG).show()

    //清空輸入的選項
    private fun cleanEditText() = findViewById<EditText>(R.id.ed_wanteat).setText("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hesitate)

        val btn_hesitate_back = findViewById<Button>(R.id.btn_hesitate_back)
        //返回按鈕觸發 -> 跳回主畫面
        btn_hesitate_back.setOnClickListener {
            startActivityForResult(Intent(this, MainActivity::class.java), 1)
        }

        //取得資料庫實體
        dbrw = hesitate_SQL(this).writableDatabase

        //宣告 Adapter 並連結 ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        findViewById<ListView>(R.id.listView).adapter = adapter
        //設定監聽器
        setListener()

    }

    //設定監聽器
     fun setListener() {
        val ed_wanteat = findViewById<EditText>(R.id.ed_wanteat)

        val btn_insert = findViewById<Button>(R.id.btn_insert)
        btn_insert.setOnClickListener {
            if (ed_wanteat.length() < 1)//判斷是否有填入食物
                Toast.makeText(this, "欄位請勿留空", Toast.LENGTH_SHORT).show()
            else
                try {
                    //新增一筆書籍紀錄於 myTable 資料表
                    dbrw.execSQL("INSERT INTO myTable(food) VALUES(?)", arrayOf(ed_wanteat.text.toString())
                    )
                    showToast("新增 : ${ed_wanteat.text}")
                    cleanEditText()
                } catch (e: Exception) {
                    showToast("新增失敗:$e")
                }

            //列出所有項目
            val queryString = "SELECT * FROM myTable"

            val c = dbrw.rawQuery(queryString, null)
            c.moveToFirst() //從第一筆開始輸出
            items.clear() //清空舊資料
            for (i in 0 until c.count) {
                //加入新資料
                items.add("選擇 : ${c.getString(0)}\t\t")
                c.moveToNext() //移動到下一筆
            }
            adapter.notifyDataSetChanged() //更新列表資料
            c.close() //關閉 Cursor
        }

        val btn_delet = findViewById<Button>(R.id.btn_delet)
        btn_delet.setOnClickListener {
            //判斷是否有填入食物
            if (ed_wanteat.length() < 1)
                showToast("欄位請勿留空")
            else
                try {
                    //從 myTable 資料表刪除相同名字的食物
                    dbrw.execSQL("DELETE FROM myTable WHERE food LIKE '${ed_wanteat.text}'")
                    showToast("刪除:${ed_wanteat.text}")
                    cleanEditText()
                } catch (e: Exception) {
                    showToast("刪除失敗:$e")
                }

            //列出所有項目
            val queryString = "SELECT * FROM myTable"

            val c = dbrw.rawQuery(queryString, null)
            c.moveToFirst() //從第一筆開始輸出
            items.clear() //清空舊資料
            for (i in 0 until c.count) {
                //加入新資料
                items.add("選擇 : ${c.getString(0)}\t\t")
                c.moveToNext() //移動到下一筆
            }
            adapter.notifyDataSetChanged() //更新列表資料
            c.close() //關閉 Cursor
        }

        class foodchoice(val numfoodchoice: Int) {
            fun playfoodlotto(): Int {
                return(0..numfoodchoice-1).random()
            }
        }

        val btn_hesitate_random = findViewById<Button>(R.id.btn_hesitate_random)
        btn_hesitate_random.setOnClickListener {

            val queryString = "SELECT * FROM myTable"

            val c = dbrw.rawQuery(queryString, null)
            c.moveToFirst() //從第一筆開始輸出
            items.clear() //清空舊資料

            var all = 0

            for (i in 0 until c.count) {
                c.moveToNext() //移動到下一筆
                all = all + 1 //計算有多少項目
            }
            c.moveToFirst() //從第一筆開始輸出
            items.clear() //清空舊資料

            var randomlist = arrayOfNulls<String>(all)

            for (i in 0 until c.count) {
                randomlist[i] = c.getString(0)
                c.moveToNext() //移動到下一筆
            }
            c.moveToFirst() //從第一筆開始輸出
            items.clear() //清空舊資料

            val foodchoice = foodchoice(all)//跑亂數
            val foodchoicenumber = foodchoice.playfoodlotto() //跑亂數
            val resultTextView = randomlist[foodchoicenumber] //數字對應的項目
            btn_hesitate_random.setText("${resultTextView}") //botton字輸出
        }

    }
}