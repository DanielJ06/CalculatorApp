package com.danieljrodrigues.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        var display = findViewById<TextView>(R.id.tvInput)
        display.append((view as Button).text)
        lastNumeric = true
    }

    private fun isOperatorAdded(value : String) : Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") ||
            value.contains("-") || value.contains("+")
        }
    }

    fun onOperator(view: View) {
        var display = findViewById<TextView>(R.id.tvInput)
        if (lastNumeric && !isOperatorAdded(display.text.toString())) {
            display.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            var display = findViewById<TextView>(R.id.tvInput)
            display.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onClear(view: View) {
        var display = findViewById<TextView>(R.id.tvInput)
        display.text = ""
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result;
        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return  value
    }

    fun onEqual(view: View) {
        if(lastNumeric) {
            var display = findViewById<TextView>(R.id.tvInput)
            var displayTextValue = display.text.toString()
            var prefix = ""

            try {
                if(displayTextValue.startsWith("-")) {
                    prefix = "-"
                    displayTextValue = displayTextValue.substring(1)
                }

                if(displayTextValue.contains("-")) {
                    val splitValue = displayTextValue.split("-")

                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if(!prefix.isEmpty()) {
                        firstValue = prefix + firstValue
                    }

                    display.text = removeZeroAfterDot((firstValue.toDouble() - secondValue.toDouble()).toString())
                } else if(displayTextValue.contains("*")) {
                    val splitValue = displayTextValue.split("*")

                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if(!prefix.isEmpty()) {
                        firstValue = prefix + firstValue
                    }

                    display.text = removeZeroAfterDot((firstValue.toDouble() * secondValue.toDouble()).toString())
                } else if(displayTextValue.contains("+")) {
                    val splitValue = displayTextValue.split("+")

                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if(!prefix.isEmpty()) {
                        firstValue = prefix + firstValue
                    }

                    display.text = removeZeroAfterDot((firstValue.toDouble() + secondValue.toDouble()).toString())
                } else if(displayTextValue.contains("/")) {
                    val splitValue = displayTextValue.split("/")

                    var firstValue = splitValue[0]
                    var secondValue = splitValue[1]

                    if(!prefix.isEmpty()) {
                        firstValue = prefix + firstValue
                    }

                    display.text = removeZeroAfterDot((firstValue.toDouble() / secondValue.toDouble()).toString())
                }
            } catch (e: ArithmeticException) {}
        }
    }
}