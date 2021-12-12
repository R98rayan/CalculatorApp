package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var screen: TextView

    lateinit var button0: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button5: Button
    lateinit var button6: Button
    lateinit var button7: Button
    lateinit var button8: Button
    lateinit var button9: Button

    lateinit var buttonPlus: Button
    lateinit var buttonDash: Button
    lateinit var buttonSlash: Button
    lateinit var buttonDot: Button
    lateinit var buttonX: Button

    lateinit var buttonPD: Button

    lateinit var buttonC: Button
    lateinit var buttonDELL: Button
    lateinit var buttonEqule: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init
        screen = findViewById(R.id.screen)

        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)

        buttonPlus = findViewById(R.id.buttonPlus)
        buttonDash = findViewById(R.id.buttonDash)
        buttonSlash = findViewById(R.id.buttonSlash)
        buttonDot = findViewById(R.id.buttonDot)
        buttonX = findViewById(R.id.buttonX)

        buttonPD = findViewById(R.id.buttonPD)

        buttonC = findViewById(R.id.buttonC)
        buttonDELL = findViewById(R.id.buttonDELL)
        buttonEqule = findViewById(R.id.buttonEqual)

        // onClick
        button0.setOnClickListener { numButtonAction(button0) }
        button1.setOnClickListener { numButtonAction(button1) }
        button2.setOnClickListener { numButtonAction(button2) }
        button3.setOnClickListener { numButtonAction(button3) }
        button4.setOnClickListener { numButtonAction(button4) }
        button5.setOnClickListener { numButtonAction(button5) }
        button6.setOnClickListener { numButtonAction(button6) }
        button7.setOnClickListener { numButtonAction(button7) }
        button8.setOnClickListener { numButtonAction(button8) }
        button9.setOnClickListener { numButtonAction(button9) }

        buttonPlus.setOnClickListener { symButtonAction(buttonPlus) }
        buttonDash.setOnClickListener { symButtonAction(buttonDash) }
        buttonSlash.setOnClickListener { symButtonAction(buttonSlash) }
        buttonDot.setOnClickListener { symButtonAction(buttonDot) }
        buttonX.setOnClickListener { symButtonAction(buttonX) }

        buttonPD.setOnClickListener { PDButtonAction(buttonPD) }

        buttonC.setOnClickListener {
            screen.text = "0"
        }

        buttonDELL.setOnClickListener {
            if (screen.text != "0") screen.text = screen.text.substring(0, screen.text.length - 1)
            if(screen.text.isEmpty()) screen.text = "0"
        }

        buttonEqule.setOnClickListener {
            try {
                // some code
                screen.text = mathematicalExpression(screen.text.toString())
            } catch (e: Exception) {
                // handler
                screen.text = "EROOR"
            }
        }

    }

    fun numButtonAction(button: Button){
        if(screen.text == "EROOR") screen.text = "0"
        else if (screen.text == "0") {
            if (button.text != "0") screen.text = button.text.toString()
        }
        else screen.text = screen.text.toString() + button.text.toString()
    }

    fun symButtonAction(button: Button){
        if(screen.text == "EROOR") screen.text = "0"
        screen.text = screen.text.toString() + button.text.toString()
    }

    fun PDButtonAction(button: Button){
        if(screen.text == "EROOR") screen.text = "0"
        if (screen.text[0] == '-') screen.text = screen.text.removePrefix("-")
        else screen.text = "-${screen.text}"
    }

    fun mathematicalExpression(exp: String): String {

        var num: String = ""
        var symbol: Char = '+'
        var result: Double = 0.0

        for(i in exp)
        {
            if(i in '0'..'9')
                num += i
            else if(i=='.')
                num += i
            else
            {
                if(symbol == '+')
                    result += num.toDouble()
                else if(symbol == '-')
                    result -= num.toDouble()
                else if(symbol == '*')
                    result *= num.toDouble()
                else if(symbol == '/')
                    result /= num.toDouble()

                num=""
                symbol = i
            }
        }

        if(symbol == '+')
            result += num.toDouble()
        else if(symbol == '-')
            result -= num.toDouble()
        else if(symbol == '*')
            result *= num.toDouble()
        else if(symbol == '/')
            result /= num.toDouble()

        return "$result"
    }
}