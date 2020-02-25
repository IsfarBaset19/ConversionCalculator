package com.example.calculator;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.*;
import android.widget.Spinner;

import android.content.*;

public class SettingsActivity extends AppCompatActivity {


    public static int LENGTH_UNIT = 1 ;

    private String FromUnit = "Meters";
    private String ToUnit = "Meters";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("unit1", FromUnit);
                intent.putExtra("unit2", ToUnit);
                intent.putExtra("LENGTH_UNIT", LENGTH_UNIT);


                setResult(MainActivity.UNIT_SELECTION,intent);

                finish();
            }

        });


        Intent payload = getIntent();

        String mode = "";

        if (payload.hasExtra("mode")) {
             mode = payload.getStringExtra("mode");
        }

        final Spinner FromSpinner;
        FromSpinner = findViewById(R.id.FromSpinner);
        final Spinner ToSpinner = findViewById(R.id.ToSpinner);



        ArrayAdapter<CharSequence> adapter;

        if(mode.equals("volume")) {
            adapter = ArrayAdapter.createFromResource(this, R.array.Volume, android.R.layout.simple_spinner_item);
    }
        else{
            adapter = ArrayAdapter.createFromResource(this,
                    R.array.Length, android.R.layout.simple_spinner_item);;
        }



        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FromSpinner.setAdapter(adapter);
        FromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FromUnit = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ToSpinner.setAdapter(adapter);
        ToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ToUnit = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

}
