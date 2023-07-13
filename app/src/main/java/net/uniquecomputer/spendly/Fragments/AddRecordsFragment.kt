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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.uniquecomputer.spendly.R
import net.uniquecomputer.spendly.databinding.FragmentAddRecordsBinding
import net.uniquecomputer.spendly.databinding.ListDialogBinding
import java.text.SimpleDateFormat
import java.util.Calendar


class AddRecordsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddRecordsBinding
    var transaction: Transaction? = null

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

                    val SimpleDateFormat = SimpleDateFormat("dd MMMM, yyyy")
                    val dateToShow: String = SimpleDateFormat.format(calendar.time)
//                    val dateToShow: String = Helper.formatDate(calendar.time)
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
        }

        return binding.root
    }
}
