package kr.ac.konkuk.yulgongmate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "member";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PWD = "password";
    public static final String COLUMN_NAME = "name";
    private static dbHelper helper;

    private dbHelper(Context context){
        super(context, "DB", null, 2);
    }

    public static dbHelper getInstance(Context applicationContext) {
//        if(helper == null){
//            synchronized (dbHelper.class){
//                if(helper == null){
//                    helper = new dbHelper(applicationContext);
//                }
//            }
//        }
        if(helper == null){
            helper = new dbHelper(applicationContext);
        }
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE member(id TEXT PRIMARY KEY, password TEXT, name TEXT, FOREIGN KEY(id)REFERENCES calendar);");
        db.execSQL("CREATE TABLE calendar(id TEXT , date TEXT, start INT,fin INT, PRIMARY KEY(id, date));");
//        db.execSQL("CREATE TABLE timer " +
//                "(id TEXT,  content TEXT, time TIME, total TIME, PRIMARY KEY(id, content));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS member");
        onCreate(db);
    }

}
