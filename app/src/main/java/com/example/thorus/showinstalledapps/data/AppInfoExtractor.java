package com.example.thorus.showinstalledapps.data;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.thorus.showinstalledapps.R;

import java.util.ArrayList;
import java.util.List;

public class AppInfoExtractor {
    private Context mContext;

    public AppInfoExtractor(Context context) {
        mContext = context;
    }

    public List<AppData> getInstalledAppList() {

        List<AppData> packageList = new ArrayList<>();

        final PackageManager pm = mContext.getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_META_DATA);

        for (PackageInfo packageInfo : packages) {
            if (packageInfo.packageName.contains("com.outfit7")) {
                packageList.add(new AppData(packageInfo));
            }
        }
        return packageList;
    }

    public boolean isSystemPackage(ResolveInfo resolveInfo) {
        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    public Drawable getAppIconByPackageName(String apkTempPackageName) {
        Drawable drawable;
        try {
            drawable = mContext.getPackageManager().getApplicationIcon(apkTempPackageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            drawable = ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher);
        }
        return drawable;
    }

    public String getAppName(String apkPackageName) {
        String name = "";
        ApplicationInfo applicationInfo;
        PackageManager packageManager = mContext.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(apkPackageName, 0);
            if (applicationInfo != null) {
                name = (String) packageManager.getApplicationLabel(applicationInfo);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }
}
