package com.stu.employeemanager.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

import com.stu.employeemanager.R;
import com.stu.employeemanager.models.EmployeeModel;

public class EmployeeDetailActivity extends AppCompatActivity {

    private TextView textViewEmployeeId;
    private TextView textViewEmployeeName;
    private TextView textViewEmployeeDepartment;
    private TextView textViewEmployeeImage;

    private TextView textViewEmployeeChucvu;


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
        textViewEmployeeChucvu = findViewById(R.id.textViewEmployeeChucvu);
        textViewEmployeeEmail = findViewById(R.id.textViewEmployeeEmail);
        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
//        buttonEditEmployee = findViewById(R.id.buttonEditEmployee);
        imageViewEmployee = findViewById(R.id.imageViewEmployeeDetail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeDetailActivity.this, CreateEmployeeActivity.class);
                startActivity(intent);
            }
        });


        buttonEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EmployeeModel employee = (EmployeeModel) getIntent().getSerializableExtra("employee");


                if (employee != null) {

                    Intent intent = new Intent(EmployeeDetailActivity.this, EditEmployeeActivity.class);
                    intent.putExtra("employee", employee);
                    startActivity(intent);
                } else {

                }
            }
        });


        loadEmployeeDetails();
    }

    private void loadEmployeeDetails() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            EmployeeModel employee = (EmployeeModel) bundle.getSerializable("employee");


            if (employee != null) {
                textViewEmployeeId.setText("Mã Nhân Viên: " + employee.getId());
                textViewEmployeeName.setText("Tên Nhân Viên: " + employee.getName());
                textViewEmployeeDepartment.setText("đơn vị: " + employee.getDepartmentId());
                textViewEmployeeSdt.setText("Số Điện Thoại: " + employee.getSdt());
                textViewEmployeeSdt.setText("Chức vụ: " + employee.getChucvu());
                textViewEmployeeEmail.setText("Email: " + employee.getEmail());


                setImageFromByteArray(employee.getImage(), imageViewEmployee);


            } else {

            }
        } else {

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
