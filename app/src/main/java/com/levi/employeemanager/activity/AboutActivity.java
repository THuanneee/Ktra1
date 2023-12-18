package com.levi.employeemanager.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.levi.employeemanager.R;

public class AboutActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView phoneTextView = findViewById(R.id.phoneTextView);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        phoneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add logic to initiate a phone call
                String phoneNumber = "tel:" + "YourPhoneNumber"; // Replace with your actual phone number
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
                startActivity(dialIntent);
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Đặt vị trí và marker tại Trường ĐH Công nghệ Sài Gòn
        LatLng universityLatLng = new LatLng(10.768914, 106.682142); // Thay thế với tọa độ thực tế
        googleMap.addMarker(new MarkerOptions().position(universityLatLng).title("Trường ĐH Công nghệ Sài Gòn"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(universityLatLng, 15)); // Thay đổi level zoom tùy ý
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
