package com.levi.employeemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.levi.employeemanager.R;
import com.levi.employeemanager.database.DataManager;
import com.levi.employeemanager.models.EmployeeModel;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CreateEmployeeActivity extends AppCompatActivity {

    private EditText editTextEmployeeName;
    private EditText editTextDepartmentId;
    private EditText editTextSdt;
    private EditText editTextEmail;
    private Button buttonSaveEmployee;

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageViewEmployee;
    private Uri imageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);

        editTextEmployeeName = findViewById(R.id.editTextEmployeeName);
        editTextDepartmentId = findViewById(R.id.editTextDepartmentId);
        editTextSdt = findViewById(R.id.editTextSdt);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSaveEmployee = findViewById(R.id.buttonSaveEmployee);

        imageViewEmployee = findViewById(R.id.imageViewEmployee);
        Button buttonPickImage = findViewById(R.id.buttonPickImage);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        buttonSaveEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmployee();
            }
        });

        buttonPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            Picasso.get().load(imageUri).into(imageViewEmployee);

        }
    }

    private void saveEmployee() {

        String name = editTextEmployeeName.getText().toString();
        String departmentId = editTextDepartmentId.getText().toString();

        String sdt = editTextSdt.getText().toString();
        String email = editTextEmail.getText().toString();
        byte[] imageData = convertImageToByteArray(imageUri, this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        EmployeeModel newEmployee = new EmployeeModel(null, name, departmentId, imageData, sdt, email);


        DataManager dataManager = new DataManager(this);
        dataManager.open();


        long result = dataManager.addEmployee(newEmployee);


        dataManager.close();
        if (result != -1) {


            Toast.makeText(this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);

            finish();
        } else {
            Toast.makeText(this, "Lỗi khi thêm nhân viên", Toast.LENGTH_SHORT).show();


        }
    }

    public static byte[] convertImageToByteArray(Uri imageUri, Activity activity) {
        try {
            InputStream inputStream = activity.getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            Log.e("ImageUtils", "Error converting image to byte array: " + e.getMessage());
            return null;
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
