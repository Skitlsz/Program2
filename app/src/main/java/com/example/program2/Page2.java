package com.example.program2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Page2 extends AppCompatActivity {
    public static String NameResult,SubjectResult,TimeResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        TextView textView = findViewById(R.id.Hello);
        TextView result = findViewById(R.id.textView6);

        Intent intent = getIntent();
        NameResult = intent.getStringExtra("name");
        SubjectResult = intent.getStringExtra("subject");
        TimeResult = intent.getStringExtra("time");



        textView.setText("Hello " + NameResult + ", " + SubjectResult);

        if(TimeResult.equals("English")){
            result.setText("Valid");
        }
        else if (TimeResult.equals("Not English")) {
            result.setText("Invalid");
        }
        else if (TimeResult.equals("Math")) {
            result.setText("Valid");
        }
        else if (TimeResult.equals("Not Math")) {
            result.setText("Invalid");
        }
        else if (TimeResult.equals("Science")) {
            result.setText("Valid");
        }
        else if (TimeResult.equals("Not Science")) {
            result.setText("Invalid");
        }
        else{
            result.setText("Invalid");
        }

    }
}
