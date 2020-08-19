package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(MainFragment.newInstance(index++))

        recreate_main_fragment_button.setOnClickListener {
            showFragment(MainFragment.newInstance(index++))
        }
    }

    private fun showFragment(mainFragment: MainFragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, mainFragment)
                .commit()
    }
}