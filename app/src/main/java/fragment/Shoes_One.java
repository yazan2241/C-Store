package fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cStore.shop.R;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wolfsoft4 on 21/8/18.
 */

public class Shoes_One extends Fragment {
String pic="";
  public Shoes_One(String s){
      this.pic=s;
      System.out.println("pic1"+pic);
  }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shoes_one,container,false);
        ImageView im=view.findViewById(R.id.firstPic);
        Glide.with(this).load("https://c-store.000webhostapp.com/upload/"+pic).into(im);
        return view;
    }
}
