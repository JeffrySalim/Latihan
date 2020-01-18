package com.example.android.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);

        final Thread thread = new Thread((new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Login();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }));
        thread.start();
    }
    public void Login(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
