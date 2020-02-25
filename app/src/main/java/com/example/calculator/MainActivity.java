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

        TextView tv1 = (TextView) findViewById(R.id.FromUnitLabel);
        TextView tv2 = (TextView) findViewById(R.id.ToUnitLabel);
        if (resultCode == UNIT_SELECTION) {

            tv1.setText("" + data.getStringExtra("unit1"));
            tv2.setText("" + data.getStringExtra("unit2"));
        }


        if(data.getStringExtra("unit1").equals("Meters")){
            currentLengthFromUnit = UnitsConverter.LengthUnits.Meters;
        }
        else if(data.getStringExtra("unit1").equals("Yards")){
            currentLengthFromUnit = UnitsConverter.LengthUnits.Yards;
        }
        else if(data.getStringExtra("unit1").equals("Miles")){
            currentLengthFromUnit = UnitsConverter.LengthUnits.Miles;
        }
        else if(data.getStringExtra("unit1").equals("Gallons")){
            currentVolumeFromUnit = UnitsConverter.VolumeUnits.Gallons;
        }
        else if(data.getStringExtra("unit1").equals("Liters")){
            currentVolumeFromUnit = UnitsConverter.VolumeUnits.Liters;
        }
        else if(data.getStringExtra("unit1").equals("Quarts")){
            currentVolumeFromUnit = UnitsConverter.VolumeUnits.Quarts;
        }



        if(data.getStringExtra("unit2").equals("Meters")){
            currentLengthToUnit = UnitsConverter.LengthUnits.Meters;
        }
        else if(data.getStringExtra("unit2").equals("Yards")){
            currentLengthToUnit = UnitsConverter.LengthUnits.Yards;
        }
        else if(data.getStringExtra("unit2").equals("Miles")){
            currentLengthToUnit = UnitsConverter.LengthUnits.Miles;
        }
        else if(data.getStringExtra("unit2").equals("Gallons")){
            currentVolumeToUnit = UnitsConverter.VolumeUnits.Gallons;
        }
        else if(data.getStringExtra("unit2").equals("Liters")){
            currentVolumeToUnit = UnitsConverter.VolumeUnits.Liters;
        }
        else if(data.getStringExtra("unit2").equals("Quarts")){
            currentVolumeToUnit = UnitsConverter.VolumeUnits.Quarts;
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



        CalculateButton.setOnClickListener(v -> {
            double convert = 0.0;
            try {
                convert = Double.valueOf(FromText.getText().toString());
            }
            catch (NumberFormatException e){

            }

            if(currentMode == UnitsConverter.CalculatorMode.Length){
                ToText.setText(Double.toString(UnitsConverter.convert(convert,currentLengthFromUnit,currentLengthToUnit)));
                System.out.println(currentLengthFromUnit);
                System.out.println(currentLengthToUnit);
            }
            else if(currentMode == UnitsConverter.CalculatorMode.Volume){
                ToText.setText(Double.toString(UnitsConverter.convert(convert,currentVolumeFromUnit,currentVolumeToUnit)));
                System.out.println(currentVolumeFromUnit);
                System.out.println(currentVolumeToUnit);
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
