package com.example.healthyme_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;

import org.joda.time.DateTime;

public class DietPlan extends AppCompatActivity implements DatePickerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);

        // find the picker
        HorizontalPicker picker = findViewById(R.id.datePicker);

        // initialize it and attach a listener
        picker
                .setListener(this)
                .setDays(31)
                .setOffset(10)
                .setDateSelectedColor(Color.BLUE)
                .setDateSelectedTextColor(Color.GREEN)
                .setMonthAndYearTextColor(Color.DKGRAY)
                .setTodayButtonTextColor(getColor(R.color.colorPrimary))
                .setTodayDateTextColor(getColor(R.color.colorPrimary))
                .setTodayDateBackgroundColor(Color.GRAY)
                .setUnselectedDayTextColor(Color.BLACK)

                .setDayOfWeekTextColor(Color.DKGRAY)
                .showTodayButton(true)
                .init();
                picker.setBackgroundColor(Color.WHITE);
                picker.setDate(new DateTime());

    }

    @Override
    public void onDateSelected(DateTime dateSelected) {
        // log it for demo
        Log.e("HorizontalPicker", "Selected date is " + dateSelected.toString());


    }
}