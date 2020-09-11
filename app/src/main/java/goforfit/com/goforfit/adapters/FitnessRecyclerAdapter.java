package goforfit.com.goforfit.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import goforfit.com.goforfit.models.Category;
import goforfit.com.goforfit.models.Fitness;

/**
 * Created by shyju New System on 26-Feb-18.
 */

public class FitnessRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Fitness> fitnessArrayList;
    Context context;
    Typeface fontMedium;
    FragmentManager fragmentManager;



    public FitnessRecyclerAdapter(Context context, ArrayList<Fitness> fitnessArrayList,
                                  FragmentManager fragmentManager) {
        this.fitnessArrayList = fitnessArrayList;
        this.context = context;
        fontMedium = Typeface.createFromAsset(context.getResources().getAssets(),
                "fonts/Montserrat-Medium.ttf");
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_fitness_layout, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        // Following code give a row of header and decrease the one position from listview items
        final Fitness data = fitnessArrayList.get(position);

        // You have to set your listview items values with the help of model class and you can modify as per your needs

        itemViewHolder.fitnessTitle.setText(data.getName());
        Glide.with(context).load(data.getImage())
                .thumbnail(0.5f)
                .into(itemViewHolder.fitnessImage);

        if (position == fitnessArrayList.size()-1){
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            params.rightMargin = 25;
            holder.itemView.setLayoutParams(params);
        }

        itemViewHolder.fitnessTitle.setTypeface(fontMedium);
        itemViewHolder.fitnessLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(AppConfig.BASE_URL);
                //Utility.startWebViewActivity(context, AppConfig.BANNER_URL1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return fitnessArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView fitnessTitle;
        ImageView fitnessImage;
        LinearLayout fitnessLayout;

        private ItemViewHolder(View view) {
            super(view);
            fitnessTitle = view.findViewById(R.id.fitness_title);
            fitnessImage = view.findViewById(R.id.fitness_image);
            fitnessLayout = view.findViewById(R.id.fitness_layout);

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
