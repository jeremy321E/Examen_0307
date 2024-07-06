package com.example.calculator

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declaración de las variables de la interfaz de usuario
    private lateinit var number1: EditText
    private lateinit var number2: EditText
    private lateinit var result: TextView
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button
    private lateinit var btnModulus: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de las variables de la interfaz de usuario
        number1 = findViewById(R.id.number1)
        number2 = findViewById(R.id.number2)
        result = findViewById(R.id.result)
        btnAdd = findViewById(R.id.btn_add)
        btnSubtract = findViewById(R.id.btn_subtract)
        btnMultiply = findViewById(R.id.btn_multiply)
        btnDivide = findViewById(R.id.btn_divide)
        btnModulus = findViewById(R.id.btn_modulus)

        // Configuración de los listeners de los botones
        btnAdd.setOnClickListener { calculate("+") }
        btnSubtract.setOnClickListener { calculate("-") }
        btnMultiply.setOnClickListener { calculate("*") }
        btnDivide.setOnClickListener { calculate("/") }
        btnModulus.setOnClickListener { calculate("%") }
    }

    // Función para realizar la operación de cálculo
    private fun calculate(operation: String) {
        val num1Str = number1.text.toString()
        val num2Str = number2.text.toString()

        // Validar las entradas de datos
        if (validateInputs(num1Str, num2Str)) {
            val num1 = num1Str.toDouble()
            val num2 = num2Str.toDouble()
            // Realizar la operación y mostrar el resultado
            val resultText = when (operation) {
                "+" -> add(num1, num2)
                "-" -> subtract(num1, num2)
                "*" -> multiply(num1, num2)
                "/" -> divide(num1, num2)
                "%" -> modulus(num1, num2)
                else -> "Operación no válida"
            }
            result.text = resultText
        }
    }

    // Función para validar las entradas de datos
    private fun validateInputs(num1Str: String, num2Str: String): Boolean {
        return when {
            TextUtils.isEmpty(num1Str) -> {
                showToast("Ingrese el primer número")
                false
            }
            TextUtils.isEmpty(num2Str) -> {
                showToast("Ingrese el segundo número")
                false
            }
            num1Str.toDoubleOrNull() == null -> {
                showToast("Ingrese un número válido en el primer campo")
                false
            }
            num2Str.toDoubleOrNull() == null -> {
                showToast("Ingrese un número válido en el segundo campo")
                false
            }
            else -> true
        }
    }

    // Función para mostrar un mensaje de toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Función para sumar dos números
    private fun add(num1: Double, num2: Double): String {
        return (num1 + num2).toString()
    }

    // Función para restar dos números
    private fun subtract(num1: Double, num2: Double): String {
        return (num1 - num2).toString()
    }

    // Función para multiplicar dos números
    private fun multiply(num1: Double, num2: Double): String {
        return (num1 * num2).toString()
    }

    // Función para dividir dos números
    private fun divide(num1: Double, num2: Double): String {
        return if (num2 != 0.0) {
            (num1 / num2).toString()
        } else {
            "Error: División por cero"
        }
    }

    // Función para calcular el módulo de dos números
    private fun modulus(num1: Double, num2: Double): String {
        return if (num2 != 0.0) {
            (num1 % num2).toString()
        } else {
            "Error: División por cero"
        }
    }
}