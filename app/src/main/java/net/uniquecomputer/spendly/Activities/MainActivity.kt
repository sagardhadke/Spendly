package net.uniquecomputer.spendly.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import net.uniquecomputer.spendly.Adapters.RecordsAdapter
import net.uniquecomputer.spendly.Fragments.AddRecordsFragment
import net.uniquecomputer.spendly.Model.RecordsModel
import net.uniquecomputer.spendly.R
import net.uniquecomputer.spendly.Utils.Constants
import net.uniquecomputer.spendly.Utils.Helper
import net.uniquecomputer.spendly.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var calender: Calendar

    lateinit var recordsAdapter: RecordsAdapter
    lateinit var recordsArrayList: ArrayList<RecordsModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calender = Calendar.getInstance()

        updateDate()
        Constants.setCategories()

        binding.nextDateBtn.setOnClickListener {
            calender.add(Calendar.DAY_OF_MONTH, 1)
            updateDate()
        }

        binding.previousDateBtn.setOnClickListener {
            calender.add(Calendar.DAY_OF_MONTH, -1)
            updateDate()
        }

        setSupportActionBar(binding.Toolbar)
       supportActionBar?.title = "Records"

        binding.floatingActionButton.setOnClickListener {
            AddRecordsFragment().show(supportFragmentManager, null)
        }

    }

    private fun updateDate() {
        binding.currentDate.text = Helper.formatDate(calender.time)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}