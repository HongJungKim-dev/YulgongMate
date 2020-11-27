package kr.ac.konkuk.yulgongmate;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddCalenderActivity extends AppCompatActivity {
    Button btnStart, btnEnd, btnSave, btnBack; //시작시간, 종료시간, 저장, 취소 버튼
    dbHelper helper; //dbHelper
    SQLiteDatabase db; //db
    int startHour; //시작시간 시
    int startMinute; //시작시간 분
    int endHour; //종료시간 시
    int endMinute; //종료시간 분
    int[] day; //년월일 저장
    Intent intent; //인텐트
    String id; //입력된 아이디 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.add_calender);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        btnStart = (Button)findViewById(R.id.btn_start); //시작시간 아이디 저장
        btnEnd = (Button)findViewById(R.id.btn_end); //종료시간 아이디 저장
        btnSave = (Button)findViewById(R.id.btn_save); //저장버튼 아이디 저장
        btnBack = (Button)findViewById(R.id.btn_back); //취소버튼 아이디 저장

        helper = dbHelper.getInstance(getApplicationContext()); //dbHelper불러오기

        day = new int[3]; //day 배열 생성
        intent = getIntent(); //인텐트 얻기
        day = intent.getIntArrayExtra("day"); //년월일 불러오기
        id = intent.getStringExtra("id"); //로그인한 id불러오기
    }

    public void onClick(View v){
        final Calendar c = Calendar.getInstance();   //캘린더 클래스의 객체 저장
        int mHour = c.get(Calendar.HOUR_OF_DAY);   //캘린더 클래스의 객체의 시간 저장
        int mMinute = c.get(Calendar.MINUTE);   //캘린더 클래스의 객체의 분 저장
        if(v == btnStart){ //시작시간 버튼이면
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
        if(v == btnSave){ //저장버튼이면
            insertCalendar(); //db에 저장
            finish(); //끝내기
        }

        if(v == btnBack){
            finish();//액티비티 끝내기
        }

    }
    public void insertCalendar(){
        db = helper.getWritableDatabase(); //쓰기용 db오픈
        String dayStr = arrToString(day); //day string변환
        int startTime = makeTimeFormat(startHour, startMinute); //시작시간 포맷화
        int endTime = makeTimeFormat(endHour, endMinute); //종료시간 포맷화
        try{ //sql문으로 insert수행
            db.execSQL("INSERT INTO calendar VALUES('" + id + "','" + dayStr + "','" + startTime + "','" + endTime +"');");
        }catch(Exception e){
            System.out.println("DB insert error! : " + e); //에러메시지
        }
        Toast.makeText(getApplicationContext(), "일정이 저장되었습니다", Toast.LENGTH_SHORT).show(); //출력
    }
    public String arrToString(int[] day){
        String str = null;
        if(day != null) {
            str = day[0] + "-" + day[1] + "-" + day[2]; //날짜 string변환
        }
        return str;
    }
    public int makeTimeFormat(int start, int end){ //시간 포맷 만들기
        int time = start*60 + end;//계산
        return time;
    }
}
