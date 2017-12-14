package com.example.thorus.showinstalledapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.thorus.showinstalledapps.data.AppInfoExtractor;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity representing a list of AppInfoExtractor.
 */
public class AppListActivity extends AppCompatActivity {

    @BindView(R.id.app_list)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    AppRecyclerViewAdapter appRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        // Bind views
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        AppInfoExtractor appInfoExtractor = new AppInfoExtractor(this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);

        if (appRecyclerViewAdapter == null) {
            appRecyclerViewAdapter = new AppRecyclerViewAdapter(this, appInfoExtractor);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(appRecyclerViewAdapter);
        } else {
            appRecyclerViewAdapter.setList(appInfoExtractor.getInstalledAppList());
            appRecyclerViewAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.activity_apk_list, menu);
        return true;*/
        return true;
    }
}
