package com.cStore.shop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;


import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.FrameMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import adapter.Home_Adapter;
import modelclass.Home_Model;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , RecyclerViewClickListener {
Button button;
String[] imageshose,price,desc;
int[] productId;
SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private Home_Adapter home_adapter;
    private ArrayList<Home_Model> home_model;
public ImageView mainImage;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    public DrawerLayout drawer;
    private Toolbar toolbar;
    LinearLayout linear_filter;


    RecyclerView myRecyclerView;
    private  RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

//LinearLayout first,second,third,forth,fifth,sixth;
//View popup;
    //LinearLayout th;
    public LinearLayout ly;
    String[] myDataset;
ImageButton th;
    private ArrayList<menu_model>nMenuModel=new ArrayList<menu_model>();
private ActionBarDrawerToggle toggle;
int ppid=0;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        price =new String[60];
       desc=new String[60];
       imageshose=new String[60];
        productId=new int[60];
     //   image2=new Bitmap[20];
    //    mainImage=findViewById(R.id.imageshose);
       // Picasso.get().load("https://c-store.000webhostapp.com/upload/FB_IMG_1594731278302.jpg").into(mainImage);
/*
        myRecyclerView=(RecyclerView)findViewById(R.id.my_recyclerView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter=new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);


 */


        final Handler handler=new Handler();
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Notification.NSigninActivity jsonTask=new Notification().new NSigninActivity(0);
                            jsonTask.execute();
                        }
                        catch (Exception e){

                        }
                    }
                });
            }

        };
        timer.schedule(task,0,60*1000);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        sharedPreferences=getSharedPreferences("sharedPref",Context.MODE_PRIVATE);

        new SigninActivity(this).execute();
        SearchView sv= findViewById(R.id.search_tool);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        /*
        navigationView=(NavigationView) findViewById(R.id.menu_slide_layout);
       // navigationView.bringToFront();
       // navigationView.setNavigationItemSelectedListener(this);
       drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar =findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //     getSupportActionBar().setHomeButtonEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

*/





       // button=(Button)findViewById(R.id.openMenu);
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(MainActivity.this,button);
                popupMenu.getMenuInflater().inflate(R.menu.manu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //Toast.makeText(MainActivity.this,""+menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                        switch (menuItem.getItemId()) {
                            case R.id.profile:
                                Intent i = new Intent(MainActivity.this, Profile.class);
                                startActivity(i);
                                return true;
                            case R.id.addNewProduct:
                                Intent ii = new Intent(MainActivity.this, upload_file.class);
                                startActivity(ii);
                                return true;
                            case R.id.buyingProduct:
                                Intent iii = new Intent(MainActivity.this, cart.class);
                                startActivity(iii);
                                return true;
                            case R.id.category:
                                Intent iii1 = new Intent(MainActivity.this, CategoryActivity.class);
                                startActivity(iii1);
                                return true;
                           case R.id.noti:
                                Intent iii2 = new Intent(MainActivity.this, Notification.class);
                                startActivity(iii2);
                                return true;
                            case R.id.Logout1:
                                Intent iii3 = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(iii3);
                                return true;
                            default:
                                return true;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
*/




//first= popup.findViewById(R.id.first_item);
  //      second=popup.findViewById(R.id.second_item);
      //th=findViewById(R.id.imageshose);
      //  forth=popup.findViewById(R.id.fourth_item);
     /*   th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, ProductDetailActivity.class);
                startActivity(i);
            }
        });*/
        // fifth=popup.findViewById(R.id.fifth_item);
        // sixth=popup.findViewById(R.id.six_item);
/*
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Price.class);
                startActivity(i);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Price.class);
                startActivity(i);
            }
        });
*/
/*
        forth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Price.class);
                startActivity(i);
            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Price.class);
                startActivity(i);
            }
        });
        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Price.class);
                startActivity(i);
            }
        });
*/
//        recyclerview code:

        recyclerView = findViewById(R.id.my_recyclerView);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("custom"));

  //      nMenuModel.add(new menu_model("Profile",R.drawable.abc));


  /*      im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s=new Intent(getApplicationContext(),MenuSlide.class);
                startActivity(s);
                overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_left);
            }
        });
        */



    }
private void setupDrawerContent(NavigationView navigationView){

}

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        toggle.onConfigurationChanged(newConfig);
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

       SearchView searchView = (SearchView) menu.findItem(R.id.search_tool).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

    return  true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
      //  int id = item.getItemId();
if(toggle.onOptionsItemSelected(item)){
    return true;
}
        //noinspection SimplifiableIfStatement
      //  if (id == R.id.action_settings) {
        //    return true;
     //   }

        return super.onOptionsItemSelected(item);
    }
@SuppressWarnings("StatemkentWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (@NonNull MenuItem item){

    int id = item.getItemId();

    if (id == R.id.profile) {
        // Handle the camera action
        Intent i = new Intent(MainActivity.this, Profile.class);
        startActivity(i);
    } else if (id == R.id.addNewProduct) {
        Intent ii = new Intent(MainActivity.this, upload_file.class);
        startActivity(ii);

    } else if (id == R.id.buyingProduct) {
        Intent iii = new Intent(MainActivity.this, cart.class);
        startActivity(iii);

    } else if (id == R.id.category) {
        Intent iii1 = new Intent(MainActivity.this, CategoryActivity.class);
        startActivity(iii1);

    } else if (id == R.id.noti) {
        Intent iii2 = new Intent(MainActivity.this, Notification.class);
        startActivity(iii2);

    } else if (id == R.id.Logout1) {
        Intent iii3 = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(iii3);

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
return  true;
    }
/*
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.manu,menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m=(MenuBuilder)menu;
            m.setOptionalIconsVisible(true);
        }
        return  true;
    }

 */
    public void openSearch(View view) {
        Intent i=new Intent(MainActivity.this,SearchActivity.class);
        startActivity(i);
    }

    public void itemesh(View view) {
        Intent ii=new Intent(MainActivity.this,ProductDetailActivity.class);
        startActivity(ii);
    }
    @Override
    public  void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void recyclerViewListClicked(int prodid) {
System.out.println("ppppppppppid"+prodid);
goToNextPage(prodid);
    }

    /*
    @Override
public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
}

     */
    public class SigninActivity extends AsyncTask<String,String,String> {
        private Context context;


        public SigninActivity(Context context) {
            this.context = context;


        }

        @Override
        protected String doInBackground(String... strings) {

            try{
              //  String link = "https://c-store.000webhostapp.com/profileData.php?username="+emailStr+"";
                String link = "https://c-store.000webhostapp.com/products.php?id=0";
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
                     productId[i]= Integer.parseInt(jsonObject.getString("id"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                home_model = new  ArrayList<>();

                for (int j=0;j <desc.length;j++) {
                    Home_Model ab = new Home_Model(productId[j],imageshose[j], price[j], desc[j]);
                    home_model.add(ab);
                }

                home_adapter = new Home_Adapter(MainActivity.this, MainActivity.this,home_model);
                recyclerView.setAdapter(home_adapter);

            //  int ppid=home_adapter.getProductId();




            }
            catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"email or password is wrong",Toast.LENGTH_SHORT).show();
            }
        }
    }
    /*
    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(MainActivity.this ,ProductDetailActivity.class);
        intent.putExtra("price",price[position]);
        intent.putExtra("product_name",desc[position]);
        System.out.println("kkkkkkkkk"+price[position]);


        startActivity(intent);
    }

    @Override
    public void onLongItemClick(int position) {

    }
    */


    public void goToNextPage(int ppid){
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("product_id",ppid);
        startActivity(intent);
    }

public BroadcastReceiver mMessageReceiver=new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        String id= intent.getStringExtra("productId");
        System.out.println("productId1"+id);

    }
};

}