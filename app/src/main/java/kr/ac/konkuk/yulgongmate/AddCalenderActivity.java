package kr.ac.konkuk.yulgongmate;

import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddCalenderActivity extends AppCompatActivity {
    Button btnStart, btnEnd, btnSave, btnBack;
    dbHelper helper;
    SQLiteDatabase db;
    int startHour;
    int startMinute;
    int endHour;
    int endMinute;
    int[] day;
    Intent intent;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.add_calender);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        btnStart = (Button)findViewById(R.id.btn_start);
        btnEnd = (Button)findViewById(R.id.btn_end);
        btnSave = (Button)findViewById(R.id.btn_save);
        btnBack = (Button)findViewById(R.id.btn_back);

        //helper = new dbHelper(this);
        helper = dbHelper.getInstance(getApplicationContext());

        day = new int[3];
        intent = getIntent();
        day = intent.getIntArrayExtra("day");
        id = intent.getStringExtra("id");
    }

    public void onClick(View v){
        final EditText cTitle = (EditText) findViewById(R.id.calender_title);
        final Calendar c = Calendar.getInstance();   //캘린더 클래스의 객체 저장
        int mHour = c.get(Calendar.HOUR_OF_DAY);   //캘린더 클래스의 객체의 시간 저장
        int mMinute = c.get(Calendar.MINUTE);   //캘린더 클래스의 객체의 분 저장
        String str1 = "";
        if(v == btnStart){

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            btnStart.setText(hourOfDay + ":" + minute);
                            startHour = hourOfDay;
                            startMinute = minute;
                        }
                    }, mHour, mMinute, true);   //입력받은 시, 분을 파라미터로 TimePickerDialog 객체 생성
            timePickerDialog.show();   //TimePickerDialog 출력
        }
        if(v == btnEnd){
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            btnEnd.setText(hourOfDay + ":" + minute);
                            endHour = hourOfDay;
                            endMinute = minute;
                        }
                    }, mHour, mMinute, true);   //입력받은 시, 분을 파라미터로 TimePickerDialog 객체 생성

            timePickerDialog.show();   //TimePickerDialog 출력
        }
        if(v == btnSave){
            insertCalendar();
        }

        if(v == btnBack){
            finish();
        }

    }
    public void insertCalendar(){
        db = helper.getWritableDatabase();
        String dayStr = arrToString(day);
        int startTime = makeTimeFormat(startHour, startMinute);
        int endTime = makeTimeFormat(endHour, endMinute);
        try{
            db.execSQL("INSERT INTO calendar VALUES('" + id + "','" + dayStr + "','" + startTime + "','" + endTime +"');");
        }catch(Exception e){
            System.out.println("DB insert error! : " + e);
        }
        Toast.makeText(getApplicationContext(), "일정이 저장되었습니다", Toast.LENGTH_SHORT).show();
    }
    public String arrToString(int[] day){
        String str = day[0] + "-" + day[1] + "-" + day[2];
        return str;
    }
    public int makeTimeFormat(int start, int end){
        int time = start*60 + end;
        return time;
    }
}
