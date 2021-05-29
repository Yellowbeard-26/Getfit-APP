package com.example.getfit;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMI extends AppCompatActivity {
    private static final String TAG = "BMI";
    int varweight;
    int weighty;
    int heighty;
    int inchy;
    int agey;
    double bmr;
    double bmi;
    double totalheight;
    int maintainance;
    int daily;
    int weeks;
    int temp;
    Drawable drawable;
    String gendery;
    TextView result;
    TextView statement;
    Spinner Excercise;
    EditText Goalweight;
    Button plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i);
        Intent intent=getIntent();
        weighty=intent.getIntExtra("weight",0);
        heighty=intent.getIntExtra("height",0);
        inchy=intent.getIntExtra("inches",0);
        agey=intent.getIntExtra("age",0);
        gendery=intent.getStringExtra("gender");
        totalheight=(heighty*0.305)+(inchy*0.0254);
        bmi=(weighty)/(totalheight*totalheight);
        String y=""+bmi;
        String st;
        result=(TextView) findViewById(R.id.result);
        statement=(TextView) findViewById(R.id.statement);
        result.setText(y);
        ImageView bmimeter=(ImageView) findViewById(R.id.bmimeter);
        if(bmi<=18.5) {
            drawable = getResources().getDrawable(R.drawable.underweight);
            st=" Underweight";
            statement.append(st);
        }
        else if(bmi>18.5&&bmi<=24.9)
        {
            drawable=getResources().getDrawable(R.drawable.fit);
            st=" healthy";
            statement.append(st);
        }
        else if(bmi>=25&&bmi<=29.9)
        {
            drawable=getResources().getDrawable(R.drawable.slightlyobese);
            st=" Moderatly Obese";
            statement.append(st);
        }
        else if(bmi>30)
        {
            drawable=getResources().getDrawable(R.drawable.obese);
            st=" Obese";
            statement.append(st);
        }
        bmimeter.setImageDrawable(drawable);
        Excercise=(Spinner) findViewById(R.id.excercise);

        ArrayAdapter<String> h=new ArrayAdapter<String>(BMI.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.excerfactor));
        h.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Excercise.setAdapter(h);

        Goalweight=(EditText) findViewById(R.id.goal);
        plan=(Button) findViewById(R.id.planbut);
        Log.e(TAG, "onCreate: gender is "+gendery );

        if(gendery.equalsIgnoreCase("Male"))
        {
            bmr = 66.47 + (((13.75 * weighty) + (5.003 * ((heighty * 30.48) + (inchy * 2.54)))) - (6.755 * agey));
            Log.e(TAG, "onCreate: bmr "+bmr );
        }
        else if(gendery.equalsIgnoreCase("Female"))
        {
            bmr=655.1+(9.563*weighty)+(1.85*((heighty*30.48)+(inchy*2.54)))-(4.676*agey);
            Log.e(TAG, "onCreate: bmr "+bmr );
        }
        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(Goalweight.getText().toString())) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Feild Cannot be Empty", Toast.LENGTH_LONG);
                    toast1.show();
                    Goalweight.setError("invalid");
                }
                else if ((Integer.parseInt(Goalweight.getText().toString())) > 250) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Enter a Valid weight", Toast.LENGTH_LONG);
                    toast1.show();
                    Goalweight.setError("invalid");
                }
                else
                {
                    varweight=(Integer.parseInt(Goalweight.getText().toString()));
                    String k=Excercise.getSelectedItem().toString();
                    switch(k)
                    {
                        case "little or no exercise":
                        {
                            maintainance= (int) Math.round(bmr*1.2);
                            Log.e(TAG, "onClick: ex factor is 1.2" );
                            break;
                    }
                        case "Lightly active":
                        {
                            maintainance= (int) Math.round(bmr*1.375);
                            Log.e(TAG, "onClick: ex factor is 1.375" );
                            break;
                        }
                        case "Moderately active":
                        {
                            maintainance= (int) Math.round(bmr*1.55);
                            Log.e(TAG, "onClick: ex factor is 1.55" );
                            break;
                        }
                        case "Very active": {
                            maintainance= (int) Math.round(bmr * 1.725);
                            Log.e(TAG, "onClick: ex factor is 1.725" );
                            break;
                        }
                        case "If you are extra active":
                        {
                            maintainance= (int) Math.round(bmr*1.9);
                            Log.e(TAG, "onClick: ex factor is 1.9" );
                            break;
                        }
                }
                if(varweight > weighty)
                    {
                        daily=maintainance+500;
                        temp=varweight-weighty;
                        weeks=(15*temp)/7;
                    }
                   else if(varweight < weighty)
                    {
                        daily=maintainance-800;
                        temp=weighty-varweight;
                        weeks=(10*temp)/7;

                    }
                   Intent fin=new Intent(getApplicationContext(), com.example.getfit.plan.class);
                   fin.putExtra("maintain",maintainance);
                   fin.putExtra("daily",daily);
                   fin.putExtra("weeks",weeks);
                   fin.putExtra("goal",varweight);
                   fin.putExtra("current",weighty);
                   startActivity(fin);
                    Log.e(TAG, "onClick: bmrcalories ="+ bmr);
                    Log.e(TAG, "onClick: daily calories ="+ daily);
                    Log.e(TAG, "onClick: weeks ="+weeks );
                }
            }
        });
    }

}