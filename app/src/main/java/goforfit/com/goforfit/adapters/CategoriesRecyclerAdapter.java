package goforfit.com.goforfit.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import goforfit.com.goforfit.R;
import goforfit.com.goforfit.fragments.CommonFragment;
import goforfit.com.goforfit.helper.AppConfig;
import goforfit.com.goforfit.helper.Utility;
import goforfit.com.goforfit.models.Category;

/**
 * Created by shyju New System on 26-Feb-18.
 */

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Category> categoryArrayList;
    Context context;
    FragmentManager fragmentManager;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

//    class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView categoryTitle;
//        ImageView categoryImage;
//
//        private MyViewHolder(View view) {
//            super(view);
//            categoryTitle = view.findViewById(R.id.category_title);
//            categoryImage = view.findViewById(R.id.category_image);
//
//        }
//    }


    public CategoriesRecyclerAdapter(Context context, ArrayList<Category> categoryArrayList, FragmentManager fragmentManager) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
        this.fragmentManager = fragmentManager;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_category_banner_layout, parent, false);

//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.category_layout, parent, false);
//
//        return new MyViewHolder(itemView);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int item_width = deviceWidth - (deviceWidth/100 * 15);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        params.width = item_width;
        itemView.setLayoutParams(params);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        // Following code give a row of header and decrease the one position from listview items
        final Category data = categoryArrayList.get(position);

        if (position == categoryArrayList.size()-1){
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            params.rightMargin = 25;
            holder.itemView.setLayoutParams(params);
        }

        // You have to set your listview items values with the help of model class and you can modify as per your needs

//        Glide.with(context).load(data.getBannerurl())
//                .thumbnail(0.5f)
//                .into(itemViewHolder.categoryImage);
        itemViewHolder.categoryImage.setImageResource(data.getBannerurl());
        itemViewHolder.categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Utility.startWebViewActivity(context, AppConfig.BANNER_URL1,"Sports Day");
                loadFragment(AppConfig.BANNER_URL1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;
        ImageView categoryImage;

        private ItemViewHolder(View view) {
            super(view);
//            categoryTitle = view.findViewById(R.id.category_title);
            categoryImage = view.findViewById(R.id.category_image);

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
