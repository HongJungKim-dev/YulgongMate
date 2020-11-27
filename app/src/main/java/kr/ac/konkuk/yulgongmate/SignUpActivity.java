package kr.ac.konkuk.yulgongmate;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    Button btnCheck, btnFin, btnPrev; //중복확인, 완료, 취소버튼
    dbHelper helper; //dbHelper
    SQLiteDatabase db; //db
    EditText id; //아이디 저장
    EditText password; //비밀번호 저장
    EditText re_password; //비밀번호 확인용
    EditText name; //이름 저장
    String passwordStr; //비밀번호 string변환
    String idStr; //아이디 string변환
    String nameStr; //이름 string변환
    String passwordReStr; //비밀번호 확인용 string변환
    String compId; //db에 있는 아이디
    Cursor cursor = null; //커서

    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.sign_up);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        id = (EditText) findViewById(R.id.et_id); //id edittext 아이디 저장
        name = (EditText) findViewById(R.id.et_name); //name editText 아이디 저장
        password = (EditText) findViewById(R.id.et_password); //입력되는 비밀번호 아이디 저장
        re_password = (EditText) findViewById(R.id.et_re_password);//입력되는 비밀번호확인용 저장
        btnFin = (Button) findViewById(R.id.fin); //완료버튼 아이디 저장
        btnCheck = (Button) findViewById(R.id.btn_check);//중복확인 아이디 저장
        btnPrev = (Button) findViewById(R.id.btn_prev); //취소버튼 아이디 저장

        helper = dbHelper.getInstance(getApplicationContext()); //dbHelper
        db = helper.getWritableDatabase(); //쓰기용db 오픈
    }

    public void onClick(View v) {
        idStr = id.getText().toString(); //string변환
        passwordStr = password.getText().toString();//string변환
        passwordReStr = re_password.getText().toString();//string변환
        nameStr = name.getText().toString();//string변환

        if(v == btnCheck){ //중복확인
            if(existInMember(idStr)==false){ //중복되지 않는 아이디라면
                Toast.makeText(getApplicationContext(), "사용가능한 id입니다.", Toast.LENGTH_SHORT).show(); //출력
            }else{
                Toast.makeText(getApplicationContext(), "이미 존재하는 id입니다.", Toast.LENGTH_SHORT).show();//출력
            }
        }
        if(v == btnFin){ //완료버튼

            if(password.equals(re_password)==true) { //비밀번호 확인
                Toast.makeText(getApplicationContext(), "비밀번호를 확인하시오.", Toast.LENGTH_SHORT).show();
            }
            insertMember(idStr, passwordStr, nameStr); //sql문으로 member저장
            finish();//액티비티 끝내기
        }
        if (v == btnPrev) {//취소버튼
            this.finish();//액티비티 끝내기
        }
    }
public void insertMember(String id, String password, String name){
    try{
        db.execSQL("INSERT INTO member VALUES('" + id + "','" + password + "','" + name + "');"); //sql문으로 삽입
    }catch(Exception e){
        System.out.println("DB insert error! : " + e);//에러메시지
    }
    Toast.makeText(getApplicationContext(), "저장되었습니다!", Toast.LENGTH_SHORT).show(); //출력
}
    public boolean existInMember(String id){
        int index = 0;
        try {
            cursor = db.rawQuery("SELECT id FROM member;", null); //커서 저장
        }catch(Exception e){
            System.out.println("DB rawQuery error! : " + e); //에러메시지
        }
        if(cursor != null){
            if(cursor.moveToFirst()){ //커서 처음부터
                do {
                    try{
                        index = cursor.getColumnIndex("id"); //컬럼 인덱스 저장
                    }catch(Exception e){
                        System.out.println("cursor.getColumnIndex error : " + e + "\n"); //에러메시지
                    }
                    try {
                        compId = cursor.getString(index); //db아이디 얻기
                    }catch (Exception e){
                        System.out.println("cursor.getString error : " + e + "\n");//에러메시지
                    }
                    if (compId != null && compId.equals(id)==true) {
                        return true;
                    }
                } while(cursor.moveToNext());
            }
        }else{
            Toast.makeText(getApplicationContext(), "회원정보가 없습니다.", Toast.LENGTH_SHORT).show();//출력
            System.out.println("회원정보가 없습니다.");//출력
        }
        return false;
    }
}
