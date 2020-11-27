package kr.ac.konkuk.yulgongmate;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class TimerActivity extends AppCompatActivity {
    EditText etContent;
    TextView studyContent;
    Chronometer chTimer;
    Button btnStart, btnStop, btnTotal, btnPlus, btnAdd, btnNo, btnModify;
    ArrayList<String> etContentToStr = new ArrayList<String>();
    ArrayList<View> arrRowView = new ArrayList<View>();
    ListView listTimer;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {   //레이아웃 생성
        super.onCreate(savedInstanceState);  //초기 컴포넌트 초기화
        setContentView(R.layout.timer);  //화면에 나타날 view가 담긴 xml파일을 불러와서 액티비티 설정
        btnAdd = (Button) findViewById(R.id.add_timer); //타이머추가 버튼
        CustomList adapter = new CustomList(TimerActivity.this); //어댑터
        listTimer = (ListView) findViewById(R.id.list_timer); //타이머 리스트 아이디 저장
        listTimer.setAdapter(adapter); //어댑터 등록
        listTimer.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //클릭하면
                View v= (View)parent.getItemAtPosition(position); //위치 얻기
                if(v==start){ //타이머 시작
                    chTimer.start();
                }
            }
        });
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;

        public CustomList(Activity context) { //customlist
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

            btnStart.setOnClickListener(new Button.OnClickListener(){ //시작버튼 누르면
                @Override
                public void onClick(View v) {
                    chTimer.start();//타이머시작
                }
            });
            btnStop.setOnClickListener(new Button.OnClickListener(){ //스탑버튼 누르면
                @Override
                public void onClick(View v) {
                    chTimer.stop(); //타이머 중지
                    chTimer.setBase(SystemClock.elapsedRealtime());
                }
            });
            return rowView;
        }
    }
    public void onClick(View v) {
        
        if(v == btnAdd){ //추가버튼 누르면
            final Dialog myDialog = new Dialog(this);   //커스텀 대화상자를 생성
            myDialog.setContentView(R.layout.custom_dialog); //화면 출력
            myDialog.show();   //대화상자를 계속 화면에 출력

            btnPlus = (Button) myDialog.findViewById(R.id.addOk); //ok버튼 아이디 저장
            btnNo = (Button) myDialog.findViewById(R.id.addCancel); //취소 버튼 아이디 저장
            etContent = (EditText) myDialog.findViewById(R.id.timer_title);//타이머 제목 아이디 저장

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
    }
}
