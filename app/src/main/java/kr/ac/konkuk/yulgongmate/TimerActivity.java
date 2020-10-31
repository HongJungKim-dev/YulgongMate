package kr.ac.konkuk.yulgongmate;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.timer);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
    }
}
