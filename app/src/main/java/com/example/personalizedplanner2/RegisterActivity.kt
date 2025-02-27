package com.example.personalizedplanner2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val name = findViewById<EditText>(R.id.name)
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val phone = findViewById<EditText>(R.id.phone)
        val password = findViewById<EditText>(R.id.password)
        val genderGroup = findViewById<RadioGroup>(R.id.genderGroup)
        val termsCheckBox = findViewById<CheckBox>(R.id.termsCheckBox)
        val countrySpinner = findViewById<Spinner>(R.id.countrySpinner)
        val registerBtn = findViewById<Button>(R.id.registerBtn)

        // Populate Spinner with countries
        val countries = arrayOf("Select Country", "USA", "India", "Canada", "UK", "Australia")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countries)
        countrySpinner.adapter = adapter

        registerBtn.setOnClickListener {
            val nameInput = name.text.toString().trim()
            val usernameInput = username.text.toString().trim()
            val emailInput = email.text.toString().trim()
            val phoneInput = phone.text.toString().trim()
            val passwordInput = password.text.toString().trim()
            val selectedGenderId = genderGroup.checkedRadioButtonId
            val selectedCountry = countrySpinner.selectedItem.toString()

            // Email pattern validation
            val emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")

            when {
                nameInput.isEmpty() || usernameInput.isEmpty() || emailInput.isEmpty() ||
                        phoneInput.isEmpty() || passwordInput.isEmpty() -> {
                    showToast("All fields are required!")
                }
                !emailPattern.matcher(emailInput).matches() -> {
                    showToast("Invalid email format!")
                }
                phoneInput.length != 10 || !phoneInput.all { it.isDigit() } -> {
                    showToast("Enter a valid 10-digit phone number!")
                }
                passwordInput.length < 6 -> {
                    showToast("Password must be at least 6 characters long!")
                }
                selectedGenderId == -1 -> {
                    showToast("Please select a gender!")
                }
                selectedCountry == "Select Country" -> {
                    showToast("Please select a country!")
                }
                !termsCheckBox.isChecked -> {
                    showToast("You must agree to the Terms & Conditions!")
                }
                else -> {
                    showToast("Registration Successful!")
                    finish() // Close the activity after registration
                }
            }
        }
    }

    // Helper function to show Toast messages
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
