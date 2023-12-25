package com.stu.employeemanager.activity;

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

import com.stu.employeemanager.R;
import com.stu.employeemanager.models.DepartmentModel;

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


        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DepartmentDetailActivity.this, CreateDepartmentActivity.class);
                startActivity(intent);
            }
        });


        buttonEditDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DepartmentModel department = (DepartmentModel) getIntent().getSerializableExtra("department");


                if (department != null) {

                    Intent intent = new Intent(DepartmentDetailActivity.this, EditDepartmentActivity.class);
                    intent.putExtra("department", department);
                    startActivity(intent);
                } else {

                }
            }
        });


        loadDepartmentDetails();
    }



    private void loadDepartmentDetails() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            DepartmentModel department = (DepartmentModel) bundle.getSerializable("department");


            if (department != null) {
                textViewDepartmentId.setText("ID: " + department.getId());
                textViewDepartmentName.setText("Tên Phòng Ban: " + department.getName());

            } else {

            }
        } else {

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
