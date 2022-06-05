package java.android.notes.wrapper;

import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.android.notes.R;
import java.android.notes.Time;

public class CalendarFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_calendar,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textViewDate = view.findViewById(R.id.textViewDate);
        textViewDate.setText(Time.getDateYMD());

        ////////////////////////////////////////////////////////////////
        DatePickerDialog.OnDateSetListener setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Time.setDay(i2);
                Time.setMonth(i1);
                Time.setYear(i);

                textViewDate.setText(Time.getDateYMD());
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),setListener,Time.getYear(),Time.getMonth(),Time.getDay());
        ///////////////////////////////////////////////////////////////

        view.findViewById(R.id.buttonEditDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

    }

}
