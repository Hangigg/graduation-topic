package com.hangi.schedy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Clock extends AppCompatActivity {
LinearLayout LL;
    TextView TV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        LL=(LinearLayout)findViewById(R.id.Root);
        final DrawView view=new DrawView(this);
        view.iniPaint();
        view.setLayoutParams(new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        view.invalidate();
        LL.addView(view);
        final int Max=100000;
        new CountDownTimer(Max,50){
            @Override
            public void onFinish(){
                view.setRadian(0);
                view.invalidate();
            }
            @Override
            public void onTick(long tick){
                float temp=(float)(((tick*1000)/Max)*0.36f);
                view.setRadian(temp);
                view.setTextshow(tick/60000+":"+(tick%60000)/1000);
                view.invalidate();
            }
        }.start();
    }
    public class DrawView extends View{
        public void setRadian(float radian) {
            this.radian = radian;
        }

        public void setTextshow(String textshow) {
            this.textshow = textshow;
        }

        private String textshow;
        private float radian=0;
        private Paint p1,p2,p3;
        public DrawView(Context context){super(context);}
        public void iniPaint(){
            p1=new Paint();
            p1.setColor(Color.RED);
            p1.setStrokeWidth(40);
            p1.setStyle(Paint.Style.STROKE);
            p1.setAntiAlias(true);
            p2=new Paint();
            p2.setColor(Color.GRAY);
            p2.setStrokeWidth(40);
            p2.setStyle(Paint.Style.STROKE);
            p2.setAntiAlias(true);
            p3=new Paint();
            p3.setColor(Color.GRAY);
            p3.setTextSize(150);
        }
        @Override
        protected void onDraw(Canvas canvas){super.onDraw(canvas);
        int myradius=400;
        canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,myradius,p1);
            RectF F=new RectF(canvas.getWidth()/2-myradius,canvas.getHeight()/2-myradius,canvas.getWidth()/2+myradius,canvas.getHeight()/2+myradius);
            canvas.drawArc(F,-90,radian,false,p2);
            canvas.drawText(textshow,(canvas.getWidth()/2)-150,(canvas.getHeight()/2)+20,p3);
        }
    }
}
