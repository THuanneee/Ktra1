package com.levi.employeemanager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.levi.employeemanager.R;
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

        buttonSaveEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmployee();
            }
        });
    }

    private void saveEmployee() {
        // Get the data from the EditText fields
        String name = editTextEmployeeName.getText().toString();
        String departmentId = editTextDepartmentId.getText().toString();
        String image = editTextImage.getText().toString();
        String sdt = editTextSdt.getText().toString();
        String email = editTextEmail.getText().toString();

        // Create a new EmployeeModel instance with the entered data
        EmployeeModel newEmployee = new EmployeeModel(null, name, departmentId, image, sdt, email);

        // Add your logic to save the new employee to the database or perform other actions
        // ...

        // Close the activity after saving
        finish();
    }
}
