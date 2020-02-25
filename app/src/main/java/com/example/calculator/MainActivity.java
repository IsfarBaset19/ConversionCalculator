package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
//import android.support.*;
import android.widget.EditText;
import android.widget.*;
import android.widget.TextView;
import android.view.*;
import android.content.*;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final int UNIT_SELECTION = 1;

    int lengthUnit = 1;

    UnitsConverter.CalculatorMode currentMode = UnitsConverter.CalculatorMode.Length;
    UnitsConverter.LengthUnits currentLengthFromUnit = UnitsConverter.LengthUnits.Yards;
    UnitsConverter.LengthUnits currentLengthToUnit = UnitsConverter.LengthUnits.Meters;
    UnitsConverter.VolumeUnits currentVolumeFromUnit = UnitsConverter.VolumeUnits.Gallons;
    UnitsConverter.VolumeUnits currentVolumeToUnit = UnitsConverter.VolumeUnits.Liters;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.SettingsButton) {
            Intent intent = new Intent (MainActivity.this, SettingsActivity.class);

            intent.putExtra("mode", currentMode == UnitsConverter.CalculatorMode.Length?
                    "length":"volume");

            setResult(SettingsActivity.LENGTH_UNIT,intent);
            startActivityForResult(intent,UNIT_SELECTION);



            return true;
        }
        return false;
    }

   // Intent payload = getIntent();
   // if(payload.hasExtra("lengthUnit")){
   //     String isLength = payload.getStringExtra("lengthUnit");
    //    TextView FromText = (TextView) findViewById(R.id.FromUnitLabel);
    //    FromText.setText(isLength);
    //
    // }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == UNIT_SELECTION) {
            TextView tv1 = (TextView) findViewById(R.id.FromUnitLabel);
            tv1.setText("" + data.getStringExtra("unit1"));
            TextView tv2 = (TextView) findViewById(R.id.ToUnitLabel);
            tv2.setText("" + data.getStringExtra("unit2"));
        }

     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);


        final EditText ToText = (EditText) findViewById(R.id.ToText);
        final EditText FromText= (EditText) findViewById(R.id.FromText);

        final TextView title = findViewById(R.id.title);

        final TextView fromUnit = findViewById(R.id.FromUnitLabel);
        final TextView toUnit = findViewById(R.id.ToUnitLabel);

        final Button CalculateButton = (Button) findViewById(R.id.CalculateButton);
        final Button ClearButton = (Button) findViewById(R.id.ClearButton);
        final Button ModeButton = (Button) findViewById(R.id.ModeButton);


        Intent payload = getIntent();
        if (payload.hasExtra("LENGTH_UNIT")) {
            this.lengthUnit = payload.getIntExtra("LENGTH_UNIT", 1);
        }



        FromText.setOnTouchListener(new android.view.View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ToText.setText("");
                return false;
            }

        });

        ToText.setOnTouchListener(new android.view.View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FromText.setText("");
                return false;
            }

        });

        CalculateButton.setOnClickListener(v -> {
            double convert = 0.0;
            try {
                convert = Double.valueOf(FromText.getText().toString());
            }
            catch (NumberFormatException e){

            }

            if(currentMode == UnitsConverter.CalculatorMode.Length){
                ToText.setText(Double.toString(UnitsConverter.convert(convert,currentLengthFromUnit,currentLengthToUnit)));
            }
            else if(currentMode == UnitsConverter.CalculatorMode.Volume){
                ToText.setText(Double.toString(UnitsConverter.convert(convert,currentVolumeFromUnit,currentVolumeToUnit)));
            }
        });

        ClearButton.setOnClickListener(v -> {
            ToText.setText("");
            FromText.setText("");
        });

        ModeButton.setOnClickListener(v -> {
            if(currentMode == UnitsConverter.CalculatorMode.Length) {
                currentMode = UnitsConverter.CalculatorMode.Volume;
                fromUnit.setText(currentVolumeFromUnit.toString());
                toUnit.setText(currentVolumeToUnit.toString());
                title.setText("Volume Converter");



            }
            else if(currentMode == UnitsConverter.CalculatorMode.Volume){
                currentMode = UnitsConverter.CalculatorMode.Length;
                fromUnit.setText(currentLengthFromUnit.toString());
                toUnit.setText(currentLengthToUnit.toString());
                title.setText("Length Converter");
            }

        });



    }





}
