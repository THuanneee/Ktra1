package com.levi.employeemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.levi.employeemanager.R;


public class HomeActivity extends AppCompatActivity {

    private Button buttonShowEmployeeList;
    private Button buttonShowDepartmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonShowEmployeeList = findViewById(R.id.buttonShowEmployeeList);
        buttonShowDepartmentList = findViewById(R.id.buttonShowDepartmentList);

        // Set click listener for "Hiển Thị Danh Sách Nhân Viên" button
        buttonShowEmployeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic to navigate to the EmployeeListActivity or other relevant screen
                Intent intent = new Intent(HomeActivity.this, EmployeeListActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for "Hiển Thị Danh Sách Phòng Ban" button
        buttonShowDepartmentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic to navigate to the DepartmentListActivity or other relevant screen
                Intent intent = new Intent(HomeActivity.this, DepartmentListActivity.class);
                startActivity(intent);
            }
        });
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

        if (itemId == R.id.menu_about) {
            // Add logic to handle "About" option
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        } else if (itemId == R.id.menu_exit) {
            // Add logic to handle "Exit" option
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
