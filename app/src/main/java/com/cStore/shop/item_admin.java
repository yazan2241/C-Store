
package com.cStore.shop;
import com.cStore.shop.admin_activity;
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

import adapter.FromCategoryAdapter;
import adapter.Home_Adapter_admin;
import adapter.ShoesPagerAdapter;
import modelclass.FRomCategoryModel;
import modelclass.Home_Model_admin;

public class item_admin extends AppCompatActivity {
        private RecyclerView recyclerView;
        private Home_Adapter_admin home_adapter;
        private ArrayList<Home_Model_admin> home_model;
      //  private TextView name,markaname,comnumber,mobilenumber,pic2;
        String[] name2;

        String[] markaname2;

        String[] comnumber2;

        String[] mobilenumber2;
        String[] pic23;
int q;
        SharedPreferences sharedPreferences;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.admins);
            name2 = new String[30];
            markaname2=new String[30];
            comnumber2=new String[30];
            mobilenumber2 = new String[30];
            pic23=new String[30];


            recyclerView = findViewById(R.id.recyclerview3);
            sharedPreferences=this.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);

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
                    String link = "https://c-store.000webhostapp.com/admin2.php";
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
                            name2[i]=jsonObject.getString("name");
                            markaname2[i]=jsonObject.getString("companyName");
                            comnumber2[i]=jsonObject.getString("commercialNumber");

                            mobilenumber2[i]=jsonObject.getString("phoneNumber");
                            pic23[i]=jsonObject.getString("picture");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                   q=JArray.length();
                 /* RecyclerView.LayoutManager layoutManager = new GridLayoutManager(admin_activity.this,1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    home_model = new  ArrayList<>();

                    for (int j=0;j <  JArray.length();j++) {
                        Home_Model_admin ab = new Home_Model_admin(pic23[j],name2[j],markaname2[j],comnumber2[j],mobilenumber2[j]);
                        home_model.add(ab);
                    }
                    System.out.println("hiiiiiiiiiiiiiii"+pic23[0]);
                    home_adapter = new Home_Adapter_admin(admin_activity.this,home_model);
                    recyclerView.setAdapter(home_adapter);*/
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"email or password is wrong",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
