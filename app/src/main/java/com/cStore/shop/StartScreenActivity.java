package  com.cStore.shop;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class StartScreenActivity extends AppCompatActivity {
    private static int SPLAESH_TIME_OUT=2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(StartScreenActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLAESH_TIME_OUT);
    }



}
