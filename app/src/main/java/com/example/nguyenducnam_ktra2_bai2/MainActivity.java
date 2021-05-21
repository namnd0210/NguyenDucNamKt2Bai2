package com.example.nguyenducnam_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvCount;
    FloatingActionButton fab;
    RecyclerView rv;
    RecycleViewAdapter adapter;
    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sqLiteHelper = new SQLiteHelper(this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        adapter = new RecycleViewAdapter();
        rv.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddKhoaHoc.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.mSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<KhoaHoc> list = sqLiteHelper.getByName(newText);
                adapter.list = list;
                rv.setAdapter(adapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);
        tvCount = findViewById(R.id.tvCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<KhoaHoc> list = sqLiteHelper.getAll();
        adapter.list = list;

        tvCount.setText("Tong so khoa: " + list.size());

        rv.setAdapter(adapter);
    }
}