package com.beatblender.beatblender;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.*;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends Activity {
    private TextView textView;
    private int touchCount;
    private float coordinateX, coordinateY;
    private RelativeLayout relativeLayout;
    Timer timer  = new Timer("Highlight after 5");

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        touchCount = 0;
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        textView = (TextView) findViewById(R.id.TimeDisplay);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ((TextView) view).append("\nTime : "+Long.toString(System.currentTimeMillis())+"Touch Count : "+Integer.toString(touchCount++));
                coordinateX = motionEvent.getX();
                coordinateY = motionEvent.getY();

                final View addView = new View(MainActivity.this);
                addView.setBackgroundResource(R.drawable.circle_red);
                addView.setX(coordinateX);
                addView.setY(coordinateY);
                addView.setLayoutParams(new RelativeLayout.LayoutParams(20,20 ));


                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        relativeLayout.addView(addView);

                    }
                },2000);

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout.removeView(addView);
                    }
                },2500);
                return false;
            }
        });
        TextView sr1;
        TextView tv3;
        TextView tv4;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
