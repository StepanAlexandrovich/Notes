package java.android.notes.wrapper.alerts;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.appcompat.app.AppCompatActivity;

public class SympleNotes {
    public static void showAlertDialog(AppCompatActivity activity, CustomDialogListener listener, String title, String textPositiv, String textNegativ){

        new AlertDialog.Builder(activity)
                .setTitle(title)
                //.setMessage("message")
                //.setIcon(R.drawable.android_icon)
                .setCancelable(false)
                .setPositiveButton(textPositiv, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onOk();
                    }
                })
                .setNegativeButton(textNegativ, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onNo();
                    }
                })
                //.setNeutralButton("neutral",null)
                .show();
    }


}
