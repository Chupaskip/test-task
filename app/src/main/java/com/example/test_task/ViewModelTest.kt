package com.example.test_task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray


class ViewModelTest : ViewModel() {
    // JSON-строка с данными о людях
    private val jsonString = "[{\"name\":\"Misha\", \"age\": \"20\"}, {\"name\":\"Dmitry\", \"age\": \"21\"}, {\"name\":\"Elena\", \"age\": \"18\"}, {\"name\":\"Pavel\", \"age\": \"25\"}]"

    // Приватное поле, содержащее список людей
    private val _people = MutableLiveData<List<Human>>()

    // Публичное поле для доступа к списку людей
    val people: LiveData<List<Human>> get() = _people

    // Переменная для хранения суммы чисел
    var sum = 0

    // LiveData для отслеживания статуса загрузки
    var status = MutableLiveData<Status>()

    // LiveData для хранения первого числа
    val number1 = MutableLiveData<Int>()

    // LiveData для хранения второго числа
    val number2 = MutableLiveData<Int>()

    // Метод для получения JSON данных
    fun getJson() {
        // Установка статуса загрузки в Loading
        status.value = Status.Loading

        // Преобразование JSON-строки в JSONArray
        val jsonArray = JSONArray(jsonString)

        // Создание списка людей
        val humanList = mutableListOf<Human>()

        // Итерация по элементам JSONArray
        for (i in 0 until jsonArray.length()) {
            // Получение объекта JSON
            val jsonHuman = jsonArray.getJSONObject(i)

            // Извлечение имени и возраста из JSON
            val name = jsonHuman.getString("name")
            val age = jsonHuman.getString("age")

            // Создание объекта Human и добавление его в список
            val human = Human(name, age)
            humanList.add(human)
        }

        // Обновление списка людей в LiveData
        _people.postValue(humanList)
        status.value = Status.Successful
    }
}
