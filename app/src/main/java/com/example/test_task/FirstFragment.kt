package com.example.test_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.test_task.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = (activity as MainActivity).viewModel

        // Получение значения number1 из ViewModel и установка его в EditText
        val num1 = viewModel.number1.value
        num1?.also {
            binding.etNum1.setText(it.toString())
        }

        // Получение значения number2 из ViewModel и установка его в EditText
        val num2 = viewModel.number2.value
        num2?.also {
            binding.etNum2.setText(it.toString())
        }

        // Обработчик нажатия на кнопку "Второй экран"
        binding.btnSecondActivity.setOnClickListener {
            if (binding.etNum1.text.isNotEmpty() || binding.etNum2.text.isNotEmpty()) {
                // Сохранение введенных значений в ViewModel
                viewModel.number1.value = binding.etNum1.text.toString().toInt()
                viewModel.number2.value = binding.etNum2.text.toString().toInt()

                // Вычисление суммы и сохранение ее в ViewModel
                viewModel.sum = viewModel.number1.value!! + viewModel.number2.value!!

                // Переход на второй фрагмент
                (activity as MainActivity).showSecondFragment()
            } else {
                // Отображение сообщения об ошибке, если поля пустые
                Toast.makeText(requireContext(), "Заполните поля", Toast.LENGTH_SHORT).show()
            }
        }
    }

}