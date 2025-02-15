package com.stu.employeemanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "company.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_EMPLOYEE = "employee";
    public static final String TABLE_DEPARTMENT = "department";


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_SDT = "sdt";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_chucvu = "chucvu";


    public static final String COLUMN_email = "email";
    public static final String COLUMN_web = "web";
    public static final String COLUMN_logo = "logo";
    public static final String COLUMN_diachi = "diachi";
    public static final String COLUMN_sdt = "sdt";
    public static final String COLUMN_madonvicha = "madonvicha";


    public static final String COLUMN_DEPARTMENT_ID = "department_id";

    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_DEPARTMENT_ID + " TEXT,"
            + COLUMN_IMAGE + " BLOB,"
            + COLUMN_SDT + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_chucvu + " TEXT"
            + ")";

    private static final String CREATE_TABLE_DEPARTMENT = "CREATE TABLE " + TABLE_DEPARTMENT + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_email + " TEXT,"
            + COLUMN_web + " TEXT,"
            + COLUMN_logo + " TEXT,"
            + COLUMN_diachi + " TEXT,"
            + COLUMN_sdt + " TEXT,"
            + COLUMN_madonvicha + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EMPLOYEE);
        db.execSQL(CREATE_TABLE_DEPARTMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
