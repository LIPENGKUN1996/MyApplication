package com.example.lipengku.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Lipengku on 2019/1/22.
 */

public class Welcome extends AppCompatActivity {
    TextView textView;
    int time = 3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_mian);
        textView = findViewById(R.id.welcome_tv);

        handler.postDelayed(runnable,0);
    }
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            handler.postDelayed(this,1000);
            textView.setText(time+1+"s"+" 跳过");

            if (time == 0){
                Intent intent = new Intent(Welcome.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Welcome.this,MainActivity.class);
                        startActivity(intent);
                        //结束线程
                        handler.removeCallbacks(runnable);
                        finish();

                    }
                });
            }
        }
    };

}
