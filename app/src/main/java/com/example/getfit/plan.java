package com.example.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class plan extends AppCompatActivity {
    int maintanance;
    int daily;
    int weeks;
    int current;
    int goal;
    TextView maint;
    TextView day;
    TextView week;
    TextView goa;
    TextView curren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        Intent fin=getIntent();
        maintanance=fin.getIntExtra("maintain",0);
        daily=fin.getIntExtra("daily",0);
        weeks=fin.getIntExtra("weeks",0);
        current=fin.getIntExtra("current",0);
        goal=fin.getIntExtra("goal",0);

        maint=(TextView) findViewById(R.id.maintanance);
        day=(TextView) findViewById(R.id.daily);
        week=(TextView) findViewById(R.id.weeks);
        goa=(TextView) findViewById(R.id.goalweight);
        curren=(TextView) findViewById(R.id.currentweight);

        String goaly=""+goal+" Kgs";
        String currenty=""+current+" Kgs";
        String maintainy=""+maintanance+" kcals";
        String Dailyy=""+daily+" Kcals";
        String weekly=""+weeks+" weeks";

        maint.setText(maintainy);
        day.setText(Dailyy);
        week.setText(weekly);
        goa.setText(goaly);
        curren.setText(currenty);

    }
}