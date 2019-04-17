package com.example.fragmentsample


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MenuListFragment : Fragment() {

    private lateinit var _parentActivity : Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _parentActivity = activity!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_list, container, false)
        val orderLvMenu = view.findViewById<ListView>(R.id.orderLvMenu)

        val orderMenuList : MutableList<MutableMap<String, String>> = mutableListOf()
        var orderMenu : MutableMap<String, String> = mutableMapOf()

        orderMenu["name"] = "唐揚げ定食"
        orderMenu["price"] = "800円"
        orderMenuList.add(orderMenu)

        orderMenu = mutableMapOf()

        orderMenu["name"] = "ハンバーグ定食"
        orderMenu["price"] = "850円"
        orderMenuList.add(orderMenu)

        val from = arrayOf("name", "price")
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)

        val adapter = SimpleAdapter(_parentActivity, orderMenuList, android.R.layout.simple_list_item_2, from, to)

        orderLvMenu.adapter = adapter

        orderLvMenu.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            @Suppress("UNCHECKED_CAST")
            val item : MutableMap<String, String> = parent.getItemAtPosition(position) as MutableMap<String, String>
            val menuName : String = item["name"]!!
            val menuPrice : String = item["price"]!!

            val intent = Intent(_parentActivity, MenuThanksActivity::class.java)
            intent.putExtra("menuName", menuName)
            intent.putExtra("menuPrice", menuPrice)

            startActivity(intent)
        }



        return view
    }
}
