package com.example.btandroidtuan3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnDialog1;
    Button btnDialog2;
    Calendar calendar;

    SimpleDateFormat sdf1;  //format text view to Date Format
    SimpleDateFormat sdf2;  // format text view to Time Format

    ArrayList<String> listEmail;    //list of email
    ArrayAdapter<String> adapterEmail; // adapter for list and view

    ArrayList<String> listTimeZone; // list of Time Zone
    ArrayAdapter<String> adapterTimeZone;   // Adapter for list and view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createControls(); //  Create 2 btn dialog1 and dialog2
        createEvents(); // Create event listener for btnDialog1 and btnDialog2
    }


    /**
     * Create event listener for btnDialog1 and btnDialog2
     */
    private void createEvents() {

        btnDialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog1();
            }
        });

        btnDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog2();
            }
        });
    }

    /*
    * Show dialog1
     */
    private void displayDialog1() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog1layout);

        TextView txtCancel = dialog.findViewById(R.id.txtCancel);


        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    /*
    * Show dialog2
     */
    private void displayDialog2() {

        Dialog dialog = new Dialog(this,R.style.MyAlertDialogStyle);
        dialog.setContentView(R.layout.dialog2layout);

        Spinner spEmail = dialog.findViewById(R.id.spEmail);
        EditText txtEventName = dialog.findViewById(R.id.txtEventName);
        EditText txtLocation = dialog.findViewById(R.id.txtLocation);

        EditText txtFromDate = dialog.findViewById(R.id.txtFromDate);
        ImageButton btnFromDate = dialog.findViewById(R.id.btnFromDate);
        EditText txtFromTime = dialog.findViewById(R.id.txtFromTime);
        ImageButton btnFromTime = dialog.findViewById(R.id.btnFromTime);

        EditText txtToDate = dialog.findViewById(R.id.txtToDate);
        ImageButton btnToDate = dialog.findViewById(R.id.btnToDate);
        EditText txtToTime = dialog.findViewById(R.id.txtToTime);
        ImageButton btnToTime = dialog.findViewById(R.id.btnToTime);

        CheckBox chkAllDay = dialog.findViewById(R.id.chkAllDay);
        Spinner spTimeZone = dialog.findViewById(R.id.spTimeZone);

        ImageButton btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnSave = dialog.findViewById(R.id.btnSave);

        calendar = Calendar.getInstance();
        sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        sdf2 = new SimpleDateFormat("HH:mm");

        txtFromDate.setText(sdf1.format(calendar.getTime()));
        txtFromTime.setText(sdf2.format(calendar.getTime()));

        txtToDate.setText(sdf1.format(calendar.getTime()));
        txtToTime.setText(sdf2.format(calendar.getTime()));

        listEmail = new ArrayList<String>();
        listEmail.add("tientien04.ss@gmail.com");
        listEmail.add("truongan@gmail.com");
        listEmail.add("thieuquan@gmail.com");
        listEmail.add("giabinh@gmail.com");
        adapterEmail = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,listEmail);
        adapterEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEmail.setAdapter(adapterEmail);

        listTimeZone = new ArrayList<String>();
        listTimeZone.add("HaNoi Time");
        listTimeZone.add("BangKok Time");
        adapterTimeZone = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,listTimeZone);
        adapterTimeZone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTimeZone.setAdapter(adapterTimeZone);

        // listener for all event in activity
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btnFromDate||v.getId() == R.id.btnToDate)
                {
                    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            calendar.set(calendar.DAY_OF_MONTH,dayOfMonth);
                            calendar.set(calendar.MONTH,month);
                            calendar.set(calendar.YEAR,year);

                            if(v.getId() == R.id.btnFromDate)
                            {
                                txtFromDate.setText(sdf1.format(calendar.getTime()));
                            }
                            else
                            {
                                txtToDate.setText(sdf1.format(calendar.getTime()));
                            }

                        }
                    };

                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                            listener,
                            calendar.get(calendar.YEAR),
                            calendar.get(calendar.MONTH),
                            calendar.get(calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
                if(v.getId() == R.id.btnFromTime||v.getId() == R.id.btnToTime)
                {
                    TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            calendar.set(calendar.HOUR_OF_DAY,hourOfDay);
                            calendar.set(calendar.MINUTE,minute);

                            if(v.getId() == R.id.btnFromTime)
                            {
                                txtFromTime.setText(sdf2.format(calendar.getTime()));
                            }
                            else
                            {
                                txtToTime.setText(sdf2.format(calendar.getTime()));
                            }

                        }
                    };

                    TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                            listener,
                            calendar.get(calendar.HOUR_OF_DAY),
                            calendar.get(calendar.MINUTE),
                            true);
                    timePickerDialog.show();
                }
            }
        };

        // Set listener
        btnFromDate.setOnClickListener(listener);
        btnToDate.setOnClickListener(listener);
        btnFromTime.setOnClickListener(listener);
        btnToTime.setOnClickListener(listener);

        // Cancel dialog
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }


    /**
     * Find btnDialog1 and btnDialog2
     */
    private void createControls() {

        btnDialog1 = findViewById(R.id.btnDialog1);
        btnDialog2 = findViewById(R.id.btnDialog2);
    }
}