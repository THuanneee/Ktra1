package com.levi.employeemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.levi.employeemanager.R; 
import com.levi.employeemanager.database.DataManager;
import com.levi.employeemanager.models.DepartmentModel;
import com.levi.employeemanager.models.EmployeeModel;

public class EditDepartmentActivity extends AppCompatActivity {

    private EditText editTextDepartmentName;
    private Button buttonSaveDepartmentChanges;

    private DepartmentModel department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_department);


        editTextDepartmentName = findViewById(R.id.editTextDepartmentName);
        buttonSaveDepartmentChanges = findViewById(R.id.buttonSaveDepartmentChanges);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        Intent intent = getIntent();
        if (intent.hasExtra("department")) {
            department = (DepartmentModel) intent.getSerializableExtra("department");


            editTextDepartmentName.setText(department.getName());
        }


        buttonSaveDepartmentChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveDepartmentChanges();
            }
        });
    }

    private void saveDepartmentChanges() {

        String newDepartmentName = editTextDepartmentName.getText().toString();

        department.setName(newDepartmentName);



        DataManager dataManager = new DataManager(EditDepartmentActivity.this);
        dataManager.open();
        dataManager.updateDepartment(department);
        dataManager.close();


        Intent intent = new Intent(EditDepartmentActivity.this, DepartmentListActivity.class);
        startActivity(intent);
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
        }
        else if (itemId == R.id.menu_about) {

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
