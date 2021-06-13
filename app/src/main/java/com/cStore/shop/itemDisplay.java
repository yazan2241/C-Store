package com.cStore.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class itemDisplay extends AppCompatActivity {
private  int firdtId=0;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        LinearLayout ll =(LinearLayout)this.findViewById(R.id.lay1);
        LinearLayout layout=(LinearLayout)LayoutInflater.from(this).inflate(R.layout.activity_item_display,null);
        //LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(180,260));
        ImageButton imageButton=new ImageButton(this);
        imageButton.setId(firdtId);
        imageButton.setLayoutParams(new LinearLayout.LayoutParams(160,160));
   LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) imageButton.getLayoutParams();
   params.setMargins(0,8,0,0);
   imageButton.setLayoutParams(params);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LinearLayout lin=new LinearLayout(this);
        LinearLayout.LayoutParams params1= (LinearLayout.LayoutParams) lin.getLayoutParams();
        lin.setOrientation(LinearLayout.HORIZONTAL);
        lin.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        params1.setMargins(0,10,0,0);


        TextView textView =new TextView(this);
        textView.setTextColor(R.color.green);
        textView.setTextSize(14);
        textView.setText(R.string.price);

        TextView textView1 =new TextView(this);
        textView1.setTextColor(R.color.green);
        textView1.setTextSize(14);
        textView1.setText(R.string.description);


        layout.addView(imageButton);
        lin.addView(textView);
        lin.addView(textView1);
        layout.addView(lin);
        ll.addView(layout);
        setContentView(ll);
       // setContentView(R.layout.activity_item_display);
*/
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        first fargment = new first();
        fragmentTransaction.add(R.id.lay1, fargment);
        fragmentTransaction.commit();
    }}