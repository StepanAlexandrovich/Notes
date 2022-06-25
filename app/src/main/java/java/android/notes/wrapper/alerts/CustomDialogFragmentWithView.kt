package java.android.notes.wrapper.alerts

import java.android.notes.wrapper.alerts.CustomDialogListener
import android.widget.EditText
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import java.android.notes.R

class CustomDialogFragmentWithView : DialogFragment() {
    private var listener: CustomDialogListener? = null
    var editText: EditText? = null
    fun setterListener(listener: CustomDialogListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val customView = inflater.inflate(R.layout.dialog_custom, null)
        editText = customView.findViewById(R.id.editTextCustomView)
        customView.findViewById<View>(R.id.buttonCustomView).setOnClickListener {
            listener!!.onOk()
            dismiss()
        }
        return customView
    }

    companion object {
        const val TAG = "CustomDialogFragmentWithViewTag"
    }
}