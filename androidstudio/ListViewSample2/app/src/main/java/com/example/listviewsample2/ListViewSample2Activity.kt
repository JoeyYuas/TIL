package com.example.listviewsample2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class ListViewSample2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_sample2)

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val menuList : MutableList<String> = mutableListOf()

        menuList.add("aaaaaa")
        menuList.add("bbbbbb")
        menuList.add("cccccc")

        val adapter : ArrayAdapter<String> = ArrayAdapter(
            this@ListViewSample2Activity, android.R.layout.simple_list_item_1, menuList
        )

        lvMenu.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val dialogFragment = OrderConfirmDialogFragment()
            dialogFragment.show(supportFragmentManager, "OrderConfirmDialogFragment")
        }

        lvMenu.adapter = adapter
    }
}
