package com.example.personalizedplanner2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private val taskList = mutableListOf<Task>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize Adapter
        taskAdapter = TaskAdapter(taskList, this::deleteTask, this::editTask)
        recyclerView.adapter = taskAdapter

        // Add task button
        val btnAddTask: Button = view.findViewById(R.id.btnAddTask)
        btnAddTask.setOnClickListener { addTask(view) }

        return view
    }

    private fun addTask(view: View) {
        val titleInput: EditText = view.findViewById(R.id.editTextTaskTitle)
        val descInput: EditText = view.findViewById(R.id.editTextTaskDescription)

        val title = titleInput.text.toString().trim()
        val description = descInput.text.toString().trim()

        if (title.isNotEmpty() && description.isNotEmpty()) {
            val newTask = Task(taskList.size + 1, title, description)
            taskList.add(newTask)
            taskAdapter.updateTasks(taskList)
            titleInput.text.clear()
            descInput.text.clear()
        } else {
            Toast.makeText(requireContext(), "Please enter title and description", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteTask(task: Task) {
        taskList.remove(task)
        taskAdapter.updateTasks(taskList)
    }

    private fun editTask(task: Task) {
        Toast.makeText(requireContext(), "Edit feature coming soon!", Toast.LENGTH_SHORT).show()
    }
}
