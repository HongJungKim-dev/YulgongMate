package kr.ac.konkuk.yulgongmate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button btnOk;
    Button btnCancel;
    dbHelper helper;
    SQLiteDatabase db;
    Cursor cursor = null;
    String compId = null;
    EditText id;
    EditText password;
    String idStr;
    String pwdStr;
    String compPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.login);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        id = (EditText) findViewById(R.id.id_text);
        password = (EditText) findViewById(R.id.pwd_text);
        helper = dbHelper.getInstance(getApplicationContext());
        db = helper.getReadableDatabase();

        idStr = id.getText().toString();
        pwdStr = password.getText().toString();
//        try{
//            db = helper.getWritableDatabase();
//        }catch(SQLiteException ex){
//            db = helper.getReadableDatabase();
//        }
    }

    public void myListener4(View Target){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }
    public void onClick(View v) {

        if(v == btnOk){
            if(existInMember(idStr)==true) {
                if(checkPwd(idStr, pwdStr)==true){
                    Intent intent = new Intent();  //보낼 인텐트 생성
                    intent.putExtra("INPUT_TEXT1", idStr);   //"INPUT_TEXT2"이름으로 사용자로부터 입력받은 내용을 string으로 변환하여 저장
                    setResult(RESULT_OK,intent);   //RESULT_OK결과코드와 인텐트 전송
                    finish();   //종료하고 이전액티비티로 돌아가기
                }else{
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "아이디가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        if(v == btnCancel){
            setResult(RESULT_CANCELED);   //RESULT_CANCELED코드를 전송
            finish();   //종료하고 이전액티비티로 돌아가기
        }

    }
    public boolean existInMember(String id){
        int index = 0;
        String cursorStr = null;

        boolean exist = false;

        try {
            cursor = db.rawQuery("SELECT id FROM member;", null);
        }catch(Exception e){
            System.out.println("DB rawQuery error! : " + e);
        }
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    try {
                        index = cursor.getColumnIndex("id");
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_SHORT).show();
                        System.out.println("getColumnIndex error! : " + e);
                    }
                    try {
                        cursorStr = cursor.getString(index);
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(), "cursor.getString error! : " +e, Toast.LENGTH_SHORT).show();
                        System.out.println("cursor.getString error! : " + e);
                    }
                    compId = cursorStr;
                    if (compId!=null && compId.equals(id)==true) {
                        exist = true;
                        break;
                    }
                } while(cursor.moveToNext());
            }
        }
        if(exist==true){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkPwd(String inputId, String inputPwd){
        try {
            cursor = db.rawQuery("SELECT password FROM member where id = " +inputId +";", null);
        }catch(Exception e){
            System.out.println("DB rawQuery error! : " + e);
        }

        if (cursor != null) {
            compPwd = cursor.getString(cursor.getColumnIndex("password"));
            Toast.makeText(getApplicationContext(), "compPwd : " +compPwd, Toast.LENGTH_SHORT).show();
            if(compPwd.equals(inputPwd)==true){
                return true;
            }
        }
        return false;
    }
}
