package adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cStore.shop.MainActivity;
import com.cStore.shop.ProductDetailActivity;
import com.cStore.shop.Profile;
import com.cStore.shop.R;
import com.cStore.shop.RecyclerViewClickListener;
import com.cStore.shop.RecyclerViewOnItemClick;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import modelclass.Home_Model;



public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.ViewHolder> implements RecyclerViewClickListener {
    public int t;
    Context context;
    private static RecyclerViewClickListener itemListener;
    ArrayList<Home_Model>home_models;
    boolean showingFirst = false;
    private ArrayList<String> pri =new ArrayList<>();
    private RecyclerViewClickListener recyclerViewOnItemClick;
public int Prodid=0;
    public Home_Adapter(Context context,RecyclerViewClickListener recyclerViewOnItemClick1,ArrayList<Home_Model> home_models) {
        this.context = context;
        this.home_models = home_models;
        this.itemListener=itemListener;
        this.recyclerViewOnItemClick = recyclerViewOnItemClick1;
    }

    @NonNull
    @Override
    public Home_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final Home_Adapter.ViewHolder holder, int position) {

       // holder.imageshose.setImageBitmap(getBitmapFromURL(home_models.get(position).getImageshose()));
        System.out.println("eeeeeee"+home_models.get(position).getImageshose());
        try {
            Glide.with(context).load(new URL("https://c-store.000webhostapp.com/upload/"+home_models.get(position).getImageshose())).into(holder.imageshose);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //  holder.discount.setText(home_models.get(position).getDiscount());
         holder.price.setText(home_models.get(position).getPrice());
        holder.desc.setText(home_models.get(position).getDesc());
        Prodid=home_models.get(position).getProductId();

        holder.lvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Prodid=home_models.get(position).getProductId();
                recyclerViewOnItemClick.recyclerViewListClicked(Prodid);

            }
        });


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



    }

    @Override
    public int getItemCount() {
        return home_models.size();
    }

    @Override
    public void recyclerViewListClicked(int s) {
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageshose;
        TextView price,desc;
        LinearLayout lvContainer;
       // TextView productId;
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
            imageshose = itemView.findViewById(R.id.imageshose);
          //  productId=itemView.findViewById(R.id.productId);
            price =itemView.findViewById(R.id.ImagePrice);
           // discount = itemView.findViewById(R.id.discount);
            desc = itemView.findViewById(R.id.ImageDesc);
            lvContainer=itemView.findViewById(R.id.lvcontainer);
/*
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent=new Intent(MainActivity.this , cart.class);
                recyclerViewOnItemClick.onItemClick(getAdapterPosition());
                t=getAdapterPosition();
                System.out.println("asdasda"+getAdapterPosition());

            }
        });
            parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                recyclerViewOnItemClick.onLongItemClick(getAdapterPosition());
                return  true;}
        });

 */

    }

    }
    public int  getpos(){

        return t;
    }
    public int getProductId(){
        return Prodid;
    }
}
