package java.android.notes.wrapper.alerts

import android.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity


object SympleNotifications {
    fun showAlertDialog(
        activity: AppCompatActivity?,
        listener: CustomDialogListener,
        title: String?,
        textPositiv: String?,
        textNegativ: String?
    ) {
        AlertDialog.Builder(activity)
            .setTitle(title) //.setMessage("message")
            //.setIcon(R.drawable.android_icon)
            .setCancelable(false)
            .setPositiveButton(textPositiv) { dialogInterface, i -> listener.onOk() }
            .setNegativeButton(textNegativ) { dialogInterface, i -> listener.onNo() } //.setNeutralButton("neutral",null)
            .show()
    }
}