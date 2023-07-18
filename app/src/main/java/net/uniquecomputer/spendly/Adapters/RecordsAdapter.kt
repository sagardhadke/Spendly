package net.uniquecomputer.spendly.Adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.uniquecomputer.spendly.Model.RecordsModel
import net.uniquecomputer.spendly.R
import net.uniquecomputer.spendly.Utils.Constants
import net.uniquecomputer.spendly.Utils.Helper
import net.uniquecomputer.spendly.databinding.RowRecordsBinding

class RecordsAdapter(private var context: Context, private var recordsArrayList : ArrayList<RecordsModel>) : RecyclerView.Adapter<RecordsAdapter.ViewHolder>(){

    inner class ViewHolder(val binding : RowRecordsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowRecordsBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return recordsArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.RecordsAmount.text = recordsArrayList[position].amount.toString()
        holder.binding.RecordsDate.text = Helper.formatDate(recordsArrayList[position].date)
        holder.binding.AccountLbl.text = recordsArrayList[position].account
        holder.binding.RecordsCategories.text = recordsArrayList[position].category

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.binding.AccountLbl.backgroundTintList = context.getColorStateList(Constants.getAccountsColor(recordsArrayList[position].account)!!)
        }

        val category = Constants.getCategoryDetails(recordsArrayList[position].category)

        category?.let { holder.binding.CategoryIcon.setImageResource(it.categoryImage) }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.binding.CategoryIcon.backgroundTintList = context.getColorStateList(category!!.categoryColor)
        }

        if (recordsArrayList[position].type == Constants.INCOME) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.binding.RecordsAmount.setTextColor(context.getColor(R.color.green))
            }

        } else if (recordsArrayList[position].type == Constants.EXPENSE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.binding.RecordsAmount.setTextColor(context.getColor(R.color.redColor))
            }
        }


    }

}


