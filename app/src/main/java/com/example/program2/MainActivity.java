package com.example.program2;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private String NameResult,SubjectResult,timeResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //arrays inside the spinner
        String[] Subjects = new String[] {
                "Math", "English", "Science"
        };
        Spinner s = (Spinner) findViewById(R.id.Spinner01);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        //button variables
        TextView start = findViewById(R.id.startText);
        TextView end = findViewById(R.id.endText);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button submit = findViewById(R.id.check);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Display the start time picker
                TimePickerDialog timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay1, int minute) {
                                // Handle the selected start time
                                String startTime;
                                if (hourOfDay1 >= 12) {
                                    startTime = String.format("%02d:%02d PM", hourOfDay1 % 12, minute);
                                } else {
                                    startTime = String.format("%02d:%02d AM", hourOfDay1 % 12, minute);
                                }
                                button1.setText(startTime);
                            }
                        }, 0, 0, true);
                timePicker.show();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Display the end time picker

                TimePickerDialog timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay2, int minute) {
                                // Handle the selected end time
                                String endTime;
                                if (hourOfDay2 >= 12) {
                                    endTime = String.format("%02d:%02d PM", hourOfDay2 % 12, minute);
                                } else {
                                    endTime = String.format("%02d:%02d AM", hourOfDay2 % 12, minute);
                                }
                                button2.setText(endTime);
                            }
                        }, 0, 0, true);
                timePicker.show();
            }
        });

        //result strings
        EditText editText = findViewById(R.id.editTextPersonName);
        Spinner spinner = findViewById(R.id.Spinner01);
        TextView startText = findViewById(R.id.startText);

        //button click for checking
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NameResult = editText.getText().toString();
                SubjectResult = spinner.getSelectedItem().toString();


                // extract the start and end times from the button text
                String startTimeString = button1.getText().toString();
                String endTimeString = button2.getText().toString();

                // parse the time strings to extract the hour values
                int startHour = Integer.parseInt(startTimeString.substring(0, 2));
                int endHour = Integer.parseInt(endTimeString.substring(0, 2));

                // determine if the start time is less than or equal to 2 hours than the end time
                boolean isEnglish = (endHour - startHour) <= 2;
                boolean isMath = (endHour - startHour) <= 3;
                boolean isScience = (endHour - startHour) <= 6;

                // display the result on the startText view

                if (s.getSelectedItem().toString().equals("English") && isEnglish) {
                    startText.setText("English");
                }
                else if (s.getSelectedItem().toString().equals("English") && !isEnglish) {
                    startText.setText("Not English");
                }
                else if (s.getSelectedItem().toString().equals("Math") && isMath) {
                    startText.setText("Math");
                }
                else if (s.getSelectedItem().toString().equals("Math") && !isMath) {
                    startText.setText("Not Math");
                }
                else if (s.getSelectedItem().toString().equals("Science") && isScience) {
                    startText.setText("Science");
                }
                else if (s.getSelectedItem().toString().equals("English") && !isScience)  {
                    startText.setText("Not Science");
                }
                else {
                    startText.setText("Bobo di gumana");
                }

                // get the value in the startText TextView
                timeResult = startText.getText().toString();
                // start the new activity with the name and subject values
                Intent intent = new Intent(MainActivity.this, Page2.class);
                intent.putExtra("name", NameResult);
                intent.putExtra("subject", SubjectResult);
                intent.putExtra("time", timeResult);
                startActivity(intent);
            }
        });

}

}
