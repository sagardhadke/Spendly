package net.uniquecomputer.spendly.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
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
    private lateinit var binding: ActivityMainBinding
    lateinit var calender: Calendar

    private lateinit var recordsAdapter: RecordsAdapter

   private lateinit var recordsArrayList: RealmResults<RecordsModel>

    private lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calender = Calendar.getInstance()

        setupDatabase()
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


//        recordsArrayList.add()
//        recordsArrayList.add()
//        recordsArrayList.add()
//        recordsArrayList.add(RecordsModel("EXPENSE", 6.00, "Other", "Wallet","Wallet Payment", Date(),8))
//        recordsArrayList.add()

        realm.beginTransaction()
        realm.copyToRealmOrUpdate(RecordsModel("INCOME", 549.52, "Business", "Cash","Some Nots", Date(),Date().time))
        realm.copyToRealmOrUpdate(RecordsModel(Constants.INCOME, 148.23, "Loan", "Bank"," Nots", Date(),Date().time))
        realm.copyToRealmOrUpdate(RecordsModel("EXPENSE", 41.00, "Rent", "Card","Card Nots", Date(),Date().time))
        realm.copyToRealmOrUpdate(RecordsModel("EXPENSE", 6.00, "Other", "Wallet","Wallet Payment", Date(),Date().time))
        realm.copyToRealmOrUpdate(RecordsModel("INCOME", 79.23, "Salary", "Paytm","Salary Paytm", Date(),Date().time))
        realm.commitTransaction()

        recordsArrayList = realm.where(RecordsModel::class.java).findAll()

//        realm.where(RecordsModel::class.java).findAll().forEach {
//            recordsArrayList.add(it)
//        }

        recordsAdapter = RecordsAdapter(this, recordsArrayList)
        binding.recordsList.adapter = recordsAdapter
        binding.recordsList.layoutManager = LinearLayoutManager(this)


        setSupportActionBar(binding.Toolbar)
       supportActionBar?.title = "Records"

        binding.floatingActionButton.setOnClickListener {
            AddRecordsFragment().show(supportFragmentManager, null)
        }

    }

    private fun setupDatabase() {
        Realm.init(this)
        realm = Realm.getDefaultInstance()
    }

    private fun updateDate() {
        binding.currentDate.text = Helper.formatDate(calender.time)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}