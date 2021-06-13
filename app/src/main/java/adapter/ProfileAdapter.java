package adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cStore.shop.R;

import java.util.ArrayList;

import modelclass.ProfileModel;

/**
 * Created by wolfsoft4 on 20/8/18.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    Context context;
    private ArrayList<ProfileModel> profileModelArrayList;

    public ProfileAdapter(Context context, ArrayList<ProfileModel> profileModelArrayList) {
        this.context = context;
        this.profileModelArrayList = profileModelArrayList;
    }

    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileAdapter.ViewHolder holder, int position) {
        holder.txtmyreceived.setText(profileModelArrayList.get(position).getTxtmyreceived());
        holder.txtn1.setText(profileModelArrayList.get(position).getTxtn1());


    }

    @Override
    public int getItemCount() {
        return profileModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmyreceived,txtn1;



        public ViewHolder(View itemView) {
            super(itemView);


            txtmyreceived=itemView.findViewById(R.id.txtmyreceived);
            txtn1=itemView.findViewById(R.id.txtn1);


        }
    }
}
