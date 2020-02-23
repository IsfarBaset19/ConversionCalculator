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


        final EditText FromText = (EditText) findViewById(R.id.FromText);
        final EditText ToText= (EditText) findViewById(R.id.ToText);

        final TextView title = findViewById(R.id.title);

        final Button CalculateButton = (Button) findViewById(R.id.CalculateButton);
        final Button ClearButton = (Button) findViewById(R.id.ClearButton);
        final Button ModeButton = (Button) findViewById(R.id.ModeButton);

        ClearButton.setOnClickListener(v -> {
            FromText.setText("");
            ToText.setText("");
        });





    }


}
