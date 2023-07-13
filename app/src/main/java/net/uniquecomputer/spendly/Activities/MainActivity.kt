package net.uniquecomputer.spendly.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import net.uniquecomputer.spendly.Fragments.AddRecordsFragment
import net.uniquecomputer.spendly.R
import net.uniquecomputer.spendly.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.Toolbar)
       supportActionBar?.title = "Records"

        binding.floatingActionButton.setOnClickListener {
            AddRecordsFragment().show(supportFragmentManager, null)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}