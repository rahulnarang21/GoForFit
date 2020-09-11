package goforfit.com.goforfit.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import goforfit.com.goforfit.R;
import goforfit.com.goforfit.activities.LoginActivity;
import goforfit.com.goforfit.activities.MainActivity;
import goforfit.com.goforfit.adapters.CategoriesRecyclerAdapter;
import goforfit.com.goforfit.adapters.FitnessRecyclerAdapter;
import goforfit.com.goforfit.adapters.PEClassesRecyclerAdapter;
import goforfit.com.goforfit.adapters.SportsRecyclerAdapter;
import goforfit.com.goforfit.helper.AppConfig;
import goforfit.com.goforfit.helper.Utility;
import goforfit.com.goforfit.models.Category;
import goforfit.com.goforfit.models.Fitness;
import goforfit.com.goforfit.models.PEClassModel;
import goforfit.com.goforfit.models.SportsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    RecyclerView categoriesRecyclerView,classesRecyclerView,fitnessRecyclerView,sportsRecyclerView;
    CategoriesRecyclerAdapter categoriesRecyclerAdapter;
    PEClassesRecyclerAdapter peClassesRecyclerAdapter;
    FitnessRecyclerAdapter fitnessRecyclerAdapter;
    SportsRecyclerAdapter sportsRecyclerAdapter;
    ArrayList<Category> categoriesArrayList = new ArrayList<>();
    ArrayList<PEClassModel> peClassModelArrayList = new ArrayList<>();
    ArrayList<SportsModel> sportsModelArrayList = new ArrayList<>();
    ArrayList<Fitness> fitnessArrayList = new ArrayList<>();
    private String commonImage = "http://fitindia.gov.in/wp-content/uploads/2020/03/Strong-Women_inner.jpg";
    String userId;
    TextView peSchoolLabel,fitnessLabel,sportsLabel;
    ImageView moviesImage,takeTest;
    Resources resources;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        moviesImage = view.findViewById(R.id.movies_image);
        takeTest = view.findViewById(R.id.take_test);
        peSchoolLabel = view.findViewById(R.id.pe_school_label);
        fitnessLabel = view.findViewById(R.id.fitness_label);
        sportsLabel = view.findViewById(R.id.sports_label);

        resources = getContext().getResources();

        Typeface font_bold = Typeface.createFromAsset(resources.getAssets(),
                "fonts/Montserrat-Bold.ttf");
        Typeface fontMedium = Typeface.createFromAsset(resources.getAssets(),
                "fonts/Montserrat-Medium.ttf");
//        toolbarTitle.setTypeface(font_bold);
//        toolbarTitle1.setTypeface(font_bold);
        peSchoolLabel.setTypeface(fontMedium);
        fitnessLabel.setTypeface(fontMedium);
        sportsLabel.setTypeface(fontMedium);

        setCategoryRecyclerView(view);
        setClassesRecyclerView(view);
        setFitnessRecyclerView(view);
        setSportsRecyclerView(view);

        moviesImage.setOnClickListener(this);
        takeTest.setOnClickListener(this);
        return view;
    }

    private void setCategoryRecyclerView(View view){
        categoriesArrayList.clear();
        categoriesArrayList.add(new Category(1,R.drawable.banner1));
        categoriesArrayList.add(new Category(2,R.drawable.banner2));
        categoriesRecyclerView = view.findViewById(R.id.categories_recycler_view);
        categoriesRecyclerAdapter = new CategoriesRecyclerAdapter(getContext(),categoriesArrayList,getActivity().getSupportFragmentManager());

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView.setLayoutManager(horizontalLayoutManagaer);
        categoriesRecyclerView.setNestedScrollingEnabled(false);

        categoriesRecyclerView.setAdapter(categoriesRecyclerAdapter);
        //loadCategories();
    }

    private void setClassesRecyclerView(View view){
        peClassModelArrayList.clear();
        for (int i=1;i<=6;i++){
            ArrayList<PEClassModel.PEClass> peClassArrayList = new ArrayList<>();
            if (i==1){
                peClassArrayList.add(new PEClassModel.PEClass("Class-"+(2*i-1),R.drawable.class1));
                peClassArrayList.add(new PEClassModel.PEClass("Class-"+(2*i),R.drawable.class2));
            }
            else if(i==2){
                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i-1),R.drawable.class3));
                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i),R.drawable.class4));
            }
            else if(i==3){
                peClassArrayList.add(new PEClassModel.PEClass("Class-"+(2*i-1),R.drawable.class5));
                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i),R.drawable.class6));
            }
            else if(i==4){
                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i-1),R.drawable.class7));
                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i),R.drawable.class8));
            }
            else if(i==5){
                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i-1),R.drawable.class9));
                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i),R.drawable.class10));
            }

            else if(i==6){
                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i-1),R.drawable.class11));
                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i),R.drawable.class12));
            }

            peClassModelArrayList.add(new PEClassModel(peClassArrayList));
        }
        classesRecyclerView = view.findViewById(R.id.classes_recycler_view);
        peClassesRecyclerAdapter = new PEClassesRecyclerAdapter(getContext(),peClassModelArrayList,getFragmentManager());

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        classesRecyclerView.setLayoutManager(horizontalLayoutManagaer);
        classesRecyclerView.setNestedScrollingEnabled(false);

        classesRecyclerView.setAdapter(peClassesRecyclerAdapter);
        //loadCategories();
    }

    private void setFitnessRecyclerView(View view){
        fitnessArrayList.clear();
        fitnessArrayList.add(new Fitness("For Children",R.drawable.children));
        fitnessArrayList.add(new Fitness("For youth",R.drawable.youth));
        fitnessArrayList.add(new Fitness("For Adult",R.drawable.adult));
        fitnessArrayList.add(new Fitness("Seniors",R.drawable.senior));

        fitnessRecyclerView = view.findViewById(R.id.fitness_recycler_view);
        fitnessRecyclerAdapter = new FitnessRecyclerAdapter(getContext(),fitnessArrayList,getFragmentManager());

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        fitnessRecyclerView.setLayoutManager(horizontalLayoutManagaer);
        fitnessRecyclerView.setNestedScrollingEnabled(false);

        fitnessRecyclerView.setAdapter(fitnessRecyclerAdapter);
        //loadCategories();
    }


    private void setSportsRecyclerView(View view){
        sportsModelArrayList.clear();
        for (int i=1;i<=10;i++){
            ArrayList<SportsModel.Sports> sportsArrayList = new ArrayList<>();
            if (i==1){
                sportsArrayList.add(new SportsModel.Sports("Athletics",R.drawable.sports1));
                sportsArrayList.add(new SportsModel.Sports("Archery",R.drawable.sports2));
            }
            else if(i==2){
                sportsArrayList.add(new SportsModel.Sports("Badminton",R.drawable.sports3));
                sportsArrayList.add(new SportsModel.Sports("Basketball",R.drawable.sports4));
            }
            else if(i==3){
                sportsArrayList.add(new SportsModel.Sports("Boxing",R.drawable.sports5));
                sportsArrayList.add(new SportsModel.Sports("Cricket",R.drawable.sports6));
            }
            else if(i==4){
                sportsArrayList.add(new SportsModel.Sports("Indigenous Games",R.drawable.sports7));
                sportsArrayList.add(new SportsModel.Sports("Football",R.drawable.sports8));
            }
            else if(i==5){
                sportsArrayList.add(new SportsModel.Sports("Gymnastics",R.drawable.sports9));
                sportsArrayList.add(new SportsModel.Sports("Hockey",R.drawable.sports10));
            }

            else if(i==6){
                sportsArrayList.add(new SportsModel.Sports("Judo",R.drawable.sports11));
                sportsArrayList.add(new SportsModel.Sports("Kabaddi",R.drawable.sports12));
            }

            else if(i==7){
                sportsArrayList.add(new SportsModel.Sports("Kho Kho",R.drawable.sports13));
                sportsArrayList.add(new SportsModel.Sports("Swimming",R.drawable.sports14));
            }

            else if(i==8){
                sportsArrayList.add(new SportsModel.Sports("Table Tennis",R.drawable.sports15));
                sportsArrayList.add(new SportsModel.Sports("Tennis",R.drawable.sports16));
            }

            else if(i==9){
                sportsArrayList.add(new SportsModel.Sports("Volleyball",R.drawable.sports17));
                sportsArrayList.add(new SportsModel.Sports("Warm Up & Cool Down",R.drawable.sports18));
            }

            else if(i==10){
                sportsArrayList.add(new SportsModel.Sports("Weightlifting",R.drawable.sports19));
                sportsArrayList.add(new SportsModel.Sports("Wrestling",R.drawable.sports20));
            }
            sportsModelArrayList.add(new SportsModel(sportsArrayList));
        }
        sportsRecyclerView = view.findViewById(R.id.sports_recycler_view);
        sportsRecyclerAdapter = new SportsRecyclerAdapter(getContext(),sportsModelArrayList,getFragmentManager());

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        sportsRecyclerView.setLayoutManager(horizontalLayoutManagaer);
        sportsRecyclerView.setNestedScrollingEnabled(false);

        sportsRecyclerView.setAdapter(sportsRecyclerAdapter);

        //loadCategories();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.movies_image:
                loadFragment(new CommonFragment(),AppConfig.MOVIES_URL);
                //Utility.startWebViewActivity(getContext(),AppConfig.WEB_VIEW_URL+"movie","Movies");
                break;
            case R.id.take_test:
                loadFragment(new CommonFragment(),AppConfig.TAKE_TEST_URL);
                //Utility.startWebViewActivity(this,AppConfig.WEB_VIEW_URL+"movies","Take Test");
                break;
        }
    }

    private void loadFragment(Fragment fragment, String url) {
        // load fragment
        Bundle bundle = new Bundle();
//        if (userId==null){
//            userId = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(AppConfig.USER_ID,"");
//        }
        //bundle.putString(AppConfig.INTENT_STRING_EXTRA,label);
        bundle.putString(AppConfig.INTENT_URL_EXTRA,url);
        //bundle.putString(AppConfig.USER_ID,userId);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
