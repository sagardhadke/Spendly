package net.uniquecomputer.spendly.Adapters

import android.content.Context
import android.icu.util.ULocale
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import net.uniquecomputer.spendly.Model.ModelCategory
import net.uniquecomputer.spendly.databinding.SampleCategoryItemBinding


class CategoriyAdapter(private var context: Context, val categoryArrayList: java.util.ArrayList<ModelCategory>) : RecyclerView.Adapter<CategoriyAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: SampleCategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface CategoryClickListener {
        fun onCategoryClicked(position: Int)

    }

    var categoryClickListener: CategoryClickListener? = null

    fun CategoryAdapter(
        context: Context?,
        categories: ArrayList<ULocale.Category?>,
        categoryClickListener: CategoryClickListener?
    ) {
        this.context = context!!
       val  categories = categories
        this.categoryClickListener = categoryClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SampleCategoryItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return categoryArrayList.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.categoryImage.setImageResource(categoryArrayList[position].categoryImage)
        holder.binding.categoryText.text = categoryArrayList[position].categoryText

        holder.binding.categoryImage.backgroundTintList = context.getColorStateList(categoryArrayList[position].categoryColor)

        holder.itemView.setOnClickListener {
            categoryClickListener?.onCategoryClicked(position = position)
        }

    }

}