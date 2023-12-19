package com.levi.employeemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.levi.employeemanager.R;  // Chắc chắn rằng đường dẫn là chính xác
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

        // Ánh xạ các thành phần giao diện
        editTextDepartmentName = findViewById(R.id.editTextDepartmentName);
        buttonSaveDepartmentChanges = findViewById(R.id.buttonSaveDepartmentChanges);

        // Lấy dữ liệu từ Intent hoặc Database và hiển thị lên giao diện
        Intent intent = getIntent();
        if (intent.hasExtra("department")) {
            department = (DepartmentModel) intent.getSerializableExtra("department");

            // Hiển thị thông tin hiện tại của nhân viên trên giao diện
            editTextDepartmentName.setText(department.getName());
        }

        // Xử lý sự kiện khi nhấn nút "Lưu Thay Đổi"
        buttonSaveDepartmentChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý logic lưu thay đổi thông tin phòng ban
                saveDepartmentChanges();
            }
        });
    }

    private void saveDepartmentChanges() {
        // Lấy dữ liệu từ các trường EditText
        String newDepartmentName = editTextDepartmentName.getText().toString();

        department.setName(newDepartmentName);


        // Cập nhật vào cơ sở dữ liệu
        DataManager dataManager = new DataManager(EditDepartmentActivity.this);
        dataManager.open();
        dataManager.updateDepartment(department);
        dataManager.close();

        // Trở về màn hình trước đó
        Intent intent = new Intent(EditDepartmentActivity.this, DepartmentListActivity.class);
        startActivity(intent);
    }
}
