package com.mezzasalma.calculator

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import com.mezzasalma.calculator.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalculatorBinding //  par rapport au nom du layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        compute()
    }

    private fun sum() {
        with(binding) { //  lorsqu'on utilise une variable plusieurs fois binding. devient with binding
            val operandFirst = operandFirst.toInt()
            val operandSecond = operandSecond.toInt()
            /**
             * Faire la somme des deux nombres
             * et afficcher le résultat dans le champ compute
             */
            computeResult.text = "${operandFirst.plus(operandSecond)}"
        }
    }

    private fun enableButton() {
        with(binding) {
            computeButton.isEnabled = !operandFirst.text.isNullOrEmpty() && !operandSecond.text.isNullOrEmpty()
        }
    }

    private fun compute() {
        with(binding) {
            computeButton.setOnClickListener {
                sum()
            }
            operandFirst.doAfterTextChanged {
                enableButton()
            }
            operandSecond.doAfterTextChanged {
                enableButton()
            }
        }
    }

    /**
     * Fonction d'extension permettant de convertir le text d'un edit text en Int
     * 1. Récupère
     * 2. Convetir
     */
    private fun EditText.toInt(): Int {
        return text.toString().toIntOrNull() ?: 0
    }
}
