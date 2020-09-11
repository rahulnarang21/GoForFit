package goforfit.com.goforfit.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import goforfit.com.goforfit.R;
import goforfit.com.goforfit.helper.AppConfig;
import goforfit.com.goforfit.helper.Utility;
import goforfit.com.goforfit.models.RegisterModel;
import goforfit.com.goforfit.webservice.RegisterRequest;
import goforfit.com.goforfit.webservice.ResponseListener;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements ResponseListener, View.OnClickListener {

    SharedPreferences sharedPreferences;
    //DatabaseManager databaseManager;
    TextView toolbarTitle;
    ImageView backBtn;
    //RadioGroup othersMinistryRadioGroup;
    //RadioButton individualRb,otherRb,ministryRb;
    //EditText categoryEdt,stateEdt,districtEdt,blockEdt;
    EditText nameEdt,emailEdt,mobileEdt,passwordEdt,confirmPasswordEdt;
    TextView submitBtn,loginBtn;
    private String name,email,mobile,password,confirmPass;
    ProgressDialog progressDialog;
//    ListView categoriesListView,statesListView,districtsListView,blocksListView;
//    ListViewDialogAdapter categoriesListAdapter,statesListAdapter,districtsListAdapter,blocksListAdapter;
//    ArrayList<ListViewDialogModel> statesArrayList = new ArrayList<>();
//    AlertDialog categoriesAlertDialog,statesAlertDialog,districtsAlertDialog,blocksAlertDialog;
//    int selectedCategoryPos=0,selectedStatePos = -1,selectedDistrictPos = -1,selectedBlockPos=-1;
//    ArrayList<ListViewDialogModel> districtsArrayList = new ArrayList<>();
//    ArrayList<ListViewDialogModel> blocksArrayList = new ArrayList<>();
//    ArrayList<ListViewDialogModel> categoriesArrayList = new ArrayList<>();
//    String selectedStateName = "";
//    StateDistrictBlockModel stateDistrictBlockModel;
//    ProgressDialog savingProgressDialog;
//    boolean noBlocksForDistrict = false;
//    boolean forFitIndiaChampion = false;
//    boolean areCategoriesLoaded = false;
    Resources resources;
//    LinearLayout individualDetailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbarTitle = findViewById(R.id.toolbar_title);
        //registerAsLabel = findViewById(R.id.register_as_label);
        backBtn = findViewById(R.id.back_btn);
//        categoryEdt = findViewById(R.id.category_edt);
//        stateEdt = findViewById(R.id.state_edt);
//        districtEdt = findViewById(R.id.district_edt);
//        blockEdt = findViewById(R.id.block_edt);
        nameEdt = findViewById(R.id.name_edt);
        //designationEdt = findViewById(R.id.designation_edt);
        emailEdt = findViewById(R.id.email_edt);
        mobileEdt = findViewById(R.id.mobile_edt);
        //cityEdt = findViewById(R.id.city_edt);
        passwordEdt = findViewById(R.id.password_edt);
        confirmPasswordEdt = findViewById(R.id.confirm_password_edt);
        submitBtn = findViewById(R.id.submit_btn);
        loginBtn = findViewById(R.id.login_btn);
//        othersMinistryRadioGroup = findViewById(R.id.other_ministry_rgroup);
//        individualRb = findViewById(R.id.individual_rb);
//        otherRb = findViewById(R.id.other_rb);
//        ministryRb = findViewById(R.id.ministry_rb);
//        individualDetailsLayout = findViewById(R.id.individual_details_layout);

        init();

    }

    public void init(){
        resources = this.getResources();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        databaseManager = new DatabaseManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

//        if (!sharedPreferences.getBoolean(AppConfig.IS_STATE_SAVED,false)){
//            GetStateDistrictBlockRequest getStateDistrictBlockRequest =
//                    new GetStateDistrictBlockRequest(this,this);
//            getStateDistrictBlockRequest.hitUserRequest();
//        }
//
//        if (this.getIntent().hasExtra(AppConfig.FOR_FIT_INDIA_CHAMPION))
//            forFitIndiaChampion = true;

//        if (forFitIndiaChampion) {
//            registerAsLabel.setVisibility(View.GONE);
//            othersMinistryRadioGroup.setVisibility(View.GONE);
//            toolbarTitle.setText(R.string.register_as_fit_india_champion);
//            categoryEdt.setVisibility(View.GONE);
//            loginBtn.setVisibility(View.GONE);
//        }
//        else {
//            toolbarTitle.setText(R.string.register);
//        }

        toolbarTitle.setText(R.string.register);

        Typeface font_bold = Typeface.createFromAsset(getAssets(),
                "fonts/Montserrat-Bold.ttf");
        Typeface fontMedium = Typeface.createFromAsset(getAssets(),
                "fonts/Montserrat-Medium.ttf");
        Typeface font_semi_bold = Typeface.createFromAsset(getAssets(),
                "fonts/Barlow-BlackItalic.otf");

        toolbarTitle.setTypeface(font_bold);

//        registerAsLabel.setTypeface(fontRegular);
//        individualRb.setTypeface(fontRegular);
//        otherRb.setTypeface(fontRegular);
//        ministryRb.setTypeface(fontRegular);
//        categoryEdt.setTypeface(fontRegular);
        nameEdt.setTypeface(fontMedium);
        emailEdt.setTypeface(fontMedium);
        mobileEdt.setTypeface(fontMedium);
//        stateEdt.setTypeface(fontRegular);
//        districtEdt.setTypeface(fontRegular);
//        blockEdt.setTypeface(fontRegular);
//        cityEdt.setTypeface(fontRegular);
        passwordEdt.setTypeface(fontMedium);
        confirmPasswordEdt.setTypeface(fontMedium);
        submitBtn.setTypeface(fontMedium);
        loginBtn.setTypeface(fontMedium);




        backBtn.setOnClickListener(this);
//        categoryEdt.setOnClickListener(this);
//        stateEdt.setOnClickListener(this);
//        districtEdt.setOnClickListener(this);
//        blockEdt.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
//        othersMinistryRadioGroup.setOnCheckedChangeListener(this);

//        getCategories();
//        getMinistryCategories();
//        getStates();

    }

    private boolean validate(){
        name = nameEdt.getText().toString().trim();
        email = emailEdt.getText().toString().trim();
        mobile = mobileEdt.getText().toString().trim();
//        city = cityEdt.getText().toString().trim();
        password = passwordEdt.getText().toString().trim();
        confirmPass = confirmPasswordEdt.getText().toString().trim();

        if (name.length() < 1 || !name.matches("[a-zA-Z ]+")) {
            nameEdt.requestFocus();
            nameEdt.setError(getString(R.string.validate_string_not_empty,getString(R.string.name)));
            return false;
        } else if (email.length() < 1 || !Utility.verify(email, RegisterActivity.this)) {
            emailEdt.requestFocus();
            emailEdt.setError(getString(R.string.validate_string_not_empty,getString(R.string.email)));
            return false;
        } else if (mobile.length() != 10) {
            mobileEdt.requestFocus();
            mobileEdt.setError(getString(R.string.validate_string_not_empty,getString(R.string.mobile_number)));
            return false;

        }
//        else if (selectedStatePos == -1) {
//            Toast.makeText(this, getString(R.string.validate_select_not_empty,getString(R.string.state)), Toast.LENGTH_LONG).show();
//            return false;
//        } else if (selectedDistrictPos==-1) {
//            Toast.makeText(this, getString(R.string.validate_select_not_empty,getString(R.string.district)), Toast.LENGTH_LONG).show();
//            return false;
//        }
//        else if (!noBlocksForDistrict && selectedBlockPos == -1){
//            Toast.makeText(this, getString(R.string.validate_select_not_empty,getString(R.string.block)), Toast.LENGTH_LONG).show();
//            return false;
//        }
//        else if (noBlocksForDistrict && blockEdt.getText().toString().trim().length()<1){
//            blockEdt.requestFocus();
//            blockEdt.setError(getString(R.string.validate_string_not_empty,getString(R.string.block)));
//            return false;
//        }
//        else if (city.length() < 3 || !city.matches("[a-zA-Z ]+")) {
//            cityEdt.requestFocus();
//            cityEdt.setError(getString(R.string.validate_string_not_empty, getString(R.string.city)));
//            return false;
//        }
        else if (password.length()<6){
            passwordEdt.requestFocus();
            passwordEdt.setError("Password must be of 6 characters long");
            return false;
        }
        else if (!confirmPass.equals(password)){
            confirmPasswordEdt.requestFocus();
            confirmPasswordEdt.setError("Password did not match");
            return false;
        }
        return true;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_btn:
                super.onBackPressed();
                break;
//            case R.id.category_edt:
//                if (ministryRb.isChecked())
//                    getCategories(true);
//                else
//                    getCategories(false);
//                break;
//            case R.id.state_edt:
//                showStatesDialog();
//                break;
//            case R.id.district_edt:
//                if (selectedStatePos!=-1) {
//                    if (selectedDistrictPos==-1) {
//                        progressDialog.show();
//                        getDistricts();
//                    }
//                    else
//                        showDistrictsDialog();
//                }
//                else
//                    Toast.makeText(this, getString(R.string.select_valid_state), Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.block_edt:
//                if (selectedStatePos!=-1 && selectedDistrictPos!=-1) {
//                    if (!noBlocksForDistrict) {
//                        if (selectedBlockPos == -1) {
//                            progressDialog.show();
//                            getBlocks();
//                        } else
//                            showBlocksDialog();
//                    }
//                }
//                else
//                    Toast.makeText(this, getString(R.string.select_valid_state_district), Toast.LENGTH_SHORT).show();
//                break;
            case R.id.submit_btn:
                if (validate()){
                    //Toast.makeText(this, "Validated!", Toast.LENGTH_SHORT).show();
                    HashMap<String,Object> postParams = new HashMap<>();
                    postParams.put(AppConfig.NAME,name);
                    postParams.put(AppConfig.PASSWORD,password);
                    postParams.put(AppConfig.MOBILE,mobile);
                    postParams.put(AppConfig.EMAIL,email);
//                    postParams.put(AppConfig.STATE,statesArrayList.get(selectedStatePos).getListItemTitle());
//                    postParams.put(AppConfig.DISTRICT,districtsArrayList.get(selectedDistrictPos).getListItemTitle());
//                    postParams.put(AppConfig.BLOCK,blockEdt.getText().toString());
//                    postParams.put(AppConfig.CITY,city);
//                    if (forFitIndiaChampion || othersMinistryRadioGroup.getCheckedRadioButtonId() == R.id.individual_rb)
//                        postParams.put(AppConfig.ROLE,getString(R.string.subscriber));
//                    else
//                        postParams.put(AppConfig.ROLE,categoriesArrayList.get(selectedCategoryPos).getListItemSubtitle());

                    RegisterRequest registerRequest = new RegisterRequest(this,this,postParams);
                    registerRequest.hitUserRequest();
                }
                break;
            case R.id.login_btn:
                Utility.showExitDialog(this,LoginActivity.class);
                break;
        }
    }

    @Override
    public void onResponse(Object obj) {
        if (obj instanceof RegisterModel){
            RegisterModel registerModel = (RegisterModel) obj;
            String msg = registerModel.getMessage();

            if (registerModel.isIsSuccess()){
                Utility.showResponseDialog(msg,this,R.drawable.success,true);
                //finish();
            }
            else {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }


        }
    }

//    @Override
//    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//        if (radioGroup.getCheckedRadioButtonId() == R.id.individual_rb){
//            areCategoriesLoaded = false;
//            categoryEdt.setVisibility(View.GONE);
//            nameEdt.setHint(getString(R.string.name));
//            individualDetailsLayout.setVisibility(View.VISIBLE);
//            designationEdt.setVisibility(View.VISIBLE);
//        }
//        else {
//            if (radioGroup.getCheckedRadioButtonId() == R.id.ministry_rb) {
//                categoryEdt.setText(resources.getStringArray(R.array.ministry_categories_array)[0]);
//                nameEdt.setHint(getString(R.string.name));
//            } else {
//                categoryEdt.setText(resources.getStringArray(R.array.categories_array)[0]);
//                nameEdt.setHint(getString(R.string.name_of, resources.getStringArray(R.array.categories_array)[0]));
//            }
//            categoryEdt.setVisibility(View.VISIBLE);
//            areCategoriesLoaded = false;
//            individualDetailsLayout.setVisibility(View.GONE);
//            designationEdt.setVisibility(View.GONE);
//        }
//    }
//
//    public class saveStateDistrictDataTask extends AsyncTask<Integer,Integer,String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            showDialog();
//        }
//
//        @Override
//        protected String doInBackground(Integer... integers) {
//            List<StateDistrictBlockModel.Result> stateDistrictBlockModelList = stateDistrictBlockModel.getData();
//            databaseManager.insertMultipleValues(DatabaseManager.TBL_STATE_DISTRICT_BLOCK_MASTER,stateDistrictBlockModelList,this);
//            return null;
//        }
//
//        public void doProgress(int count,int total){
//            publishProgress(Integer.valueOf(""+((count*100)/total)));
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... progress) {
//            savingProgressDialog.setProgress(progress[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String params) {
//            super.onPostExecute(params);
//            savingProgressDialog.dismiss();
//            getStates();
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean(AppConfig.IS_STATE_SAVED,true);
//            editor.apply();
//            Toast.makeText(RegisterActivity.this, R.string.data_saved_successfully, Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void showDialog() {
//        savingProgressDialog = new ProgressDialog(this);
//        savingProgressDialog.setMax(100);
//        savingProgressDialog.setMessage("Please wait while we are synchronising states, districts and blocks..");
//        savingProgressDialog.setProgressNumberFormat(null);
//        savingProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        savingProgressDialog.setCancelable(false);
//        savingProgressDialog.show();
//    }
}
