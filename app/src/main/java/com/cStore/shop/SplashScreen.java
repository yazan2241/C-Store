package com.cStore.shop;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static com.cStore.shop.R.anim.move;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


   /* Thread background = new Thread() {
        public void run() {

            try {
                // Thread will sleep for 5 seconds
                sleep(3*1000);

                Intent intent = new Intent(SplashScreen.this,Activity_list.class);
                startActivity(intent);


                // After 5 seconds redirect to another intent


                //Remove activity
                finish();

            } catch (Exception e) {

            }

        }*/
        startAnimation();
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timerThread.start();
        startAnimation();
    };

    // start thread
    //    background.start();
    private void startAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, move);
        anim.reset();
        ImageView l=(ImageView) findViewById(R.id.spl);
        l.clearAnimation();
        l.startAnimation(anim);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}
