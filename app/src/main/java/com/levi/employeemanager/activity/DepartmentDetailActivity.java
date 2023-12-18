package com.levi.employeemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.levi.employeemanager.R;

public class DepartmentDetailActivity extends AppCompatActivity {

    private TextView textViewDepartmentId;
    private TextView textViewDepartmentName;
    private Button buttonAddEmployee;
    private Button buttonEditDepartment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_detail);

        textViewDepartmentId = findViewById(R.id.textViewDepartmentId);
        textViewDepartmentName = findViewById(R.id.textViewDepartmentName);
        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
        buttonEditDepartment = findViewById(R.id.buttonEditDepartment);

        // Set click listener for "Thêm Nhân Viên" button
        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic to navigate to the AddEmployeeActivity or other relevant screen
//                Intent intent = new Intent(DepartmentDetailActivity.this, AddEmployeeActivity.class);
//                startActivity(intent);
            }
        });

        // Set click listener for "Sửa Thông Tin Phòng Ban" button
        buttonEditDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic to navigate to the EditDepartmentActivity or other relevant screen
//                Intent intent = new Intent(DepartmentDetailActivity.this, EditDepartmentActivity.class);
//                startActivity(intent);
            }
        });

        // Load department details from intent or other source
        loadDepartmentDetails();
    }

    private void loadDepartmentDetails() {
        // Get department details from the intent or another source
        // Example: Intent intent = getIntent();
        // DepartmentModel department = (DepartmentModel) intent.getSerializableExtra("department");

        // Update the TextViews with department details
        // Example:
        // textViewDepartmentId.setText("ID: " + department.getId());
        // textViewDepartmentName.setText("Tên Phòng Ban: " + department.getName());
    }
}
