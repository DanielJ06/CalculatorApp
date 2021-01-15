package com.danieljrodrigues.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        var display = findViewById<TextView>(R.id.tvInput)
        display.append((view as Button).text)
    }

    fun onOperator(view: View) {}

    fun onDecimalPoint(view: View) {}

    fun onClear(view: View) {
        var display = findViewById<TextView>(R.id.tvInput)
        display.text = ""
    }

    fun onEqual(view: View) {}
}