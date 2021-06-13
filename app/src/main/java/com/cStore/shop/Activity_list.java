package com.cStore.shop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapter.ListAdapter;
import modelclass.ListModel;

public class Activity_list extends AppCompatActivity {

    private ListAdapter listAdapter;
    private RecyclerView recyclerview;
    private ArrayList<ListModel> listModelArrayList;

    String txt[]={"activity_start_screen","activity_walkthrough","activity_sign_up","activity_login","activity_forgot_password","activity_home","activity_product_detail","activity_most_liked","activity_from_category","activity_promote","activity_details","activity_profile","activity_settings","activity_category"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        recyclerview=findViewById(R.id.recyclerView1);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Activity_list.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        listModelArrayList = new ArrayList<>();

        for (int i=0;i<txt.length;i++){

            ListModel listModel = new ListModel(txt[i]);

            listModelArrayList.add(listModel);

        }
        listAdapter = new ListAdapter(Activity_list.this,listModelArrayList);
        recyclerview.setAdapter(listAdapter);
*/
    }


}
