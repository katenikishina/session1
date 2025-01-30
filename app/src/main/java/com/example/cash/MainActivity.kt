package com.example.cash

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var tvResult:TextView
    var counter = 0
    var pref:SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
        counter =pref?.getInt("counter",0)!!
        tvResult.text = counter.toString()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onClickAdd(view: View) {
        counter++
        tvResult.text = counter.toString()
        saveData(counter)
    }
    fun saveData(res:Int){
        val editor = pref?.edit()
        editor?.putInt("counter",res)
        editor?.apply()
    }

}