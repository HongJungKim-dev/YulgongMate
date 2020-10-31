package kr.ac.konkuk.yulgongmate;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    Button btnCheck, btnFin, btnPrev;
    dbHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.sign_up);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        helper = new dbHelper(this);
        try{
            db = helper.getWritableDatabase();
        }catch(SQLiteException ex){
            db = helper.getReadableDatabase();
        }
    }

    public void onClick(View v) {
        EditText id = (EditText) findViewById(R.id.et_id);
        EditText pw = (EditText) findViewById(R.id.et_password);
        EditText rePw = (EditText) findViewById(R.id.et_re_password);
        EditText name = (EditText) findViewById(R.id.et_name);
        String str = null;
        if(v == btnCheck){

        }
        if(v == btnFin){
            if(id.getText().toString().trim().length()>0 && pw.getText().toString().trim().length()>0
                    && rePw.getText().toString().trim().length()>0 && name.getText().toString().trim().length()>0) {
                str = "INSERT INTO member(ID, password, name) VALUES (";
                db.execSQL(str + id +"," + pw +"," + rePw +", "+name+");");
                //check whether pwd is same
                //openSSL

            }else{
                Toast toast = Toast.makeText(getApplicationContext(),"다시 입력하시오", Toast.LENGTH_SHORT);   //해당 문구를 SHORT타임(잠시)에 나타나도록 설정
                toast.setGravity(Gravity.CENTER,0,0);  //위치 변경
                toast.show();  //출력
            }
            this.finish();

        }
        if (v == btnPrev) {
            this.finish();
        }
    }

}
