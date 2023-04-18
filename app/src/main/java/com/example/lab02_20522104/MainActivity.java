package com.example.lab02_20522104;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> salary;
    ArrayList<String> fullname;
    TextView textView, textView1, list, result;
    EditText inputText, inputText1;
    Button submit;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView); //ten
        inputText = findViewById(R.id.inputText);

        textView1 = findViewById(R.id.textView1); //gross salary
        inputText1 = findViewById(R.id.inputText1);

        list = findViewById(R.id.list);
        result= findViewById(R.id.result);
        submit = findViewById(R.id.submit);

        sharedPreferences = getSharedPreferences("dataSalary", MODE_PRIVATE);
        inputText.setText(sharedPreferences.getString("name", ""));
        inputText1.setText(sharedPreferences.getString("netsalary",""));

        fullname = new ArrayList<>();
        salary = new ArrayList<>();
    }
    @SuppressLint("SetTextI18n")
    public void onUpdate(View view) {
        String name = inputText.getText().toString().trim();

        double grossSalary = Double.parseDouble(inputText1.getText().toString());
        double dependentCost = 11000000;

        double temp = grossSalary * (1 - 0.105);
        String netSalary1 = String.valueOf(temp);

        double temp2 = dependentCost + (temp - dependentCost) * (1 - 0.05);
        String netSalary2 = String.valueOf(temp2);

        if (temp <= dependentCost) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("netsalary", netSalary1);
            editor.apply();
            list.setText(inputText.getText() + " - Net Salary: " + netSalary1);

            salary.add(netSalary1);
            fullname.add(name);
            for(int i=1; i < salary.size(); i++) {
                result.setText(fullname.get(0) + " - Net Salary: " + salary.get(0));
            }

            inputText.setText("");
            inputText.requestFocus();
            inputText1.setText("");
            inputText1.requestFocus();

        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("netsalary", netSalary2);
            editor.apply();
            list.setText(inputText.getText() + " - Net Salary: " + netSalary2);

            salary.add(netSalary2);
            fullname.add(name);
            for(int i=1; i < salary.size(); i++) {
                result.setText(fullname.get(0) + " - Net Salary: " + salary.get(0));
            }

            inputText.setText("");
            inputText.requestFocus();
            inputText1.setText("");
            inputText1.requestFocus();
        }
    }
}