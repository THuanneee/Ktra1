package com.levi.employeemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.levi.employeemanager.R;
import com.levi.employeemanager.models.DepartmentModel;
import com.levi.employeemanager.models.EmployeeModel;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set click listener for "Thêm Nhân Viên" button
        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic to navigate to the AddEmployeeActivity or other relevant screen
                Intent intent = new Intent(DepartmentDetailActivity.this, CreateDepartmentActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for "Sửa Thông Tin Phòng Ban" button
        buttonEditDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic to navigate to the EditDepartmentActivity or other relevant screen
//                Intent intent = new Intent(DepartmentDetailActivity.this, EditDepartmentActivity.class);
//                startActivity(intent);
                DepartmentModel department = (DepartmentModel) getIntent().getSerializableExtra("department");

                // Kiểm tra null trước khi mở màn hình chỉnh sửa
                if (department != null) {
                    // Mở EditEmployeeActivity và truyền dữ liệu nhân viên
                    Intent intent = new Intent(DepartmentDetailActivity.this, EditDepartmentActivity.class);
                    intent.putExtra("department", department);
                    startActivity(intent);
                } else {
                    // Xử lý trường hợp employee là null
                }
            }
        });

        // Load department details from intent or other source
        loadDepartmentDetails();
    }



    private void loadDepartmentDetails() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            DepartmentModel department = (DepartmentModel) bundle.getSerializable("department");

            // Kiểm tra null trước khi sử dụng dữ liệu
            if (department != null) {
                textViewDepartmentId.setText("ID: " + department.getId());
                textViewDepartmentName.setText("Tên Phòng Ban: " + department.getName());

            } else {
                // Xử lý trường hợp employee là null
            }
        } else {
            // Xử lý trường hợp bundle là null
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
            // Xử lý sự kiện khi nút quay lại được nhấn
            finish();
            return true;
        }
        else if (itemId == R.id.menu_about) {
            // Add logic to handle "About" option
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        } else if (itemId == R.id.menu_exit) {
            // Add logic to handle "Exit" option
            finishAffinity(); // Đóng
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
