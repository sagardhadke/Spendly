package net.uniquecomputer.spendly.Fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.uniquecomputer.spendly.Adapters.AccountAdapter
import net.uniquecomputer.spendly.Adapters.CategoriyAdapter
import net.uniquecomputer.spendly.Model.ModelAccount
import net.uniquecomputer.spendly.Model.ModelCategory
import net.uniquecomputer.spendly.R
import net.uniquecomputer.spendly.Utils.Constants
import net.uniquecomputer.spendly.Utils.Helper
import net.uniquecomputer.spendly.databinding.FragmentAddRecordsBinding
import net.uniquecomputer.spendly.databinding.ListDialogBinding
import java.util.Calendar


class AddRecordsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddRecordsBinding
    var transaction: Transaction? = null

    private lateinit var categoriyAdapter: CategoriyAdapter



    private lateinit var accountAdapter: AccountAdapter
    private lateinit var accountArrayList: ArrayList<ModelAccount>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRecordsBinding.inflate(layoutInflater, container, false)

        binding.incomeBtn.setOnClickListener { view ->
            binding.incomeBtn.background = requireContext().getDrawable(R.drawable.income_selector)
            binding.expenseBtn.background =
                requireContext().getDrawable(R.drawable.default_selector)
            binding.expenseBtn.setTextColor(requireContext().getColor(R.color.textColor))
            binding.incomeBtn.setTextColor(requireContext().getColor(R.color.greenColor))
//            transaction.setType(Constants.INCOME)
        }

        binding.expenseBtn.setOnClickListener { view ->
            binding.incomeBtn.background = requireContext().getDrawable(R.drawable.default_selector)
            binding.expenseBtn.background =
                requireContext().getDrawable(R.drawable.expense_selector)
            binding.incomeBtn.setTextColor(requireContext().getColor(R.color.textColor))
            binding.expenseBtn.setTextColor(requireContext().getColor(R.color.redColor))
//            transaction.setType(Constants.EXPENSE)
        }

        binding.date.setOnClickListener {
            val datePickerDialog = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                DatePickerDialog(requireContext())
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                datePickerDialog.setOnDateSetListener { datePicker: DatePicker, i: Int, i1: Int, i2: Int ->
                    val calendar = Calendar.getInstance()
                    calendar[Calendar.DAY_OF_MONTH] = datePicker.dayOfMonth
                    calendar[Calendar.MONTH] = datePicker.month
                    calendar[Calendar.YEAR] = datePicker.year

//                    val SimpleDateFormat = SimpleDateFormat("dd MMMM, yyyy")
                    val dateToShow = Helper.formatDate(calendar.time)
                    binding.date.setText(dateToShow)
//                    transaction.setDate(calendar.time)
//                    transaction.setId(calendar.time.time)
                }
            }
            datePickerDialog.show()

        }

        binding.category.setOnClickListener {
            val dialogBinding = ListDialogBinding.inflate(inflater)
            val categoryDialog: AlertDialog = AlertDialog.Builder(context).create()
            categoryDialog.setView(dialogBinding.root)

            categoriyAdapter = CategoriyAdapter(requireContext(),Constants.categoryArrayList)

            categoriyAdapter.categoryClickListener = object : CategoriyAdapter.CategoryClickListener{
                override fun onCategoryClicked(position: Int) {
                    binding.category.setText(Constants.categoryArrayList[position].categoryText)
                    categoryDialog.dismiss()
                }
            }
            dialogBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(),3)
            dialogBinding.recyclerView.adapter = categoriyAdapter

            categoryDialog.show()
        }

        binding.account.setOnClickListener {
            val dialogABinding = ListDialogBinding.inflate(inflater)
            val accountDialog: AlertDialog = AlertDialog.Builder(context).create()
            accountDialog.setView(dialogABinding.root)

            accountArrayList = ArrayList()
            accountArrayList.add(ModelAccount(0.0,"Bank"))
            accountArrayList.add(ModelAccount(0.0,"Card"))
            accountArrayList.add(ModelAccount(0.0,"Cash"))
            accountArrayList.add(ModelAccount(0.0,"Savings"))
            accountArrayList.add(ModelAccount(0.0,"Wallet"))
            accountArrayList.add(ModelAccount(0.0,"Paytm"))
            accountArrayList.add(ModelAccount(0.0,"Others"))

            accountAdapter = AccountAdapter(requireContext(),accountArrayList)
            accountAdapter.accountClickListener = object : AccountAdapter.AccountClickListener {
                override fun onAccountClicked(position: Int) {
                    binding.account.setText(accountArrayList[position].accountName)
                    accountDialog.dismiss()
                }

            }
            dialogABinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            dialogABinding.recyclerView.adapter = accountAdapter

            accountDialog.show()

        }

        return binding.root
    }


}
