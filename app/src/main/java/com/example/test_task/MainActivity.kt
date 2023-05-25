package com.example.test_task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.test_task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ViewModelTest
    private var currentFragmentId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ViewModelTest::class.java]

        // Восстановление текущего фрагмента после пересоздания активити
        if (savedInstanceState != null) {
            currentFragmentId = savedInstanceState.getInt(KEY_CURRENT_FRAGMENT_ID)
        }

        // Отображение первого фрагмента, если текущий фрагмент не был восстановлен
        if (currentFragmentId == -1) {
            showFirstFragment()
        } else {
            restoreCurrentFragment()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_CURRENT_FRAGMENT_ID, currentFragmentId)
    }

    // Отображение первого фрагмента
    fun showFirstFragment() {
        val fragment = FirstFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        currentFragmentId = R.id.fragment_container
    }

    // Отображение второго фрагмента
    fun showSecondFragment() {
        val fragment = SecondFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        currentFragmentId = R.id.fragment_container
    }

    // Восстановление текущего фрагмента после пересоздания активити
    private fun restoreCurrentFragment() {
        val fragment = supportFragmentManager.findFragmentById(currentFragmentId)
        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, it)
                .commit()
        }
    }

    companion object {
        private const val KEY_CURRENT_FRAGMENT_ID = "currentFragmentId"
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
}
