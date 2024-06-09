package com.stu.employeemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.stu.employeemanager.R;
import com.stu.employeemanager.database.DataManager;
import com.stu.employeemanager.models.DepartmentModel;

public class CreateDepartmentActivity extends AppCompatActivity {

    private EditText editTextDepartmentName;
    private EditText editTextDepartmentEmail;
    private EditText editTextDepartmentDiachi;
    private EditText editTextDepartmentsdt;
    private EditText editTextDepartmentWeb;
    private EditText editTextDepartmentmadonvicha;

    private Button buttonSaveDepartment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_department);

        editTextDepartmentName = findViewById(R.id.editTextDepartmentName);
        editTextDepartmentEmail = findViewById(R.id.editTextDepartmentEmail);
        editTextDepartmentDiachi = findViewById(R.id.editTextDepartmentDiachi);
        editTextDepartmentsdt = findViewById(R.id.editTextDepartmentsdt);
        editTextDepartmentWeb = findViewById(R.id.editTextDepartmentWeb);
        editTextDepartmentmadonvicha = findViewById(R.id.editTextDepartmentmadonvicha);


        buttonSaveDepartment = findViewById(R.id.buttonSaveDepartment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        buttonSaveDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDepartment();
            }
        });
    }

    private void saveDepartment() {


        String name = editTextDepartmentName.getText().toString();
        String name1 = editTextDepartmentEmail.getText().toString();
        String name2 = editTextDepartmentDiachi.getText().toString();
        String name3 = editTextDepartmentsdt.getText().toString();
        String name4 = editTextDepartmentWeb.getText().toString();
        String name5 = editTextDepartmentmadonvicha.getText().toString();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        DepartmentModel newEmployee = new DepartmentModel(null, name, name1, name4, "", name2, name3, name5);


        DataManager dataManager = new DataManager(this);
        dataManager.open();


        long result = dataManager.addDepartment(newEmployee);


        dataManager.close();
        if (result != -1) {

            Toast.makeText(this, "Thêm đơn vị thành công", Toast.LENGTH_SHORT).show();


            finish();
        } else {
            Toast.makeText(this, "Lỗi khi thêm đơn vị", Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {

            finish();
            return true;
        } else if (itemId == R.id.menu_about) {

            startActivity(new Intent(this, AboutActivity.class));
            return true;
        } else if (itemId == R.id.menu_exit) {

            finishAffinity();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
