package es.rufflecol.lara.todolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLDatabaseController {

    private SQLDatabaseHelper sqlDatabaseHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public SQLDatabaseController(Context context) {
        this.context = context;
    }

    public SQLDatabaseController open() throws SQLException {
        sqlDatabaseHelper = new SQLDatabaseHelper(context);
        sqLiteDatabase = sqlDatabaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        sqlDatabaseHelper.close();
    }

    public void insert(String name, String description) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(SQLDatabaseHelper.SUBJECT, name);
        contentValue.put(SQLDatabaseHelper.DESCRIPTION, description);
        sqLiteDatabase.insert(SQLDatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{SQLDatabaseHelper.ID, SQLDatabaseHelper.SUBJECT, SQLDatabaseHelper.DESCRIPTION};
        Cursor cursor = sqLiteDatabase.query(SQLDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLDatabaseHelper.SUBJECT, name);
        contentValues.put(SQLDatabaseHelper.DESCRIPTION, description);
        int intValue = sqLiteDatabase.update(SQLDatabaseHelper.TABLE_NAME, contentValues, SQLDatabaseHelper.ID + " = " + _id, null);
        return intValue;
    }

    public void delete(long _id) {
        sqLiteDatabase.delete(SQLDatabaseHelper.TABLE_NAME, SQLDatabaseHelper.ID + "=" + _id, null);
    }
}