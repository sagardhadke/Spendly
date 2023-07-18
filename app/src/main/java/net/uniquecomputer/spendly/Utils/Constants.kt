package net.uniquecomputer.spendly.Utils

import net.uniquecomputer.spendly.Model.ModelCategory
import net.uniquecomputer.spendly.R

open class Constants {

    companion object{

        var INCOME = "INCOME"
        var EXPENSE = "EXPENSE"


        lateinit var categoryArrayList: ArrayList<ModelCategory>
       fun setCategories(){
           categoryArrayList = ArrayList()
            categoryArrayList.add(ModelCategory("Salary", R.drawable.ic_salary, R.color.category1))
            categoryArrayList.add(
                ModelCategory("Business",
                    R.drawable.ic_business,
                    R.color.category2)
            )
            categoryArrayList.add(
                ModelCategory("Investment",
                    R.drawable.ic_investment,
                    R.color.category3)
            )
            categoryArrayList.add(ModelCategory("Loan", R.drawable.ic_loan, R.color.category4))
            categoryArrayList.add(ModelCategory("Rent", R.drawable.ic_rent, R.color.category5))
            categoryArrayList.add(ModelCategory("Other", R.drawable.ic_other, R.color.category6))
        }

    }

}