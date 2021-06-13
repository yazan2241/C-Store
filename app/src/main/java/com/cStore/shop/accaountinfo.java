package com.cStore.shop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class accaountinfo extends AppCompatActivity {
    Spinner spinner_sort_by;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accaountinfo);
        spinner_sort_by = (Spinner) findViewById(R.id.spinner_type);

        //        Spinner for Sending Id

        List<String> list = new ArrayList<String>();
        list.add("اختر اختصاصك");
        list.add("بائع-ورشة");
        list.add("جملة الجملة");
        list.add("جملة");
        list.add("مفرق");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(accaountinfo.this, R.layout.spinner_item, R.id.spinner_text, list);
        spinner_sort_by.setAdapter(dataAdapter);
        spinner_sort_by = (Spinner) findViewById(R.id.spinner_cuse);

        //        Spinner for Sending Id

        List<String> list1 = new ArrayList<String>();
        list.add("سبب التواجد");

        list.add("بيع");
        list.add("شراء");
        list.add("بيع وشراء");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(accaountinfo.this, R.layout.spinner_item, R.id.spinner_text, list);
        spinner_sort_by.setAdapter(dataAdapter);
        spinner_sort_by = (Spinner) findViewById(R.id.spinner_account_type);

        //        Spinner for Sending Id

        List<String> list2 = new ArrayList<String>();
        list.add("نوع الاشتراك");
        list.add("اشتراك مجاني");
        list.add("عادل");
        list.add("اشتراك ممتاز");


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(accaountinfo.this, R.layout.spinner_item, R.id.spinner_text, list);
        spinner_sort_by.setAdapter(dataAdapter);
    }

}
