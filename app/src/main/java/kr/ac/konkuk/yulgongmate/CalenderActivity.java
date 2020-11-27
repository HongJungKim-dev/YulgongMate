package kr.ac.konkuk.yulgongmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CalenderActivity extends AppCompatActivity {
    Button btnMod, btnDel; //수정, 삭제버튼
    CalendarView cv; //캘린더뷰
    dbHelper helper; //dbHelper
    int[] day; //년월일 저장 배열
    String id; //로그인한 아이디

    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.calender);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        btnMod = (Button) findViewById(R.id.modCalendar); //수정버튼 아이디 저장
        btnDel = (Button) findViewById(R.id.delCalendar); // 삭제버튼 아이디 저장

        cv = (CalendarView) findViewById(R.id.CV); //캘린더뷰 아이디 저장
        Intent intent = getIntent(); //인텐트 얻기
        id = intent.getStringExtra("id"); //로그인한 아이디 불러오기
        helper = dbHelper.getInstance(getApplicationContext()); //dbHelper가져오기
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) { //캘린더뷰 값들 저장
                day = new int[3];
                day[0] = year;
                day[1] = month;
                day[2] = dayOfMonth;
            }
        });
    }
    public void onClick(View v){
        EditText reserved = findViewById(R.id.reservedCalendar);
        if(v == btnMod) { //수정버튼이면
            reserved.setText(reserved.getText().toString()); //개정사항 출력
        }
        if(v == btnDel){//삭제버튼이면
            reserved.setText(""); //빈공간 출력
        }
    }
    public void myListener3(View Target){
        Intent intent = new Intent(getApplicationContext(), AddCalenderActivity.class);
        intent.putExtra("id",id); //로그인한 id전달
        intent.putExtra("day", day); //입력된 day 전달
        startActivity(intent);
    }

}