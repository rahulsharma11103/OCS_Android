package com.aeon.myoncall;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TabUser extends BaseActivity implements OnClickListener {

    // here to only text
    TextView txtx_idd, txtx_email;

    // here to st
    TextView txt_userid, txt_useremail, txt_usname;
    Button btn_calloncl, btn_updatee, btn_logout;
    public static ImageView img_userrr;
    // otheresss
    SharedPreferences sh_Pref;
    Editor toEdit;
    RelativeLayout lay_ussss;

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        /*
         * if(TabPage.bitmap_prof!=null){ img_userrr.setBackgroundResource(0);
         * img_userrr.setImageBitmap(null); img_userrr.destroyDrawingCache();
         * img_userrr.setImageBitmap(TabPage.bitmap_prof); //
         * img_userrr.setImageBitmap
         * (UserUpdate.getImageBitmap(getApplicationContext(), "user_prof",
         * null));
         *
         * }
         */
        lay_ussss.getBackground().setAlpha(10);
        Bitmap mm = UserUpdate.getImageBitmap(getApplicationContext(), "user_prof", null);
        if (mm != null) {
            img_userrr.setBackgroundResource(0);
            img_userrr.setImageBitmap(UserUpdate.getImageBitmap(getApplicationContext(),
                    "user_prof", null));
        } else {
            img_userrr.setImageResource(R.drawable.user_pic);
        }

        String usercallid = sh_Pref.getString("on_call_id", null);
        String useemailid = sh_Pref.getString("email_id", null);
        String user_fname = sh_Pref.getString("first_name", null);
        String user_lname = sh_Pref.getString("last_name", null);

        // String user_profilepc = sh_Pref.getString("on_call_profile", null);
        if (usercallid != null) {

            txt_userid.setText(usercallid);
            txt_useremail.setText(useemailid);
            txt_usname.setText("" + user_fname + " " + user_lname);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setScreenName("User Screen");

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_user);

        init();
        sh_Pref = getSharedPreferences("register_details", MODE_PRIVATE);
        toEdit = sh_Pref.edit();

        String usercallid = sh_Pref.getString("on_call_id", null);
        String useemailid = sh_Pref.getString("email_id", null);
        String user_fname = sh_Pref.getString("first_name", null);
        String user_lname = sh_Pref.getString("last_name", null);

        // String user_profilepc = sh_Pref.getString("on_call_profile", null);
        if (usercallid != null) {

            txt_userid.setText(usercallid);
            txt_useremail.setText(useemailid);
            txt_usname.setText("" + user_fname + " " + user_lname);
        }

        if (TabPage.bitmap_prof == null) {
            img_userrr.setImageBitmap(null);
            img_userrr.destroyDrawingCache();
            int iconss = R.drawable.user_pic;
        } else {
            // img_userrr.setBackgroundResource(0);
        }

        Bitmap mm = UserUpdate.getImageBitmap(getApplicationContext(), "user_prof", null);
        if (mm != null) {
            img_userrr.setBackgroundResource(0);
            img_userrr.setImageBitmap(UserUpdate.getImageBitmap(getApplicationContext(),
                    "user_prof", null));
        } else {
            img_userrr.setImageResource(R.drawable.user_pic);
        }

    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    private void init() {
        // TODO Auto-generated method stub
        txtx_idd = (TextView) findViewById(R.id.txtx_idd);
        txtx_email = (TextView) findViewById(R.id.txtx_email);
        txt_userid = (TextView) findViewById(R.id.txt_userid);
        txt_useremail = (TextView) findViewById(R.id.txt_useremail);
        btn_calloncl = (Button) findViewById(R.id.btn_calloncl);
        btn_updatee = (Button) findViewById(R.id.btn_updatee);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        txt_usname = (TextView) findViewById(R.id.txt_usname);
        img_userrr = (ImageView) findViewById(R.id.img_userrr);
        btn_logout.setOnClickListener(this);
        btn_updatee.setOnClickListener(this);
        btn_calloncl.setOnClickListener(this);
        lay_ussss = (RelativeLayout) findViewById(R.id.laybakkk);
        lay_ussss.getBackground().setAlpha(10);
        try {
            Bitmap bb = UserUpdate.getImageBitmap(getApplicationContext(), "user_prof", null);
            if (bb != null) {
                img_userrr.setImageBitmap(bb);

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int id = v.getId();
        if (id == R.id.btn_calloncl) {
            if (isPermissionGranted()) {
                callToNumber();
            } else {
                callPermissionActivity();
            }
        } else if (id == R.id.btn_updatee) {
            TabPage.update_selection = "user";
            TabPage.tabHost.setCurrentTab(6);

        } else if (id == R.id.btn_logout) {

            toEdit = sh_Pref.edit();
            toEdit.putString("login_flag", "false");
            toEdit.commit();

            Intent ineew = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(ineew);
            finish();
        }
    }

    private void callToNumber() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0398962468"));
        startActivity(callIntent);
    }

    private void callPermissionActivity() {
        Intent callIntent = new Intent(this, PermissionHelperActivity.class);
        callIntent.putExtra(PermissionHelperActivity.PERMISSION_TYPE, PermissionHelperActivity.PERMISSION_TYPE_PHONE_CALL);
        startActivity(callIntent);
    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
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
}