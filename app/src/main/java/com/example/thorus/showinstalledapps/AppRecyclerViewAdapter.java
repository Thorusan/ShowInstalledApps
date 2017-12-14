package com.example.thorus.showinstalledapps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thorus.showinstalledapps.data.AppData;
import com.example.thorus.showinstalledapps.data.AppInfoExtractor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AppRecyclerViewAdapter extends RecyclerView.Adapter<AppRecyclerViewAdapter.ViewHolder> {

    final int requestCodeDetail = 123;  // The request code

    private Activity activity;
    private AppInfoExtractor appInfoExtractor;
    private List<AppData> appPackageList;

    public AppRecyclerViewAdapter(Activity context, AppInfoExtractor appInfoExtractor) {
        this.activity = context;
        this.appInfoExtractor = appInfoExtractor;
        setList(appInfoExtractor.getInstalledAppList());
    }

    @Override
    public AppRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view2 = LayoutInflater.from(activity).inflate(R.layout.cardview_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view2);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        AppInfoExtractor apkInfoExtractor = new AppInfoExtractor(activity);

        final String applicationPackageName = appPackageList.get(position).getPackageName();
        Drawable drawable = apkInfoExtractor.getAppIconByPackageName(applicationPackageName);

        String applicationLabelName = appInfoExtractor.getAppName(applicationPackageName);

        viewHolder.textAppName.setText(applicationLabelName);
        viewHolder.textAppPackageName.setText(applicationPackageName);
        viewHolder.imageView.setImageDrawable(drawable);

        //Adding click listener on CardView to open clicked application directly from here .
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AppInfoDetailActivity.class);
                intent.putExtra("appName", appInfoExtractor.getAppName(applicationPackageName));
                intent.putExtra("packageName", applicationPackageName);
                intent.putExtra("versionName", appPackageList.get(position).getVersionName());
                intent.putExtra("versionCode", appPackageList.get(position).getVersionCode());

                activity.startActivityForResult(intent, requestCodeDetail);
            }
        });
    }

    /**
     * Set installed App List
     */
    public void setList(List<AppData> packageList) {
        this.appPackageList = appInfoExtractor.getInstalledAppList();
    }

    @Override
    public int getItemCount() {
        return appPackageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.imageview)
        ImageView imageView;
        @BindView(R.id.apk_name)
        TextView textAppName;
        @BindView(R.id.apk_package_name)
        TextView textAppPackageName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}