package com.example.test_task

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_task.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var humanAdapter: HumanAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получение экземпляра ViewModel из активити
        val viewModel = (activity as MainActivity).viewModel

        // Наблюдение за изменениями статуса загрузки
        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                Status.Loading -> {
                    // Показывать прогресс бар и скрывать остальные элементы
                    binding.tvSum.visibility = View.GONE
                    binding.layoutHeaders.visibility = View.GONE
                    binding.rvPeople.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.Successful -> {
                    // Показывать сумму, список людей и скрывать прогресс бар
                    binding.tvSum.visibility = View.VISIBLE
                    binding.layoutHeaders.visibility = View.VISIBLE
                    binding.rvPeople.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        // Запуск процесса получения JSON данных
        viewModel.getJson()

        // Установка текста суммы в TextView
        binding.tvSum.text = viewModel.sum.toString()

        // Инициализация адаптера и привязка его к RecyclerView
        humanAdapter = HumanAdapter()
        binding.rvPeople.adapter = humanAdapter
        binding.rvPeople.layoutManager = LinearLayoutManager(requireContext())

        // Наблюдение за изменениями списка людей и обновление адаптера
        viewModel.people.observe(viewLifecycleOwner) { humanList ->
            humanAdapter.submitList(humanList)
        }
    }
}
