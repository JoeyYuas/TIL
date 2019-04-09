package com.example.listviewsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_sample)

        val lvMenu : ListView = findViewById(R.id.lvMenu)
        lvMenu.onItemClickListener = ListItemClickListener()
//        lvMenu.setOnItemClickListener { parent, view, position, id ->
//            val item : String = parent?.getItemAtPosition(position) as String
//            val show : String = "あなたが選んだ定食：$item"
//            Toast.makeText(this@MainActivity, show, Toast.LENGTH_LONG).show()
//        }
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item : String = parent?.getItemAtPosition(position) as String
            val show : String = "あなたが選んだ定食：$item"
            Toast.makeText(this@MainActivity, show, Toast.LENGTH_LONG).show()
        }
    }
}
