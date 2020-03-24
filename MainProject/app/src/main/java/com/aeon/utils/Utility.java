package com.aeon.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

public class Utility {

	// Get OS Version Info
	public static String getVersionInfo() {
		String versionName = android.os.Build.VERSION.RELEASE;
		int sdkVersion = android.os.Build.VERSION.SDK_INT;

		String versionTitle = "";
		switch (sdkVersion) {
		case 3:
			versionTitle = "Cupcake";
			break;
		case 4:
			versionTitle = "Donut";
			break;
		case 5:
			versionTitle = "Eclair";
			break;
		case 6:
			versionTitle = "Eclair";
			break;
		case 7:
			versionTitle = "Eclair";
			break;
		case 8:
			versionTitle = "Froyo";
			break;
		case 9:
			versionTitle = "Gingerbread";
			break;
		case 10:
			versionTitle = "Gingerbread";
			break;
		case 11:
			versionTitle = "Honeycomb";
			break;
		case 12:
			versionTitle = "Honeycomb";
			break;
		case 13:
			versionTitle = "Honeycomb";
			break;
		case 14:
			versionTitle = "Ice Cream Sandwich";
			break;
		case 15:
			versionTitle = "Ice Cream Sandwich";
			break;
		case 16:
			versionTitle = "Jelly Bean";
			break;
		case 17:
			versionTitle = "Jelly Bean";
			break;
		case 18:
			versionTitle = "Jelly Bean";
			break;
		case 19:
			versionTitle = "KitKat";
			break;
		case 20:
			versionTitle = "KitKat";
			break;
		case 21:
			versionTitle = "Lollipop";
			break;
		case 22:
			versionTitle = "Lollipop";
			break;
		default:
			versionTitle = "Marshmallow";
			break;
		}

		String versionInfo;
		if (!TextUtils.isEmpty(versionTitle))
			versionInfo = String.format("%s(%s)", versionName, versionTitle);
		else
			versionInfo = versionName;

		return versionInfo;
	}

	// Get Device Name(Model Info)
	public static String getDeviceName() {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if (model.startsWith(manufacturer)) {
			return capitalize(model);
		} else {
			return capitalize(manufacturer) + " " + model;
		}
	}

	private static String capitalize(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		char first = s.charAt(0);
		if (Character.isUpperCase(first)) {
			return s;
		} else {
			return Character.toUpperCase(first) + s.substring(1);
		}
	}

	// Get App Version Info
	public static String getAppVersionInfo(Context context) {

		String appVersionName = "1.0";
		try {
			PackageInfo i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			appVersionName = i.versionName;
		} catch (NameNotFoundException e) {
		}
		return appVersionName;
	}

	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;

	/** Create a File for saving an image or video */
	public static File getOutputMediaFile(int type) {
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.

		File mediaStorageDir = new File(
				Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"MyCameraApp");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("MyCameraApp", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File mediaFile;
		if (type == MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp
					+ ".jpg");
		} else if (type == MEDIA_TYPE_VIDEO) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp
					+ ".mp4");
		} else {
			return null;
		}

		return mediaFile;
	}

	public static void SaveBitmapToFileCache(Bitmap bitmap, String strFilePath) {
		File fileCacheItem = new File(strFilePath);
		OutputStream out = null;

		try {
			fileCacheItem.createNewFile();
			out = new FileOutputStream(fileCacheItem);

			bitmap.compress(CompressFormat.JPEG, 100, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
