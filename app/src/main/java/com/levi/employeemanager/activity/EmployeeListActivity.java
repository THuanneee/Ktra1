package com.levi.employeemanager.activity;

import static com.levi.employeemanager.activity.CreateEmployeeActivity.convertImageToByteArray;
import static com.levi.employeemanager.activity.EmployeeDetailActivity.setImageFromByteArray;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.levi.employeemanager.R;
import com.levi.employeemanager.database.DataManager;
import com.levi.employeemanager.models.EmployeeModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;
import androidx.appcompat.widget.Toolbar;


public class EmployeeListActivity extends AppCompatActivity {
    private static final int CREATE_EMPLOYEE_REQUEST_CODE = 1;


    private List<EmployeeModel> employeeList; // Replace with your actual data source
    private ArrayAdapter<EmployeeModel> adapter;
    ListView listViewEmployees;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        listViewEmployees  = findViewById(R.id.listViewEmployees);
        Button buttonCreateEmployee = findViewById(R.id.buttonCreateEmployee);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


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
                setImageFromByteArray(employee.getImage(), imageViewEmployee);
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
                startActivityForResult(intent, CREATE_EMPLOYEE_REQUEST_CODE);
            }
        });

        // Load your actual employee data into the employeeList
        // ...

        // Notify the adapter that the data has changed
        adapter.notifyDataSetChanged();
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor == null) {
            return contentUri.getPath(); // Trả về đường dẫn gốc nếu không thể truy cập thông tin từ MediaStore
        } else {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_EMPLOYEE_REQUEST_CODE && resultCode == RESULT_OK) {
            // Nếu kết quả là OK, cập nhật danh sách nhân viên
            updateEmployeeList();
        }
    }
    protected void onResume() {
        super.onResume();
        // Cập nhật danh sách nhân viên mỗi khi màn hình quay lại
        updateEmployeeList();
    }

    private void updateEmployeeList() {


        // Load the updated employee data from the database
        loadEmployeeData();

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

                setImageFromByteArray(employee.getImage(), imageViewEmployee);

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
