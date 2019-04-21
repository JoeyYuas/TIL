package com.example.fragmentsample


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_menu_list.*
import org.w3c.dom.Text

class MenuThanksFragment : Fragment() {

    private lateinit var _parentActivity : Activity
    private var _isLayoutXLarge = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _parentActivity = activity!!

        val manager = fragmentManager
        val menuListFragment = manager?.findFragmentById(R.id.fragmentMenuList) as MenuListFragment
        if(menuThanksFrame == null) {
            _isLayoutXLarge = false
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_menu_thanks, container, false)

        val extras = if(_isLayoutXLarge) {
            arguments
        } else {
            val intent = _parentActivity.intent
            intent.extras
        }


        var menuName = ""
        var menuPrice = ""

        if(extras != null) {
            menuName = extras.getString("menuName")
            menuPrice = extras.getString("menuPrice")
//            以下の方法でもいいけどここはnullチェックをしたいがためにextrasに代入してる
//            でもKotlinだとデフォでnullチェックが走るので、ぶっちゃけ下がベストアンサーとなる
//            menuName = intent.getStringExtra("menuName")
//            menuPrice = intent.getStringExtra("menuPrice")
        }

        val tvMenuName = view.findViewById<TextView>(R.id.tvMenuName)
        val tvMenuPrice = view.findViewById<TextView>(R.id.tvMenuPrice)

        tvMenuName.text = menuName
        tvMenuPrice.text = menuPrice

        val btBackButton = view.findViewById<Button>(R.id.btBackButton)
        btBackButton.setOnClickListener {
            if(_isLayoutXLarge) {
                val manager = fragmentManager
                val transaction = manager?.beginTransaction()
                transaction?.remove(MenuThanksFragment@this)
                transaction?.commit()
            } else {
                _parentActivity.finish()
            }
        }
        return view
    }


}
