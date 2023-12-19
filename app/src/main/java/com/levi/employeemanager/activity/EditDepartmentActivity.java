package com.levi.employeemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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
}
