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
import com.levi.employeemanager.models.DepartmentModel;
import com.levi.employeemanager.models.EmployeeModel;

public class CreateDepartmentActivity extends AppCompatActivity {

    private EditText editTextDepartmentName;
    private Button buttonSaveDepartment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_department);

        editTextDepartmentName = findViewById(R.id.editTextDepartmentName);

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
        // Lấy dữ liệu từ các trường EditText
        String name = editTextDepartmentName.getText().toString();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Tạo một đối tượng EmployeeModel mới với dữ liệu đã nhập
        DepartmentModel newEmployee = new DepartmentModel(null, name);

        // Khởi tạo DataManager và mở cơ sở dữ liệu
        DataManager dataManager = new DataManager(this);
        dataManager.open();

        // Gọi phương thức thêm nhân viên vào cơ sở dữ liệu
        long result = dataManager.addDepartment(newEmployee);

        // Đóng cơ sở dữ liệu và kiểm tra kết quả
        dataManager.close();
        if (result != -1) {
            // Nếu thêm thành công, thực hiện các hành động khác nếu cần
            // ...
            Toast.makeText(this, "Thêm phòng ban thành công", Toast.LENGTH_SHORT).show();

            // Đóng activity sau khi lưu
            finish();
        } else {
            Toast.makeText(this, "Lỗi khi thêm phòng ban", Toast.LENGTH_SHORT).show();

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
