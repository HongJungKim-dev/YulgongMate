package kr.ac.konkuk.yulgongmate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    public dbHelper(Context context){
        super(context, "DB", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE member (ID TEXT PRIMARY KEY, password TEXT, name TEXT, FOREIGN KEY(ID)REFERENCES calendar);");
        db.execSQL("CREATE TABLE calendar (ID TEXT , date DATE, start TIME,fin TIME, PRIMARY KEY(ID, date));");
        db.execSQL("CREATE TABLE timer (ID TEXT,  content TEXT, time TIME, total TIME, PRIMARY KEY(ID, content));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }
}
