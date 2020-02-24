package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//import android.support.*;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.view.*;
import android.content.*;

public class MainActivity extends AppCompatActivity {

    public static final int UNIT_SELECTION = 1;

    int lengthUnit = 1;

    UnitsConverter.CalculatorMode currentMode = UnitsConverter.CalculatorMode.Length;
    UnitsConverter.LengthUnits currentLengthFromUnit = UnitsConverter.LengthUnits.Meters;
    UnitsConverter.LengthUnits currentLengthToUnit = UnitsConverter.LengthUnits.Yards;
    UnitsConverter.VolumeUnits currentVolumeFromUnit = UnitsConverter.VolumeUnits.Gallons;
    UnitsConverter.VolumeUnits currentVolumeToUnit = UnitsConverter.VolumeUnits.Liters;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true; // "true" means the menu should be visible
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.SettingsButton) {
            Intent intent = new Intent (MainActivity.this, SettingsActivity.class);

            intent.putExtra("lengthUnit", lengthUnit);
            setResult(SettingsActivity.LENGTH_UNIT,intent);
            startActivity(intent);
            finish();

            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText ToText = (EditText) findViewById(R.id.ToText);
        final EditText FromText= (EditText) findViewById(R.id.FromText);

        final TextView title = findViewById(R.id.title);

        final TextView fromUnit = findViewById(R.id.FromUnit);
        final TextView toUnit = findViewById(R.id.ToUnit);

        final Button CalculateButton = (Button) findViewById(R.id.CalculateButton);
        final Button ClearButton = (Button) findViewById(R.id.ClearButton);
        final Button ModeButton = (Button) findViewById(R.id.ModeButton);

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
                fromUnit.setText("Gallons");
                toUnit.setText("Liters");

            }
            else if(currentMode == UnitsConverter.CalculatorMode.Volume){
                currentMode = UnitsConverter.CalculatorMode.Length;
                fromUnit.setText("Meters");
                toUnit.setText("Yards");
            }

        });



    }


}
