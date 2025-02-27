package com.example.personalizedplanner2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import java.util.*

class TravelFragment : Fragment() {
    private lateinit var travelInput: EditText
    private lateinit var pickDateBtn: Button
    private lateinit var travelListView: ListView
    private val travelPlans = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_travel, container, false)

        travelInput = view.findViewById(R.id.travelInput)
        pickDateBtn = view.findViewById(R.id.pickDateBtn)
        travelListView = view.findViewById(R.id.travelListView)

        // Set up ListView Adapter
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, travelPlans)
        travelListView.adapter = adapter

        // Button Click: Open Date Picker
        pickDateBtn.setOnClickListener {
            openDateTimePicker()
        }

        return view
    }

    private fun openDateTimePicker() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(requireContext(),
            { _, year, month, dayOfMonth ->
                val timePickerDialog = TimePickerDialog(requireContext(),
                    { _, hour, minute ->
                        val selectedDateTime = "$dayOfMonth/${month + 1}/$year - $hour:$minute"
                        travelPlans.add("${travelInput.text}: $selectedDateTime")
                        adapter.notifyDataSetChanged()
                    },
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
                timePickerDialog.show()
            },
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datePickerDialog.show()
    }
}
