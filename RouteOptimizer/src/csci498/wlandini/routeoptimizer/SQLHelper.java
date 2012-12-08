package csci498.wlandini.routeoptimizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "paths.db";
	private static final int SCHEMA_VERSION = 1;

	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	public long insertPath(String start, String finish, String description, int time){
		ContentValues cv = new ContentValues();
		
		cv.put("start", start);
		cv.put("finish", finish);
		cv.put("description", description);
		cv.put("time", time);
		
		return getWritableDatabase().insert("paths", null, cv);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE paths (_id INTEGER PRIMARY KEY AUTOINCREMENT, start TEXT, finish TEXT, description TEXT, time INTEGER);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public Cursor getAllPaths() {
        return getWritableDatabase().rawQuery("SELECT * FROM paths", null);
    }
	
	public String getStart(Cursor c) {
        return c.getString(1);
    }

    public String getFinish(Cursor c) {
        return c.getString(2);
    }
}
