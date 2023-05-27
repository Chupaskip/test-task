package com.example.test_task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.test_task.FragmentNames.Companion.FIRST_FRAGMENT
import com.example.test_task.FragmentNames.Companion.SECOND_FRAGMENT
import com.example.test_task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ViewModelTest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ViewModelTest::class.java]
        if (viewModel.currentFragment == FIRST_FRAGMENT) {
            showFirstFragment()
        } else {
            showSecondFragment()
        }
    }

    // Отображение первого фрагмента
    fun showFirstFragment() {
        val fragment = FirstFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        viewModel.currentFragment = FIRST_FRAGMENT
    }

    // Отображение второго фрагмента
    fun showSecondFragment() {
        val fragment = SecondFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        viewModel.currentFragment = SECOND_FRAGMENT
    }

    // Обработка нажатия кнопки "назад"
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        // Проверяем, является ли текущий фрагмент SecondFragment
        if (currentFragment is SecondFragment) {
            showFirstFragment() // Отображаем первый фрагмент
        } else {
            onBackPressedDispatcher.onBackPressed() // Вызываем обработку нажатия кнопки "назад" по умолчанию
        }
    }

    fun testFun(smth: String) {
        val value = smth
    }
}
