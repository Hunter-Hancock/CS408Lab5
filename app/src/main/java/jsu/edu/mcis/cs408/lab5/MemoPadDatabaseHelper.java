package jsu.edu.mcis.cs408.lab5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MemoPadDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "memopad.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MEMO = "memo";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";

    public MemoPadDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMO_TABLE = "CREATE TABLE memo (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
        db.execSQL(CREATE_MEMO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_MEMO_TABLE = "DROP TABLE IF EXISTS " + TABLE_MEMO;
        db.execSQL(DROP_MEMO_TABLE);
    }

    public void insertMemo(Memo memo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, memo.getName());
        long id = db.insert(TABLE_MEMO, null, values);
        db.close();
        memo.setId((int) id);
    }

    public List<Memo> listMemos() {
        List<Memo> memos = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MEMO;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                Memo m = new Memo(id, name);
                memos.add(m);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return memos;
    }

    public void deleteMemo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEMO, COLUMN_ID + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }
}
