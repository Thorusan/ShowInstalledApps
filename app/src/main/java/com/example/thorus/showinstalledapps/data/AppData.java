package com.example.thorus.showinstalledapps.data;

import android.app.Application;
import android.content.pm.PackageInfo;

import java.io.Serializable;


public class AppData extends Application implements Serializable {

    PackageInfo packageInfo;

    public AppData(PackageInfo packageInfo) {
        setPackageInfo(packageInfo);
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public String getPackageName() {
        return getPackageInfo().packageName;
    }

    public String getVersionName() {
        return getPackageInfo().versionName;
    }

    public int getVersionCode() {
        return getPackageInfo().versionCode;
    }


}
