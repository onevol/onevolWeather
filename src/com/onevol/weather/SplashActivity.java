package com.onevol.weather;

import com.onevol.weather.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class SplashActivity extends Activity  {  
    private Handler handler = new Handler(); 
    private Runnable runnable; 
  
    @Override  
    protected void onCreate(Bundle savedInstanceState)  
    {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_splash);  
  
        handler.postDelayed(new Runnable()  
        {  
            @Override  
            public void run()  
            {  
                Intent intent = new Intent(SplashActivity.this, WeatherActivity.class);  
                startActivity(intent); 
                finish(); 
            }  
        }, 3000);  
    }
    @Override  
    public boolean onTouchEvent(MotionEvent event)  
    {  
          
        if(event.getAction()==MotionEvent.ACTION_UP)  
        {  
            Intent intent = new Intent(SplashActivity.this, WeatherActivity.class);  
            startActivity(intent);  
            finish();  
            if (runnable != null)  
                handler.removeCallbacks(runnable);  
        }  
          
        return super.onTouchEvent(event);  
    } 
  
}  
