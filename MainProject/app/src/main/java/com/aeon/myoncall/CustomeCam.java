package com.aeon.myoncall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aeon.utils.Utility;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class CustomeCam extends BaseActivity {

	private Camera mCamera;
	private CameraPreview mPreview;
	private String TAG = "CustomeCam";

	FrameLayout preview;
	private ImageView imggird;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setScreenName("CustomCamera Screen");
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customcame);
		getActionBar().hide();

		if (!checkCameraHardware()) {
			Toast.makeText(CustomeCam.this, "Camera Not Available", Toast.LENGTH_LONG).show();
		} else {
			Log.e(TAG, "1");
			openSurfaceview();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e(TAG, "2");
	}

	public void openSurfaceview() {

		imggird = (ImageView) findViewById(R.id.imggrid);
		preview = (FrameLayout) findViewById(R.id.camera_preview);

		// preview.addView(imggird);

		// Create an instance of Camera
		mCamera = getCameraInstance();
		Log.e(TAG, "3");
		// Create our Preview view and set it as the content of our activity.
		mPreview = new CameraPreview(this, mCamera);

		mPreview.setZOrderOnTop(false);
		mPreview.setZOrderMediaOverlay(false);
		preview.addView(mPreview);

		Log.e(TAG, "4");

		// Add a listener to the Capture button
		Button captureButton = (Button) findViewById(R.id.button_capture);
		captureButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// get an image from the camera
				mCamera.takePicture(null, null, mPicture);
			}
		});

	}

	/** Check if this device has a camera */
	private boolean checkCameraHardware() {
		if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			// no camera on this device
			return false;
		}
	}

	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}

	private PictureCallback mPicture = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {

			File pictureFile = Utility.getOutputMediaFile(Utility.MEDIA_TYPE_IMAGE);
			if (pictureFile == null) {
				Log.d(TAG, "Error creating media file, check storage permissions: ");
				return;
			}

			try {
				Log.e(TAG, "" + pictureFile.getAbsolutePath());
				FileOutputStream fos = new FileOutputStream(pictureFile);
				fos.write(data);
				fos.close();

				setResult(RESULT_OK, getIntent().putExtra("data", pictureFile.getAbsolutePath()));

				CustomeCam.this.finish();

			} catch (FileNotFoundException e) {
				Log.d(TAG, "File not found: " + e.getMessage());
			} catch (IOException e) {
				Log.d(TAG, "Error accessing file: " + e.getMessage());
			}
		}
	};
}
