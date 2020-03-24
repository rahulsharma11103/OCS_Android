package com.aeon.myoncall;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.app.Application;
import android.graphics.Bitmap;
import com.aeon.myoncall.R;

public class MyApplication extends Application {

	private Tracker mTracker;

	// for Cashing Bitmap
	protected static ImageLoader imageLoader;
	protected static DisplayImageOptions options;

	@Override
	public void onCreate() {
		super.onCreate();

		// load ImageLoader
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(getBaseContext()));

		options = new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.EXACTLY)
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	@Override
	public void onTerminate() {
		imageLoader.clearDiskCache();
		imageLoader.clearMemoryCache();
		super.onTerminate();
	}

	// return ImageLoader object
	public static ImageLoader getImageLoader() {
		return imageLoader;
	}

	// return ImageLoader Option
	public static DisplayImageOptions getImageOption() {
		return options;
	}

	/**
	 * Gets the default {@link Tracker} for this {@link Application}.
	 * 
	 * @return tracker
	 */
	synchronized public Tracker getDefaultTracker() {
		if (mTracker == null) {
			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
			mTracker = analytics.newTracker(R.xml.analytics);
		}
		return mTracker;
	}
}
