package com.cStore.shop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import adapter.ProductDetailPagerAdapter;

public class ProductDetailActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    private Typeface mTypeface;
    private Typeface mTypefaceBold;
    TextView quantity;
    String color, size;
    String emailStr;
    SharedPreferences sharedPreferences;
    private TextView test1, test3;
    private String value, value2;
    private String image1="", image2="", image3="", image4="", product_name="", product_price="", seller_name="";
    private String product_quantity="",productCategory="";
    int product_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // test1 =(TextView)findViewById(R.id.priceprod1);

        //  test3=(TextView)findViewById(R.id.nameprod2);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {


            //value =extras.getString("product_price");
            product_id = extras.getInt("product_id");
            System.out.println("ppppppid"+product_id);

                new gSigninActivity(this, 1, product_id).execute();


            //  test1.setText(value);
            //  Log.d("this is id", String.valueOf(product_id));
            // value2 =extras.getString("product_name");

            // test3.setText(value2);
        }


        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);
        quantity = findViewById(R.id.item_details_quant);
        tabLayout.addTab(tabLayout.newTab().setText(""));

        sharedPreferences = this.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        emailStr = sharedPreferences.getString("email", null);
        //tabLayout.addTab(tabLayout.newTab().setText(""));
        //tabLayout.addTab(tabLayout.newTab().setText(""));
        //tabLayout.addTab(tabLayout.newTab().setText(""));
        //tabLayout.addTab(tabLayout.newTab().setText(""));
        Typeface mTypeface = Typeface.createFromAsset(ProductDetailActivity.this.getAssets(), "fonts/Roboto-Regular.ttf");
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
                }
            }
        }

        System.out.println("pppanem"+product_price);
        System.out.println("pppanem1"+product_name);
        System.out.println("pppanem2"+image1);


    }


    private void setCustomFontAndStyle(TabLayout tabLayout, Integer position) {
        mTypeface = Typeface.createFromAsset(ProductDetailActivity.this.getAssets(), "fonts/Roboto-Medium.ttf");
        mTypefaceBold = Typeface.createFromAsset(ProductDetailActivity.this.getAssets(), "fonts/Roboto-Medium.ttf");
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    if (j == position) {
                        ((TextView) tabViewChild).setTypeface(mTypefaceBold, Typeface.NORMAL);
                    } else {
                        ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
                    }
                }
            }
        }

    }

    int o1 = 0, o2 = 0, o3 = 0, o4 = 0, o5 = 0, o6 = 0;

    public void choosingColor(View view) {
        switch (view.getId()) {
            case R.id.redColor: {
                if (o1 % 2 == 0) o1 = 1;
                else o1 = 0;
            }
            case R.id.blackColor: {
                if (o2 % 2 == 0) o2 = 1;
                else o2 = 0;
            }
            case R.id.blueColor: {
                if (o3 % 2 == 0) o3 = 1;
                else o3 = 0;
            }
            case R.id.greenColor: {
                if (o4 % 2 == 0) o4 = 1;
                else o4 = 0;
            }
            case R.id.whiteColor: {
                if (o5 % 2 == 0) o5 = 1;
                else o5 = 0;
            }
            case R.id.grayColor: {
                if (o6 % 2 == 0) o6 = 1;
                else o6 = 0;
            }
        }
    }

    int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0, c6 = 0;

    public void choosSize(View view) {
        switch (view.getId()) {
            case R.id.small: {
                if (c1 % 2 == 0) c1 = 1;
                else c1 = 0;
            }
            case R.id.mediam: {
                if (c2 % 2 == 0) c2 = 1;
                else c2 = 0;
            }

            case R.id.larg: {
                if (c3 % 2 == 0) c3 = 1;
                else c3 = 0;
            }

            case R.id.xlarg: {
                if (c4 % 2 == 0) c4 = 1;
                else c4 = 0;
            }

            case R.id.xxlarg: {
                if (c5 % 2 == 0) c5 = 1;
                else c5 = 0;
            }

            case R.id.xxxlarg: {
                if (c1 % 6 == 0) c6 = 1;
                else c6 = 0;
            }
        }
    }

    public void sendNotif(View view) {
        int id = 1;
        TextView tt = (TextView) view;
        String s = tt.getText().toString();
        color += o1;
        color += o2;
        color += o3;
        color += o4;
        color += o5;
        color += o6;
        size += c1;
        size += c2;
        size += c3;
        size += c4;
        size += c5;
        size += c6;
        new gSigninActivity(this, 0, emailStr, id, size, color, s).execute(emailStr);
    }

    public void addQuantity(View view) {
        TextView tt = (TextView) view;
        String s = tt.getText().toString();
        int x = Integer.parseInt(s);
        x += 1;
        tt.setText(x);
        view = tt;
    }

    public void subQantity(View view) {
        TextView tt = (TextView) view;
        String s = tt.getText().toString();
        int x = Integer.parseInt(s);
        x -= 1;
        tt.setText(x);
        view = tt;
    }

    private class gSigninActivity extends AsyncTask<String, String, String> {
        private Context context;
        private String emailStr, size, color, quantity;
        private int id;
        private int status;
        private int product_id;

        public gSigninActivity(Context context, int status, String emailStr, int id, String size, String color, String quantity) {
            this.context = context;
            this.status = status;
            this.emailStr = emailStr;
            this.id = id;
            this.size = size;
            this.color = color;
            this.quantity = quantity;
        }

        public gSigninActivity(Context context, int status, int product_id) {
            this.context = context;
            this.status = status;
            this.product_id = product_id;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                String link = "";
                if (status == 0)
                    link = "https://c-store.000webhostapp.com/buyingOrder.php?username=" + emailStr + "&id=" + id + "&size=" + size + "&color=" + color + "&quantity=" + quantity + "";
                if (status == 1)
                    link = "https://c-store.000webhostapp.com/productDetails.php?product_id="+product_id+"";
              System.out.println("productRequired"+product_id);
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
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onPostExecute(String result2) {
            super.onPostExecute(result2);
            System.out.println("json" + result2);
            if (status == 1) {
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
                            image1=jsonObject.getString("product_pic");
                            image2=jsonObject.getString("product_pic1");
                            image3=jsonObject.getString("product_pic2");
                            image4=jsonObject.getString("product_pic3");
                            seller_name=jsonObject.getString("sellerEmail");
                            product_price=jsonObject.getString("Price");
                            product_name=jsonObject.getString("product_name");
                            product_quantity= jsonObject.getString("quantity");
                            productCategory= jsonObject.getString("category");
System.out.println(product_name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context.getApplicationContext(), "email or password is wrong", Toast.LENGTH_SHORT).show();
                }
                ProductDetailPagerAdapter adapter = new ProductDetailPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), product_name, product_price,seller_name,image1,image2,image3,image4,product_quantity,productCategory);
                viewPager.setAdapter(adapter);
                viewPager.setOffscreenPageLimit(5);
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

            }
        }
    }
}