package com.hangi.schedy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Script extends AppCompatActivity {
    EditText etarray[][] = new EditText[5][8];
    private SharedPreferences settings;
    private String fileName = "my_script.txt";
    private String data = "apk file";
    Button scriptviewbtn;
    String Str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script);
        settings = getSharedPreferences(fileName,MODE_PRIVATE);

        LinearLayout LL[] = new LinearLayout[5];
        LL[0] = (LinearLayout) findViewById(R.id.LL1);
        LL[1] = (LinearLayout) findViewById(R.id.LL2);
        LL[2] = (LinearLayout) findViewById(R.id.LL3);
        LL[3] = (LinearLayout) findViewById(R.id.LL4);
        LL[4] = (LinearLayout) findViewById(R.id.LL5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                etarray[i][j] = new EditText(this);
                etarray[i][j].setText("");
                etarray[i][j].setGravity(Gravity.CENTER);
                etarray[i][j].setHeight(150);
                etarray[i][j].setWidth(200);
                LL[i].addView(etarray[i][j]);
            }
        }
        //讀成績
        try{for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                if(settings.getString("Script"+i+""+j, "No").equals("No"))
                    break;
                etarray[i][j].setText(settings.getString("Script" + i+""+ j, "No"));
            }

        }
        }catch(Exception e)
        {

            Log.e("TAG",e.getMessage());

        }
        scriptviewbtn = (Button) findViewById(R.id.scriptbtn);
        scriptviewbtn.setOnClickListener(ClickIntHere);

    }
    private View.OnClickListener ClickIntHere = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == scriptviewbtn.getId()) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 8; j++) {
                        settings.edit().putString("Script" + i +""+ j, etarray[i][j].getText().toString()).commit();

                    }

                }
            }
            Intent intent = new Intent();
            intent.setClass(Script.this,ScriptShow.class);
            startActivity(intent);
            finish();
        }

    };
    public void gotoActivityWeek(android.view.View v){Intent it = new Intent(this,ActivityWeek.class);startActivity(it);}
    public void gotoActivityMon(android.view.View v){Intent it = new Intent(this,ActivityMon.class);startActivity(it);}
    public void gotoScore(android.view.View v){Intent it = new Intent(this,ScoreShow.class);startActivity(it);}
    public void gotoScript(android.view.View v){Intent it = new Intent(this,ScriptShow.class);startActivity(it);}
    public void gotoActivityDay(android.view.View v){Intent it = new Intent(this,ActivityDay.class);startActivity(it);}

    public void gotosetting(android.view.View v){Intent it = new Intent(this,Setting.class);startActivity(it);}
}