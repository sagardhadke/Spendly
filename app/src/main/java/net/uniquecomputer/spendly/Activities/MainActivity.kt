package net.uniquecomputer.spendly.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import net.uniquecomputer.spendly.Adapters.RecordsAdapter
import net.uniquecomputer.spendly.Fragments.AddRecordsFragment
import net.uniquecomputer.spendly.Model.RecordsModel
import net.uniquecomputer.spendly.R
import net.uniquecomputer.spendly.Utils.Constants
import net.uniquecomputer.spendly.Utils.Helper
import net.uniquecomputer.spendly.databinding.ActivityMainBinding
import java.util.Calendar
import java.util.Date

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

        recordsArrayList = ArrayList()
        recordsArrayList.add(RecordsModel("INCOME", 549.52, "Business", "Cash","Some Nots", Date(),1))
        recordsArrayList.add(RecordsModel(Constants.INCOME, 148.23, "Loan", "Bank"," Nots", Date(),2))
        recordsArrayList.add(RecordsModel("EXPENSE", 41.00, "Rent", "Card","Card Nots", Date(),6))
        recordsArrayList.add(RecordsModel("EXPENSE", 6.00, "Other", "Wallet","Wallet Payment", Date(),8))
        recordsArrayList.add(RecordsModel("INCOME", 79.23, "Salary", "Paytm","Salary Paytm", Date(),3))

        recordsAdapter = RecordsAdapter(this, recordsArrayList)
        binding.recordsList.adapter = recordsAdapter
        binding.recordsList.layoutManager = LinearLayoutManager(this)


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