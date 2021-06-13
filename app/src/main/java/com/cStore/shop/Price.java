package com.cStore.shop;




import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.viewpagerindicator.LinePageIndicator;
import com.cStore.shop.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import adapter.FromCategoryAdapter;
import adapter.ShoesPagerAdapter;
import modelclass.FRomCategoryModel;

/**
 * Created by wolfsoft4 on 18/8/18.
 */

public class Price extends Fragment {
    private ViewPager viewPager;
    private LinePageIndicator indicator;
    Spinner spinner_sort_by;
    private ShoesPagerAdapter shoesPagerAdapter;


    private FromCategoryAdapter fromCategoryAdapter;
    private RecyclerView recyclerview;
    private ArrayList<FRomCategoryModel> fRomCategoryModelArrayList;

    Integer img1[]={R.drawable.ic_like_heart_outline,R.drawable.ic_like_heart_outline,R.drawable.ic_like_heart_outline,R.drawable.ic_like_heart_outline,};
    Integer img2[]={R.drawable.shoes,R.drawable.shoes2,R.drawable.shoes,R.drawable.shoes2};

    String  price[]={"$75","$225","$210","$145"};
    String  shoes[]={"Ninja high top sneackers","Ninja high top sneackers","Ninja high top sneackers","Ninja high top sneackers"};


TextView product_name,product_price,Seller,Quan,Cat;
    ImageView Pic1,Pic2,Pic3,Pic4;
    String name,pprice,seller ,pic1 ,pic2 ,pic3 ,pic4;
    String quan ,cat;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.price,container,false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        LinePageIndicator indicator = (LinePageIndicator) view.findViewById(R.id.indicator);

        shoesPagerAdapter = new ShoesPagerAdapter(getChildFragmentManager(),pic1,pic2,pic3,pic4);

        viewPager.setAdapter(shoesPagerAdapter);

        indicator.setViewPager(viewPager);

        //shoesPagerAdapter.registerDataSetObserver(indicator.getDataSetObserver());

        product_name=(TextView) view.findViewById(R.id.product_name) ;
        product_price=(TextView)view.findViewById(R.id.product_price);
        Seller=view.findViewById(R.id.seller);
        Quan=view.findViewById(R.id.quan);
        Cat=view.findViewById(R.id.cat);
       // Pic1=view.findViewById(R.id.);
       // Pic2=view.findViewById(R.id.);
      //  Pic3=view.findViewById(R.id.);
      //  Pic4=view.findViewById(R.id.);

//        recyclerview code is here
        product_name.setText(name);
        product_price.setText(pprice);
        Seller.setText(seller);
      Quan.setText(quan);
        Cat.setText(cat);


        recyclerview = view.findViewById(R.id.recyclerview3);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        fRomCategoryModelArrayList = new ArrayList<>();

        for (int i = 0; i < img1.length; i++) {
            FRomCategoryModel view1 = new FRomCategoryModel(img1[i],img2[i],price[i],shoes[i]);
            fRomCategoryModelArrayList.add(view1);
        }
        fromCategoryAdapter = new FromCategoryAdapter(getActivity(),fRomCategoryModelArrayList);
        recyclerview.setAdapter(fromCategoryAdapter);




        return view;
        /*
        spinner_sort_by = (Spinner)findViewById(R.id.spinner_custom_details);

        //        Spinner for Sending Id

        List<String> list = new ArrayList<String>();
        list.add("اختر القياس");
        list.add("S");
        list.add("M");
        list.add("L");
        list.add("XL");
        list.add("XXL");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Price.this, R.layout.spinner_item, R.id.spinner_text, list);
        spinner_sort_by.setAdapter(dataAdapter);
*/
    }
    public Price(String name,String price,String seller,String pic1,String pic2,String pic3,String pic4,String quan,String cat){
this.name=name;
this.pprice=price;
this.seller=seller;
this.pic1=pic1;
this.pic2=pic2;
this.pic3=pic3;
this.pic4=pic4;
this.quan=quan;
this.cat=cat;

    }
}


