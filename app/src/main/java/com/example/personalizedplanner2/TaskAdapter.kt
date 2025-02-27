package com.example.personalizedplanner2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private var taskList: MutableList<Task>,
    private val deleteTask: (Task) -> Unit,
    private val editTask: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskTitle: TextView = view.findViewById(R.id.taskTitle)
        val taskDesc: TextView = view.findViewById(R.id.taskDescription)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
        val btnEdit: Button = view.findViewById(R.id.btnEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.taskTitle.text = task.title
        holder.taskDesc.text = task.description

        holder.btnDelete.setOnClickListener { deleteTask(task) }
        holder.btnEdit.setOnClickListener { editTask(task) }
    }

    override fun getItemCount(): Int = taskList.size

    fun updateTasks(newList: MutableList<Task>) {
        taskList.clear()
        taskList.addAll(newList)
        notifyDataSetChanged()
    }
}
