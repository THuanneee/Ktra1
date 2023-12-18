package com.levi.employeemanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.levi.employeemanager.models.DepartmentModel;
import com.levi.employeemanager.models.EmployeeModel;

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

    // CRUD operations for Employee
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

    // CRUD operations for Department
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

    // Additional methods for querying and fetching data as needed
}
