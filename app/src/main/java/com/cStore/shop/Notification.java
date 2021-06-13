package com.cStore.shop;





import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.firebase.messaging.RemoteMessage;

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
import java.util.Timer;
import java.util.TimerTask;

import adapter.Home_Adapter;
import adapter.Notification_Adapter;
import modelclass.Home_Model;


public class Notification extends AppCompatActivity implements RecyclerViewClickListener {
//public LinearLayout lay;
//public EditText et;
////public PopupWindow popup;
    NotificationManager nm;
boolean click=true;


    RecyclerView myRecyclerView;
    private  RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String[] imageshose;
    String[] type ;
    String[] desc ;
    int productId[];
    boolean readNotif[];
    Long previosDate;
    String emailStr;
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private Notification_Adapter notification_Adapter;
    private ArrayList<Home_Model> home_model;

    private Activity mActivity;
    private ConstraintLayout mRelativeLayout;
    private PopupWindow mPopupWindow;
    private Context context;
    private Button mbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        type =new String[20];
        desc=new String[20];
        imageshose=new String[20];
        productId=new int[20];
        readNotif=new boolean[20];
        sharedPreferences=this.getSharedPreferences("sharedPref",Context.MODE_PRIVATE);
         emailStr=sharedPreferences.getString("email",null);
        System.out.println(emailStr);
       // popup = new PopupWindow(this);
     //   lay=new LinearLayout(this);
  //  et=new EditText(this);
        /*
context=getApplicationContext();
mActivity =Notification.this;
mRelativeLayout=findViewById(R.id.rl);
mbutton=findViewById(R.id.buOpen);
mbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView=inflater.inflate(R.layout.activity_notification_details,null);
        mPopupWindow = new PopupWindow(
                customView,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        if(Build.VERSION.SDK_INT>=21)mPopupWindow.setElevation(5.0f);
        Button prepareRepest= (Button) customView.findViewById(R.id.prepareRepest);
        prepareRepest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPopupWindow.dismiss();
            new gSigninActivity(this,1).execute();
            }
        });
        Button DeniedRequest= (Button) customView.findViewById(R.id.DeniedRequest);
        DeniedRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                new gSigninActivity(this,2).execute();
            }
        });
        mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
    }
});
*/
        System.out.println("hiiiiiiiiiiiiiii");
       // Notification notification=new Notification(android.R.drawable.stat_notify_more,,System.currentTimeMillis());
final Handler handler=new Handler();
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            System.out.println("hohohhoho");
                            NSigninActivity jsonTask=new NSigninActivity(1);
                            jsonTask.execute();
                        }
                        catch (Exception e){

                        }
                    }
                });
            }

        };
        timer.schedule(task,0,60*1000);
        recyclerView = findViewById(R.id.Notification_recyclerView);

    }

    @Override
    public void recyclerViewListClicked(int s) {
        goToNextPage(s);
    }
    public void goToNextPage(int ppid){
        Intent intent = new Intent(this, notification_details.class);
        intent.putExtra("product_id",ppid);
        startActivity(intent);
    }
    public class NSigninActivity extends AsyncTask<String,String, String> {
        private Context context;
        private int NOTIFICATION_ID=1;
        private Notification mNotification;
        private NotificationManager mNotificationManager;
          private  Long previosDate;
          int Kind;
        public NSigninActivity(int kind) {
this.Kind=kind;
        }

        @Override
        protected String doInBackground(String... String) {
               // while (true){
                //   if((Interval-previosDate)>10000){
                        try{
                            System.out.println("heeeeeeeee");
                            //  String link = "https://c-store.000webhostapp.com/profileData.php?username="+emailStr+"";
                          String  link = "https://c-store.000webhostapp.com/notification.php?username="+emailStr+"";
                            System.out.println("StartTime"+previosDate);
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



       //             }
         //       }

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
                            if(i<JArray.length()/2)
                            type[i] = jsonObject.getString("type");
                            productId[i] = Integer.parseInt(jsonObject.getString("id"));
                            //   price[i]= jsonObject.getString("price");
                            //  desc[i]=jsonObject.getString("product_name");
                            readNotif[i]= Boolean.parseBoolean(jsonObject.getString("readNotif"));
if(i>=JArray.length()/2)
                            imageshose[i] = jsonObject.getString("product_pic");

                            //   Bitmap bitmap=null;
                            String ss = null;
                            //  Glide.with(context).asBitmap().load(new URL("https://c-store.000webhostapp.com/upload/"+imageshose[i])).into(bitmap);
                            if (type[i].equals("0")) ss = "لديط طلب شراء جديد";
                            if (type[i].equals("1")) {
                                System.out.println("this is" + type[i]);
                                ss = "تمت الموافقة على طلب الشؤاء";
                            }
                            if (type[i].equals("2")) ss = "تم رفض طلب الشراء";
                            System.out.println("this is" + type[i]);
                            if(!readNotif[i])
                            careteNotification(i, ss, productId[i], Notification.this);
                        } catch (JSONException | MalformedURLException e) {
                            e.printStackTrace();
                        }

                    }
                    if (Kind == 1) {
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Notification.this, 1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    home_model = new ArrayList<>();

                    for (int j = 0; j < JArray.length()/2; j++) {
                        String ss = null;
                        if (type[j].equals("0")) ss = "لديط طلب شراء جديد";
                        if (type[j].equals("1")) ss = "تمت الموافقة على طلب الشؤاء";
                        if (type[j].equals("2")) ss = "تم رفض طلب الشراء";
                        Home_Model ab = new Home_Model(productId[j], imageshose[j], ss, ss);
                        home_model.add(ab);
                    }

                    notification_Adapter = new Notification_Adapter(Notification.this, Notification.this, home_model);
                    recyclerView.setAdapter(notification_Adapter);
                    int ppid = notification_Adapter.getProductId();
                    //  if(ppid!=0){
                    //       goToNextPage(ppid);
                    //    }


                }
            } catch (JSONException e) {
                    e.printStackTrace();
                }
        }
private  void careteNotification(int i,String msg,int pId,Context context) throws MalformedURLException {
            mNotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    android.app.Notification.Builder builder= null;

        builder = new android.app.Notification.Builder(context)
                .setSmallIcon(R.drawable.trevor)
                .setAutoCancel(true)
                .setContentTitle(msg);
                //.setLargeIcon(R.drawable.trevor);
                //.setLargeIcon(Glide.with(context).load(new URL("https://c-store.000webhostapp.com/upload/"+im)));


    mNotificationManager.notify(i,builder.build());
}

    }






/*


NotificationManagerCompat notiManager;
    static int id=1;
    static  String channel_id="ch1";
*/
/*
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void sendNotification(View view) {


        Intent notificationResult=new Intent(this,Notification.class);
        notificationResult.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent resultPendingIntent=PendingIntent.getActivity(this,0,notificationResult,0);

        NotificationCompat.Builder notibuilder=new NotificationCompat.Builder(this,channel_id)
                .setContentTitle("new Demand")
                .setContentText("Customer send new Demand")
                .setSmallIcon(R.drawable.download123)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true);

       notiManager=NotificationManagerCompat.from(this);
       notiManager.notify(1,notibuilder.build());
       /*
notibuilder.addAction(R.drawable.circle_blue,"read it",resultPendingIntent);

        notiManager.notify(1,notibuilder.build());
        */
/*
        id++;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private  void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= 26){
            CharSequence nam=getString(R.string.channel_name);
            String description=getString(R.string.channel_des);
            int importance=NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel(channel_id,nam,importance);
            channel.setDescription(description);
            NotificationManager notimanager=getSystemService(NotificationManager.class);
            notimanager.createNotificationChannel(channel);
        }
    }
/*
        ly=(LinearLayout) findViewById(R.id.notif);
        ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Price.class);
                startActivity(i);
            }
        });
*/
    public void notifi(View view) {
        Intent i=new Intent(Notification.this,notification_details.class);
        startActivity(i);
        /*
    if(click){
        popup.showAtLocation(lay, Gravity.BOTTOM,10,10);
        popup.update(50,50,300,80);
        click=false;
    }
    else{
        popup.dismiss();
        click=true;
    }

     LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    lay.setOrientation(LinearLayout.VERTICAL);
    et.setText("user demand tv" +
            "quantity is 3");
    lay.addView(et,params);
    popup.setContentView(lay);

         */
    }
}
