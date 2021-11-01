package org.hyperskill.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val dotButton = findViewById<Button>(R.id.dotButton)
        val equalButton = findViewById<Button>(R.id.equalButton)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val minusButton = findViewById<Button>(R.id.subtractButton)
        val plusButton = findViewById<Button>(R.id.addButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)

        val calculations = Calculations()

        button0.setOnClickListener {
            when {
                editText.text.toString() == "0" || editText.text.toString() == "0.0" -> {
                    Toast.makeText(this, "It's already zero!", Toast.LENGTH_SHORT).show()
                    editText.setText("0")
                }
                editText.text.toString() == "-" -> {
                    Toast.makeText(
                        this,
                        "There is no such thing as minus 0, hit clear to start over",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    editText.append("0")
                }
            }
        }

        button1.setOnClickListener {
            if (editText.text.toString() == "0") {
                editText.setText(R.string._1)
            } else {
                editText.append(getString(R.string._1))
            }
        }

        button2.setOnClickListener {
            if (editText.text.toString() == "0") {
                editText.setText(R.string._2)
            } else {
                editText.append(getString(R.string._2))
            }
        }

        button3.setOnClickListener {
            if (editText.text.toString() == "0") {
                editText.setText(R.string._3)
            } else {
                editText.append(getString(R.string._3))
            }
        }

        button4.setOnClickListener {
            if (editText.text.toString() == "0") {
                editText.setText(R.string._4)
            } else {
                editText.append(getString(R.string._4))
            }
        }

        button5.setOnClickListener {
            if (editText.text.toString() == "0") {
                editText.setText(R.string._5)
            } else {
                editText.append(getString(R.string._5))
            }
        }

        button6.setOnClickListener {
            if (editText.text.toString() == "0") {
                editText.setText(R.string._6)
            } else {
                editText.append(getString(R.string._6))
            }
        }

        button7.setOnClickListener {
            if (editText.text.toString() == "0") {
                editText.setText(R.string._7)
            } else {
                editText.append(getString(R.string._7))
            }
        }

        button8.setOnClickListener {
            if (editText.text.toString() == "0") {
                editText.setText(R.string._8)
            } else {
                editText.append(getString(R.string._8))
            }
        }

        button9.setOnClickListener {
            if (editText.text.toString() == "0") {
                editText.setText(R.string._9)
            } else {
                editText.append(getString(R.string._9))
            }
        }

        clearButton.setOnClickListener {
            editText.setText("0")
            editText.hint = "0"
            calculations.operationSign = ""
            calculations.contextValue = ""
        }

        dotButton.setOnClickListener {
            when {
                editText.text.toString().contains(".") -> {
                    Toast.makeText(
                        this, "There is a decimal point in the number already!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                editText.text.toString() == "-" -> {
                    editText.append("0.")
                }
                else -> {
                    editText.append(".")
                }
            }
        }

        minusButton.setOnClickListener {
            if (editText.text.toString() == "0" && calculations.contextValue == "") {
                editText.setText(R.string.minusSign)
            } else if (calculations.operationSign != "") {
                calculations.operationSign = "-"
            } else {
                calculations.operationSign = "-"
                calculations.contextValue = editText.text.toString()
                editText.hint = editText.text.toString()
                editText.setText("")
            }
        }

        plusButton.setOnClickListener {
            if (calculations.operationSign != "") {
                calculations.operationSign = "+"
            } else {
                calculations.operationSign = "+"
                calculations.contextValue = editText.text.toString()
                editText.hint = editText.text.toString()
                editText.setText("")
            }
        }

        multiplyButton.setOnClickListener {
            if (calculations.operationSign != "") {
                calculations.operationSign = "x"
            } else {
                calculations.operationSign = "x"
                calculations.contextValue = editText.text.toString()
                editText.hint = editText.text.toString()
                editText.setText("")
            }
        }

        divideButton.setOnClickListener {
            if (calculations.operationSign != "") {
                calculations.operationSign = "/"
            } else {
                calculations.operationSign = "/"
                calculations.contextValue = editText.text.toString()
                editText.hint = editText.text.toString()
                editText.setText("")
            }
        }

        equalButton.setOnClickListener {
            if (calculations.operationSign == "/" && editText.text.toString() == "0") {
                Toast.makeText(
                    this, "Division by 0 is not possible!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (calculations.contextValue == "") {
                Toast.makeText(
                    this, "Nothing to do here!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (calculations.contextValue != "" && editText.hint == "0") {
                editText.setText(calculations.contextValue)
                calculations.operationSign = ""
                editText.hint = "0"
            } else {
                val result = calculations.doMath(editText.text.toString())
                if (0.0 == result % 1) {
                    editText.setText(result.toLong().toString())
                } else {
                    editText.setText(result.toString())
                }
                calculations.contextValue = editText.text.toString()
                calculations.operationSign = ""
                editText.hint = "0"

            }
        }

    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Confirmation")
            setMessage("Are you sure you want to exit the app?")

            setPositiveButton("Yes") { _, _ ->
                super.onBackPressed()
            }

            setNegativeButton("No") { _, _ ->

                Toast.makeText(
                    applicationContext, "^~^",
                    Toast.LENGTH_LONG
                ).show()
            }
        }.show()
    }
}