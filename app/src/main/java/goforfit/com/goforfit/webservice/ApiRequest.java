package goforfit.com.goforfit.webservice;

/**
 * Created by CT13 on 2017-05-08.
 */


import java.util.ArrayList;
import java.util.HashMap;

import goforfit.com.goforfit.helper.AppConfig;
import goforfit.com.goforfit.models.Category;
import goforfit.com.goforfit.models.LoginModel;
import goforfit.com.goforfit.models.RegisterModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiRequest {

    @POST(AppConfig.LOGIN_URL)
    Call<LoginModel> loginUser(@Body HashMap<String, Object> hashMap);

    @POST(AppConfig.REGISTER_URL)
    Call<RegisterModel> registerUser(@Body HashMap<String, Object> hashMap);

    @POST(AppConfig.GET_EVENTS_CATEGORY_URL)
    Call<ArrayList<Category>> getEventsCategory();
}