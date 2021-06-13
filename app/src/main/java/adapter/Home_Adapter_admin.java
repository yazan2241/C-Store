package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cStore.shop.R;

import java.util.ArrayList;

import modelclass.Home_Model_admin;

//import com.cStore.shop.MainActivity;


public class Home_Adapter_admin extends RecyclerView.Adapter<Home_Adapter_admin.ViewHolder> {

    Context context;
    ArrayList<Home_Model_admin>home_models;
    boolean showingFirst = false;


    public Home_Adapter_admin(Context context, ArrayList<Home_Model_admin> home_models) {
        this.context = context;
        this.home_models = home_models;
    }

    @NonNull
    @Override
    public Home_Adapter_admin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_admin,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final Home_Adapter_admin.ViewHolder holder, int position) {
// holder.imageshose.setImageBitmap(getBitmapFromURL(home_models.get(position).getImageshose()));
        System.out.println("eeeeeee" + home_models.get(position).getImageshose());
        Glide.with(context).load("https://c-store.000webhostapp.com/upload/" + home_models.get(position).getImageshose()).into(holder.pic23);
        //  holder.discount.setText(home_models.get(position).getDiscount());
    holder.name2.setText(home_models.get(position).getPrice());
    holder.text4.setText(home_models.get(position).getDesc());
        holder.text2.setText(home_models.get(position).getDesc1());
        holder.text3.setText(home_models.get(position).getDesc2());
}
    //  Home_Model model=home_models.get(position);
    //  Picasso.get().load("https://c-store.000webhostapp.com/upload/FB_IMG_1594731278302.jpg"+model.getImageshose()).into(holder.imageshose);

/*
         holder.heart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (showingFirst == true) {
                     holder.heart.setBackgroundResource(R.drawable.ic_like_heart_outline);
                     showingFirst = false;
                 }else {
                     holder.heart.setBackgroundResource(R.drawable.ic_like);
                     showingFirst = true;
                 }
             }
         });

 */





    @Override
    public int getItemCount() {
        return home_models.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pic23;
        TextView name2,text4,text2,text3;
        public ViewHolder(View parent) {
            super(parent);
            /*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context=view.getContext();
                }
            });
*/
            //heart = itemView.findViewById(R.id.heart);
            pic23 = itemView.findViewById(R.id.Pic2);

            name2 =itemView.findViewById(R.id.name);
           // discount = itemView.findViewById(R.id.discount);
            text4 = itemView.findViewById(R.id.markaname);
            text2 = itemView.findViewById(R.id.comnumber);
            text3 = itemView.findViewById(R.id.mobilenumber);
        }
    }
}
