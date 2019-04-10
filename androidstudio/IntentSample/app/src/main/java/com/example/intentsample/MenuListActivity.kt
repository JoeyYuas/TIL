package com.example.intentsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter

class MenuListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_list)

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
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

    }

}
