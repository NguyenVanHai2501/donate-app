package ie.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import ie.app.activities.Donate.Donate;
import ie.app.activities.Report.Report;
import ie.app.main.DonationApp;

public class Base extends AppCompatActivity {

    public DonationApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (DonationApp) this.getApplication();
        app.dbManager.open();
        app.dbManager.setTotalDonated(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void bluetooth(MenuItem item) {
        Toast.makeText(this, "Bluetooth selected", Toast.LENGTH_SHORT).show();
    }
    public void settings(MenuItem item)
    {
        Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
    }
    public void report(MenuItem item)
    {
        startActivity (new Intent(this, Report.class));
    }
    public void donate(MenuItem item)
    {
        startActivity (new Intent(this, Donate.class));
    }
    public void reset(MenuItem item)
    {
        app.totalDonated = 0;
        app.dbManager.reset();
        finish();
        overridePendingTransition(0, 0);
        startActivity(this.getIntent());
        overridePendingTransition(0, 0);
    }
    public void fabClick(View view) {
        Snackbar.make(view, "Replace with your own action",
                        Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
