package java.android.notes.wrapper.alerts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.android.notes.R;
import java.android.notes.wrapper.alerts.CustomDialogListener;

public class CustomDialogFragmentWithView extends DialogFragment {
    public final static String TAG = "CustomDialogFragmentWithViewTag";

    private CustomDialogListener listener;

    public EditText editText;

    public void setListener(CustomDialogListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View customView = inflater.inflate(R.layout.dialog_custom,null);

        editText = customView.findViewById(R.id.editTextCustomView);

        customView.findViewById(R.id.buttonCustomView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOk();
                dismiss();
            }
        });

        return customView;
    }
}
