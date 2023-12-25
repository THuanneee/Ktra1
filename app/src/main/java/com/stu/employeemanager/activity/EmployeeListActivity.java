package com.stu.employeemanager.activity;

import static com.stu.employeemanager.activity.EmployeeDetailActivity.setImageFromByteArray;

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

import com.stu.employeemanager.R;
import com.stu.employeemanager.database.DataManager;
import com.stu.employeemanager.models.EmployeeModel;

import java.util.List;
import androidx.appcompat.widget.Toolbar;


public class EmployeeListActivity extends AppCompatActivity {
    private static final int CREATE_EMPLOYEE_REQUEST_CODE = 1;


    private List<EmployeeModel> employeeList; 
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


        loadEmployeeData();


        adapter = new ArrayAdapter<EmployeeModel>(this, R.layout.list_item_employee, employeeList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.list_item_employee, parent, false);
                }


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


                        DataManager dataManager = new DataManager(EmployeeListActivity.this);
                        dataManager.open();
                        dataManager.deleteEmployee(selectedEmployee.getId());
                        dataManager.close();


                        remove(selectedEmployee);
                        notifyDataSetChanged();
                    }
                });
                return convertView;
            }
        };


        listViewEmployees.setAdapter(adapter);


        listViewEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ListViewClick", "Item clicked at position: " + position);

                EmployeeModel selectedEmployee = employeeList.get(position);

                Intent intent = new Intent(EmployeeListActivity.this, EmployeeDetailActivity.class);


                Bundle bundle = new Bundle();
                bundle.putSerializable("employee", selectedEmployee);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        buttonCreateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EmployeeListActivity.this, CreateEmployeeActivity.class);
                startActivityForResult(intent, CREATE_EMPLOYEE_REQUEST_CODE);
            }
        });


        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_EMPLOYEE_REQUEST_CODE && resultCode == RESULT_OK) {

            updateEmployeeList();
        }
    }
    protected void onResume() {
        super.onResume();

        updateEmployeeList();
    }

    private void updateEmployeeList() {



        loadEmployeeData();

        adapter = new ArrayAdapter<EmployeeModel>(this, R.layout.list_item_employee, employeeList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.list_item_employee, parent, false);
                }


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


                        DataManager dataManager = new DataManager(EmployeeListActivity.this);
                        dataManager.open();
                        dataManager.deleteEmployee(selectedEmployee.getId());
                        dataManager.close();


                        remove(selectedEmployee);
                        notifyDataSetChanged();
                    }
                });
                return convertView;
            }
        };


        listViewEmployees.setAdapter(adapter);


        adapter.notifyDataSetChanged();
    }

    private void loadEmployeeData() {
        DataManager dataManager = new DataManager(this);
        dataManager.open();

        employeeList = dataManager.getAllEmployees();

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

            Intent intent = new Intent(EmployeeListActivity.this, HomeActivity.class);
            startActivity(intent);
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
