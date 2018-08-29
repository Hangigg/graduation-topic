package com.hangi.schedy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = findViewById(R.id.button5);//找到你要设透明背景的layout 的id
        v.getBackground().setAlpha(150);
    }
    public void gotoActivityDay(android.view.View v){Intent it = new Intent(this,ActivityDay.class);startActivity(it);}

}
