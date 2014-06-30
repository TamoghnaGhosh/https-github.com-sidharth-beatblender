package com.beatblender.beatblender;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TapFragment extends Fragment {
    private float coordinateX, coordinateY;
    private RelativeLayout relativeLayout;
    private SoundPool soundPool;
    private int soundId[];
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tapfragment_layout,container,false);
        soundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,0);
        soundId = new int[5];
        soundId[0] = soundPool.load(getActivity(),R.raw.snare1,0);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int p_index;
                int action = event.getAction() & MotionEvent.ACTION_MASK;
                switch (action) {
                    case MotionEvent.ACTION_DOWN: {
                        coordinateX = event.getX();
                        coordinateY = event.getY();
                        Log.v("X", String.valueOf(coordinateX));

                        final View addView = new View(getActivity());
                        addView.setBackgroundResource(R.drawable.circle_red);

                        addView.setLayoutParams(new RelativeLayout.LayoutParams(200,200));
                        addView.setX(coordinateX - 100);
                        addView.setY(coordinateY - 100);

                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                relativeLayout.addView(addView);
                                soundPool.play(soundId[0],1,1,0,0,1);

                            }
                        },5);

                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                relativeLayout.removeView(addView);
                            }
                        },100);
                        break;
                    }

                    case MotionEvent.ACTION_POINTER_DOWN: {
                        p_index = event.getActionIndex();
                        coordinateX = event.getX(p_index);
                        coordinateY = event.getY(p_index);
                        Log.v("X",String.valueOf(coordinateX));

                        final View addView = new View(getActivity());
                        addView.setBackgroundResource(R.drawable.circle_red);

                        addView.setLayoutParams(new RelativeLayout.LayoutParams(200,200));
                        addView.setX(coordinateX - 100);
                        addView.setY(coordinateY - 100);

                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                relativeLayout.addView(addView);
                                soundPool.play(soundId[0],1,1,0,0,1);


                            }
                        },5);

                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                relativeLayout.removeView(addView);
                            }
                        },100);
                        break;
                    }
                }
                return true;
            }


        });

        return  view;

    }



}
