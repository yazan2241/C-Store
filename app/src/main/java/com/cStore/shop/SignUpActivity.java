package com.cStore.shop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Random;

public class SignUpActivity extends AppCompatActivity {
private EditText companyName1,companyHeadName1,companyNumber1,companyPhone1,companyEmail1,companyPassword1,companyRePass1;
    private String CompanyName,CompanyHeadName,CompanyEmail,CompanyPassword,CompanyRePass;
    private String CompanyNumber,CompanyPhone,code1;
   public static String code;

SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        companyName1=findViewById(R.id.companyName);
        companyHeadName1=findViewById(R.id.companyHeadName);
        companyNumber1=findViewById(R.id.companyNumber);
        companyPhone1=findViewById(R.id.companyPhone);
        companyEmail1=findViewById(R.id.companyEmail);
        companyPassword1=findViewById(R.id.companyPassword);
        companyRePass1=findViewById(R.id.companyRePass);

        Random rand =new Random();
          code=String.valueOf(rand.nextInt(10000));

        sharedPreferences=getSharedPreferences("sharedPref",Context.MODE_PRIVATE);


    }

    public void next(View view) {
        Intent intent2 = new Intent(SignUpActivity.this , verfication.class);
        startActivity(intent2);
    }

    public void Signup(View view) {
        CompanyName=companyName1.getText().toString();
        CompanyHeadName=companyHeadName1.getText().toString();
        CompanyNumber= companyNumber1.getText().toString();
        CompanyPhone=companyPhone1.getText().toString();
        CompanyEmail=companyEmail1.getText().toString();
        CompanyPassword=companyPassword1.getText().toString();
        CompanyRePass=companyRePass1.getText().toString();
        if(!CompanyRePass.equals(CompanyPassword)){
            System.out.println("CompanyPassword"+CompanyPassword);
            System.out.println("CompanyRePass"+CompanyRePass);
            Toast.makeText(getApplicationContext(),"كلمة السر لا تتطابق تأكيد كلمة السر",Toast.LENGTH_SHORT).show();
        }
        else{
            String method="Post Method";
            String status="",role="";
            Toast.makeText(getApplicationContext(),"تم إرسال رمز التحقق الى الإيميل",Toast.LENGTH_SHORT).show();
           new SigninActivity(this,status,role,code,CompanyName,CompanyHeadName,CompanyNumber,CompanyPhone,CompanyEmail,CompanyPassword,0,1).execute(CompanyEmail,CompanyName,CompanyHeadName,code);

        }

    }

    public void goToLogInPage(View view) {
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    public class SigninActivity extends AsyncTask<String,String,String> {
        private String statusField;
        private String roleField;
        private Context context;
        public String result;
       // public String userName,userPicture,userEmail;
         private String companyName,companyHeadName,companyEmail,companyPassword;
         private String companyNumber,companyPhone,code;
      //  private String emailStr,passStr;
        private int byGetOrPost = 0,page;
        /*
        public SigninActivity(Context context,String statusField,String roleField,String email,String pass,int page ,int flag) {
            this.context = context;
            this.statusField = statusField;
            this.roleField = roleField;
            byGetOrPost = flag;
            this.emailStr=email;
            this.passStr=pass;
            this.page=page;
        }

         */

               public SigninActivity(Context context,String statusField,String roleField,String code,String companyName,String companyHeadName,String companyNumber,String companyPhone,String companyEmail,String companyPassword,int page,int flag) {
                   this.context = context;
                   byGetOrPost = flag;
this.code=code;
                   this.companyName = companyName;
                   this.companyHeadName = companyHeadName;

                   this.companyNumber=companyNumber;
                   this.companyPhone=companyPhone;
                   this.companyEmail=companyEmail;
                   this.companyPassword=companyPassword;
                   this.page=page;


               }

        @Override
        protected String doInBackground(String... strings) {
         //   if(byGetOrPost == 1){ //means by Get Method

                try{
                    //  String username = (String) arg0[0];
                    // String password = (String)arg0[1];

                 //   String link = "https://c-store.000webhostapp.com/indexGet.php?username='"+emailStr+"'&password='"+passStr+"'";
                 String  link = "https://c-store.000webhostapp.com/sellerRegisterGet.php/?companyName="+companyName+"&companyHeadName="+companyHeadName+
                         "&companyNumber="+companyNumber+"&companyPhone="+companyPhone+"&companyEmail="+companyEmail+"&companyPassword="+companyPassword+"&code="+code+"";

                  /*
                    if(page==0) {
                        link = "https://c-store.000webhostapp.com/index1.php/?username="+emailStr+"&password="+passStr+"";

                    }
                    if(page==1){
                        //link = "https://c-store.000webhostapp.com/sellerRegister.php";
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
        //    }
             /*
            else{


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
 */
        }

        @Override
        public void onPostExecute(String result2) {
            super.onPostExecute(result2);
            System.out.println("json" + result2);
            this.statusField = "Login Successful";
            this.roleField = result2;
            goToNextPage();
            //Toast.makeText(getApplicationContext(),emailStr,Toast.LENGTH_SHORT).show();

            // System.out.println(result2);
            /*
            if (page == 1) {


                String s = "";
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


            }
            result = result2;
          */
        }
    }
    public  void goToNextPage(){




        Intent intent = new Intent(this, verfication.class);
      //  intent.putExtra(bundle);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email", CompanyEmail);


        editor.commit();

        startActivity(intent);
    }
}