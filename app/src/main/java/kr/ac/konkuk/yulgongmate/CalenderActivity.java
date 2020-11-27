package kr.ac.konkuk.yulgongmate;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CalenderActivity extends AppCompatActivity {
    Button btnMod, btnDel;
    CalendarView cv;
    dbHelper helper;
    SQLiteDatabase db;
    int[] day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.calender);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        btnMod = (Button) findViewById(R.id.modCalendar);
        btnDel = (Button) findViewById(R.id.delCalendar);
        cv = (CalendarView) findViewById(R.id.CV);

        //helper = new dbHelper(this);
        helper = dbHelper.getInstance(getApplicationContext());
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                day = new int[3];
                day[0] = year;
                day[1] = month;
                day[2] = dayOfMonth;
            }
        });
//        try{
//            db = helper.getWritableDatabase();
//        }catch (SQLiteException ex){
//            db = helper.getReadableDatabase();
//        }
    }
    public void onClick(View v){
        EditText reserved = findViewById(R.id.reservedCalendar);
        if(v == btnMod) {
            reserved.setText(reserved.getText().toString());
        }
        if(v == btnDel){
            reserved.setText("");
        }
    }
    public void myListener3(View Target){
        Intent intent = new Intent(getApplicationContext(), AddCalenderActivity.class);
        intent.putExtra("day", day);
        startActivity(intent);
    }

}