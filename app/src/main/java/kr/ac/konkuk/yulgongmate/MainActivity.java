package kr.ac.konkuk.yulgongmate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int GET_STRING=1;   //요청코드
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {//레이아웃 생성
        super.onCreate(savedInstanceState); //초기 컴포넌트 초기화
        setContentView(R.layout.activity_main);//화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정

    }

    public void myListener(View Target){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivityForResult(intent, GET_STRING); //서브액티비티 시작
    }
    public void myListener1(View Target){
        Intent intent = new Intent(getApplicationContext(), TimerActivity.class); //타이머 화면 인텐트
        intent.putExtra("id",id); //로그인id 보내기
        startActivity(intent);//인텐트 시작
    }
    public void myListener2(View Target){
        Intent intent = new Intent(getApplicationContext(), CalenderActivity.class); //캘린더 화면 인텐트
        intent.putExtra("id",id); //로그인한 id보내기
        startActivity(intent); //인텐트 시작
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //Activity띄울때 전달했던 요청코드, 새로 띄운 SubActivity에서 전달받은 결과코드, 전달받은 인텐트로 결과 실행
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_STRING) {   //요청코드가 GET_STRING이라면
            if (resultCode == RESULT_OK) {   //전달받은 결과 코드가 RESULT_OK이라면
                id = data.getStringExtra("INPUT_TEXT"); //결과데이터 받기
                Toast.makeText(getApplicationContext(), "로그인되었습니다.", Toast.LENGTH_SHORT).show(); //출력
            }
        }
    }
}