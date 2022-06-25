package java.android.notes

import java.text.SimpleDateFormat
import java.util.*

object Time {
    var calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd.MMMM.yyyy")
    var day: Int
        get() = calendar[Calendar.DAY_OF_MONTH]
        set(day) {
            calendar[Calendar.DAY_OF_MONTH] = day
        }
    var month: Int
        get() = calendar[Calendar.MONTH]
        set(month) {
            calendar[Calendar.MONTH] = month
        }
    var year: Int
        get() = calendar[Calendar.YEAR]
        set(year) {
            calendar[Calendar.YEAR] = year
        }
    val dateYMD: String
        get() = dateFormat.format(calendar.time)
}