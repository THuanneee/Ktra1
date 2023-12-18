package com.levi.employeemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.levi.employeemanager.R;
import com.levi.employeemanager.models.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {

    private List<EmployeeModel> employeeList; // Replace with your actual data source
    private ArrayAdapter<EmployeeModel> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        ListView listViewEmployees = findViewById(R.id.listViewEmployees);
        Button buttonCreateEmployee = findViewById(R.id.buttonCreateEmployee);

        // Initialize your data source (employeeList) with actual data
        employeeList = new ArrayList<>();

        // Initialize the adapter with custom layout (list_item_employee)
        adapter = new ArrayAdapter<EmployeeModel>(this, R.layout.list_item_employee, employeeList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // Populate the item view with employee data
                EmployeeModel employee = getItem(position);

                ImageView imageViewEmployee = view.findViewById(R.id.imageViewEmployee);
                TextView textViewEmployeeCode = view.findViewById(R.id.textViewEmployeeCode);
                TextView textViewEmployeeName = view.findViewById(R.id.textViewEmployeeName);
                TextView textViewEmployeeDepartment = view.findViewById(R.id.textViewEmployeeClassification);

                // Set the data for each view
                // (Assuming you have appropriate methods in EmployeeModel to get the data)
                // Example:
                // imageViewEmployee.setImageResource(employee.getImage());
                textViewEmployeeCode.setText("Mã: " + employee.getId());
                textViewEmployeeName.setText("Tên: " + employee.getName());
                textViewEmployeeDepartment.setText("Phòng ban: " + employee.getDepartmentId());

                return view;
            }
        };

        // Set the adapter to the ListView
        listViewEmployees.setAdapter(adapter);

        // Set click listener for ListView items
        listViewEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click (e.g., show details)
                EmployeeModel selectedEmployee = employeeList.get(position);
                // Add your logic here
            }
        });

        // Set click listener for "Tạo Nhân Viên" button
        buttonCreateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateEmployeeActivity to create a new employee
//                Intent intent = new Intent(EmployeeListActivity.this, CreateEmployeeActivity.class);
//                startActivity(intent);
            }
        });

        // Load your actual employee data into the employeeList
        // ...

        // Notify the adapter that the data has changed
        adapter.notifyDataSetChanged();
    }
}
