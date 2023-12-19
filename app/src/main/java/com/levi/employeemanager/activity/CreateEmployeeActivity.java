package com.levi.employeemanager.activity;

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

import com.levi.employeemanager.R;
import com.levi.employeemanager.database.DataManager;
import com.levi.employeemanager.models.EmployeeModel;

public class CreateEmployeeActivity extends AppCompatActivity {

    private EditText editTextEmployeeName;
    private EditText editTextDepartmentId;
    private EditText editTextImage;
    private EditText editTextSdt;
    private EditText editTextEmail;
    private Button buttonSaveEmployee;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);

        editTextEmployeeName = findViewById(R.id.editTextEmployeeName);
        editTextDepartmentId = findViewById(R.id.editTextDepartmentId);
        editTextImage = findViewById(R.id.editTextImage);
        editTextSdt = findViewById(R.id.editTextSdt);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSaveEmployee = findViewById(R.id.buttonSaveEmployee);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        buttonSaveEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmployee();
            }
        });
    }

    private void saveEmployee() {
        // Lấy dữ liệu từ các trường EditText
        String name = editTextEmployeeName.getText().toString();
        String departmentId = editTextDepartmentId.getText().toString();
        String image = editTextImage.getText().toString();
        String sdt = editTextSdt.getText().toString();
        String email = editTextEmail.getText().toString();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Tạo một đối tượng EmployeeModel mới với dữ liệu đã nhập
        EmployeeModel newEmployee = new EmployeeModel(null, name, departmentId, image, sdt, email);

        // Khởi tạo DataManager và mở cơ sở dữ liệu
        DataManager dataManager = new DataManager(this);
        dataManager.open();

        // Gọi phương thức thêm nhân viên vào cơ sở dữ liệu
        long result = dataManager.addEmployee(newEmployee);

        // Đóng cơ sở dữ liệu và kiểm tra kết quả
        dataManager.close();
        if (result != -1) {
            // Nếu thêm thành công, thực hiện các hành động khác nếu cần
            // ...
            Toast.makeText(this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            // Đóng activity sau khi lưu
            finish();
        } else {
            Toast.makeText(this, "Lỗi khi thêm nhân viên", Toast.LENGTH_SHORT).show();

            // Hiển thị thông báo lỗi hoặc log lỗi nếu cần
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
