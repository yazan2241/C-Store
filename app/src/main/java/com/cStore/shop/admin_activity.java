
package com.cStore.shop;

import com.cStore.shop.item_admin;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.viewpagerindicator.LinePageIndicator;

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

import adapter.Home_Adapter_admin;
import modelclass.Home_Model_admin;


public class admin_activity extends item_admin {
    private RecyclerView recyclerView;
    private Home_Adapter_admin home_adapter;
    private ArrayList<Home_Model_admin> home_model;

    private TextView marka,username,count,marka1,username1,count1,marka2,username2,count2;
    String[] mark;

    String[] usernm;

    String[] counter;

    String[] mark1;
    String[] usernm1;
    String[] counter1;
    String[] mark2;
    String[] usernm2;
    String[] counter2;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admins);
        mark = new String[30];
        usernm=new String[30];
        counter=new String[30];
        mark1 = new String[30];
        usernm1=new String[30];
        counter1=new String[30];
        mark2 = new String[30];
        usernm2=new String[30];
        counter2=new String[30];
        username  =findViewById(R.id.textView4);
        marka=findViewById(R.id.textView5);
        count=findViewById(R.id.textView6);
        username1  =findViewById(R.id.textView7);
        marka1=findViewById(R.id.textView8);
        count1=findViewById(R.id.textView9);
        username2  =findViewById(R.id.textView10);
        marka2=findViewById(R.id.textView11);
        count2=findViewById(R.id.textView12);
        recyclerView = findViewById(R.id.recyclerview3);
        sharedPreferences=this.getSharedPreferences("sharedPref",Context.MODE_PRIVATE);

        new SigninActivity(this).execute();
    }

    public class SigninActivity extends AsyncTask<String,String,String> {
        private Context context;
       // private String mark,usernm,counter;

        public SigninActivity(Context context) {
this.context=context;


        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                String link = "https://c-store.000webhostapp.com/admin.php";
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

        @RequiresApi(api = Build.VERSION_CODES.O)
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


                        usernm[i] = jsonObject.getString("product_name");
                        mark[i] = jsonObject.getString("marka");
                        counter[i]=jsonObject.getString("buyingNumber");
                        username.setText(usernm[0]);

                        marka.setText(mark[0]);
                        count.setText(counter[0]);
                        username1.setText(usernm[1]);

                        marka1.setText(mark[1]);
                        count1.setText(counter[1]);
                        username2.setText(usernm[2]);

                        marka2.setText(mark[2]);
                        count2.setText(counter[2]);
                        System.out.println("hellllllllllo" + usernm[0]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
               /////
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(admin_activity.this,1);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                home_model = new  ArrayList<>();

                for (int j=0;j <  q;j++) {
                    Home_Model_admin ab = new Home_Model_admin(pic23[j],name2[j],markaname2[j],comnumber2[j],mobilenumber2[j]);
                    home_model.add(ab);
                }
                System.out.println("hiiiiiiiiiiiiiii"+pic23[0]);
                home_adapter = new Home_Adapter_admin(admin_activity.this,home_model);
                recyclerView.setAdapter(home_adapter);
            }
            catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"email or password is wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }



    }