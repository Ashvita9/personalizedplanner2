package com.example.personalizedplanner2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNav.setOnItemSelectedListener {
            var selectedFragment: Fragment? = null
            when (it.itemId) {
                R.id.nav_tasks -> selectedFragment = TaskFragment()
                R.id.nav_travel -> selectedFragment = TravelFragment()
                R.id.nav_profile -> selectedFragment = ProfileFragment()
            }
            selectedFragment?.let { frag ->
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, frag).commit()
            }
            true
        }

        // Default Fragment on Launch
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, TaskFragment()).commit()
    }
}
