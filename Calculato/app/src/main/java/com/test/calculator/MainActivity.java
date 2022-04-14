package com.test.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.test.calculator.Calculator;
import com.test.calculator.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnEquals;
    private Button btnDot;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnMultiply;
    private Button btnDivide;
    private Button btnClear;
    private Button btnStepen;
    private TextView textView;
    private String value;
    private Calculator calc;
    private HorizontalScrollView s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnClear = findViewById(R.id.btnClear);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnDivide = findViewById(R.id.btnDivide);
        btnDot = findViewById(R.id.btnDot);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnEquals = findViewById(R.id.btnEquals);
        btnStepen = findViewById(R.id.btnStepen);
        textView = findViewById(R.id.textView);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnStepen.setOnClickListener(this);
        calc = new Calculator();
        s = findViewById(R.id.s);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btn0:
                value = "0";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btn1:
                value = "1";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btn2:
                value = "2";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btn3:
                value = "3";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btn4:
                value = "4";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btn5:
                value = "5";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btn6:
                value = "6";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btn7:
                value = "7";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btn8:
                value = "8";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btn9:
                value = "9";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btnClear:
                value = "clear";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btnMinus:
                value = "-";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btnPlus:
                value = "+";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btnDivide:
                value = "/";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btnDot:
                value = ".";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btnEquals:
                value = "=";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btnMultiply:
                value = "*";
                textView.setText(calc.calculation(value));
                scroll();
                break;
            case R.id.btnStepen:
                value = "^";
                textView.setText(calc.calculation(value));
                scroll();
                break;
        }
    }
    public void scroll(){
        s.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
    }
}