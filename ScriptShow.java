package com.hangi.schedy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScriptShow extends AppCompatActivity {
    TextView tvarray[][]=new TextView[5][8];
    private SharedPreferences settings;
    private String fileName = "my_script.txt";
    private String data = "apk file";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script_show);
        settings = getSharedPreferences(fileName,MODE_PRIVATE);
        LinearLayout LL[] = new LinearLayout[5];
        LL[0] = (LinearLayout) findViewById(R.id.LL1);
        LL[1] = (LinearLayout) findViewById(R.id.LL2);
        LL[2] = (LinearLayout) findViewById(R.id.LL3);
        LL[3] = (LinearLayout) findViewById(R.id.LL4);
        LL[4] = (LinearLayout) findViewById(R.id.LL5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                tvarray[i][j] = new TextView(this);
                tvarray[i][j].setText("");
                tvarray[i][j].setGravity(Gravity.CENTER);
                tvarray[i][j].setBackgroundResource(R.drawable.text_view_border);
                tvarray[i][j].setHeight(150);
                tvarray[i][j].setWidth(200);
                if(settings.getString("Script"+i+""+j, "No").equals("No"))
                    break;
                tvarray[i][j].setText(settings.getString("Script" + i+""+ j, "No"));
                LL[i].addView(tvarray[i][j]);

            }
        }

    }
    public void gotoActivityWeek(android.view.View v){Intent it = new Intent(this,ActivityWeek.class);startActivity(it);}
    public void gotoActivityMon(android.view.View v){Intent it = new Intent(this,ActivityMon.class);startActivity(it);}
    public void gotoScore(android.view.View v){Intent it = new Intent(this,ScoreShow.class);startActivity(it);}
    public void gotoScript(android.view.View v){Intent it = new Intent(this,ScriptShow.class);startActivity(it);}
    public void gotoScriptEDIT(android.view.View v){Intent it = new Intent(this,Script.class);startActivity(it);}

    public void gotoActivityDay(android.view.View v){Intent it = new Intent(this,ActivityDay.class);startActivity(it);}
    public void gotosetting(android.view.View v){Intent it = new Intent(this,Setting.class);startActivity(it);}
}
