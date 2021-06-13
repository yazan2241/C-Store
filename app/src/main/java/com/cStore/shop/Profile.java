package com.cStore.shop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

import adapter.ProfileAdapter;
import modelclass.ProfileModel;

public class Profile extends AppCompatActivity {

    private ProfileAdapter profileAdapter;
    private RecyclerView recyclerview;
    SharedPreferences sharedPreferences;
    private ArrayList<ProfileModel> profileModelArrayList;
Button edit_prof,uplaod;
private TextView userName,userEmail;
String name,email,pic,imageString;
Base64 ima;
ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
edit_prof=(Button)findViewById(R.id.editProfile);
uplaod=(Button)findViewById(R.id.my_product);
        userName=findViewById(R.id.userName);
        userEmail=findViewById(R.id.userEmail);
        img=findViewById(R.id.profile_image);
        sharedPreferences=this.getSharedPreferences("sharedPref",Context.MODE_PRIVATE);
        String emailStr=sharedPreferences.getString("email",null);
        new SigninActivity(this,emailStr).execute(emailStr);

        /*
        if(getIntent().getExtras()!=null) {
            userName.setText(getIntent().getStringExtra("username"));
            userEmail.setText(getIntent().getStringExtra("email"));

        }
         */

edit_prof.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent2 = new Intent(Profile.this , Edit_profile.class);
        startActivity(intent2);
    }
});
uplaod.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Profile.this , my_upload.class);
        startActivity(intent);
    }
});
    }

    public void changePicture(View view) {

    }

    public class SigninActivity extends AsyncTask<String,String,String> {
    private Context context;
    private String emailStr;

    public SigninActivity(Context context,String email) {
        this.context = context;
        this.emailStr=email;

    }

    @Override
    protected String doInBackground(String... strings) {

            try{
                String link = "https://c-store.000webhostapp.com/profileData.php?username="+emailStr+"";
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
                        name = jsonObject.getString("name");
                        email = jsonObject.getString("email");
                        pic=jsonObject.getString("picture");
               userName.setText(name);
               userEmail.setText(email);
              // imageString= jsonObject.getString("image");
                        byte[] decodedString=Base64.decode(pic,Base64.DEFAULT);
                        Bitmap decodeArray= BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
                        img.setImageBitmap(decodeArray);
                        System.out.println("hellllllllllo" + name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"email or password is wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void goToNextPage(String userName,String userPicture,String userEmail){

        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}
