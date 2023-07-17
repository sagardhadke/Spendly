package net.uniquecomputer.spendly.Adapters

import android.content.Context
import android.icu.util.ULocale
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.uniquecomputer.spendly.Model.ModelAccount
import net.uniquecomputer.spendly.databinding.RowAccountBinding

class AccountAdapter(private var context: Context, val accountArrayList: ArrayList<ModelAccount>) : RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RowAccountBinding) : RecyclerView.ViewHolder(binding.root)

    interface AccountClickListener {
        fun onAccountClicked(position: Int)
    }

    var accountClickListener: AccountClickListener? = null

    fun AccountAdapter(
        context: Context?,
        accountArrayList: ArrayList<ModelAccount>,
        accountClickListener: AccountAdapter.AccountClickListener?
    ) {
        this.context = context!!
        val accountArrayList = accountArrayList
        this.accountClickListener = accountClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowAccountBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return accountArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (accountArrayList.isEmpty()) {
            // handle empty accountArrayList
            return
        }
        if (position < 0 || position >= accountArrayList.size) {
            // handle invalid position
            return
        }

        holder.binding.accountName.text = accountArrayList[position].accountName

        holder.itemView.setOnClickListener {
            accountClickListener?.onAccountClicked(position = position)
        }
    }

}
