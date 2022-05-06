package com.egor.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll1;
    private LinearLayout ll2;
    private LinearLayout ll3;
    private LinearLayout ll4;
    private LinearLayout ll5;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll1 = findViewById(R.id.weight);
        ll2 = findViewById(R.id.currency);
        ll3 = findViewById(R.id.temperature);
        ll4 = findViewById(R.id.length);
        ll5 =findViewById(R.id.area);

        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.weight:
                intent = new Intent(this, Weight.class);
                startActivity(intent);
                break;
            case R.id.currency:
                intent = new Intent(this, Currency.class);
                startActivity(intent);
                break;
            case R.id.temperature:
                intent = new Intent(this, Temperature.class);
                startActivity(intent);
                break;
            case R.id.length:
                intent = new Intent(this, Length.class);
                startActivity(intent);
                break;
            case R.id.area:
                intent = new Intent(this, Area.class);
                startActivity(intent);
                break;
        }
    }
}