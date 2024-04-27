package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    private var lastDigit = false
    private var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastDigit = true
        lastDot = false
        //        Toast.makeText(this,"Button Pressed",Toast.LENGTH_LONG).show()
    }

    fun onClear(view: View) {
        tvInput?.text = ""
    }

    fun onDecimal(view: View) {
        if (lastDigit && !lastDot) {
            tvInput?.append(".")
            lastDot = true
            lastDigit = false
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if ((it.isEmpty() && (view as Button).text == "-") || (lastDigit && !isOperatorAdded(it.toString()))) {
                tvInput?.append((view as Button).text)
                lastDigit = false
                lastDot = false

            }
        }

    }
    fun onEqual(view: View){
        if (lastDigit){
            var tvValue = tvInput?.text.toString()
            var prefix=""
            try{
            if(tvValue.startsWith("-")) {
                prefix = "-"
                tvValue=tvValue.substring(1)
            }
                if(tvValue.contains("-")){
                val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    val two = splitValue[1]

                    if(tvValue.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeDotZero((one.toDouble() - two.toDouble()).toString())
                }else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (tvValue.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput?.text = removeDotZero((one.toDouble() + two.toDouble()).toString())
                }else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (tvValue.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput?.text = removeDotZero((one.toDouble() * two.toDouble()).toString())
                }else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    val two = splitValue[1]

                    if (tvValue.isNotEmpty()) {
                        one = prefix + one
                    }

                    tvInput?.text = removeDotZero((one.toDouble() / two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }


}
    private  fun removeDotZero(result:String): String{
        var result1 = result
        if (result.contains(".0")){
              result1 = result.substring(0,result.length-2)
        }
        return result1
    }
    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else{
            value.contains("+")
                    ||value.contains("/")
                    ||value.contains("*")
                    ||value.contains("-")
        }


        }
     fun onBack(view: View) {
        tvInput?.text?.let {
             tvInput?.text = backPress(it.toString())
        }

    }
    private fun backPress(result : String): String{
        val backTvInput = tvInput?.text.toString()
        return backTvInput.substring(0, backTvInput.length - 1)
    }

}