package com.cStore.shop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {
    Button reg;
    SharedPreferences sharedPreferences;
  EditText email,pass;
  String emailStr,passStr;
    public String userName,userPicture,userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences=getSharedPreferences("sharedPref",Context.MODE_PRIVATE);
if(Build.VERSION.SDK_INT>9){
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
    System.out.println("threaf is configired to allow connection");
}


    }
    public void reg(View view) {
        Intent intent2 = new Intent(LoginActivity.this , SignUpActivity.class);
        startActivity(intent2);
    }

    public void logInProcess(View view) {

        String method = "Post Method";
        String status = "", role = "";
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        emailStr = email.getText().toString();
        passStr = pass.getText().toString();
         new SigninActivity(this, status, role, emailStr, passStr, 0, 1).execute(emailStr, passStr);




    }
    public void loginPost(View view){

    }
    public class SigninActivity extends AsyncTask<String,String,String>{
        private String statusField;
        private String roleField;
        private Context context;
        public String result;
        public String userName,userPicture,userEmail;
       // private String companyName,companyHeadName,companyEmail,companyPassword;
       // private int companyNumber,companyPhone;
        private String emailStr,passStr;
        private int byGetOrPost = 0,page;
        public SigninActivity(Context context,String statusField,String roleField,String email,String pass,int page ,int flag) {
            this.context = context;
            this.statusField = statusField;
            this.roleField = roleField;
            byGetOrPost = flag;
            this.emailStr=email;
            this.passStr=pass;
            this.page=page;
        }
 /*
        public SigninActivity(Context context,String statusField,String roleField,String companyName,String companyHeadName,int companyNumber,int companyPhone,String companyEmail,String companyPassword,int page,int flag) {
            this.context = context;
            byGetOrPost = flag;

            this.companyName = companyName;
            this.companyHeadName = companyHeadName;

            this.companyNumber=companyNumber;
            this.companyPhone=companyPhone;
            this.companyEmail=companyEmail;
            this.companyPassword=companyPassword;
            this.page=page;


        }
 */
        @Override
        protected String doInBackground(String... strings) {
            if(byGetOrPost == 1){ //means by Get Method

                try{
                    //  String username = (String) arg0[0];
                    // String password = (String)arg0[1];

                    String link = "https://c-store.000webhostapp.com/indexGet.php?username="+emailStr+"&password="+passStr+"";
                    System.out.println(link);
                    /*
                    if(page==0) {
                        link = "https://c-store.000webhostapp.com/index1.php/?username="+emailStr+"&password="+passStr+"";

                    }
                    if(page==1){
                        //link = "https://c-store.000webhostapp.com/sellerRegister.php";
                        link = "https://c-store.000webhostapp.com/sellerRegisterGet.php/?companyName="+companyName+"&companyHeadName="+companyHeadName+
                                "&companyNumber"+companyNumber+"&companyPhone"+companyPhone+"&companyEmail"+companyEmail+"companyPassword"+companyPassword+"";
                    }
*/

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
            } else{
                try{
                    //  String username = (String)arg0[0];
                    // String password = (String)arg0[1];
                    String link = "";
                    String data="";
                    if(page==0) {
                        link = "https://c-store.000webhostapp.com/index.php";
                        data = URLEncoder.encode("username", "UTF-8") + "=" +
                                URLEncoder.encode(emailStr, "UTF-8");
                        data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                                URLEncoder.encode(passStr, "UTF-8");
                    }
                    /*
                    if(page==1){
                        link = "https://c-store.000webhostapp.com/sellerRegister.php";
                        data = URLEncoder.encode("companyName", "UTF-8") + "=" +
                                URLEncoder.encode(companyName, "UTF-8");
                        data += "&" + URLEncoder.encode("companyHeadName", "UTF-8") + "=" +
                                URLEncoder.encode(companyHeadName, "UTF-8");
                        data += "&" + URLEncoder.encode("companyNumber", "UTF-8") + "=" +
                                URLEncoder.encode(String.valueOf(companyNumber), "UTF-8");
                        data += "&" + URLEncoder.encode("companyPhone", "UTF-8") + "=" +
                                URLEncoder.encode(String.valueOf(companyPhone), "UTF-8");
                        data += "&" + URLEncoder.encode("companyEmail", "UTF-8") + "=" +
                                URLEncoder.encode(companyEmail, "UTF-8");
                        data += "&" + URLEncoder.encode("companyPassword", "UTF-8") + "=" +
                                URLEncoder.encode(companyPassword, "UTF-8");
                    }

                     */
                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }

                    return sb.toString();
                } catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }

        }

        @Override
        public void onPostExecute(String result2) {
            super.onPostExecute(result2);
            System.out.println("json" + result2);
            this.statusField = "Login Successful";
            this.roleField = result2;
            //Toast.makeText(getApplicationContext(),emailStr,Toast.LENGTH_SHORT).show();
            // System.out.println(result2);
                    String s = "";
                    JSONArray JArray = null;

                    try {
                //    JSONObject object=new JSONObject(result2);
                    /*
    if(object.getString("notReg")=="empty"){
        Toast.makeText(getApplicationContext(),"this user not exist please create new account",Toast.LENGTH_LONG).show();
    }
    if(object.getString("notReg")=="contact"){
        Toast.makeText(getApplicationContext(),"please contact with administrator",Toast.LENGTH_LONG).show();
    }

                     */
                        //JArray=object.getJSONArray("");
                        JArray = new JSONArray(result2);

                        for (int i = 0; i < JArray.length(); i++) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = JArray.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {

                                    userName = jsonObject.getString("name");
                                    userPicture = jsonObject.getString("picture");
                                    userEmail = jsonObject.getString("email");
                                    goToNextPage(userName, userPicture, userEmail);

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




            result = result2;
        }
    }
public void goToNextPage(String userName,String userPicture,String userEmail){
    Intent intent = new Intent(this, MainActivity.class);
   // intent.putExtra("username", userName);
   // intent.putExtra("picture", userPicture);
   // intent.putExtra("email", userEmail);
    SharedPreferences.Editor editor=sharedPreferences.edit();
    editor.putString("username", userName);
    editor.putString("picture", userPicture);
    editor.putString("email", userEmail);
    editor.commit();
    startActivity(intent);
}
}
