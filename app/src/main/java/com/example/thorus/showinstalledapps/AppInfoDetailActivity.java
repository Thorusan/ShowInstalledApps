package com.example.thorus.showinstalledapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a single AppInfoExtractor detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link AppListActivity}.
 */
public class AppInfoDetailActivity extends AppCompatActivity {
    @BindView(R.id.applabel)
    TextView appLabel;
    @BindView(R.id.package_name)
    TextView packageName;
    @BindView(R.id.version_name)
    TextView versionName;
    @BindView(R.id.version_code)
    TextView versionCode;
    @BindView(R.id.openAppBtn)
    Button openApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);*/
        // Bind views
        ButterKnife.bind(this);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setValues();

        // open App
        openApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().hasExtra("packageName")) {
                    String applicationPackageName = getIntent().getStringExtra("packageName");
                    Intent intent = getPackageManager().getLaunchIntentForPackage(applicationPackageName);
                    if (intent != null) {
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown.
            NavUtils.navigateUpTo(this, new Intent(this, AppListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Set Package info
     */
    private void setValues() {
        // App name
        if (getIntent().hasExtra("appName")) {
            String appName = getIntent().getStringExtra("appName");
            appLabel.setText(String.format("%s %s", getResources().getString(R.string.apkname), appName));
        }
        // package name
        if (getIntent().hasExtra("packageName")) {
            String strPackageName = getIntent().getStringExtra("packageName");
            packageName.setText(String.format("%s %s", getResources().getString(R.string.package_name), strPackageName));
        }
        // version name
        if (getIntent().hasExtra("versionName")) {
            String strVersionName = getIntent().getStringExtra("versionName");
            versionName.setText(String.format("%s %s", getResources().getString(R.string.version_name), strVersionName));
        }
        // version code
        if (getIntent().hasExtra("versionCode")) {
            int intVersionCode = getIntent().getIntExtra("versionCode", 0);
            versionCode.setText(String.format("%s %d", getResources().getString(R.string.version_code), intVersionCode));
        }
    }

}
