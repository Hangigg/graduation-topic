package com.hangi.schedy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreShow extends AppCompatActivity {
    TextView tvarray[][]=new TextView[8][3];
    private SharedPreferences settings;
    private String fileName = "my_score.txt";
    private String data = "apk file";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_show2);
        settings = getSharedPreferences(fileName,MODE_PRIVATE);
        View a = findViewById(R.id.button9);
        a.getBackground().setAlpha(150);
        View b = findViewById(R.id.button8);
        b.getBackground().setAlpha(150);


        LinearLayout LL[] = new LinearLayout[8];
        LL[0] = (LinearLayout) findViewById(R.id.LL2);
        LL[1] = (LinearLayout) findViewById(R.id.LL3);
        LL[2] = (LinearLayout) findViewById(R.id.LL4);
        LL[3] = (LinearLayout) findViewById(R.id.LL5);
        LL[4] = (LinearLayout) findViewById(R.id.LL6);
        LL[5] = (LinearLayout) findViewById(R.id.LL7);
        LL[6] = (LinearLayout) findViewById(R.id.LL8);
        LL[7] = (LinearLayout) findViewById(R.id.LL9);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                tvarray[i][j] = new TextView(this);
                tvarray[i][j].setText("0");
                tvarray[i][j].setGravity(Gravity.CENTER);
                tvarray[i][j].setBackgroundResource(R.drawable.text_view_border);
                tvarray[i][j].setHeight(140);
                tvarray[i][j].setWidth(330);
                        if(settings.getString("Score"+i+""+j, "No").equals("No"))
                            break;
                        tvarray[i][j].setText(settings.getString("Score" + i+""+ j, "No"));

                LL[i].addView(tvarray[i][j]);

                }
            }
        }
    public void gotoActivityWeek(android.view.View v){Intent it = new Intent(this,ActivityWeek.class);startActivity(it);}
    public void gotoActivityMon(android.view.View v){Intent it = new Intent(this,ActivityMon.class);startActivity(it);}
    public void gotoScore(android.view.View v){Intent it = new Intent(this,ScoreShow.class);startActivity(it);}
    public void gotoScoreEDIT(android.view.View v){Intent it = new Intent(this,Score.class);startActivity(it);}
    public void gotoScoreChar(android.view.View v){Intent it = new Intent(this,ActivityScoreShow.class);startActivity(it);}

    public void gotoScript(android.view.View v){Intent it = new Intent(this,ScriptShow.class);startActivity(it);}
    public void gotoActivityDay(android.view.View v){Intent it = new Intent(this,ActivityDay.class);startActivity(it);}
    public void gotosetting(android.view.View v){Intent it = new Intent(this,Setting.class);startActivity(it);}


}