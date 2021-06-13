package com.cStore.shop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

public class chooseCustom extends AppCompatActivity {
    Spinner spinner_sort_by;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

/*
        spinner_sort_by = (Spinner) findViewById(R.id.spinner_custom_details);

        //        Spinner for Sending Id

        List<String> list = new ArrayList<String>();
        list.add("Small");
        list.add("Large");
        list.add("XLarge");
        list.add("XXLarge");
        list.add("XXXLarge");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(chooseCustom.this, R.layout.spinner_item, R.id.spinner_text, list);
        spinner_sort_by.setAdapter(dataAdapter);

*/
    }

}
