package com.levi.employeemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.levi.employeemanager.R;
import com.levi.employeemanager.database.DataManager;
import com.levi.employeemanager.models.DepartmentModel;
import com.levi.employeemanager.models.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

public class DepartmentListActivity extends AppCompatActivity {

    private List<DepartmentModel> departmentList; // Replace with your actual data source
    private ArrayAdapter<DepartmentModel> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_list);

        Button buttonCreateEmployee = findViewById(R.id.buttonCreateDepartment);

        ListView listViewDepartments = findViewById(R.id.listViewDepartments);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // Initialize your data source (departmentList) with actual data
        loadDepartmentData();

        // Initialize the adapter with custom layout (list_item_department)
        adapter = new ArrayAdapter<DepartmentModel>(this, R.layout.list_item_department, departmentList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.list_item_department, parent, false);
                }

                // Populate the item view with department data
                DepartmentModel department = getItem(position);

                TextView textViewDepartmentId = convertView.findViewById(R.id.textViewDepartmentId);
                TextView textViewDepartmentName = convertView.findViewById(R.id.textViewDepartmentName);
                Button buttonDeleteDepartment = convertView.findViewById(R.id.buttonDeleteDepartment);

                // Set the data for each view
                textViewDepartmentId.setText("ID: " + department.getId());
                textViewDepartmentName.setText("Tên Phòng Ban: " + department.getName());

                // Set click listener for "Xóa" button
                buttonDeleteDepartment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DepartmentModel departmentModel = getItem(position);

                        // Delete the employee from the database
                        DataManager dataManager = new DataManager(DepartmentListActivity.this);
                        dataManager.open();
                        dataManager.deleteDepartment(departmentModel.getId());
                        dataManager.close();

                        // Remove the employee from the list and refresh the adapter
                        remove(departmentModel);
                        notifyDataSetChanged();
                        // Add logic to delete the department
                        // ...

                        // Remove the department from the list


                    }
                });

                return convertView;
            }
        };

        // Set the adapter to the ListView
        listViewDepartments.setAdapter(adapter);

        // Set click listener for ListView items
        listViewDepartments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click (e.g., show details)
                DepartmentModel selectedDepartment = departmentList.get(position);
                // Add your logic her
                Log.d("ListViewClick", "Item clicked at position: " + position);

                // Open EmployeeDetailActivity
                Intent intent = new Intent(DepartmentListActivity.this, DepartmentDetailActivity.class);

                // Sử dụng Bundle để truyền dữ liệu
                Bundle bundle = new Bundle();
                bundle.putSerializable("department", selectedDepartment);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        // Load your actual department data into the departmentList
        // ...

        buttonCreateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CreateEmployeeActivity to create a new employee
                Intent intent = new Intent(DepartmentListActivity.this, CreateDepartmentActivity.class);
                startActivity(intent);
            }
        });

        // Notify the adapter that the data has changed
        adapter.notifyDataSetChanged();


    }

    private void loadDepartmentData() {
        // Initialize DataManager and open the database
        DataManager dataManager = new DataManager(this);
        dataManager.open();

        // Load employee data from the database
        departmentList = dataManager.getAllDepartments();

        // Close the database
        dataManager.close();
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
