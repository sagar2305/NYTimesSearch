package com.nytsearch.nytimessearch.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.nytsearch.nytimessearch.R;
import com.nytsearch.nytimessearch.utils.FilterSettings;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FilterActivity extends AppCompatActivity {

    Calendar myCalendar;
    TextView tvDate;
    Spinner spSortOrder;
    CheckBox cbArts;
    CheckBox cbFashion;
    CheckBox cbSports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupViews();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void setupViews() {
        tvDate = findViewById(R.id.tvDateValue);
        spSortOrder = findViewById(R.id.spSortSpinner);
        cbArts = findViewById(R.id.cbArts);
        cbFashion = findViewById(R.id.cbFashion);
        cbSports = findViewById(R.id.cbSports);

        myCalendar = Calendar.getInstance();
        updateLabel();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FilterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private String getNewsDesk() {
        String newsDesk = "";
        if (cbSports.isChecked()) {
            newsDesk += "\"Sports\" ";
        }
        if (cbArts.isChecked()) {
            newsDesk += "\"Arts\" ";
        }
        if (cbFashion.isChecked()) {
            newsDesk += "\"Fashion & Style\"";
        }

        newsDesk = TextUtils.isEmpty(newsDesk) ? "" : "(" + newsDesk + ")";
        return newsDesk;
    }

    public void onSaveClicked(View view) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String currentDate = formatter.format(myCalendar.getTime());

        String sortOrder = spSortOrder.getSelectedItem().toString();

        String newsDesk = getNewsDesk();

        FilterSettings settings = new FilterSettings(currentDate, sortOrder, newsDesk);

        Intent intent =  new Intent(this, FilterActivity.class);
        intent.putExtra("settings", Parcels.wrap(settings));
        setResult(RESULT_OK, intent);
        finish();
    }
}
