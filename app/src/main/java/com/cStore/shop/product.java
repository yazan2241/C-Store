package com.cStore.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import adapter.Home_Adapter;
import modelclass.Home_Model;

public class product extends AppCompatActivity {
    RecyclerView myRecyclerView;
    private  RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String[] imageshose ;
    String[] price ;
    String[] desc ;
    int[] productId;
    private RecyclerView recyclerView;
    private Home_Adapter home_adapter;
    private ArrayList<Home_Model> home_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        price =new String[20];
        desc=new String[20];
        imageshose=new String[20];
        productId=new int[20];
        recyclerView = findViewById(R.id.my_recyclerView);
        String sessionId=getIntent().getStringExtra("product_id");
        int ppid= Integer.parseInt(sessionId);
        new SigninActivity(this).execute();
    }

    public void openMyProduct(View view) {
    }


    public class SigninActivity extends AsyncTask<String,String,String> {
        private Context context;


        public SigninActivity(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                //  String link = "https://c-store.000webhostapp.com/profileData.php?username="+emailStr+"";
                String link = "https://c-store.000webhostapp.com/Similarproducts.php";
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line="";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    //    break;
                }
                in.close();
                return sb.toString();

            } catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        public void onPostExecute(String result2) {
            super.onPostExecute(result2);
            System.out.println("json" + result2);

            JSONArray JArray = null;
            try {
                JArray = new JSONArray(result2);
                for (int i = 0; i < JArray.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = JArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        price[i]=jsonObject.getString("price");
                        desc[i]=jsonObject.getString("product_name");
                        imageshose[i]=jsonObject.getString("product_pic");
                        productId[i]= Integer.parseInt(jsonObject.getString("product_id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(product.this,2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                home_model = new  ArrayList<>();

                for (int j=0;j <desc.length;j++) {
                    Home_Model ab = new Home_Model(productId[j],imageshose[j], price[j], desc[j]);
                    home_model.add(ab);
                }
                System.out.println("hiiiiiiiiiiiiiii"+ imageshose);
                home_adapter = new Home_Adapter(product.this, (RecyclerViewClickListener) product.this,home_model);
                recyclerView.setAdapter(home_adapter);
                int ppid=home_adapter.getProductId();
                if(ppid!=0){
                    goToNextPage(ppid);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"email or password is wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void goToNextPage(int ppid){
        Intent intent = new Intent(this, product.class);
        intent.putExtra("product_id",ppid);
        startActivity(intent);
    }
            }
