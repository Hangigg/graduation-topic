package com.hangi.schedy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class ActivityMon extends AppCompatActivity {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Date dt=new Date();
    String dts;
    TextView textDate;

    private SharedPreferences settings;
    private String fileName="MyDate.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon);
        View a = findViewById(R.id.button);
        a.getBackground().setAlpha(150);
        View b = findViewById(R.id.button2);
        b.getBackground().setAlpha(150);
        View c = findViewById(R.id.button3);
        c.getBackground().setAlpha(150);
        dts =sdf.format(dt);
        TextView dtv=(TextView)findViewById(R.id.textView2);
        dtv.setText(dts);
        Date dt1=dt;
        Date dt2=dt;
        try {
            dt1 = dt;
            String textDatestr = settings.getString("textDate", "2017/11/11");
            dt2 =sdf.parse(textDatestr);
        }
        catch(Exception e) {

        }
        //取得兩個時間的Unix時間
        Long ut1=dt1.getTime();
        Long ut2=dt2.getTime();
        Long timeP=ut2-ut1;
        Long day=timeP/(1000*60*60*24)+1;//日差
        textDate =(TextView)findViewById(R.id.textDate);
        textDate.setText(day.toString());





    }
    public void gotoActivityWeek(android.view.View v){Intent it = new Intent(this,ActivityWeek.class);startActivity(it);}
    public void gotoActivityMon(android.view.View v){Intent it = new Intent(this,ActivityMon.class);startActivity(it);}
    public void gotoScore(android.view.View v){Intent it = new Intent(this,ScoreShow.class);startActivity(it);}
    public void gotoScript(android.view.View v){Intent it = new Intent(this,ScriptShow.class);startActivity(it);}
    public void gotoActivityDay(android.view.View v){Intent it = new Intent(this,ActivityDay.class);startActivity(it);}
    public void gotosetting(android.view.View v){Intent it = new Intent(this,Setting.class);startActivity(it);}

}