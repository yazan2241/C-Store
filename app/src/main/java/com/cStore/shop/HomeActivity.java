package com.cStore.shop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

import adapter.Home_Adapter;
import modelclass.Home_Model;

public class HomeActivity extends AppCompatActivity {

    Integer[] hert = {R.drawable.ic_like_heart_outline,R.drawable.ic_like_heart_outline,R.drawable.ic_like_heart_outline,R.drawable.ic_like_heart_outline};
    Integer[] imageshose = {R.drawable.shoes3,R.drawable.shoes3,R.drawable.shoes3,R.drawable.shoes3};
    String[] text1 = {"$120","$150","$150","$150"};

    private RecyclerView recyclerView;
    private Home_Adapter home_adapter;
    private ArrayList<Home_Model> home_model;
Button button;
private ArrayList<menu_model>nMenuModel=new ArrayList<menu_model>();

//private ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

//        recyclerview code:
/*
        recyclerView = findViewById(R.id.recycle);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HomeActivity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        home_model = new  ArrayList<>();

        for (int i=0;i < hert.length;i++) {
            Home_Model ab = new Home_Model(hert[i],imageshose[i],text1[i]);
            home_model.add(ab);
        }

        home_adapter = new Home_Adapter(HomeActivity.this,home_model);
        recyclerView.setAdapter(home_adapter);


        nMenuModel.add(new menu_model("Profile",R.drawable.abc));

*/
  /*      im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s=new Intent(getApplicationContext(),MenuSlide.class);
                startActivity(s);
                overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_left);
            }
        });
        */

    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.manu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(this,"profile",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.addNewProduct:
                Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.buyingProduct:
                Toast.makeText(this,"buy",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    public void openSearch(View view) {
        Intent i=new Intent(HomeActivity.this,SearchActivity.class);
        startActivity(i);
    }


    /*
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_left);
    }

     */
}
