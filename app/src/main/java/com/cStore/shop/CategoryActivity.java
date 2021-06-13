package com.cStore.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

    }

    public void goToCatPageShirt(View view) {
        Intent i=new Intent(getBaseContext(),shirt.class);
        startActivity(i);
    }

    public void goToCatPage(View view) {
    }

    public void goToCatPageShoes(View view) {
        Intent i=new Intent(getBaseContext(),shirt.class);
        startActivity(i);
    }

    public void goToCatPageTies(View view) {
        Intent i=new Intent(getBaseContext(),ties.class);
        startActivity(i);
    }

    public void goToCatPageJacket(View view) {
        Intent i=new Intent(getBaseContext(),jacket.class);
        startActivity(i);
    }

    public void goToCatPageTrouser(View view) {
        Intent i=new Intent(getBaseContext(),trouser.class);
        startActivity(i);
    }

    public void goToCatPageDress(View view) {
        Intent i=new Intent(getBaseContext(),dresses.class);
        startActivity(i);
    }

    public void goToCatPageTexido(View view) {Intent i=new Intent(getBaseContext(),texido.class);
        startActivity(i);
    }
}
