package kr.ac.konkuk.yulgongmate;

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

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    Button btnCheck, btnFin, btnPrev;
    dbHelper helper;
    SQLiteDatabase db;
    EditText id;
    EditText password;
    EditText re_password;
    EditText name;
    String passwordStr;
    String idStr;
    String nameStr;
    String passwordReStr;
    String compId;
    Cursor cursor = null;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.sign_up);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        id = (EditText) findViewById(R.id.et_id);
        name = (EditText) findViewById(R.id.et_name);
        password = (EditText) findViewById(R.id.et_password);
        re_password = (EditText) findViewById(R.id.et_re_password);
        btnFin = (Button) findViewById(R.id.fin);
        btnCheck = (Button) findViewById(R.id.btn_check);
        btnPrev = (Button) findViewById(R.id.btn_prev);

        //helper = new dbHelper(this);
        helper = dbHelper.getInstance(getApplicationContext());
        db = helper.getWritableDatabase();

        idStr = id.getText().toString();
        passwordStr = password.getText().toString();
        passwordReStr = re_password.getText().toString();
        nameStr = name.getText().toString();
//        try{
//            db = helper.getWritableDatabase();
//        }catch(SQLiteException ex){
//            db = helper.getReadableDatabase();
//        }
    }

    public void onClick(View v) {
//        EditText id = (EditText) findViewById(R.id.et_id);
//        EditText pw = (EditText) findViewById(R.id.et_password);
//        EditText rePw = (EditText) findViewById(R.id.et_re_password);
//        EditText name = (EditText) findViewById(R.id.et_name);
//        String str = null;

        if(v == btnCheck){
            if(existInMember(idStr)==true){
                Toast.makeText(getApplicationContext(), "사용가능한 id입니다.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "이미 존재하는 id입니다.", Toast.LENGTH_SHORT).show();
            }
        }
        if(v == btnFin){
//            while(true){
//                if(password.equals(re_password)==true){
//                    break;
//                }
//                Toast.makeText(getApplicationContext(), "비밀번호를 확인하시오.", Toast.LENGTH_SHORT).show();
//            }
            if(password.equals(re_password)==true) {
                Toast.makeText(getApplicationContext(), "비밀번호를 확인하시오.", Toast.LENGTH_SHORT).show();
            }
            insertMember(idStr, passwordStr, nameStr);
            finish();
//            Bundle extras = getIntent().getExtras();
//            if (extras != null) {
//                int Value = extras.getInt("id");
//                passwordStr = password.getText().toString();
//                passwordReStr = re_password.getText().toString();
//                if(passwordStr.equals(passwordReStr)){
//                    Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getApplicationContext(), "비밀번호 다시 입력하시오", Toast.LENGTH_SHORT).show();
//                }
//                if (Value > 0) {
//                    if(helper.insertMember(id.getText().toString(), passwordStr, name.getText().toString())) {
//                        Toast.makeText(getApplicationContext(), "저장되었음", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "저장되지 않았음", Toast.LENGTH_SHORT).show();
//                    }
//                    finish();
//                } else {
//                    if(helper.insertMember(id.getText().toString(), passwordStr, name.getText().toString())) {
//                        Toast.makeText(getApplicationContext(), "저장되었음", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "저장되지 않았음", Toast.LENGTH_SHORT).show();
//                    }
//                    finish();
//                }
//            }
        }
//            if(id.getText().toString().trim().length()>0 && pw.getText().toString().trim().length()>0
//                    && rePw.getText().toString().trim().length()>0 && name.getText().toString().trim().length()>0) {
//                str = "INSERT INTO member(ID, password, name) VALUES (";
//                db.execSQL(str + id +"," + pw +"," + rePw +", "+name+");");
//                //check whether pwd is same
//                //openSSL
//
//            }else{
//                Toast toast = Toast.makeText(getApplicationContext(),"다시 입력하시오", Toast.LENGTH_SHORT);   //해당 문구를 SHORT타임(잠시)에 나타나도록 설정
//                toast.setGravity(Gravity.CENTER,0,0);  //위치 변경
//                toast.show();  //출력
//            }
//            this.finish();


        if (v == btnPrev) {
            //printMember();
            this.finish();
        }
    }
//    public void saveSignUp(View view){
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            int Value = extras.getInt("id");
//            passwordStr = password.getText().toString();
//            passwordReStr = re_password.getText().toString();
//            if(passwordStr.equals(passwordReStr)){
//                Toast.makeText(getApplicationContext(), "비밀번호 일치", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(getApplicationContext(), "비밀번호 다시 입력하시오", Toast.LENGTH_SHORT).show();
//            }
//            if (Value > 0) {
//                helper.insertMember(id.getText().toString(), passwordStr, name.getText().toString());
//                Toast.makeText(getApplicationContext(), "저장되었음", Toast.LENGTH_SHORT).show();
//                finish();
//            } else {
//                Toast.makeText(getApplicationContext(), "저장되지 않았음", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
public void insertMember(String id, String password, String name){
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put("id", id);
//        contentValues.put("password", password);
//        contentValues.put("name", name);
//        db.insert("member", null, contentValues);
//        try {
//
//        }catch(Exception e){
//            return false;
//        }

    try{
        db.execSQL("INSERT INTO member VALUES('" + id + "','" + password + "','" + name + "');");
    }catch(Exception e){
        System.out.println("DB insert error! : " + e);
    }

    //db.execSQL("INSERT INTO member VALUES(id , password, name)");
    //db.close();
    Toast.makeText(getApplicationContext(), "저장되었습니다!", Toast.LENGTH_SHORT).show();
}
    public boolean existInMember(String id){
        int index = 0;
        try {
            cursor = db.rawQuery("SELECT id FROM member;", null);
        }catch(Exception e){
            System.out.println("DB rawQuery error! : " + e);
        }
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    try{
                        index = cursor.getColumnIndex("id");
                    }catch(Exception e){
                        System.out.println("cursor.getColumnIndex error : " + e + "\n");
                    }
                    try {
                        compId = cursor.getString(index);
                    }catch (Exception e){
                        System.out.println("cursor.getString error : " + e + "\n");
                    }
                    if (compId != null && compId.equals(id)==true) {
                        return true;
                    }
                } while(cursor.moveToNext());
            }
        }else{
            Toast.makeText(getApplicationContext(), "회원정보가 없습니다.", Toast.LENGTH_SHORT).show();
            System.out.println("회원정보가 없습니다.");
        }
        return false;
    }
    public void printMember(){
        try {
            cursor = db.rawQuery("SELECT id FROM MEMBER;", null);
        }catch(Exception e){
            System.out.println("DB rawQuery error! : " + e);
        }
        if(cursor != null){
            if(cursor.moveToFirst()==true){
                do {
                    list.add(cursor.getString(cursor.getColumnIndex("id"))+"\n");
                } while(cursor.moveToNext()==true);
            }
        }
        if(list!=null){
            while(!list.isEmpty()) {
                Toast.makeText(getApplicationContext(), "이미 존재하는 id입니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
