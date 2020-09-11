package goforfit.com.goforfit.webservice;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.HashMap;

import goforfit.com.goforfit.helper.AppConfig;
import goforfit.com.goforfit.models.LoginModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRequest implements Callback<LoginModel> {

    Context context;
    ResponseListener responseListener;
    ProgressDialog progressDialog;
    String username,password;

    public LoginRequest(Context context, ResponseListener responseListener,String username, String password) {
        this.context = context;
        this.responseListener = responseListener;
        this.username = username;
        this.password = password;
    }

    public void hitUserRequest() {
        ApiRequest apiService =
                ApiClient.getClient(context).create(ApiRequest.class);
        Call<LoginModel> call = apiService.loginUser(getRequestBody());
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Logging In! Please wait...");
        progressDialog.show();
        call.enqueue(this);
    }

    private HashMap<String, Object> getRequestBody() {
        HashMap hashMap = new HashMap<String, Object>();
        hashMap.put(AppConfig.USERNAME, username);
        hashMap.put(AppConfig.PASSWORD, password);

        return hashMap;
    }

    @Override
    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
        try {
            progressDialog.dismiss();
            responseListener.onResponse(response.body());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<LoginModel> call, Throwable t) {
        progressDialog.dismiss();
        responseListener.onResponse(null);
    }
}

