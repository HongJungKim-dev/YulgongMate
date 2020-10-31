package kr.ac.konkuk.yulgongmate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button btnOk;
    dbHelper helper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.login);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        btnOk = (Button) findViewById(R.id.btn_ok);
        helper = new dbHelper(this);
        try{
            db = helper.getWritableDatabase();
        }catch(SQLiteException ex){
            db = helper.getReadableDatabase();
        }
    }

    public void myListener4(View Target){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }
    public void onClick(View v) {
        if(v == btnOk){
            Cursor cursor = db.rawQuery("SELECT ID, password, name FROM member", null);
            if(cursor != null){
                if(cursor.moveToFirst())
                do{
                    String str = cursor.getString(cursor.getColumnIndex("ID"));
                    Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);   //해당 문구를 SHORT타임(잠시)에 나타나도록 설정
                    toast.setGravity(Gravity.CENTER,0,0);  //위치 변경
                    toast.show();  //출력

                }while(cursor.moveToNext());
            }
        }

    }
}
