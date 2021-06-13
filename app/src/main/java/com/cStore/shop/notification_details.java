package com.cStore.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import adapter.Home_Adapter;
import modelclass.Home_Model;

public class notification_details extends AppCompatActivity {
ImageView im,proim;
TextView colorR,sizeR,quanR;
    String emailStr;
    SharedPreferences sharedPreferences;
int notificationId=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
im=findViewById(R.id.profile_image);
colorR=findViewById(R.id.colorR);
sizeR=findViewById(R.id.sizeR);
proim=findViewById(R.id.productImageR);
quanR=findViewById(R.id.quantityR);
        new SigninActivity(this).execute();

        sharedPreferences=this.getSharedPreferences("sharedPref",Context.MODE_PRIVATE);
        emailStr=sharedPreferences.getString("email",null);

        Button prepareRepest= (Button) findViewById(R.id.prepareRepest);
        prepareRepest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new gSigninActivity(this,1).execute();
            }
        });
        Button DeniedRequest= (Button) findViewById(R.id.DeniedRequest);
        DeniedRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new gSigninActivity(this,2).execute();
            }
        });
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


                String link = "https://c-store.000webhostapp.com/products.php?id=3";
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
                        String picture=jsonObject.getString("buyer_pic");
                        Glide.with(context).load(new URL("https://c-store.000webhostapp.com/upload/"+picture)).into(im);
                    String size=jsonObject.getString("size");
                    String color=jsonObject.getString("color");
                     String quantity=jsonObject.getString("quantity");
                     String productPic=jsonObject.getString("product_pic");

                     for(int j=0;j<size.length();j++){
                         if(size.charAt(j)=='1'){
                             if(j==0)sizeR.append("small/n");
                             if(j==1)sizeR.append("medium/n");
                             if(j==2)sizeR.append("large/n");
                             if(j==3)sizeR.append("Xlarge/n");
                             if(j==4)sizeR.append("XXlarge/n");
                             if(j==5)sizeR.append("XXXlarge/n");
                         }
                         if(color.charAt(j)=='1'){
                             if(j==0)colorR.append("/nاحمر");
                             if(j==1)colorR.append("/nاسود");
                             if(j==2)colorR.append("/nازرق");
                             if(j==3)colorR.append("/nابيض");
                             if(j==4)colorR.append("/nاخضر");
                             if(j==5)colorR.append("/nرمادي");
                         }
                     }
                     quanR.setText(quantity);
                        Glide.with(context).load(new URL("https://c-store.000webhostapp.com/upload/"+productPic)).into(proim);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                }

            }
            catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"email or password is wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class gSigninActivity extends AsyncTask<String,String, String> {
        private Context context;
        private int NOTIFICATION_ID = 1;
        private Notification mNotification;
        private NotificationManager mNotificationManager;
        private Long previosDate;
        private int type;
        public gSigninActivity(View.OnClickListener context, int type) {
            this.context = (Context) context;
            this.type=type;
        }

        @Override
        protected String doInBackground(String... String) {
            // while (true){
            //   if((Interval-previosDate)>10000){
            try {
                //  String link = "https://c-store.000webhostapp.com/profileData.php?username="+emailStr+"";
                String link = "https://c-store.000webhostapp.com/notificationReply.php?username="+ emailStr +"&type="+type+"&buyerId=1";
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    //    break;
                }
                in.close();
                return sb.toString();

            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }


            //             }
            //       }

        }

        @Override
        public void onPostExecute(String result2) {
            super.onPostExecute(result2);
            System.out.println("json" + result2);


        }

    }




}
