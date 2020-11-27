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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void myListener(View Target){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
    public void myListener1(View Target){
        Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    public void myListener2(View Target){
        Intent intent = new Intent(getApplicationContext(), CalenderActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //Activity띄울때 전달했던 요청코드, 새로 띄운 SubActivity에서 전달받은 결과코드, 전달받은 인텐트로 결과 실행
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_STRING) {   //요청코드가 GET_STRING이라면
            if (resultCode == RESULT_OK) {   //전달받은 결과 코드가 RESULT_OK이라면
                id = data.getStringExtra("INPUT_TEXT");
                Toast.makeText(getApplicationContext(), id+"으로 로그인되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}