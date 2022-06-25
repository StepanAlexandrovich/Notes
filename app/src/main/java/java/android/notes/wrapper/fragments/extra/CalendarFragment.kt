package java.android.notes.wrapper.fragments.extra

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import java.android.notes.R
import android.widget.TextView
import android.app.DatePickerDialog.OnDateSetListener
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.fragment.app.Fragment
import java.android.notes.Time

class CalendarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textViewDate = view.findViewById<TextView>(R.id.textViewDate)
        textViewDate.text = Time.dateYMD

        ////////////////////////////////////////////////////////////////
        val setListener = OnDateSetListener { datePicker, i, i1, i2 ->
            Time.day = i2
            Time.month = i1
            Time.year = i
            textViewDate.text = Time.dateYMD
        }
        val datePickerDialog =
            DatePickerDialog(context!!, setListener, Time.year, Time.month, Time.day)
        ///////////////////////////////////////////////////////////////
        view.findViewById<View>(R.id.buttonEditDate).setOnClickListener {
            datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            datePickerDialog.show()
        }
    }
}