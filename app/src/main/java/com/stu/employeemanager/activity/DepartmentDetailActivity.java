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

    private TextView textViewDepartmentmadonvicha;
    private TextView textViewDepartmentweb;
    private TextView textViewDepartmentsdt;
    private TextView textViewDepartmentdiachi;
    private TextView textViewDepartmentemail;


    private Button buttonAddEmployee;
    private Button buttonEditDepartment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_detail);

        textViewDepartmentId = findViewById(R.id.textViewDepartmentId);
        textViewDepartmentName = findViewById(R.id.textViewDepartmentName);
        textViewDepartmentemail = findViewById(R.id.textViewDepartmentemail);
        textViewDepartmentdiachi = findViewById(R.id.textViewDepartmentdiachi);
        textViewDepartmentsdt = findViewById(R.id.textViewDepartmentsdt);
        textViewDepartmentweb = findViewById(R.id.textViewDepartmentweb);
        textViewDepartmentmadonvicha = findViewById(R.id.textViewDepartmentmadonvicha);

        buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
//        buttonEditDepartment = findViewById(R.id.buttonEditDepartment);

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
                textViewDepartmentName.setText("Tên đơn vị: " + department.getName());
                textViewDepartmentName.setText("email: " + department.getEmail());
                textViewDepartmentName.setText("web: " + department.getWeb());
                textViewDepartmentName.setText("địa chỉ: " + department.getDiachi());
                textViewDepartmentName.setText("sdt: " + department.getSdt());
                textViewDepartmentName.setText("mã đơn vị cha: " + department.getMadonvicha());

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
