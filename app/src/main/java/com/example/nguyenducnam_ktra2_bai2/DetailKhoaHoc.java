package com.example.nguyenducnam_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetailKhoaHoc extends AppCompatActivity {
    EditText etName, etDate;
    Spinner spinner;
    CheckBox cbActive;
    Button btnUpdate, btnDelete;
    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_khoa_hoc);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());
        initView();
        setIntentValue();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (getIntent().getStringExtra("id"));
                sqLiteHelper.delete(id);
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String date = etDate.getText().toString();
                String chuyenNganh = spinner.getSelectedItem().toString();
                String active = cbActive.isChecked() ? "1": "0";

                String id = (getIntent().getStringExtra("id"));

                KhoaHoc o = new KhoaHoc(id, name, date, chuyenNganh, active);
                sqLiteHelper.update(o);
                finish();
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeDate();
            }
        });
    }

    public void onChangeDate() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                etDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void initView() {
        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        spinner = findViewById(R.id.spinner);
        cbActive = findViewById(R.id.cbActive);

        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    private void setIntentValue() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.khoahoc_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(getIntent().getStringExtra("chuyenNganh"));
        spinner.setSelection(spinnerPosition);


        etName.setText(getIntent().getStringExtra("name"));
        etDate.setText(getIntent().getStringExtra("date"));

        cbActive.setChecked(getIntent().getStringExtra("active").equals("1") );
    }
}