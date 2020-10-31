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
        startActivity(intent);
    }
    public void myListener2(View Target){
        Intent intent = new Intent(getApplicationContext(), CalenderActivity.class);
        startActivity(intent);
    }
}