package com.stu.employeemanager.activity;

import static java.sql.DriverManager.println;
import static java.util.Locale.filter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.stu.employeemanager.R;
import com.stu.employeemanager.database.DataManager;
import com.stu.employeemanager.models.DepartmentModel;

import java.util.ArrayList;
import java.util.List;

public class DepartmentListActivity extends AppCompatActivity {

    private List<DepartmentModel> departmentList;
    private ArrayAdapter<DepartmentModel> adapter;
    ListView listViewDepartments;
    private EditText editTextSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_list);

        Button buttonCreateEmployee = findViewById(R.id.buttonCreateDepartment);

        editTextSearch = findViewById(R.id.editTextSearch);

        listViewDepartments = findViewById(R.id.listViewDepartments);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No action needed
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        loadDepartmentData();


        adapter = new ArrayAdapter<DepartmentModel>(this, R.layout.list_item_department, departmentList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.list_item_department, parent, false);
                }


                DepartmentModel department = getItem(position);

                TextView textViewDepartmentId = convertView.findViewById(R.id.textViewDepartmentId);
                TextView textViewDepartmentName = convertView.findViewById(R.id.textViewDepartmentName);
                Button buttonDeleteDepartment = convertView.findViewById(R.id.buttonDeleteDepartment);


                textViewDepartmentId.setText("ID: " + department.getId());
                textViewDepartmentName.setText("Tên đơn vị: " + department.getName());


                buttonDeleteDepartment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DepartmentModel departmentModel = getItem(position);


                        DataManager dataManager = new DataManager(DepartmentListActivity.this);
                        dataManager.open();
                        dataManager.deleteDepartment(departmentModel.getId());
                        dataManager.close();


                        remove(departmentModel);
                        notifyDataSetChanged();


                    }
                });

                return convertView;
            }
        };


        listViewDepartments.setAdapter(adapter);


        listViewDepartments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DepartmentModel selectedDepartment = departmentList.get(position);

                Log.d("ListViewClick", "Item clicked at position: " + position);


                Intent intent = new Intent(DepartmentListActivity.this, DepartmentDetailActivity.class);


                Bundle bundle = new Bundle();
                bundle.putSerializable("department", selectedDepartment);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        buttonCreateEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DepartmentListActivity.this, CreateDepartmentActivity.class);
                startActivity(intent);
            }
        });


        adapter.notifyDataSetChanged();


    }

    protected void onResume() {
        super.onResume();

        updateDepartmentData();
    }

    private void updateDepartmentData() {


        loadDepartmentData();

        adapter = new ArrayAdapter<DepartmentModel>(this, R.layout.list_item_department, departmentList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.list_item_department, parent, false);
                }


                DepartmentModel department = getItem(position);

                TextView textViewDepartmentId = convertView.findViewById(R.id.textViewDepartmentId);
                TextView textViewDepartmentName = convertView.findViewById(R.id.textViewDepartmentName);
                Button buttonDeleteDepartment = convertView.findViewById(R.id.buttonDeleteDepartment);


                textViewDepartmentId.setText("ID: " + department.getId());
                textViewDepartmentName.setText("Tên đơn vị: " + department.getName());


                buttonDeleteDepartment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DepartmentModel departmentModel = getItem(position);


                        DataManager dataManager = new DataManager(DepartmentListActivity.this);
                        dataManager.open();
                        dataManager.deleteDepartment(departmentModel.getId());
                        dataManager.close();


                        remove(departmentModel);
                        notifyDataSetChanged();


                    }
                });

                return convertView;
            }
        };


        listViewDepartments.setAdapter(adapter);


        adapter.notifyDataSetChanged();
    }

    private void loadDepartmentData() {

        DataManager dataManager = new DataManager(this);
        dataManager.open();


        departmentList = dataManager.getAllDepartments();


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

            Intent intent = new Intent(DepartmentListActivity.this, HomeActivity.class);
            startActivity(intent);
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

    private void filter(String text) {
        if (text.isEmpty()) {
            updateDepartmentData();
            return;
        }
        Log.d("filter", "levi -filter: " + text);

        List<DepartmentModel> a = new ArrayList<>();
        for (DepartmentModel item : departmentList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                a.add(item);
            }
        }


        departmentList = a;
        adapter = new ArrayAdapter<DepartmentModel>(this, R.layout.list_item_department, departmentList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.list_item_department, parent, false);
                }


                DepartmentModel department = getItem(position);

                TextView textViewDepartmentId = convertView.findViewById(R.id.textViewDepartmentId);
                TextView textViewDepartmentName = convertView.findViewById(R.id.textViewDepartmentName);
                Button buttonDeleteDepartment = convertView.findViewById(R.id.buttonDeleteDepartment);


                textViewDepartmentId.setText("ID: " + department.getId());
                textViewDepartmentName.setText("Tên đơn vị: " + department.getName());


                buttonDeleteDepartment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DepartmentModel departmentModel = getItem(position);


                        DataManager dataManager = new DataManager(DepartmentListActivity.this);
                        dataManager.open();
                        dataManager.deleteDepartment(departmentModel.getId());
                        dataManager.close();


                        remove(departmentModel);
                        notifyDataSetChanged();


                    }
                });

                return convertView;
            }
        };


        listViewDepartments.setAdapter(adapter);


        adapter.notifyDataSetChanged();
    }
}
