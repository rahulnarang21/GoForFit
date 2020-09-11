package goforfit.com.goforfit.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import goforfit.com.goforfit.R;
import goforfit.com.goforfit.fragments.CommonFragment;
import goforfit.com.goforfit.helper.AppConfig;
import goforfit.com.goforfit.helper.Utility;
import goforfit.com.goforfit.models.SportsModel;

/**
 * Created by shyju New System on 26-Feb-18.
 */

public class SportsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<SportsModel> sportsModelArrayList;
    Context context;
    FragmentManager fragmentManager;


    public SportsRecyclerAdapter(Context context, ArrayList<SportsModel> sportsModelArrayList,
                                 FragmentManager fragmentManager) {
        this.sportsModelArrayList = sportsModelArrayList;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_sports_layout, parent, false);

//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.category_layout, parent, false);
//
//        return new MyViewHolder(itemView);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        // Following code give a row of header and decrease the one position from listview items
        final SportsModel data = sportsModelArrayList.get(position);


        // You have to set your listview items values with the help of model class and you can modify as per your needs

        ArrayList<SportsModel.Sports> sportsArrayList = data.getSportsArrayList();
        for (int i=0;i<sportsArrayList.size();i++){
            final SportsModel.Sports sports = sportsArrayList.get(i);
            if (i==0){
                Glide.with(context).load(sports.getImage())
                        .thumbnail(0.5f)
                        .into(itemViewHolder.classImage1);

                itemViewHolder.classImage1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String sportsNameInURL = sports.getName().replace(" ","-").toLowerCase();
                        loadFragment(AppConfig.WEB_VIEW_URL +sportsNameInURL);
                    }
                });
            }
            else if(i==1){
                Glide.with(context).load(sports.getImage())
                        .thumbnail(0.5f)
                        .into(itemViewHolder.classImage2);

                itemViewHolder.classImage2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String sportsNameInURL = sports.getName().replace(" ","-").toLowerCase();
                        loadFragment(AppConfig.WEB_VIEW_URL +sportsNameInURL);
                    }
                });
            }
        }

        if (position == sportsModelArrayList.size()-1){
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            params.rightMargin = 25;
            holder.itemView.setLayoutParams(params);
        }




    }

    @Override
    public int getItemCount() {
        return sportsModelArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView classImage1,classImage2;

        private ItemViewHolder(View view) {
            super(view);
            classImage1 = view.findViewById(R.id.class_image1);
            classImage2 = view.findViewById(R.id.class_image2);

        }
    }

    private void loadFragment(String url) {
        // load fragment
        Fragment fragment = new CommonFragment();
        Bundle bundle = new Bundle();
//        if (userId==null){
//            userId = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(AppConfig.USER_ID,"");
//        }
        //bundle.putString(AppConfig.INTENT_STRING_EXTRA,label);
        bundle.putString(AppConfig.INTENT_URL_EXTRA,url);
        //bundle.putString(AppConfig.USER_ID,userId);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
