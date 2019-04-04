package com.example.hellosample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.app.Activity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class HelloSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_sample)

        val btClick : Button = findViewById(R.id.btClick)
        val listener = HelloListener()

        btClick.setOnClickListener(listener)

        val btClear = findViewById<Button>(R.id.btClear)
        btClear.setOnClickListener {
            val input : EditText = findViewById(R.id.etName)
            val output : TextView = findViewById(R.id.tvOutput)
            input.setText("")
            output.text = ""
        }

    }

    private inner class HelloListener : View.OnClickListener {

        override fun onClick(view: View?) {
            val input : EditText = findViewById(R.id.etName)
            val output : TextView = findViewById(R.id.tvOutput)
            val inputStr : String = input.text.toString()
            output.text = " ${inputStr}さん、こんにちは！"
        }
    }
}
