package com.example.yassi.schoolup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Resrevation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resrevation);
        setContentView(R.layout.activity_main);
        final Spinner monspin =(Spinner) findViewById(R.id.monspin);
        final List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Choose the purpose");
        spinnerArray.add("booking the classroom for a club meeting");
        spinnerArray.add("booking the classroom for revision");
        spinnerArray.add("booking the classroom to celebrate a school event");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monspin.setAdapter(adapter);

    }
}
