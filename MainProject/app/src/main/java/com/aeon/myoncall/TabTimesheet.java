package com.aeon.myoncall;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabTimesheet extends BaseActivity {


    Button btn_newpic, btn_existingpic, btn_infonew, btn_infoexis;
    public static String img_flag = "";
    RelativeLayout lay_bckggs;


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        lay_bckggs.getBackground().setAlpha(80);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setScreenName("Timesheet Screen");

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_timesheet);

        btn_newpic = (Button) findViewById(R.id.btn_newpic);
        btn_existingpic = (Button) findViewById(R.id.btn_existingpic);
        btn_infonew = (Button) findViewById(R.id.btn_infonew);
        btn_infoexis = (Button) findViewById(R.id.btn_infoexis);
        lay_bckggs = (RelativeLayout) findViewById(R.id.lay_bckggs);

        lay_bckggs.getBackground().setAlpha(80);

        btn_newpic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                img_flag = "newpic";
                if (isPermissionGranted()) {
                    TabPage.tabHost.setCurrentTab(5);
                } else {
                    callPermissionActivity();
                }
            }
        });
        btn_existingpic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                img_flag = "upload";
                if (isPermissionGranted()) {
                    TabPage.tabHost.setCurrentTab(5);
                } else {
                    callPermissionActivity();
                }
            }
        });

        btn_infonew.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                CallDialogue("2");
            }
        });
        btn_infoexis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                CallDialogue("1");
            }
        });
    }

    private void callPermissionActivity() {
        Intent callIntent = new Intent(this, PermissionHelperActivity.class);
        callIntent.putExtra(PermissionHelperActivity.PERMISSION_TYPE, PermissionHelperActivity.PERMISSION_TYPE_CAMERA_GALLERY);
        startActivity(callIntent);
    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }

    }

    private void CallDialogue(String here) {
        final Dialog Alert_dialogg;
        Alert_dialogg = new Dialog(TabTimesheet.this);
        Alert_dialogg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Alert_dialogg.setContentView(R.layout.dialog_error);
        Alert_dialogg.setCancelable(false);

        TextView txt_msg = (TextView) Alert_dialogg.findViewById(R.id.shshs);
        Button btn_okk = (Button) Alert_dialogg.findViewById(R.id.btn_ok);
        TextView txt_errr = (TextView) Alert_dialogg.findViewById(R.id.txt_errr);
        if (here.equalsIgnoreCase("2")) {
            txt_msg.setText("Take a new photo allows you to take a \r\n photo of your signed timesheet after \r\n completion" +
                    " and submit straight to \r\n timesheets@oncall.com.au. Always \r\n remember timesheet MUST be in by \r\n 10am" +
                    " Monday mornings. ");
            txt_errr.setText("New Photo");
        } else if (here.equalsIgnoreCase("1")) {
            txt_msg.setText("Upload existing photos allows you to \r\n upload any photos of your signed \r\n timesheet" +
                    " after completion and  \r\n submit in one lot to \r\n " +
                    "timesheets@oncall.com.au. (Maximum \r\n of 6 timesheets per email) Always \r\n remember timesheets MUST be in by \r\n 10am Monday mornings");
            txt_errr.setText("Existing Photo");
        }

        Alert_dialogg.show();
        btn_okk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Alert_dialogg.dismiss();

            }
        });
    }

}
