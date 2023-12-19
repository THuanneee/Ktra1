package com.levi.employeemanager.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.levi.employeemanager.R;
import com.levi.employeemanager.models.EmployeeModel;
import com.squareup.picasso.Picasso;

import java.io.File;

public class EmployeeDetailActivity extends AppCompatActivity {

    private TextView textViewEmployeeId;
    private TextView textViewEmployeeName;
    private TextView textViewEmployeeDepartment;
    private TextView textViewEmployeeImage;
    private TextView textViewEmployeeSdt;
    private TextView textViewEmployeeEmail;
    private Button buttonAddEmployee;
    private Button buttonEditEmployee;

    private ImageView imageViewEmployee;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        textViewEmployeeId = findViewById(R.id.textViewEmployeeId);
        textViewEmployeeName = findViewById(R.id.textViewEmployeeName);
        textViewEmployeeDepartment = findViewById(R.id.textViewEmployeeDepartment);
        textViewEmployeeImage = findViewById(R.id.textViewEmployeeImage);
        textViewEmployeeSdt = findViewById(R.id.textViewEmployeeSdt);
        textViewEmployeeEmail = findViewById(R.id.textViewEmployeeEmail);
        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
        buttonEditEmployee = findViewById(R.id.buttonEditEmployee);
        imageViewEmployee  = findViewById(R.id.imageViewEmployeeDetail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set click listener for "Thêm Nhân Viên" button
        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeDetailActivity.this, CreateEmployeeActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for "Sửa Thông Tin Nhân Viên" button
        buttonEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin nhân viên từ intent
                EmployeeModel employee = (EmployeeModel) getIntent().getSerializableExtra("employee");

                // Kiểm tra null trước khi mở màn hình chỉnh sửa
                if (employee != null) {
                    // Mở EditEmployeeActivity và truyền dữ liệu nhân viên
                    Intent intent = new Intent(EmployeeDetailActivity.this, EditEmployeeActivity.class);
                    intent.putExtra("employee", employee);
                    startActivity(intent);
                } else {
                    // Xử lý trường hợp employee là null
                }
            }
        });


        // Load employee details from intent or other source
        loadEmployeeDetails();
    }

    private void loadEmployeeDetails() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            EmployeeModel employee = (EmployeeModel) bundle.getSerializable("employee");

            // Kiểm tra null trước khi sử dụng dữ liệu
            if (employee != null) {
                textViewEmployeeId.setText("Mã Nhân Viên: " + employee.getId());
                textViewEmployeeName.setText("Tên Nhân Viên: " + employee.getName());
                textViewEmployeeDepartment.setText("Phòng Ban: " + employee.getDepartmentId());
                textViewEmployeeSdt.setText("Số Điện Thoại: " + employee.getSdt());
                textViewEmployeeEmail.setText("Email: " + employee.getEmail());

                setImageFromByteArray(employee.getImage(), imageViewEmployee);



            } else {
                // Xử lý trường hợp employee là null
            }
        } else {
            // Xử lý trường hợp bundle là null
        }


    }
    public static void setImageFromByteArray(byte[] imageData, ImageView imageView) {
        if (imageData != null && imageData.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            imageView.setImageBitmap(bitmap);
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
