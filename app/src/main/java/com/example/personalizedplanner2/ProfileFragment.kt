package com.example.personalizedplanner2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var profileName: EditText
    private lateinit var profileEmail: EditText
    private lateinit var saveProfileBtn: Button
    private lateinit var logoutBtn: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileName = view.findViewById(R.id.profileName)
        profileEmail = view.findViewById(R.id.profileEmail)
        saveProfileBtn = view.findViewById(R.id.saveProfileBtn)
        logoutBtn = view.findViewById(R.id.logoutBtn)

        // 🔹 Load User Data from SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "")
        val email = sharedPref.getString("email", "")

        profileName.setText(username)
        profileEmail.setText(email)

        // ✅ Save Updated Profile Data
        saveProfileBtn.setOnClickListener {
            val newName = profileName.text.toString().trim()
            val newEmail = profileEmail.text.toString().trim()

            if (newName.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(requireContext(), "Fields cannot be empty!", Toast.LENGTH_SHORT).show()
            } else {
                val editor = sharedPref.edit()
                editor.putString("username", newName)
                editor.putString("email", newEmail)
                editor.apply()

                Toast.makeText(requireContext(), "Profile Updated!", Toast.LENGTH_SHORT).show()
            }
        }

        // ✅ Logout: Clears SharedPreferences and redirects to Login
        logoutBtn.setOnClickListener {
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()

            Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return view
    }
}
