package com.cStore.shop;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SigninActivity extends AsyncTask<String,String,String>{
    private String statusField;
    private String roleField;
    private Context context;
    public String result;

    private String companyName,companyHeadName,companyEmail,companyPassword;
    private int companyNumber,companyPhone,page;
private String emailStr,passStr;
    private int byGetOrPost = 0;
    public SigninActivity(Context context,String statusField,String roleField,String email,String pass,int page ,int flag) {
        this.context = context;
        this.statusField = statusField;
        this.roleField = roleField;
        byGetOrPost = flag;
        this.emailStr=email;
        this.passStr=pass;
        this.page=page;
    }

    public SigninActivity(Context context,String statusField,String roleField,String companyName,String companyHeadName,int companyNumber,int companyPhone,String companyEmail,String companyPassword,int page,int flag) {
        this.context = context;
        this.companyName = companyName;
        this.companyHeadName = companyHeadName;
        byGetOrPost = flag;
        this.companyNumber=companyNumber;
        this.companyPhone=companyPhone;
        this.companyEmail=companyEmail;
        this.companyPassword=companyPassword;
        this.page=page;
    }

    @Override
    protected String doInBackground(String... strings) {
        if(byGetOrPost == 1){ //means by Get Method

            try{
                //  String username = (String) arg0[0];
                // String password = (String)arg0[1];
                String link = "";
                if(page==0) {
                    link = "https://c-store.000webhostapp.com/index1.php/?username="+emailStr+"&password="+passStr+"";

                }
                if(page==1){
                    //link = "https://c-store.000webhostapp.com/sellerRegister.php";
                    link = "https://c-store.000webhostapp.com/sellerRegisterGet.php/?companyName="+companyName+"&companyHeadName="+companyHeadName+
                            "&companyNumber"+companyNumber+"&companyPhone"+companyPhone+"&companyEmail"+companyEmail+"companyPassword"+companyPassword+"";
                }


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
                    break;
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
       //  Toast.makeText(context.getApplicationContext(),result2,Toast.LENGTH_SHORT).show();
        // System.out.println(result2);
if(page==0){
        if (result2 == "0") {
            Toast.makeText(context.getApplicationContext(), "Your Email or Password is Wrong", Toast.LENGTH_SHORT).show();
        } else {
            /*
            String s = "";
            JSONArray JArray = null;
            try {
                JArray = new JSONArray(result2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

                    //  Intent intent=new Intent(Profile.class);

                    // s = s + "login" + jsonObject.getString("name") + jsonObject.getString("picture") + jsonObject.getString("email");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

             */
        }

        }
if(page==1){
    if (result2 == "0") {
        Toast.makeText(context.getApplicationContext(), "لم تتم عملية انشاء الحساب", Toast.LENGTH_SHORT).show();
    }
    }
        result=result2;
    }

}