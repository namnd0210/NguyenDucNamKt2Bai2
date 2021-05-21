package com.example.nguyenducnam_ktra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HocOnline.db";
    private static final int DATABASE_VERSION = 1;
    private final String ID = "id";
    private final String NAME = "name";
    private final String DATE = "date";
    private final String CHUYENNGANH = "chuyenNganh";
    private final String ACTIVE = "active";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE HocOnline(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME + " TEXT," +
                DATE + " TEXT," +
                CHUYENNGANH + " TEXT," +
                ACTIVE + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long addOrder(KhoaHoc o) {
        ContentValues cv = new ContentValues();
        cv.put(NAME, o.getName());
        cv.put(DATE, o.getDate());
        cv.put(CHUYENNGANH, o.getChuyenNganh());
        cv.put(ACTIVE, o.isActive());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("HocOnline", null, cv);
    }

    public int update(KhoaHoc o) {
        ContentValues v = new ContentValues();
        v.put("name", o.getName());
        v.put("date", o.getDate());
        v.put("chuyenNganh", o.getChuyenNganh());
        v.put("active", o.isActive());
        SQLiteDatabase st = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(o.getId())};
        return st.update("HocOnline", v, whereClause, whereArgs);
    }

    public int delete(String id) {
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id)};
        SQLiteDatabase st = getWritableDatabase();
        return st.delete("HocOnline", whereClause, whereArgs);
    }

    public List<KhoaHoc> getAll() {
        List<KhoaHoc> list = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor rs = statement.query("HocOnline", null,
                null, null,
                null, null, null);
        while ((rs != null) && rs.moveToNext()) {
            String id = rs.getString(0);
            String name = rs.getString(1);
            String date = rs.getString(2);
            String chuyenNganh = rs.getString(3);
            String active = rs.getString(4);

            KhoaHoc o = new KhoaHoc(id, name, date, chuyenNganh, active);
            list.add(o);
        }
        return list;
    }

    public List<KhoaHoc> getByName(String name) {
        String oldName = name;
        String whereClause = "name like ?";
        String[] args = { "%" + name + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("HocOnline", null, whereClause, args, null, null, null);
        List<KhoaHoc> orders = new ArrayList<>();
        while(cursor.moveToNext()) {
            String id = cursor.getString(0);
//            String name = cursor.getString(1);
            String date = cursor.getString(2);
            String chuyenNganh = cursor.getString(3);
            String active = cursor.getString(4);
            KhoaHoc order = new KhoaHoc(id, oldName, date, chuyenNganh, active);
            orders.add(order);
        }
        return orders;
    }
}
