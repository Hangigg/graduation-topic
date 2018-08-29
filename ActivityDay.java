package com.hangi.schedy;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hangi.schedy.R.id.add;
import static com.hangi.schedy.R.id.ltv3;

//import android.support.v7.widget.RecyclerView;

public class ActivityDay extends AppCompatActivity {

    private SharedPreferences settings;
    private String fileName="MyDate.txt";
    Date dt=new Date();
    String dts;
    TextView dateTextView;
    TextView textDate;
    Calendar c= Calendar.getInstance();
    final Context context=this;
    private ListView dListView;
    private int sizeOfDateList=0;
    List<CourseFile> cFL=new ArrayList<CourseFile>();
    List<Integer> sizeOfSubjectList=new ArrayList<Integer>();
    List<MyDate> dateList=new ArrayList<MyDate>();
    private int Todaylist=0;
    int countListSize=0;

    private String Today="text";
    private Myadapter adapter;
    private ImageButton addListbutton;
    private ArrayList<CourseFile> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
//        按鈕樣式
        View a = findViewById(R.id.button);
        a.getBackground().setAlpha(150);
        View b = findViewById(R.id.button2);
        b.getBackground().setAlpha(150);
        View c = findViewById(R.id.button3);
        c.getBackground().setAlpha(150);
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd");
        DateFormat df=DateFormat.getDateInstance();


        //建立settings
        settings=getSharedPreferences(fileName,MODE_PRIVATE);
        dts = sdf.format(dt);
        dateTextView = (TextView) findViewById(R.id.dateView);
        dateTextView.setText(dts);
        //計算時差
        Date dt1=dt;Date dt2=dt;
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

        dateTextView.setOnClickListener(ClickIntHere);
        dListView = (ListView) findViewById(R.id.listInputText);
        countListSize=0;
        Today=dts;
        for(int i=0;i<10;i++) {
            if(settings.getString("Subject" + Today + "" + i, "NO").equals("NO"))
                break;
            cFL.add(new CourseFile(settings.getString("Subject" + Today + "" + i, "NO"),settings.getString("Lesson" + Today + "" + i, "NO"),settings.getString("Time" + Today + "" + i, "NO")));
              countListSize++;
        }
        Log.i("TAG",""+countListSize);
        //加號鍵
        addListbutton = (ImageButton) findViewById(add);
        addListbutton.setOnClickListener(ClickIntHere);
        adapter = new Myadapter(ActivityDay.this, cFL);
        dListView.setAdapter(adapter);

        //選單
        dListView = (ListView) this.findViewById(R.id.listInputText);


        dListView = (ListView) findViewById(R.id.listInputText);
        items = new ArrayList<CourseFile>();
        dListView.setAdapter(adapter);
        dListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long id) {
                final int pos = position;
                new AlertDialog.Builder(ActivityDay.this)
                        .setTitle("刪除列")
                        .setMessage("你確定要刪除？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cFL.remove(pos);
                                dListView.setAdapter(adapter);
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();

                return false;
            }

        });



    }
    //轉換器
    private class Myadapter extends BaseAdapter{
        private LayoutInflater myInflater= LayoutInflater.from(context);
        private List<CourseFile> courseFileList;
        private Myadapter(Context context,List<CourseFile> cFL){
            myInflater=LayoutInflater.from(context);
            this.courseFileList=cFL;
        }
        @Override
        public int getCount(){
            return courseFileList.size();
        }
        @Override
        public Object getItem(int position){
            return courseFileList.get(position);
        }
        @Override
        public long getItemId(int position){
            return courseFileList.indexOf(getItem(position));
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View v= convertView;
            ViewHolder holder;
            if(v==null){
                v=LayoutInflater.from(getApplicationContext()).inflate(R.layout.listview,null);
                holder=new ViewHolder((TextView)v.findViewById(R.id.ltv1),(TextView)v.findViewById(R.id.ltv2),(TextView)v.findViewById(ltv3));
                v.setTag(holder);
            }
            else
                holder=(ViewHolder)v.getTag();
            CourseFile cf=(CourseFile)getItem(position);
            holder.text1.setText(cf.getSubject_str());
            holder.text2.setText(cf.getLesson_str());
            holder.text3.setText(cf.getTime_str());
            return v;
        }
        class ViewHolder{
            TextView text1;
            TextView text2;
            TextView text3;
            public ViewHolder(TextView text1, TextView text2, TextView text3) {
                this.text1 = text1;
                this.text2 = text2;
                this.text3 = text3;
            }

        }
    }




    //按鈕監聽器
    private View.OnClickListener ClickIntHere= new View.OnClickListener(){
        @Override
        //按下按鈕進入function
        public void onClick(View v){

            if(v.getId()==dateTextView.getId()){
                new DatePickerDialog(ActivityDay.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String date=(""+year+"/"+(month+1)+"/"+day);
                        dateTextView.setText(date);
                        Today=date;
                        Log.i("TAG",Today);
                        cFL.clear();
                        countListSize=0;
                        for(int i=0;i<10;i++) {
                            if(settings.getString("Subject" + Today + "" + i, "NO").equals("NO"))
                                break;
                            cFL.add(new CourseFile(settings.getString("Subject" + Today + "" + i, "NO"),settings.getString("Lesson" + Today + "" + i, "NO"),settings.getString("Time" + Today + "" + i, "NO")));
                            countListSize++;
                        }
                    }

                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                //建立日期檔
            }
            if(v.getId()==addListbutton.getId()){
                LayoutInflater li=LayoutInflater.from(context);
                View pView=li.inflate(R.layout.input,null);
                AlertDialog.Builder alertDialogBuiler=new AlertDialog.Builder(context);
                alertDialogBuiler.setView(pView);
                final Spinner userInput1=(Spinner) pView.findViewById(R.id.sInput);
                final EditText userInput2=(EditText)pView.findViewById(R.id.lInput);
                final Spinner userInput3=(Spinner) pView.findViewById(R.id.tInput);
                alertDialogBuiler.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        cFL.add(new CourseFile(userInput1.getSelectedItem().toString(),userInput2.getText().toString(),userInput3.getSelectedItem().toString()));
                        adapter=new Myadapter(ActivityDay.this,cFL);
                        dListView.setAdapter(adapter);
                        settings.edit().putString("Subject"+Today+""+countListSize,userInput1.getSelectedItem().toString()).commit();
                        settings.edit().putString("Lesson"+Today+""+countListSize,userInput2.getText().toString()).commit();
                        settings.edit().putString("Time"+Today+""+countListSize,userInput3.getSelectedItem().toString()).commit();
                        Log.i("TAG",""+countListSize);
                        countListSize++;
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=alertDialogBuiler.create();
                alertDialog.show();
            }
        }
    };
    public void gotoActivityWeek(android.view.View v){Intent it = new Intent(this,ActivityWeek.class);startActivity(it);}
    public void gotoActivityMon(android.view.View v){Intent it = new Intent(this,ActivityMon.class);startActivity(it);}
    public void gotoScore(android.view.View v){Intent it = new Intent(this,ScoreShow.class);startActivity(it);}
    public void gotoScript(android.view.View v){Intent it = new Intent(this,ScriptShow.class);startActivity(it);}
    public void gotoActivityDay(android.view.View v){Intent it = new Intent(this,ActivityDay.class);startActivity(it);}
    public void gotoSetting(android.view.View v){Intent it = new Intent(this,Setting.class);startActivity(it);}




}
