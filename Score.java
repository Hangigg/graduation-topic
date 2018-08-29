package com.hangi.schedy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import static com.hangi.schedy.R.id.center;

public class Score extends AppCompatActivity {
    EditText etarray[][] = new EditText[8][3];
    private SharedPreferences settings;
    private String fileName = "my_score.txt";
    private String data = "apk file";
    Button scoreviewbtn;
    String Str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
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
                etarray[i][j] = new EditText(this);
                etarray[i][j].setText("0");
                etarray[i][j].setGravity(Gravity.CENTER);
                etarray[i][j].setHeight(20);
                etarray[i][j].setInputType(InputType.TYPE_CLASS_NUMBER);
                etarray[i][j].setWidth(330);
                etarray[i][j].setGravity(center);

                LL[i].addView(etarray[i][j]);
            }
        }
        //讀成績
        try{for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                if(settings.getString("Score"+i+""+j, "No").equals("No"))
                    break;
                etarray[i][j].setText(settings.getString("Score" + i+""+ j, "No"));
            }

        }
    }catch(Exception e)
    {

        Log.e("TAG",e.getMessage());

    }
        scoreviewbtn = (Button) findViewById(R.id.scoreviewbtn);
        scoreviewbtn.setOnClickListener(ClickIntHere);

    }
    private View.OnClickListener ClickIntHere = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == scoreviewbtn.getId()) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 3; j++) {
                        settings.edit().putString("Score" + i +""+ j, etarray[i][j].getText().toString()).commit();

                    }

                }
            }
            Intent intent = new Intent();
            intent.setClass(Score.this  , ScoreShow.class);

            startActivity(intent);
            finish();
        }

    };
    public void gotoActivityWeek(android.view.View v){Intent it = new Intent(this,ActivityWeek.class);startActivity(it);}
    public void gotoActivityMon(android.view.View v){Intent it = new Intent(this,ActivityMon.class);startActivity(it);}
    public void gotoScore(android.view.View v){Intent it = new Intent(this,ScoreShow.class);startActivity(it);}
    public void gotoScoreChar(android.view.View v){Intent it = new Intent(this,ActivityScoreShow.class);startActivity(it);}

    public void gotoScript(android.view.View v){Intent it = new Intent(this,ScriptShow.class);startActivity(it);}
    public void gotoActivityDay(android.view.View v){Intent it = new Intent(this,ActivityDay.class);startActivity(it);}
    public void gotosetting(android.view.View v){Intent it = new Intent(this,Setting.class);startActivity(it);}
}


