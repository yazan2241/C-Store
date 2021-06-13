package fragment;

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

/**
 * Created by wolfsoft4 on 21/8/18.
 */

public class Shoes_Two extends Fragment {
    String pic;
    public Shoes_Two(String s){
        this.pic=s;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shoes_two,container,false);
        ImageView im=view.findViewById(R.id.secondpic);
        Glide.with(this).load("https://c-store.000webhostapp.com/upload/"+pic).into(im);
        return view;
    }
}
