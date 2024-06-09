package com.stu.employeemanager.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.stu.employeemanager.models.DepartmentModel;
import com.stu.employeemanager.models.EmployeeModel;

import java.util.ArrayList;
import java.util.List;


public class DataManager {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DataManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<EmployeeModel> getAllEmployees() {
        List<EmployeeModel> employeeList = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_EMPLOYEE,
                null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                @SuppressLint("Range") String departmentId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DEPARTMENT_ID));
                @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE));
                @SuppressLint("Range") String sdt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SDT));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMAIL));
                @SuppressLint("Range") String chucvu = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_chucvu));

                EmployeeModel employee = new EmployeeModel(id, name, departmentId, image, sdt, email, chucvu);
                employeeList.add(employee);
            }

            cursor.close();
        }

        return employeeList;
    }

    public List<DepartmentModel> getAllDepartments() {
        List<DepartmentModel> departmentList = new ArrayList<>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_DEPARTMENT,
                null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                @SuppressLint("Range") String COLUMN_email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_email));
                @SuppressLint("Range") String COLUMN_web = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_web));
                @SuppressLint("Range") String COLUMN_logo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_logo));
                @SuppressLint("Range") String COLUMN_diachi = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_diachi));
                @SuppressLint("Range") String COLUMN_sdt = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_sdt));
                @SuppressLint("Range") String COLUMN_madonvicha = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_madonvicha));


                DepartmentModel department = new DepartmentModel(id, name, COLUMN_email, COLUMN_web, "", COLUMN_diachi, COLUMN_sdt, COLUMN_madonvicha);
                departmentList.add(department);
            }

            cursor.close();
        }

        return departmentList;
    }


    public long addEmployee(EmployeeModel employee) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, employee.getName());
        values.put(DatabaseHelper.COLUMN_DEPARTMENT_ID, employee.getDepartmentId());
        values.put(DatabaseHelper.COLUMN_IMAGE, employee.getImage());
        values.put(DatabaseHelper.COLUMN_SDT, employee.getSdt());
        values.put(DatabaseHelper.COLUMN_EMAIL, employee.getEmail());

        return database.insert(DatabaseHelper.TABLE_EMPLOYEE, null, values);
    }

    public int updateEmployee(EmployeeModel employee) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, employee.getName());
        values.put(DatabaseHelper.COLUMN_DEPARTMENT_ID, employee.getDepartmentId());
        values.put(DatabaseHelper.COLUMN_IMAGE, employee.getImage());
        values.put(DatabaseHelper.COLUMN_SDT, employee.getSdt());
        values.put(DatabaseHelper.COLUMN_EMAIL, employee.getEmail());

        return database.update(DatabaseHelper.TABLE_EMPLOYEE, values,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{employee.getId()});
    }

    public void deleteEmployee(String employeeId) {
        database.delete(DatabaseHelper.TABLE_EMPLOYEE,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{employeeId});
    }


    public long addDepartment(DepartmentModel department) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, department.getName());

        return database.insert(DatabaseHelper.TABLE_DEPARTMENT, null, values);
    }

    public int updateDepartment(DepartmentModel department) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, department.getName());

        return database.update(DatabaseHelper.TABLE_DEPARTMENT, values,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{department.getId()});
    }

    public void deleteDepartment(String departmentId) {
        database.delete(DatabaseHelper.TABLE_DEPARTMENT,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{departmentId});
    }


}
