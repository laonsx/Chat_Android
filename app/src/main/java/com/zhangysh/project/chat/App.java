package com.zhangysh.project.chat;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.WindowManager;
import android.widget.Toast;

import com.zhangysh.project.chat.util.LogUtil;
import com.zhangysh.project.chat.util.PhoneConfigInfo;

import java.io.File;

public class App extends Application {

	public static App mApp;
	private Activity mCurrentActivityContext;

	public static String APP_VERSION;// 版本号
	public static String APP_PACKAGE;// 包名
	public static File EXT_STORAGE1;// sd卡或本地存储路径
	@Override
	public void onCreate() {
		super.onCreate();
		App.mApp = this;
		int pid = android.os.Process.myPid();
		LogUtil.i("pid="+pid);
		String processNameString = "";
		ActivityManager mActivityManager = (ActivityManager) this.getSystemService(getApplicationContext().ACTIVITY_SERVICE);
		for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
			if (appProcess.pid == pid) {
				processNameString = appProcess.processName;
				LogUtil.i("processName=" + processNameString);
			}
		}
		PackageManager pm = getPackageManager();
		try {
			PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
			APP_VERSION = pi.versionName;
			APP_PACKAGE = pi.packageName;
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED))
				EXT_STORAGE1 = Environment.getExternalStorageDirectory();
			else
				EXT_STORAGE1 = getCacheDir();
		} catch (Exception e) {
			e.printStackTrace();
		}
		init();
	}


	private void init() {
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		float density=getContext().getResources().getDisplayMetrics().density;
		PhoneConfigInfo.setWidth(wm.getDefaultDisplay().getWidth());
		PhoneConfigInfo.setHeight(wm.getDefaultDisplay().getHeight());
		PhoneConfigInfo.setDensity(density);
	}

	public void setSharedPreferenceStr(String key, String value) {
		SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor edit = sharedPreferences.edit();
		edit.putString(key, value);
		edit.commit();
	}
	public void setSharedPreferenceInt(String key, int value) {
		SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor edit = sharedPreferences.edit();
		edit.putInt(key, value);
		edit.commit();
	}
	public String getSharedePreferenceStr(String key) {
		SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		return sharedPreferences.getString(key, "");
	}
	public int getSharedePreferenceInt(String key) {
		SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
		return sharedPreferences.getInt(key,-1);
	}

	public static App getContext() {
		return App.mApp;
	}

	public void setCurrentActivityContext(Activity mCurrentActivityContext) {
		this.mCurrentActivityContext = mCurrentActivityContext;
	}

	public Activity getCurrentActivityContext() {
		return mCurrentActivityContext;
	}

}
