package com.cStore.shop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    Spinner spinner_sort_by;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        spinner_sort_by = (Spinner) findViewById(R.id.spinner_sort_by);

        //        Spinner for Sending Id

        List<String> list = new ArrayList<String>();
        list.add("اختر اختصاصك");
        list.add("بائع-ورشة");
        list.add("جملة الجملة");
        list.add("جملة");
        list.add("مفرق");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.spinner_item, R.id.spinner_text, list);
        spinner_sort_by.setAdapter(dataAdapter);

    }

    public void searchResult(View view) {
        Intent i=new Intent(SearchActivity.this,SearchResult.class);
        startActivity(i);
    }
}
