package java.android.notes.wrapper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SympleNotes {
    public static void showAlertDialog(AppCompatActivity activity,String title,String textPositiv,String textNegativ){

        new AlertDialog.Builder(activity)
                .setTitle(title)
                //.setMessage("message")
                //.setIcon(R.drawable.android_icon)
                .setCancelable(false)
                .setPositiveButton(textPositiv, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(activity,"yes", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(textNegativ, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(activity,"not", Toast.LENGTH_LONG).show();
                    }
                })
                //.setNeutralButton("neutral",null)
                .show();
    }

}
