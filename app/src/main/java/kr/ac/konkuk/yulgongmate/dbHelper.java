package kr.ac.konkuk.yulgongmate;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    public dbHelper(Context context){
        super(context, "DB", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE member " +
                "(id TEXT PRIMARY KEY, password TEXT, name TEXT, FOREIGN KEY(id)REFERENCES calendar);");
        db.execSQL("CREATE TABLE calendar " +
                "(id TEXT , date DATE, start TIME,fin TIME, PRIMARY KEY(id, date));");
        db.execSQL("CREATE TABLE timer " +
                "(id TEXT,  content TEXT, time TIME, total TIME, PRIMARY KEY(id, content));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS member");
        onCreate(db);
    }
    public boolean insertMember(String id, String password, String name){
        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put("id", id);
//        contentValues.put("password", password);
//        contentValues.put("name", name);
//        db.insert("member", null, contentValues);
        db.execSQL("INSERT INTO member VALUES(id, password, name);");
        return true;
    }
}
