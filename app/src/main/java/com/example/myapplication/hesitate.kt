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
    private fun setListener() {
        val ed_wanteat = findViewById<EditText>(R.id.ed_wanteat)

        val btn_insert = findViewById<Button>(R.id.btn_insert)
        btn_insert.setOnClickListener {
            if (ed_wanteat.length() < 1)//判斷是否有填入書名或價格
                Toast.makeText(this, "欄位請勿留空", Toast.LENGTH_SHORT).show()
            else
                try {
                    //新增一筆書籍紀錄於 myTable 資料表
                    dbrw.execSQL(
                        "INSERT INTO myTable(book) VALUES(?)",
                        arrayOf(ed_wanteat.text.toString())
                    )
                    showToast("新增:${ed_wanteat.text}")
                    cleanEditText()
                    fun EditText.clear() {
                        text.clear()
                    }
                    //cleanEditText()
                } catch (e: Exception) {
                    showToast("新增失敗:$e")
                }
        }

        val btn_list = findViewById<Button>(R.id.btn_list)
        btn_list.setOnClickListener {
            //若無輸入則查詢全部，反之查詢該資料
            val queryString = if (ed_wanteat.length() < 1)
                "SELECT * FROM myTable"
            else
                "SELECT * FROM myTable WHERE food LIKE '${ed_wanteat.text}'"

            val c = dbrw.rawQuery(queryString, null)
            c.moveToFirst() //從第一筆開始輸出
            items.clear() //清空舊資料
            showToast("共有${c.count}筆資料")
            for (i in 0 until c.count) {
                //加入新資料
                items.add("選擇 : ${c.getString(0)}\t\t")
                c.moveToNext() //移動到下一筆
            }
            adapter.notifyDataSetChanged() //更新列表資料
            c.close() //關閉 Cursor
        }


    }
}