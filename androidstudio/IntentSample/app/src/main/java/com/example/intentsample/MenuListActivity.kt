package com.example.intentsample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MenuListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_list)

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        //Map型のListを持つListを定義する
        val menuList = mutableListOf<MutableMap<String, String>>()
        var menu = mutableMapOf<String, String>()

        menu["name"] = "唐揚げ定食"
        menu["price"] = "800円"
        menuList.add(menu)

        menu = mutableMapOf()

        menu["name"] = "ハンバーグ定食"
        menu["price"] = "850円"
        menuList.add(menu)

        val adapter = SimpleAdapter(this@MenuListActivity, menuList,
            android.R.layout.simple_list_item_2, arrayOf("name", "price"),
            intArrayOf(android.R.id.text1, android.R.id.text2))

        lvMenu.adapter = adapter

        lvMenu.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            @Suppress("UNCHECKED_CAST")
            val item : MutableMap<String, String> = parent.getItemAtPosition(position) as MutableMap<String, String>
            val menuName : String = item["name"]!!
            val menuPrice : String = item["price"]!!

            val intent = Intent(this@MenuListActivity, MenuThanksActivity::class.java)
            intent.putExtra("menuName", menuName)
            intent.putExtra("menuPrice", menuPrice)

            startActivity(intent)
        }

    }

}
