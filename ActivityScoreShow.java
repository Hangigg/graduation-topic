package com.hangi.schedy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
public class ActivityScoreShow extends AppCompatActivity {
    int c1,c2,c3,e1,e2,e3,m1,m2,m3,g1,g2,g3,h1,h2,h3,p1,p2,p3,phy1,phy2,phy3,che1,che2,che3;
    private SharedPreferences settings;
    private String fileName = "my_score.txt";
    private String data = "apk file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_show);
        LinearLayout ll=(LinearLayout)findViewById(R.id.chartLL);
        View a = findViewById(R.id.button9);
        a.getBackground().setAlpha(150);
        View b = findViewById(R.id.button8);
        b.getBackground().setAlpha(150);

        //抓分數
        settings = getSharedPreferences(fileName,MODE_PRIVATE);
        c1=Integer.parseInt(settings.getString("Score00","0"));
        c2=Integer.parseInt(settings.getString("Score01","0"));
        c3=Integer.parseInt(settings.getString("Score02","0"));
        e1=Integer.parseInt(settings.getString("Score10","0"));
        e2=Integer.parseInt(settings.getString("Score11","0"));
        e3=Integer.parseInt(settings.getString("Score12","0"));
           m1 = Integer.parseInt(settings.getString("Score20", "0"));
           m2 = Integer.parseInt(settings.getString("Score21", "0"));
           m3 = Integer.parseInt(settings.getString("Score22", "0"));
           h1 = Integer.parseInt(settings.getString("Score30", "0"));
           h2 = Integer.parseInt(settings.getString("Score31", "0"));
           h3 = Integer.parseInt(settings.getString("Score32", "0"));
           g1 = Integer.parseInt(settings.getString("Score40", "0"));
           g2 = Integer.parseInt(settings.getString("Score41", "0"));
           g3 = Integer.parseInt(settings.getString("Score42", "0"));
           p1 = Integer.parseInt(settings.getString("Score50", "0"));
           p2 = Integer.parseInt(settings.getString("Score51", "0"));
           p3 = Integer.parseInt(settings.getString("Score52", "0"));
           phy1 = Integer.parseInt(settings.getString("Score60", "0"));
           phy2 = Integer.parseInt(settings.getString("Score61", "0"));
           phy3 = Integer.parseInt(settings.getString("Score62", "0"));
           che1 = Integer.parseInt(settings.getString("Score70", "0"));
           che2 = Integer.parseInt(settings.getString("Score71", "0"));
           che3 = Integer.parseInt(settings.getString("Score72", "0"));




        List<Entry> chartDataA = new ArrayList<>();
        chartDataA.add(new Entry(c1, 0));
        chartDataA.add(new Entry(c2, 1));
        chartDataA.add(new Entry(c3, 2));
        List<String> chartLabels = new ArrayList<>();
        chartLabels.add("第一次");
        chartLabels.add("第二次");
        chartLabels.add("第三次");
        LineDataSet dataSetA = new LineDataSet(chartDataA, "國文");
        dataSetA.setColor(Color.GRAY);
        List<Entry> chartDataB = new ArrayList<>();
        chartDataB.add(new Entry(e1, 0));
        chartDataB.add(new Entry(e2, 1));
        chartDataB.add(new Entry(e3, 2));
        LineDataSet dataSetB = new LineDataSet(chartDataB, "英文");
        dataSetB.setColor(Color.GRAY);

        List<Entry> chartDataD = new ArrayList<>();
        chartDataD.add(new Entry(m1, 0));
        chartDataD.add(new Entry(m2, 1));
        chartDataD.add(new Entry(m3, 2));
        LineDataSet dataSetD = new LineDataSet(chartDataD, "數學");
        dataSetD.setColor(Color.RED);

        List<Entry> chartDataE = new ArrayList<>();
        chartDataE.add(new Entry(g1, 0));
        chartDataE.add(new Entry(g2, 1));
        chartDataE.add(new Entry(g3, 2));
        LineDataSet dataSetE = new LineDataSet(chartDataE, "地理");
        dataSetE.setColor(Color.GRAY);

        List<Entry> chartDataF = new ArrayList<>();
        chartDataF.add(new Entry(h1, 0));
        chartDataF.add(new Entry(h2, 1));
        chartDataF.add(new Entry(h3, 2));
        LineDataSet dataSetF = new LineDataSet(chartDataF, "歷史");
        dataSetF.setColor(Color.GRAY);

        List<Entry> chartDataG = new ArrayList<>();
        chartDataG.add(new Entry(p1, 0));
        chartDataG.add(new Entry(p2, 1));
        chartDataG.add(new Entry(p3, 2));
        LineDataSet dataSetG = new LineDataSet(chartDataG, "公民");
        dataSetG.setColor(Color.GRAY);

        List<Entry> chartDataH = new ArrayList<>();
        chartDataH.add(new Entry(phy1, 0));
        chartDataH.add(new Entry(phy2, 1));
        chartDataH.add(new Entry(phy3, 2));
        LineDataSet dataSetH = new LineDataSet(chartDataH, "物理");
        dataSetH.setColor(Color.GRAY);

        List<Entry> chartDataI = new ArrayList<>();
        chartDataI.add(new Entry(che1, 0));
        chartDataI.add(new Entry(che2, 1));
        chartDataI.add(new Entry(che3, 2));
        LineDataSet dataSetI = new LineDataSet(chartDataI, "化學");
        dataSetI.setColor(Color.GRAY);


        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSetA);
        dataSets.add(dataSetB);
        dataSets.add(dataSetD);
        dataSets.add(dataSetE);
        dataSets.add(dataSetF);
        dataSets.add(dataSetG);
        dataSets.add(dataSetH);
        dataSets.add(dataSetI);


        LineChart chart_line = new LineChart(this);
        LineData data =new LineData(chartLabels,  dataSets);
        chart_line.setData(data);
        chart_line.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT));
        ll.addView(chart_line);

    }

    public void gotoActivityWeek(android.view.View v){Intent it = new Intent(this,ActivityWeek.class);startActivity(it);}
    public void gotoActivityMon(android.view.View v){Intent it = new Intent(this,ActivityMon.class);startActivity(it);}
    public void gotoScore(android.view.View v){Intent it = new Intent(this,ScoreShow.class);startActivity(it);}
    public void gotoScript(android.view.View v){Intent it = new Intent(this,ScriptShow.class);startActivity(it);}
    public void gotoActivityDay(android.view.View v){Intent it = new Intent(this,ActivityDay.class);startActivity(it);}
    public void gotoBack(android.view.View v){Intent it = new Intent(this,ScoreShow.class);startActivity(it);}
    public void gotosetting(android.view.View v){Intent it = new Intent(this,Setting.class);startActivity(it);}
}
