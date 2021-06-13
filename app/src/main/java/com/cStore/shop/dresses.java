package com.cStore.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class dresses extends AppCompatActivity implements RecyclerViewClickListener {
    RecyclerView myRecyclerView;
    private  RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String[] imageshose ;
    String[] price ;
    String[] desc ;
    private RecyclerView recyclerView;
    private Home_Adapter home_adapter;
    private ArrayList<Home_Model> home_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dresses);

        price =new String[60];
        desc=new String[60];
        imageshose=new String[60];
        recyclerView = findViewById(R.id.my_recyclerView);
        new SigninActivity(this).execute();

    }

    @Override
    public void recyclerViewListClicked(int s) {

    }


    private class SigninActivity extends AsyncTask<String,String,String> {
        private Context context;


        public SigninActivity(Context context) {
            this.context = context;


        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                //  String link = "https://c-store.000webhostapp.com/profileData.php?username="+emailStr+"";


                String link = "https://c-store.000webhostapp.com/products.php?id=2";
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(dresses.this,3);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                home_model = new  ArrayList<>();

                for (int j=0;j <desc.length;j++) {
                    Home_Model ab = new Home_Model(imageshose[j], price[j], desc[j]);
                    home_model.add(ab);
                }
                System.out.println("hiiiiiiiiiiiiiii"+ imageshose);
                home_adapter = new Home_Adapter(dresses.this,  dresses.this,home_model);
                recyclerView.setAdapter(home_adapter);
            }
            catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"email or password is wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
