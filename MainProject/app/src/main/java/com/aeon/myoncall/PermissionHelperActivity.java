package com.aeon.myoncall;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


public class PermissionHelperActivity extends Activity {

    public static String PERMISSION_TYPE = "permission_type";
    public static String PERMISSION_TYPE_PHONE_CALL = "phone_call";
    public static String PERMISSION_TYPE_CAMERA_GALLERY = "camera_gallery";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String permissionType = getIntent().getStringExtra(PERMISSION_TYPE);
        if (PERMISSION_TYPE_PHONE_CALL.equalsIgnoreCase(permissionType)) {
            isPermissionGrantedForPhoneCall();
        } else if (PERMISSION_TYPE_CAMERA_GALLERY.equalsIgnoreCase(permissionType)) {
            isPermissionGrantedForCameraGallery();
        }
        finish();
    }

    public boolean isPermissionGrantedForPhoneCall() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(PermissionHelperActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }


    public void isPermissionGrantedForCameraGallery() {
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(PermissionHelperActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        finish();

        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    //callToNumber();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }

                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }*/
}
