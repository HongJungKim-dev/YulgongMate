package kr.ac.konkuk.yulgongmate;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TimerActivity extends AppCompatActivity {
    EditText etContent;
    TextView studyContent;
    //TextView etContent;
    Chronometer chTimer;
    Button btnStart, btnStop, btnTotal, btnPlus, btnAdd, btnNo, btnModify;
    ImageButton ib;
    ArrayList<String> etContentToStr = new ArrayList<String>();
    ArrayList<View> arrRowView = new ArrayList<View>();
    //String[] etContentToStr;
    ListView listTimer;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.timer);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        btnAdd = (Button) findViewById(R.id.add_timer);
        CustomList adapter = new CustomList(TimerActivity.this);
        listTimer = (ListView) findViewById(R.id.list_timer);
        //start = (Button) listTimer.findViewById(R.layout.timeritem.R.id.btn_start);
        listTimer.setAdapter(adapter);
        listTimer.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v= (View)parent.getItemAtPosition(position);
                if(v==start){
                    chTimer.start();
                }
            }
        });
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;

        public CustomList(Activity context) {
            super(context, R.layout.timeritem, etContentToStr);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.timeritem,null,true);
            arrRowView.add(rowView);
            studyContent = (TextView) rowView.findViewById(R.id.content);
            chTimer = (Chronometer) rowView.findViewById(R.id.timer);
            btnStart = (Button) rowView.findViewById(R.id.btn_start);
            btnStop = (Button) rowView.findViewById(R.id.btn_stop);
            btnModify = (Button) rowView.findViewById(R.id.btn_mod);

            studyContent.setText(etContentToStr.get(position));

            //need to be revised//
            btnStart.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    chTimer.start();
                }
            });
            btnStop.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    chTimer.stop();
                    chTimer.setBase(SystemClock.elapsedRealtime());
                }
            });
            //need to be revised//

            return rowView;
        }
    }
    public void onClick(View v) {
        
        if(v == btnAdd){
            final Dialog myDialog = new Dialog(this);   //커스텀 대화상자를 생성
            myDialog.setContentView(R.layout.custom_dialog);
            myDialog.show();   //대화상자를 계속 화면에 출력

            btnPlus = (Button) myDialog.findViewById(R.id.addOk);
            btnNo = (Button) myDialog.findViewById(R.id.addCancel);
            etContent = (EditText) myDialog.findViewById(R.id.timer_title);

            btnPlus.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(etContent.getText().toString().trim().length()>0){   //EditText에 입력된 값이 있는 경우,
                        etContentToStr.add(etContent.getText().toString());
                        myDialog.dismiss();   //대화상자를 종료하고 화면에서 삭제
                    }else{   //EditText에 입력된 값이 없는 경우,
                        Toast toast = Toast.makeText(getApplicationContext(),"다시 입력하시오", Toast.LENGTH_SHORT);   //해당 문구를 SHORT타임(잠시)에 나타나도록 설정
                        toast.setGravity(Gravity.CENTER,0,0);  //위치 변경
                        toast.show();  //출력
                    }
                }
            });
            btnNo.setOnClickListener(new View.OnClickListener() {   //사용자가 'CANCEL' 버튼을 누른 경우,
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();   //대화상자를 종료하고 화면에서 삭제
                }
            });

        }
//        if(v==btnStart){
//            chTimer.start();
//        }
//        if(v==btnStop){
//            chTimer.stop();
//            chTimer.setBase(SystemClock.elapsedRealtime());
//        }
    }
}
