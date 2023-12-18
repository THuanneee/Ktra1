package com.levi.employeemanager.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.levi.employeemanager.R;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView phoneTextView = findViewById(R.id.phoneTextView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        MapView mapView = findViewById(R.id.mapView);
        IMapController mapController = mapView.getController();
        GeoPoint startPoint = new GeoPoint(10.738069280747185, 106.67780687036539); // Replace with your desired coordinates
        mapController.setCenter(startPoint);
        mapController.setZoom(18);
        addMarker(mapView, startPoint, "Marker Title", "Marker Description");

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

            return true;
        } else if (itemId == R.id.menu_exit) {
            // Add logic to handle "Exit" option
            finishAffinity(); // Đóng
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

private void addMarker(MapView mapView, GeoPoint position, String title, String snippet) {
        // Create a list to hold the OverlayItems
        ArrayList<OverlayItem> items = new ArrayList<>();
        OverlayItem marker = new OverlayItem(title, snippet, position);
        items.add(marker);

        // Create an ItemizedIconOverlay to display the marker
        ItemizedIconOverlay<OverlayItem> overlay = new ItemizedIconOverlay<>(items,
                getResources().getDrawable(android.R.drawable.star_big_on), null, mapView.getContext());

        // Add the overlay to the map
        mapView.getOverlays().add(overlay);
    }

}
