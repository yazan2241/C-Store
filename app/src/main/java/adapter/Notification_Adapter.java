package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cStore.shop.R;
import com.cStore.shop.RecyclerViewClickListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import modelclass.Home_Model;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.ViewHolder> {


    Context context;
    ArrayList<Home_Model> home_models;
    boolean showingFirst = false;
    private RecyclerViewClickListener recyclerViewOnItemClick;
    public int Prodid=0;
    public Notification_Adapter(Context context,RecyclerViewClickListener recyclerViewClickListener, ArrayList<Home_Model> home_models) {
        this.context = context;
        this.home_models = home_models;
        this.recyclerViewOnItemClick=recyclerViewClickListener;
    }

    @NonNull
    @Override
    public Notification_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);

        return new  Notification_Adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final Notification_Adapter.ViewHolder holder, int position) {

        // holder.imageshose.setImageBitmap(getBitmapFromURL(home_models.get(position).getImageshose()));
        System.out.println("eeeeeee"+home_models.get(position).getImageshose());
        try {
            Glide.with(context).load(new URL("https://c-store.000webhostapp.com/upload/"+home_models.get(position).getImageshose())).into(holder.imageshose);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //  holder.discount.setText(home_models.get(position).getDiscount());
        holder.buyer.setText(home_models.get(position).getPrice());
        holder.time.setText(home_models.get(position).getDesc());

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




    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageshose;
        TextView buyer,time;
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
            imageshose = itemView.findViewById(R.id.profilePic);
            //  productId=itemView.findViewById(R.id.productId);
            buyer =itemView.findViewById(R.id.cart_item_name);
            // discount = itemView.findViewById(R.id.discount);
            time = itemView.findViewById(R.id.time_notification);
            lvContainer=itemView.findViewById(R.id.lvcontainerNotification);
        }
    }
    public int getProductId(){
        return Prodid;
    }
}
