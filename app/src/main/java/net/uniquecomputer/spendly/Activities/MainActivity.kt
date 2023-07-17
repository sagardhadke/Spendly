package net.uniquecomputer.spendly.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import net.uniquecomputer.spendly.Fragments.AddRecordsFragment
import net.uniquecomputer.spendly.R
import net.uniquecomputer.spendly.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var calender: Calendar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calender = Calendar.getInstance()

        updateDate()

        setSupportActionBar(binding.Toolbar)
       supportActionBar?.title = "Records"

        binding.floatingActionButton.setOnClickListener {
            AddRecordsFragment().show(supportFragmentManager, null)
        }

    }

    private fun updateDate() {
        val SimpleDateFormat = java.text.SimpleDateFormat("dd MMMM yyyy")
        binding.currentDate.text = SimpleDateFormat.format(calender.time)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}