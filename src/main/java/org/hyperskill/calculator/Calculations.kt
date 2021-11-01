package org.hyperskill.calculator

import android.widget.EditText

class Calculations {
    var contextValue = ""
    var operationSign = ""

    fun doMath(value: String): Double {
        var sign = 1

        if (contextValue.first().toString() == "-"){
            sign = -1
        }

        return when(operationSign){
            "+" -> contextValue.toDouble() * sign + value.toDouble()
            "-" -> contextValue.toDouble() * sign - value.toDouble()
            "x" -> contextValue.toDouble() * sign * value.toDouble()
            "/" -> contextValue.toDouble() * sign / value.toDouble()
            else -> 0.0
        }
    }
}