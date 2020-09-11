package goforfit.com.goforfit.activities;

import androidx.appcompat.app.AppCompatActivity;
import goforfit.com.goforfit.R;
import goforfit.com.goforfit.helper.AppConfig;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        convertToFullScreen();
        setContentView(R.layout.activity_splash_screen);


        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (sharedPreferences.getBoolean(AppConfig.IS_LOGGED_IN,false)){
                    startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        new Thread(runnable).start();
    }

    private void convertToFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
