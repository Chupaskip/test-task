package com.example.test_task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task.databinding.ItemHumanBinding

class HumanAdapter() :
    ListAdapter<Human, HumanAdapter.HumanViewHolder>(diffUtil) {

    // Создание ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HumanViewHolder {
        return HumanViewHolder(
            ItemHumanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: HumanViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HumanViewHolder(private val binding: ItemHumanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Привязка данных объекта Human к View элементам
        fun bind(human: Human) {
            binding.tvName.text = human.name
            binding.tvAge.text = human.age
        }
    }

    companion object {
        // Объект DiffUtil для оптимизации обновлений списка
        val diffUtil = object : DiffUtil.ItemCallback<Human>() {
            override fun areItemsTheSame(oldItem: Human, newItem: Human): Boolean {
                // Проверка, являются ли объекты одним и тем же элементом
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Human, newItem: Human): Boolean {
                // Проверка, содержат ли объекты одинаковые данные
                return oldItem == newItem
            }
        }
    }
}
