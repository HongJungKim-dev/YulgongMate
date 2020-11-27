package kr.ac.konkuk.yulgongmate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button btnOk; //확인 버튼
    Button btnCancel; //취소 버튼
    dbHelper helper; //dbHelper
    SQLiteDatabase db; //SQLiteDatabase
    Cursor cursor = null; //커서
    String compId = null; //db안의 id
    EditText id; //사용자 입력 id
    EditText password; //사용자 입력 비밀번호
    String idStr; //id string저장
    String pwdStr; //password string저장
    String compPwd; //db안에 있는 비밀번호
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.login);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        btnOk = (Button) findViewById(R.id.btn_ok); //ok버튼 아이디 저장
        btnCancel = (Button) findViewById(R.id.btn_cancel); //cancel버튼 아이디 저장

        id = (EditText) findViewById(R.id.id_text);  //아이디 editText 아이디 저장
        password = (EditText) findViewById(R.id.pwd_text); // 비밀번호 editText 아이디 저장
        helper = dbHelper.getInstance(getApplicationContext());  // dbHelper객체
        db = helper.getReadableDatabase();  //db읽을 수 있게 설정
    }

    public void myListener4(View Target){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class); //인텐트 생성
        startActivity(intent);  //회원가입화면 띄우기
    }
    public void onClick(View v) {
        idStr = id.getText().toString(); //아이디 string변환
        pwdStr = password.getText().toString();  //비밀번호 string변환
        if(v == btnOk){
            if(existInMember(idStr)==true) {  //아이디 존재 여부 검색
                if(rightPwd(idStr, pwdStr)==true){ // 비밀번호 존재 여부 검색
                    Toast.makeText(getApplicationContext(), "'"+idStr + "'로 로그인되었습니다.", Toast.LENGTH_SHORT).show();  //로그인 성공
                    Intent intent = new Intent();  //보낼 인텐트 생성
                    intent.putExtra("INPUT_TEXT", idStr);   //"INPUT_TEXT"이름으로 사용자로부터 입력받은 내용을 string으로 변환하여 저장
                    setResult(RESULT_OK,intent);   //RESULT_OK결과코드와 인텐트 전송
                    finish();   //종료하고 이전액티비티로 돌아가기
                }else{
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();  //출력
                }
            }else{
                Toast.makeText(getApplicationContext(), "아이디가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();  //출력
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
            cursor = db.rawQuery("SELECT id FROM member;", null); //select문으로 커서 저장
        }catch(Exception e){
            System.out.println("===================DB rawQuery error! : " + e+"========================="); //에러메시지
        }
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    try {
                        index = cursor.getColumnIndex("id"); //칼럼 인덱스 저장
                    }catch(Exception e){
                        System.out.println("=====================getColumnIndex error! : " + e+"========================="); //에러메시지
                    }
                    try {
                        cursorStr = cursor.getString(index);  //string으로 변환
                    }catch(Exception e){
                        System.out.println("=========================cursor.getString error! : " + e+"====================");//에러메시지
                    }
                    compId = cursorStr; //커서string 저장
                    //System.out.println("===============DB에 있는 id : "+compId+"==================\n");
                    if (compId!=null && compId.equals(id)==true) { //db에 있는 값과 같은지 비교
                        exist = true;
                        break;
                    }
                } while(cursor.moveToNext());
            }
        }else{
            System.out.println("=============== cursor empty (id) ==================\n");
        }
        if(exist==true){
            return true;
        }else{
            return false;
        }
    }
    public boolean rightPwd(String inputId, String inputPwd){
        int index = 0;
        boolean rightPwd = false;
        try {
            cursor = db.rawQuery("SELECT password FROM member WHERE id='" + inputId +"';", null);//select문으로 커서 저장
        }catch(Exception e){
            System.out.println("DB rawQuery error! : " + e); //에러메시지
        }

        if (cursor != null) {
            while (cursor.moveToNext()) {
                try {
                index = cursor.getColumnIndex("password"); //칼럼 인덱스저장
                //System.out.println("======================index : " + index +"===========================\n");
            }catch (Exception e){
                System.out.println("cursor.getColumnIndex error! : " + e); //에러메시지
            }
                try {
                    compPwd = cursor.getString(index); //인덱스 저장
                }catch (Exception e){
                System.out.println("cursor.getString error! : " + e); //에러 메시지
            }
                if(compPwd !=null && compPwd.equals(inputPwd)==true){ //db에 있는 비밀번화와 같은지 비교
                    rightPwd = true;
                }
            }
        }else{
            System.out.println("===============cursor empty====================\n");  //에러메시지
        }
        if(rightPwd == true){
            return true;
        }else {
            return false;
        }
    }
}
