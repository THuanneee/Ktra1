package com.levi.employeemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.levi.employeemanager.database.DataManager;
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
        loadEmployeeData();

        // Initialize the adapter with custom layout (list_item_employee)
        adapter = new ArrayAdapter<EmployeeModel>(this, R.layout.list_item_employee, employeeList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.list_item_employee, parent, false);
                }

                // Populate the item view with employee data
                EmployeeModel employee = getItem(position);

                ImageView imageViewEmployee = convertView.findViewById(R.id.imageViewEmployee);
                TextView textViewEmployeeCode = convertView.findViewById(R.id.textViewEmployeeCode);
                TextView textViewEmployeeName = convertView.findViewById(R.id.textViewEmployeeName);
                TextView textViewEmployeeDepartment = convertView.findViewById(R.id.textViewEmployeeClassification);
                Button buttonDeleteEmployee = convertView.findViewById(R.id.buttonDeleteEmployee);


                // Set the data for each view
                // (Assuming you have appropriate methods in EmployeeModel to get the data)
                // Example:
                // imageViewEmployee.setImageResource(employee.getImage());
                textViewEmployeeCode.setText("Mã: " + employee.getId());
                textViewEmployeeName.setText("Tên: " + employee.getName());
                textViewEmployeeDepartment.setText("Phòng ban: " + employee.getDepartmentId());
                buttonDeleteEmployee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EmployeeModel selectedEmployee = getItem(position);

                        // Delete the employee from the database
                        DataManager dataManager = new DataManager(EmployeeListActivity.this);
                        dataManager.open();
                        dataManager.deleteEmployee(selectedEmployee.getId());
                        dataManager.close();

                        // Remove the employee from the list and refresh the adapter
                        remove(selectedEmployee);
                        notifyDataSetChanged();
                    }
                });
                return convertView;
            }
        };

        // Set the adapter to the ListView
        listViewEmployees.setAdapter(adapter);

        // Set click listener for ListView items
        listViewEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ListViewClick", "Item clicked at position: " + position);

                EmployeeModel selectedEmployee = employeeList.get(position);
                // Open EmployeeDetailActivity
                Intent intent = new Intent(EmployeeListActivity.this, EmployeeDetailActivity.class);

                // Sử dụng Bundle để truyền dữ liệu
                Bundle bundle = new Bundle();
                bundle.putSerializable("employee", selectedEmployee);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        // Set click listener for "Tạo Nhân Viên" button
        buttonCreateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateEmployeeActivity to create a new employee
                Intent intent = new Intent(EmployeeListActivity.this, CreateEmployeeActivity.class);
                startActivity(intent);
            }
        });

        // Load your actual employee data into the employeeList
        // ...

        // Notify the adapter that the data has changed
        adapter.notifyDataSetChanged();
    }

    private void loadEmployeeData() {
        // Initialize DataManager and open the database
        DataManager dataManager = new DataManager(this);
        dataManager.open();

        // Load employee data from the database
        employeeList = dataManager.getAllEmployees();

        // Close the database
        dataManager.close();
    }
}
