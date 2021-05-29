package com.example.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class formactivity extends AppCompatActivity {
    private static final String TAG = "formactivity";

    Spinner gender;
    EditText weigh;
    EditText heigh;
    EditText inc;
    EditText ag;
    Button Submitform;
    int weight;
    int height;
    int age;
    int inch;
    String gend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formactivity);
        gender=(Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> h=new ArrayAdapter<String>(formactivity.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.genders));
        h.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(h);

        weigh=(EditText) findViewById(R.id.weight);
        heigh=(EditText) findViewById(R.id.heightinft);
        ag=(EditText) findViewById(R.id.age);
        inc=(EditText) findViewById(R.id.inches);
        Submitform=(Button) findViewById(R.id.button);

        Submitform.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              gend = gender.getSelectedItem().toString();

                                              if (TextUtils.isEmpty(weigh.getText().toString())) {
                                                  Toast toast1 = Toast.makeText(getApplicationContext(), "Feild Cannot be Empty", Toast.LENGTH_LONG);
                                                  toast1.show();
                                                  weigh.setError("invalid");
                                                  return;

                                              } else if (Integer.parseInt(weigh.getText().toString()) > 250) {
                                                  Toast toast1 = Toast.makeText(getApplicationContext(), "Enter a Valid weight", Toast.LENGTH_LONG);
                                                  toast1.show();
                                                  weigh.setError("invalid");

                                              } else if (TextUtils.isEmpty(heigh.getText().toString())) {
                                                  Toast toast = Toast.makeText(getApplicationContext(), "Feild Cannot be Empty", Toast.LENGTH_LONG);
                                                  toast.show();
                                                  heigh.setError("invalid");

                                              } else if (Integer.parseInt(heigh.getText().toString()) > 7) {
                                                  Toast toast = Toast.makeText(getApplicationContext(), "Enter a Valid height", Toast.LENGTH_LONG);
                                                  toast.show();
                                                  heigh.setError("invalid");

                                              }
                                              else if (TextUtils.isEmpty(inc.getText().toString())) {
                                                  Toast toast = Toast.makeText(getApplicationContext(), "Feild Cannot be Empty", Toast.LENGTH_LONG);
                                                  toast.show();
                                                  inc.setError("invalid");

                                              } else if (Integer.parseInt(inc.getText().toString()) > 12) {
                                                  Toast toast = Toast.makeText(getApplicationContext(), "Inches should be less than 12", Toast.LENGTH_LONG);
                                                  toast.show();
                                                  inc.setError("invalid");

                                              }
                                              else if (TextUtils.isEmpty(ag.getText().toString())) {
                                                  Toast toast2 = Toast.makeText(getApplicationContext(), "Feild Cannot be Empty", Toast.LENGTH_LONG);
                                                  toast2.show();
                                                  ag.setError("invalid");

                                              } else if (Integer.parseInt(ag.getText().toString())> 120) {
                                                  Toast toast2 = Toast.makeText(getApplicationContext(), "Enter a Valid Age", Toast.LENGTH_LONG);
                                                  toast2.show();
                                                  ag.setError("invalid");
                                              }
                                              else {
                                                  weight=Integer.parseInt(weigh.getText().toString());
                                                  height=Integer.parseInt(heigh.getText().toString());
                                                  age=Integer.parseInt(ag.getText().toString());
                                                  inch=Integer.parseInt(inc.getText().toString());
                                                  Intent intent = new Intent(getApplicationContext(), BMI.class);
                                                  intent.putExtra("weight", weight);
                                                  intent.putExtra("height", height);
                                                  intent.putExtra("inches", inch);
                                                  intent.putExtra("age", age);
                                                  intent.putExtra("gender", gend);
                                                  startActivity(intent);
                                              }
                                          }
                                      }
        );




}
    }