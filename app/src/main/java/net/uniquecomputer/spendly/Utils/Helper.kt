package net.uniquecomputer.spendly.Utils


import android.icu.text.SimpleDateFormat
import android.os.Build
import net.uniquecomputer.spendly.R
import java.util.Date


open class Helper {


    companion object{
        fun formatDate(date: Date?): String? {
            val dateFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                SimpleDateFormat("dd MMMM, yyyy")
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            return dateFormat.format(date)
        }

        fun formatDateByMonth(date: Date?): String? {
            val dateFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                SimpleDateFormat("MMMM, yyyy")
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            return dateFormat.format(date)
        }

    }





}