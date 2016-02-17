package es.rufflecol.lara.todolistapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLDatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "ToDoList.DB";
    static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "ToDoListTable";
    public static final String ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESCRIPTION = "description";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESCRIPTION + " TEXT);";

    public SQLDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
