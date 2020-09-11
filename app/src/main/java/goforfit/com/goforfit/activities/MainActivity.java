package goforfit.com.goforfit.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import goforfit.com.goforfit.R;
import goforfit.com.goforfit.adapters.CategoriesRecyclerAdapter;
import goforfit.com.goforfit.adapters.FitnessRecyclerAdapter;
import goforfit.com.goforfit.adapters.PEClassesRecyclerAdapter;
import goforfit.com.goforfit.adapters.SportsRecyclerAdapter;
import goforfit.com.goforfit.fragments.CommonFragment;
import goforfit.com.goforfit.fragments.HomeFragment;
import goforfit.com.goforfit.helper.AppConfig;
import goforfit.com.goforfit.helper.Utility;
import goforfit.com.goforfit.models.Category;
import goforfit.com.goforfit.models.Fitness;
import goforfit.com.goforfit.models.PEClassModel;
import goforfit.com.goforfit.models.SportsModel;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    DrawerLayout drawer;
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
    TextView toolbarTitle,toolbarTitle1,peSchoolLabel,fitnessLabel,sportsLabel;
    ImageView moviesImage,takeTest;
//    CardView takeTestCard,knowTestsCard,getActiveCard,registerFitIndiaChampionCard,fitnessBlogCard,nutritionBlogCard,shareStoryCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
//        toolbarTitle = findViewById(R.id.toolbar_title);
//        toolbarTitle1 = findViewById(R.id.toolbar_title1);
//        moviesImage = findViewById(R.id.movies_image);
//        takeTest = findViewById(R.id.take_test);
//        peSchoolLabel = findViewById(R.id.pe_school_label);
//        fitnessLabel = findViewById(R.id.fitness_label);
//        sportsLabel = findViewById(R.id.sports_label);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.side_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView userNameTxt = headerView.findViewById(R.id.user_name);
        TextView userImageTxt = headerView.findViewById(R.id.user_image);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPreferences.getString(AppConfig.NAME,"");
        userId = PreferenceManager.getDefaultSharedPreferences(this).getString(AppConfig.USER_ID,"");
        userNameTxt.setText(userName);
        if (!userName.equals(""))
            userImageTxt.setText(String.valueOf(userName.toUpperCase().charAt(0)));

//        Typeface font_bold = Typeface.createFromAsset(getAssets(),
//                "fonts/Montserrat-Bold.ttf");
//        Typeface fontMedium = Typeface.createFromAsset(getAssets(),
//                "fonts/Montserrat-Medium.ttf");
////        toolbarTitle.setTypeface(font_bold);
////        toolbarTitle1.setTypeface(font_bold);
//        peSchoolLabel.setTypeface(fontMedium);
//        fitnessLabel.setTypeface(fontMedium);
//        sportsLabel.setTypeface(fontMedium);
//
//        setCategoryRecyclerView();
//        setClassesRecyclerView();
//        setFitnessRecyclerView();
//        setSportsRecyclerView();
//
//        moviesImage.setOnClickListener(this);
//        takeTest.setOnClickListener(this);
        loadFragment(new HomeFragment(),"");
    }

//    private void setCategoryRecyclerView(){
//        categoriesArrayList.clear();
//        categoriesArrayList.add(new Category(1,R.drawable.banner1));
//        categoriesArrayList.add(new Category(2,R.drawable.banner2));
//        categoriesRecyclerView = findViewById(R.id.categories_recycler_view);
//        categoriesRecyclerAdapter = new CategoriesRecyclerAdapter(this,categoriesArrayList);
//
//        LinearLayoutManager horizontalLayoutManagaer
//                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        categoriesRecyclerView.setLayoutManager(horizontalLayoutManagaer);
//        categoriesRecyclerView.setNestedScrollingEnabled(false);
//
//        categoriesRecyclerView.setAdapter(categoriesRecyclerAdapter);
//        //loadCategories();
//    }
//
//    private void setClassesRecyclerView(){
//        peClassModelArrayList.clear();
//        for (int i=1;i<=6;i++){
//            ArrayList<PEClassModel.PEClass> peClassArrayList = new ArrayList<>();
//            if (i==1){
//                peClassArrayList.add(new PEClassModel.PEClass("Class-"+(2*i-1),R.drawable.class1));
//                peClassArrayList.add(new PEClassModel.PEClass("Class-"+(2*i),R.drawable.class2));
//            }
//            else if(i==2){
//                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i-1),R.drawable.class3));
//                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i),R.drawable.class4));
//            }
//            else if(i==3){
//                peClassArrayList.add(new PEClassModel.PEClass("Class-"+(2*i-1),R.drawable.class5));
//                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i),R.drawable.class6));
//            }
//            else if(i==4){
//                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i-1),R.drawable.class7));
//                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i),R.drawable.class8));
//            }
//            else if(i==5){
//                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i-1),R.drawable.class9));
//                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i),R.drawable.class10));
//            }
//
//            else if(i==6){
//                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i-1),R.drawable.class11));
//                peClassArrayList.add(new PEClassModel.PEClass("class-"+(2*i),R.drawable.class12));
//            }
//
//            peClassModelArrayList.add(new PEClassModel(peClassArrayList));
//        }
//        classesRecyclerView = findViewById(R.id.classes_recycler_view);
//        peClassesRecyclerAdapter = new PEClassesRecyclerAdapter(this,peClassModelArrayList);
//
//        LinearLayoutManager horizontalLayoutManagaer
//                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        classesRecyclerView.setLayoutManager(horizontalLayoutManagaer);
//        classesRecyclerView.setNestedScrollingEnabled(false);
//
//        classesRecyclerView.setAdapter(peClassesRecyclerAdapter);
//        //loadCategories();
//    }
//
//    private void setFitnessRecyclerView(){
//        fitnessArrayList.clear();
//        fitnessArrayList.add(new Fitness("For Children",R.drawable.children));
//        fitnessArrayList.add(new Fitness("For youth",R.drawable.youth));
//        fitnessArrayList.add(new Fitness("For Adult",R.drawable.adult));
//        fitnessArrayList.add(new Fitness("Seniors",R.drawable.senior));
//
//        fitnessRecyclerView = findViewById(R.id.fitness_recycler_view);
//        fitnessRecyclerAdapter = new FitnessRecyclerAdapter(this,fitnessArrayList);
//
//        LinearLayoutManager horizontalLayoutManagaer
//                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        fitnessRecyclerView.setLayoutManager(horizontalLayoutManagaer);
//        fitnessRecyclerView.setNestedScrollingEnabled(false);
//
//        fitnessRecyclerView.setAdapter(fitnessRecyclerAdapter);
//        //loadCategories();
//    }
//
//
//    private void setSportsRecyclerView(){
//        sportsModelArrayList.clear();
//        for (int i=1;i<=10;i++){
//            ArrayList<SportsModel.Sports> sportsArrayList = new ArrayList<>();
//            if (i==1){
//                sportsArrayList.add(new SportsModel.Sports("Athletics",R.drawable.sports1));
//                sportsArrayList.add(new SportsModel.Sports("Archery",R.drawable.sports2));
//            }
//            else if(i==2){
//                sportsArrayList.add(new SportsModel.Sports("Badminton",R.drawable.sports3));
//                sportsArrayList.add(new SportsModel.Sports("Basketball",R.drawable.sports4));
//            }
//            else if(i==3){
//                sportsArrayList.add(new SportsModel.Sports("Boxing",R.drawable.sports5));
//                sportsArrayList.add(new SportsModel.Sports("Cricket",R.drawable.sports6));
//            }
//            else if(i==4){
//                sportsArrayList.add(new SportsModel.Sports("Indigenous Games",R.drawable.sports7));
//                sportsArrayList.add(new SportsModel.Sports("Football",R.drawable.sports8));
//            }
//            else if(i==5){
//                sportsArrayList.add(new SportsModel.Sports("Gymnastics",R.drawable.sports9));
//                sportsArrayList.add(new SportsModel.Sports("Hockey",R.drawable.sports10));
//            }
//
//            else if(i==6){
//                sportsArrayList.add(new SportsModel.Sports("Judo",R.drawable.sports11));
//                sportsArrayList.add(new SportsModel.Sports("Kabaddi",R.drawable.sports12));
//            }
//
//            else if(i==7){
//                sportsArrayList.add(new SportsModel.Sports("Kho Kho",R.drawable.sports13));
//                sportsArrayList.add(new SportsModel.Sports("Swimming",R.drawable.sports14));
//            }
//
//            else if(i==8){
//                sportsArrayList.add(new SportsModel.Sports("Table Tennis",R.drawable.sports15));
//                sportsArrayList.add(new SportsModel.Sports("Tennis",R.drawable.sports16));
//            }
//
//            else if(i==9){
//                sportsArrayList.add(new SportsModel.Sports("Volleyball",R.drawable.sports17));
//                sportsArrayList.add(new SportsModel.Sports("Warm Up & Cool Down",R.drawable.sports18));
//            }
//
//            else if(i==10){
//                sportsArrayList.add(new SportsModel.Sports("Weightlifting",R.drawable.sports19));
//                sportsArrayList.add(new SportsModel.Sports("Wrestling",R.drawable.sports20));
//            }
//            sportsModelArrayList.add(new SportsModel(sportsArrayList));
//        }
//        sportsRecyclerView = findViewById(R.id.sports_recycler_view);
//        sportsRecyclerAdapter = new SportsRecyclerAdapter(this,sportsModelArrayList);
//
//        LinearLayoutManager horizontalLayoutManagaer
//                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        sportsRecyclerView.setLayoutManager(horizontalLayoutManagaer);
//        sportsRecyclerView.setNestedScrollingEnabled(false);
//
//        sportsRecyclerView.setAdapter(sportsRecyclerAdapter);
//
//        //loadCategories();
//    }

    public void showLogoutDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(getString(R.string.logout_confirm_msg));

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.alarm);

        alertDialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                editor.remove(AppConfig.USER_ID);
                editor.remove(AppConfig.IS_LOGGED_IN);
                editor.apply();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
                dialog.cancel();

            }
        });

        alertDialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        CommonFragment commonFragment = new CommonFragment();
        switch (menuItem.getItemId()){
            case R.id.side_nav_home:
                loadFragment(new HomeFragment(),"");
                break;
            case R.id.side_nav_pe_activities:
                loadFragment(commonFragment,AppConfig.PE_ACTIVITIES_URL);
                break;
            case R.id.side_nav_fitness:
                loadFragment(commonFragment,AppConfig.BASE_URL);
                break;
            case R.id.side_nav_sports:
                loadFragment(commonFragment,AppConfig.SPORTS_URL);
                break;
            case R.id.side_nav_movies:
                loadFragment(commonFragment,AppConfig.MOVIES_URL);
                break;
            case R.id.side_nav_take_test:
                loadFragment(commonFragment,AppConfig.BASE_URL);
                break;

            case R.id.side_nav_logout:
                showLogoutDialog();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.movies_image:
//                Utility.startWebViewActivity(this,AppConfig.WEB_VIEW_URL+"movie","Movies");
//                break;
//            case R.id.take_test:
//                //Utility.startWebViewActivity(this,AppConfig.WEB_VIEW_URL+"movies","Take Test");
//                break;
//        }
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setMessage(getResources().getString(R.string.exit_from_app));

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.alarm);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                dialog.cancel();
                finish();
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }
}
