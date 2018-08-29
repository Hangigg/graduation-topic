package com.hangi.schedy;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;

public class Setting extends AppCompatActivity {
    Calendar c= Calendar.getInstance();
    private TextView txTime;
    private TextView txDate;
    public long remintime;
    private SharedPreferences settings;
    private String fileName = "MyDate.txt";
    private String data = "apk file";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat sdf2 = new SimpleDateFormat("mm:ss");
    Date remintimedt;
    Date textDatedt;
    String remintimest;
    String textDatest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        settings = getSharedPreferences(fileName,MODE_PRIVATE);

//提醒功能
        NotificationManager mNotificationManager
                = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Intent notifyIntent = new Intent(Setting.this, ActivityDay.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent appIntent
                = PendingIntent.getActivity(Setting.this, 0, notifyIntent, 0);
        Notification notification
                = new Notification.Builder(Setting.this)
                .setContentIntent(appIntent)
                .setSmallIcon(R.drawable.logo) // 設置狀態列裡面的圖示（小圖示）　　
//                .setLargeIcon(BitmapFactory.decodeResource(Setting.this.getResources(), R.drawable.logo) // 下拉下拉清單裡面的圖示（大圖示）
                .setTicker("notification on status bar.") // 設置狀態列的顯示的資訊
                .setWhen(remintime)// 設置時間發生時間
                .setAutoCancel(false) // 設置通知被使用者點擊後是否清除  //notification.flags = Notification.FLAG_AUTO_CANCEL;
                .setContentTitle("Schedy 讀書計畫") // 設置下拉清單裡的標題
                .setContentText("親愛的，該念書喽!")// 設置上下文內容
                .setOngoing(true)      //true使notification变为ongoing，用户不能手动清除  // notification.flags = Notification.FLAG_ONGOING_EVENT; notification.flags = Notification.FLAG_NO_CLEAR;

                .setDefaults(Notification.DEFAULT_ALL) //使用所有默認值，比如聲音，震動，閃屏等等
                // .setDefaults(Notification.DEFAULT_VIBRATE) //使用默認手機震動提示
                // .setDefaults(Notification.DEFAULT_SOUND) //使用默認聲音提示
                // .setDefaults(Notification.DEFAULT_LIGHTS) //使用默認閃光提示
                // .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND) //使用默認閃光提示 與 默認聲音提示

                // .setVibrate(vibrate) //自訂震動長度
                // .setSound(uri) //自訂鈴聲
                // .setLights(0xff00ff00, 300, 1000) //自訂燈光閃爍 (ledARGB, ledOnMS, ledOffMS)
                                .build();

                //把指定ID的通知持久的發送到狀態條上
                        mNotificationManager.notify(0, notification);
        //設定時間
        txDate = (TextView)findViewById(R.id.textDate);
        if(settings.getString("textDate", "NO").equals("NO")){}
        else
        {txDate.setText(settings.getString("textDate","NO"));}

        txTime = (TextView)findViewById(R.id.remine);
        if(settings.getString("reminTime", "NO").equals("NO")){}
        else
        {txTime.setText(settings.getString("reminTime","NO"));}
        txDate.setOnClickListener(ClickIntHere);
        txTime.setOnClickListener(ClickIntHere);
    }
    private View.OnClickListener ClickIntHere= new View.OnClickListener(){
        @Override
        //按下按鈕進入function
        public void onClick(View v){

            if(v==txDate){
                new DatePickerDialog(Setting.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String date=(""+year+"/"+(month+1)+"/"+day);
                        txDate.setText(date);
                        settings.edit().putString("textDate",date).commit();
                    }

                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

            }
            else if(v== txTime){
                new TimePickerDialog(Setting.this,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker v,int h,int m){
                        txTime.setText(h+":"+m);
                        remintimest=sdf2.format(h+":"+m);
                        settings.edit().putString("reminTime",remintimest).commit();

                    }

                },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true).show();
            }
        }

    };
    public void gotoActivityWeek(android.view.View v){Intent it = new Intent(this,ActivityWeek.class);startActivity(it);}
    public void gotoActivityMon(android.view.View v){Intent it = new Intent(this,ActivityMon.class);startActivity(it);}
    public void gotoScore(android.view.View v){Intent it = new Intent(this,ScoreShow.class);startActivity(it);}
    public void gotoScript(android.view.View v){Intent it = new Intent(this,ScriptShow.class);startActivity(it);}
    public void gotoScriptEDIT(android.view.View v){Intent it = new Intent(this,Script.class);startActivity(it);}
    public void gotoActivityDay(android.view.View v){Intent it = new Intent(this,ActivityDay.class);startActivity(it);}
    public void gotosetting(android.view.View v){Intent it = new Intent(this,Setting.class);startActivity(it);}
}
