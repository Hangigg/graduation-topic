package com.hangi.schedy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class ActivityWeek extends AppCompatActivity {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    Date dt=new Date();
    String dts;
    int dayOfWeek;
    final Calendar calendar = Calendar.getInstance();
    TextView textDate;
    private SharedPreferences settings;
    private String fileName="MyDate.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
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
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        TextView wk1=(TextView)findViewById(R.id.wkm1);TextView wk2=(TextView)findViewById(R.id.wkm2);TextView wk3=(TextView)findViewById(R.id.wkm3);TextView wk4=(TextView)findViewById(R.id.wkm4);TextView wk5=(TextView)findViewById(R.id.wkm5);TextView wk6=(TextView)findViewById(R.id.wkm6);TextView wk7=(TextView)findViewById(R.id.wkm7);
        switch(dayOfWeek) {
            case Calendar.SUNDAY:
                wk1.setText("星期日");wk2.setText("星期一");wk3.setText("星期二");wk4.setText("星期三");wk5.setText("星期四");wk6.setText("星期五");wk7.setText("星期六");
                break;
            case Calendar.MONDAY:
                wk1.setText("星期一");wk2.setText("星期二");wk3.setText("星期三");wk4.setText("星期四");wk5.setText("星期五");wk6.setText("星期六");wk7.setText("星期日");
                break;
            case Calendar.TUESDAY:
                wk1.setText("星期二");wk2.setText("星期三");wk3.setText("星期四");wk4.setText("星期五");wk5.setText("星期六");wk6.setText("星期日");wk7.setText("星期一");
                break;
            case Calendar.WEDNESDAY:
                wk1.setText("星期三");wk2.setText("星期四");wk3.setText("星期五");wk4.setText("星期六");wk5.setText("星期日");wk6.setText("星期一");wk7.setText("星期二");                break;
            case Calendar.THURSDAY:
                wk1.setText("星期四");wk2.setText("星期五");wk3.setText("星期六");wk4.setText("星期日");wk5.setText("星期一");wk6.setText("星期二");wk7.setText("星期三");                break;
            case Calendar.FRIDAY:
                wk1.setText("星期五");wk2.setText("星期六");wk3.setText("星期日");wk4.setText("星期一");wk5.setText("星期二");wk6.setText("星期三");wk7.setText("星期四");
                break;
            case Calendar.SATURDAY:
                wk1.setText("星期六");wk2.setText("星期日");wk3.setText("星期一");wk4.setText("星期二");wk5.setText("星期三");wk6.setText("星期四");wk7.setText("星期五");
                break;
        }
    }
    public void gotoActivityWeek(android.view.View v){Intent it = new Intent(this,ActivityWeek.class);startActivity(it);}
    public void gotoActivityMon(android.view.View v){Intent it = new Intent(this,ActivityMon.class);startActivity(it);}
    public void gotoScore(android.view.View v){Intent it = new Intent(this,ScoreShow.class);startActivity(it);}
    public void gotoScript(android.view.View v){Intent it = new Intent(this,ScriptShow.class);startActivity(it);}
    public void gotoActivityDay(android.view.View v){Intent it = new Intent(this,ActivityDay.class);startActivity(it);}
    public void gotosetting(android.view.View v){Intent it = new Intent(this,Setting.class);startActivity(it);}

}