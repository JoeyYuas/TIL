package com.example.menusample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class MenuListActivity : AppCompatActivity() {

    private lateinit var _lvMenu : ListView
    private lateinit var _menuList : List<Map<String, Any>>
    private var FROM = arrayOf("name", "price")
    private var TO : IntArray = intArrayOf(R.id.tvMenuName, R.id.tvMenuPrice)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_list)

        _lvMenu = findViewById(R.id.lvMenu)
        _menuList = createTeishokuList()

        val adapter = SimpleAdapter(this@MenuListActivity, _menuList, R.layout.row, FROM, TO)

        _lvMenu.adapter = adapter

        _lvMenu.setOnItemClickListener { parent, view, position, id ->
            @Suppress("UNCHECKED_CAST")
            val item : MutableMap<String, Any> = parent.getItemAtPosition(position) as MutableMap<String, Any>
            order(item)
        }

        registerForContextMenu(_lvMenu)

    }

    private fun createTeishokuList() : MutableList<MutableMap<String, Any>> {
        val menuList = mutableListOf<MutableMap<String, Any>>()
        var menu = mutableMapOf<String, Any>()
        menu["name"] = "唐揚げ定食"
        menu["price"] = 850
        menu["desc"] = "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。"
        menuList.add(menu)

        menu = mutableMapOf()
        menu["name"] = "鯖定食"
        menu["price"] = 900
        menu["desc"] = "焼き鯖とお豆腐、ご飯とお味噌汁が付きます。"
        menuList.add(menu)

        return menuList
    }

    private fun createCurryList() : MutableList<MutableMap<String, Any>> {
        val menuList = mutableListOf<MutableMap<String, Any>>()
        var menu = mutableMapOf<String, Any>()
        menu["name"] = "ビーフカレー"
        menu["price"] = 900
        menu["desc"] = "牛を食べるカレーです。"
        menuList.add(menu)

        menu = mutableMapOf()
        menu["name"] = "ポークカレー"
        menu["price"] = 900
        menu["desc"] = "豚を食べるカレーです。"
        menuList.add(menu)

        return menuList
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_options_menu_list, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item?.itemId

        when(itemId){
            R.id.menuListOptionTeishoku -> {
                _menuList = createTeishokuList()
            }
            R.id.menuListOptionCurry -> {
                _menuList = createCurryList()
            }
        }

        val adapter = SimpleAdapter(this@MenuListActivity, _menuList, R.layout.row, FROM, TO)
        _lvMenu.adapter = adapter

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_context_menu_list, menu)
        menu?.setHeaderTitle(R.string.menu_list_context_header)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val info : AdapterView.AdapterContextMenuInfo = item?.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val menu = _menuList[listPosition] as MutableMap<String, Any>

        val itemId = item.itemId

        when(itemId) {
            R.id.menuListContextDesc -> {
                val desc = menu["desc"] as String
                Toast.makeText(this@MenuListActivity, desc, Toast.LENGTH_LONG).show()
            }
            R.id.menuListContextOrder -> {
                order(menu)
            }

        }
        return super.onContextItemSelected(item)
    }

    private fun order(menu: MutableMap<String, Any>) {
        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as Int

        val intent = Intent(this@MenuListActivity, MenuThanksActivity::class.java)
        intent.putExtra("menuName", menuName)
        intent.putExtra("menuPrice", "${menuPrice}円")
        startActivity(intent)
    }
}
