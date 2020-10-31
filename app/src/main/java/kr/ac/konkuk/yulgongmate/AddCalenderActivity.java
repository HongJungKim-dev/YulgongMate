package kr.ac.konkuk.yulgongmate;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddCalenderActivity extends AppCompatActivity {
    Button btnStart, btnEnd, btnSave, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.add_calender);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        btnStart = (Button)findViewById(R.id.btn_start);
        btnEnd = (Button)findViewById(R.id.btn_end);
        btnSave = (Button)findViewById(R.id.btn_save);
        btnBack = (Button)findViewById(R.id.btn_back);
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
                        }
                    }, mHour, mMinute, false);   //입력받은 시, 분을 파라미터로 TimePickerDialog 객체 생성
            timePickerDialog.show();   //TimePickerDialog 출력
        }
        if(v == btnEnd){
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            btnEnd.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);   //입력받은 시, 분을 파라미터로 TimePickerDialog 객체 생성

            timePickerDialog.show();   //TimePickerDialog 출력
        }
        if(v == btnSave){
            str1 = "일정 : '" + cTitle.getText().toString() +"'";
            str1 += "이 저장되었습니다.";
            Toast toast = Toast.makeText(getBaseContext(),str1,Toast.LENGTH_LONG);
            toast.show();
        }

        if(v == btnBack){
            finish();
        }

    }
}
