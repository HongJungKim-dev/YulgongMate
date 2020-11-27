package kr.ac.konkuk.yulgongmate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    private static dbHelper helper;

    private dbHelper(Context context){
        super(context, "DB.sqlite3", null, 2);
    }

    public static dbHelper getInstance(Context applicationContext) {  //db한번만 생성해서 여러 액티비티에서 사용
        if(helper == null){
            helper = new dbHelper(applicationContext);
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //테이블 3개 생성
        db.execSQL("CREATE TABLE member(id TEXT PRIMARY KEY, password TEXT, name TEXT, FOREIGN KEY(id)REFERENCES calendar);"); //sql문으로 테이블 생성
        db.execSQL("CREATE TABLE calendar(id TEXT , date TEXT, start INT,fin INT, PRIMARY KEY(id, date));"); //sql문으로 테이블 생성
        db.execSQL("CREATE TABLE timer(id TEXT,  content TEXT, time TIME, total TIME, PRIMARY KEY(id, content));"); //sql문으로 테이블 생성
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS member");
        onCreate(db);
    }

}
