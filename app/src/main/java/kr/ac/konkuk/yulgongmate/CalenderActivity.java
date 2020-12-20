package kr.ac.konkuk.yulgongmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CalenderActivity extends AppCompatActivity {
    EditText reserved;
    CalendarView cv; //캘린더뷰
    dbHelper helper; //dbHelper
    int[] day; //년월일 저장 배열
    String id; //로그인한 아이디
    private CalendarAdapter calendarAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.calender);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        reserved = (EditText) findViewById(R.id.reservedCalendar);

        cv = (CalendarView) findViewById(R.id.CV); //캘린더뷰 아이디 저장
        Intent intent = getIntent(); //인텐트 얻기
        id = intent.getStringExtra("id"); //로그인한 아이디 불러오기
        //Toast.makeText(getApplicationContext(), "id : "+id, Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<CalendarData> calendarItemList = new ArrayList<>();

        calendarAdapter = new CalendarAdapter(calendarItemList);
        recyclerView.setAdapter(calendarAdapter);

        calendarItemList.add(new CalendarData("study1","2020-12-15", "11:00~15:00"));
        calendarItemList.add(new CalendarData("study2","2020-12-16", "11:00~15:00"));
        calendarItemList.add(new CalendarData("study3","2020-12-17", "11:00~15:00"));
        calendarItemList.add(new CalendarData("study4","2020-12-18", "11:00~15:00"));
        calendarItemList.add(new CalendarData("study5","2020-12-19", "11:00~15:00"));
        calendarItemList.add(new CalendarData("study6","2020-12-20", "11:00~15:00"));
        calendarItemList.add(new CalendarData("study7","2020-12-21", "11:00~15:00"));
        calendarAdapter.notifyDataSetChanged();

        try{
            System.out.println("=========================");
            System.out.println("id : "+id);
        }catch(Exception e){
            System.out.println("=========================");
            System.out.println("error" + e);
        }
        helper = dbHelper.getInstance(getApplicationContext()); //dbHelper가져오기

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) { //캘린더뷰 값들 저장
                day = new int[3];
                day[0] = year;
                day[1] = month;
                day[2] = dayOfMonth;
                if(day[0]==0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "날짜를 선택하시오", Toast.LENGTH_SHORT);
                }else {
                    String dayStr = "'"+day[0] + "-" + day[1] + "-" + day[2]+"'로 날짜가 선택되었습니다.";
                    reserved.setText(dayStr);
                }
               // Toast.makeText(getApplicationContext(), "day : "+day[0]+","+day[1]+","+day[2], Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onClick(View v){

    }
    public void myListener3(View Target){

        Intent intent = new Intent(getApplicationContext(), AddCalenderActivity.class);
        intent.putExtra("id",id); //로그인한 id전달
        intent.putExtra("day", day); //입력된 day 전달
        startActivity(intent);
    }

}