package com.example.sneackers.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sneackers.Model.UserModel
import com.example.sneackers.R

class UserAdapter(private val users: MutableList<UserModel>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private lateinit var context: Context
    private var selectedPosition = -1

    // Класс для хранения ссылок на элементы макета viewholder_user.xml
    class UserViewHolder(val binding: ViewholderUserBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Класс для генерации экземпляра ViewholderUserBinding
    class ViewholderUserBinding private constructor(val root: View) {
        val tvEmail: TextView = root.findViewById(R.id.email_et)
        val tvPassword: TextView = root.findViewById(R.id.password_et)

        // Статический метод для создания экземпляра ViewholderUserBinding
        companion object {
            fun inflate(parent: ViewGroup): ViewholderUserBinding {
                val inflater = LayoutInflater.from(parent.context)
                val root = inflater.inflate(R.layout.viewholder_brand, parent, false)
                return ViewholderUserBinding(root)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        context = parent.context
        val binding = ViewholderUserBinding.inflate(parent)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        holder.binding.tvEmail.text = user.email
        holder.binding.tvPassword.text = user.password

        // Обработчик клика на элемент списка
        holder.binding.root.setOnClickListener {
            // Добавьте код обработки клика по вашему усмотрению
        }

        // Пример изменения внешнего вида элемента списка в зависимости от его состояния
        if (selectedPosition == position) {
            // Выделение выбранного элемента списка
            // holder.binding.root.setBackgroundColor(...)
        } else {
            // Сброс выделения элемента списка
            // holder.binding.root.setBackgroundColor(...)
        }
    }

    override fun getItemCount(): Int = users.size

    // Метод для обновления списка пользователей
    fun updateUsers(newUsers: List<UserModel>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }
}
