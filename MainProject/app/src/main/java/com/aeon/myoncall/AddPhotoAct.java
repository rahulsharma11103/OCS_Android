package com.aeon.myoncall;

import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.aeon.utils.Utility;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class AddPhotoAct extends BaseActivity implements OnClickListener {

    private static final int CAMERA_REQUEST = 1888;
    TextView txt_topadd;
    Button btn_back, btn_uploadd;
    int flassh = 0;
    ImageView img11, img12, img13, img21, img22, img23, img31, img32, img33;
    TextView txt11, txt12, txt13, txt21, txt22, txt23, txt31, txt32, txt33;

    String selcetedLay = "";
    public static Bitmap btmp = null;
    public static String here = "", RETURN_URI = "";

    SharedPreferences sh_Pref;
    Editor toEdit;

    ArrayList<Uri> email_images = new ArrayList<Uri>(9);
    ArrayList<Uri> imageIds = new ArrayList<Uri>();
    String user_clid, user_fname, user_lname, user_emailss;

    boolean actStopped;

    private static String TAG = "AddPhotoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setScreenName("AddPhoto Screen");

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_addpicc);

        init();

        sh_Pref = getSharedPreferences("register_details", MODE_PRIVATE);
        toEdit = sh_Pref.edit();

        user_clid = sh_Pref.getString("on_call_id", null);
        user_fname = sh_Pref.getString("first_name", null);
        user_lname = sh_Pref.getString("last_name", null);
        user_emailss = sh_Pref.getString("email_id", null);

        if (TabTimesheet.img_flag.equalsIgnoreCase("newpic")) {
            txt_topadd.setText("New Photo");
            openCamerGetImage();
            selcetedLay = "11";
        } else if (TabTimesheet.img_flag.equalsIgnoreCase("upload")) {
            txt_topadd.setText("Upload");

        }

        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AddPhotoAct.btmp = null;

                TabPage.tabHost.setCurrentTab(2);
            }
        });

        email_images.clear();
        for (int i = 0; i < 9; i++) {
            email_images.add(null);
        }

        /*
         * btn_clearall.setOnClickListener(new View.OnClickListener() {
         *
         * @Override public void onClick(View v) { // TODO Auto-generated method
         * stub AddPhotoAct.btmp=null; email_images.clear();
         * img11.setImageBitmap(null); img11.destroyDrawingCache();
         * img12.setImageBitmap(null); img12.destroyDrawingCache();
         * img13.setImageBitmap(null); img13.destroyDrawingCache();
         *
         * img21.setImageBitmap(null); img21.destroyDrawingCache();
         * img22.setImageBitmap(null); img22.destroyDrawingCache();
         * img23.setImageBitmap(null); img23.destroyDrawingCache();
         *
         * img31.setImageBitmap(null); img31.destroyDrawingCache();
         * img32.setImageBitmap(null); img32.destroyDrawingCache();
         * img33.setImageBitmap(null); img33.destroyDrawingCache();
         *
         * } });
         */
        btn_uploadd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    if (email_images != null) {

                        imageIds = new ArrayList<>();
                        for (int i = 0; i < email_images.size(); i++) {

                            if (email_images.get(i) != null) {
                                imageIds.add(email_images.get(i));
                            }
                        }

                        Intent ei = new Intent(Intent.ACTION_SEND_MULTIPLE);
                        ei.setType("plain/text");
                        ei.putExtra(Intent.EXTRA_EMAIL, new String[]{"timesheets@oncall.com.au"});
                        ei.putExtra(Intent.EXTRA_SUBJECT, "Timesheet Submision - " + user_clid);
                        // ei.putExtra(Intent.EXTRA_STREAM,
                        // email_images.get(0));
                        ei.putExtra(
                                Intent.EXTRA_TEXT,
                                "Dear Payroll Team, \r\n  \t\t Please find attached the submission"
                                        + " of my current timesheets for this coming weeks pay scale  \r\n "
                                        + user_fname + " " + user_lname + " - " + user_clid);
                        ei.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageIds);

                        actStopped = false;
                        startActivityForResult(
                                Intent.createChooser(ei, "Sending multiple attachment"), 12345);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }

                // clearData();
            }

        });

    }

    private void clearData() {
        // TODO Auto-generated method stub

        email_images.clear();
        for (int i = 0; i < 9; i++) {
            email_images.add(null);
        }
        img11.setImageBitmap(null);
        img11.destroyDrawingCache();
        img12.setImageBitmap(null);
        img12.destroyDrawingCache();
        img13.setImageBitmap(null);
        img13.destroyDrawingCache();

        img21.setImageBitmap(null);
        img21.destroyDrawingCache();
        img22.setImageBitmap(null);
        img22.destroyDrawingCache();
        img23.setImageBitmap(null);
        img23.destroyDrawingCache();

        img31.setImageBitmap(null);
        img31.destroyDrawingCache();
        img32.setImageBitmap(null);
        img32.destroyDrawingCache();
        img33.setImageBitmap(null);
        img33.destroyDrawingCache();

        txt11.setVisibility(View.INVISIBLE);
        txt12.setVisibility(View.INVISIBLE);
        txt13.setVisibility(View.INVISIBLE);
        txt21.setVisibility(View.INVISIBLE);
        txt22.setVisibility(View.INVISIBLE);
        txt23.setVisibility(View.INVISIBLE);
        txt31.setVisibility(View.INVISIBLE);
        txt32.setVisibility(View.INVISIBLE);
        txt33.setVisibility(View.INVISIBLE);

    }

    private void init() {
        // TODO Auto-generated method stub
        txt_topadd = (TextView) findViewById(R.id.txt_topadd);
        // MainActivity.tabHost.setCurrentTab(6);
        // btn_uploadd=(Button)findViewById(R.id.btn_uploadd33);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_uploadd = (Button) findViewById(R.id.btn_clearall);
        img11 = (ImageView) findViewById(R.id.img11);
        img12 = (ImageView) findViewById(R.id.img12);
        img13 = (ImageView) findViewById(R.id.img13);

        txt11 = (TextView) findViewById(R.id.remove11);
        txt12 = (TextView) findViewById(R.id.remove12);
        txt13 = (TextView) findViewById(R.id.remove13);

        img21 = (ImageView) findViewById(R.id.img21);
        img22 = (ImageView) findViewById(R.id.img22);
        img23 = (ImageView) findViewById(R.id.img23);

        txt21 = (TextView) findViewById(R.id.remove21);
        txt22 = (TextView) findViewById(R.id.remove22);
        txt23 = (TextView) findViewById(R.id.remove23);

        img31 = (ImageView) findViewById(R.id.img31);
        img32 = (ImageView) findViewById(R.id.img32);
        img33 = (ImageView) findViewById(R.id.img33);

        txt31 = (TextView) findViewById(R.id.remove31);
        txt32 = (TextView) findViewById(R.id.remove32);
        txt33 = (TextView) findViewById(R.id.remove33);

        img11.setOnClickListener(this);
        img12.setOnClickListener(this);
        img13.setOnClickListener(this);

        img21.setOnClickListener(this);
        img22.setOnClickListener(this);
        img23.setOnClickListener(this);

        img31.setOnClickListener(this);
        img32.setOnClickListener(this);
        img33.setOnClickListener(this);

        txt11.setOnClickListener(this);
        txt12.setOnClickListener(this);
        txt13.setOnClickListener(this);
        txt21.setOnClickListener(this);
        txt22.setOnClickListener(this);
        txt23.setOnClickListener(this);
        txt31.setOnClickListener(this);
        txt32.setOnClickListener(this);
        txt33.setOnClickListener(this);

        txt11.setVisibility(View.INVISIBLE);
        txt12.setVisibility(View.INVISIBLE);
        txt13.setVisibility(View.INVISIBLE);
        txt21.setVisibility(View.INVISIBLE);
        txt22.setVisibility(View.INVISIBLE);
        txt23.setVisibility(View.INVISIBLE);
        txt31.setVisibility(View.INVISIBLE);
        txt32.setVisibility(View.INVISIBLE);
        txt33.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("AddPHotoAct", "OnCtivityResult : " + actStopped);
        if (requestCode == 12345) {

            if (actStopped) {
                clearData();
                return;
            } else {
                return;
            }

        }

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            // Bitmap photo = (Bitmap) data.getExtras().get("data");
            Log.e("Image", "" + data.getExtras().get("data"));
            Bitmap bit = BitmapFactory.decodeFile(data.getExtras().get("data").toString());

            Bitmap photo = null;
            String path = data.getExtras().get("data").toString();

            // try{

            /*
             * int orien = getOrientation(AddPhotoAct.this, targetUri);
             * switch(orien) { case 270: photo = RotateBitmap(ShrinkBitmap(path,
             * 300, 300),270); Log.e("AAA Photo", ""+photo); break; case 90:
             * photo = RotateBitmap(ShrinkBitmap(path, 300, 300),90);
             * Log.e("BBB Photo", ""+photo); break; case 180: photo =
             * RotateBitmap(ShrinkBitmap(path, 300, 300),180);
             * Log.e("CCC Photo", ""+photo); break; case 0: photo =
             * RotateBitmap(ShrinkBitmap(path, 300, 300),0); Log.e("CCC Photo",
             * ""+photo); break; } }catch(Exception e){ e.printStackTrace(); }
             */

            photo = RotateBitmap(ShrinkBitmap(path, 300, 300), 90);
            File pictureFile = Utility.getOutputMediaFile(Utility.MEDIA_TYPE_IMAGE);
            if (pictureFile == null) {
                Log.d(TAG, "Error creating media file, check storage permissions: ");
                return;
            }

            Utility.SaveBitmapToFileCache(photo, pictureFile.getAbsolutePath());
            //Uri tempUri = getImageUri(getApplicationContext(), photo);
            //   Uri tempUri = Uri.fromFile(pictureFile);

            Uri tempUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, pictureFile);


            if (selcetedLay.equalsIgnoreCase("11")) {
                img11.setImageBitmap(photo);
                txt11.setVisibility(View.VISIBLE);
                email_images.set(0, tempUri);
                System.out.println("captured img path-->" + tempUri);
            } else if (selcetedLay.equalsIgnoreCase("12")) {
                img12.setImageBitmap(photo);
                txt12.setVisibility(View.VISIBLE);
                email_images.set(1, tempUri);
            } else if (selcetedLay.equalsIgnoreCase("13")) {
                img13.setImageBitmap(photo);
                txt13.setVisibility(View.VISIBLE);
                email_images.set(2, tempUri);
            } else if (selcetedLay.equalsIgnoreCase("21")) {
                img21.setImageBitmap(photo);
                txt21.setVisibility(View.VISIBLE);
                email_images.set(3, tempUri);

            } else if (selcetedLay.equalsIgnoreCase("22")) {
                img22.setImageBitmap(photo);
                txt22.setVisibility(View.VISIBLE);
                email_images.set(4, tempUri);

            } else if (selcetedLay.equalsIgnoreCase("23")) {
                img23.setImageBitmap(photo);
                txt23.setVisibility(View.VISIBLE);
                email_images.set(5, tempUri);

            } else if (selcetedLay.equalsIgnoreCase("31")) {
                img31.setImageBitmap(photo);
                txt31.setVisibility(View.VISIBLE);
                email_images.set(6, tempUri);

            } else if (selcetedLay.equalsIgnoreCase("32")) {
                img32.setImageBitmap(photo);
                txt32.setVisibility(View.VISIBLE);
                email_images.set(7, tempUri);

            } else if (selcetedLay.equalsIgnoreCase("33")) {
                img33.setImageBitmap(photo);
                txt33.setVisibility(View.VISIBLE);
                email_images.set(8, tempUri);
            }
        } else if (requestCode == 101 && resultCode == RESULT_OK) {

            try {


                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap bit = BitmapFactory.decodeStream(imageStream);

                //String path = PathUtil.getPath(this, imageUri);


             /*   Uri uri = data.getData();
                File file = new File(uri.getPath());//create path from uri
                final String[] split = file.getPath().split(":");//split the path.
                String path = split[1];*/

                //  Bitmap photo = null;
                //  = file.getAbsolutePath().toString();

                // try{

                /*
                 * int orien = getOrientation(AddPhotoAct.this, targetUri);
                 * switch(orien) { case 270: photo = RotateBitmap(ShrinkBitmap(path,
                 * 300, 300),270); Log.e("AAA Photo", ""+photo); break; case 90:
                 * photo = RotateBitmap(ShrinkBitmap(path, 300, 300),90);
                 * Log.e("BBB Photo", ""+photo); break; case 180: photo =
                 * RotateBitmap(ShrinkBitmap(path, 300, 300),180);
                 * Log.e("CCC Photo", ""+photo); break; case 0: photo =
                 * RotateBitmap(ShrinkBitmap(path, 300, 300),0); Log.e("CCC Photo",
                 * ""+photo); break; } }catch(Exception e){ e.printStackTrace(); }
                 */

                // photo = RotateBitmap(ShrinkBitmap(path, 300, 300), 90);
                File pictureFile = Utility.getOutputMediaFile(Utility.MEDIA_TYPE_IMAGE);
                if (pictureFile == null) {
                    Log.d(TAG, "Error creating media file, check storage permissions: ");
                    return;
                }

                Utility.SaveBitmapToFileCache(bit, pictureFile.getAbsolutePath());
                //Uri tempUri = getImageUri(getApplicationContext(), photo);
                //   Uri tempUri = Uri.fromFile(pictureFile);

                Uri tempUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, pictureFile);


                if (selcetedLay.equalsIgnoreCase("11")) {
                    img11.setImageBitmap(bit);
                    txt11.setVisibility(View.VISIBLE);
                    email_images.set(0, tempUri);
                    System.out.println("captured img path-->" + tempUri);
                } else if (selcetedLay.equalsIgnoreCase("12")) {
                    img12.setImageBitmap(bit);
                    txt12.setVisibility(View.VISIBLE);
                    email_images.set(1, tempUri);
                } else if (selcetedLay.equalsIgnoreCase("13")) {
                    img13.setImageBitmap(bit);
                    txt13.setVisibility(View.VISIBLE);
                    email_images.set(2, tempUri);
                } else if (selcetedLay.equalsIgnoreCase("21")) {
                    img21.setImageBitmap(bit);
                    txt21.setVisibility(View.VISIBLE);
                    email_images.set(3, tempUri);

                } else if (selcetedLay.equalsIgnoreCase("22")) {
                    img22.setImageBitmap(bit);
                    txt22.setVisibility(View.VISIBLE);
                    email_images.set(4, tempUri);

                } else if (selcetedLay.equalsIgnoreCase("23")) {
                    img23.setImageBitmap(bit);
                    txt23.setVisibility(View.VISIBLE);
                    email_images.set(5, tempUri);

                } else if (selcetedLay.equalsIgnoreCase("31")) {
                    img31.setImageBitmap(bit);
                    txt31.setVisibility(View.VISIBLE);
                    email_images.set(6, tempUri);

                } else if (selcetedLay.equalsIgnoreCase("32")) {
                    img32.setImageBitmap(bit);
                    txt32.setVisibility(View.VISIBLE);
                    email_images.set(7, tempUri);

                } else if (selcetedLay.equalsIgnoreCase("33")) {
                    img33.setImageBitmap(bit);
                    txt33.setVisibility(View.VISIBLE);
                    email_images.set(8, tempUri);
                }
            } catch (Exception ex) {
                Log.e("errpr", ex.getMessage());
            }
        } else {

            if (selcetedLay.equalsIgnoreCase("11")) {
                img11.setImageBitmap(AddPhotoAct.btmp);
                txt11.setVisibility(View.INVISIBLE);
            } else if (selcetedLay.equalsIgnoreCase("12")) {
                img12.setImageBitmap(AddPhotoAct.btmp);
                txt12.setVisibility(View.INVISIBLE);

            } else if (selcetedLay.equalsIgnoreCase("13")) {
                img13.setImageBitmap(AddPhotoAct.btmp);
                txt13.setVisibility(View.INVISIBLE);

            } else if (selcetedLay.equalsIgnoreCase("21")) {
                img21.setImageBitmap(AddPhotoAct.btmp);
                txt21.setVisibility(View.INVISIBLE);

            } else if (selcetedLay.equalsIgnoreCase("22")) {
                img22.setImageBitmap(AddPhotoAct.btmp);
                txt22.setVisibility(View.INVISIBLE);

            } else if (selcetedLay.equalsIgnoreCase("23")) {
                img23.setImageBitmap(AddPhotoAct.btmp);
                txt23.setVisibility(View.INVISIBLE);

            } else if (selcetedLay.equalsIgnoreCase("31")) {
                img31.setImageBitmap(AddPhotoAct.btmp);
                txt31.setVisibility(View.INVISIBLE);

            } else if (selcetedLay.equalsIgnoreCase("32")) {
                img32.setImageBitmap(AddPhotoAct.btmp);
                txt32.setVisibility(View.INVISIBLE);

            } else if (selcetedLay.equalsIgnoreCase("33")) {
                img33.setImageBitmap(AddPhotoAct.btmp);
                txt33.setVisibility(View.INVISIBLE);

            }
            // img_user.setImageBitmap(TabPage.bitmap_prof);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title",
                null);
        if (TextUtils.isEmpty(path))
            return null;
        else
            return Uri.parse(path);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int idd = v.getId();

        switch (idd) {
            case R.id.remove11:

                img11.setImageBitmap(null);
                img11.destroyDrawingCache();

                txt11.setVisibility(View.INVISIBLE);
                email_images.set(0, null);

                return;

            case R.id.remove12:

                img12.setImageBitmap(null);
                img12.destroyDrawingCache();

                txt12.setVisibility(View.INVISIBLE);
                email_images.set(1, null);
                return;

            case R.id.remove13:

                img13.setImageBitmap(null);
                img13.destroyDrawingCache();

                txt13.setVisibility(View.INVISIBLE);
                email_images.set(2, null);

                return;

            case R.id.remove21:

                img21.setImageBitmap(null);
                img21.destroyDrawingCache();

                txt21.setVisibility(View.INVISIBLE);
                email_images.set(3, null);

                return;

            case R.id.remove22:

                img22.setImageBitmap(null);
                img22.destroyDrawingCache();

                txt22.setVisibility(View.INVISIBLE);
                email_images.set(4, null);

                return;

            case R.id.remove23:

                img23.setImageBitmap(null);
                img23.destroyDrawingCache();

                txt23.setVisibility(View.INVISIBLE);
                email_images.set(5, null);

                return;

            case R.id.remove31:

                img31.setImageBitmap(null);
                img31.destroyDrawingCache();

                txt31.setVisibility(View.INVISIBLE);
                email_images.set(6, null);

                return;

            case R.id.remove32:

                img32.setImageBitmap(null);
                img32.destroyDrawingCache();

                txt32.setVisibility(View.INVISIBLE);
                email_images.set(7, null);

                return;

            case R.id.remove33:

                img33.setImageBitmap(null);
                img33.destroyDrawingCache();

                txt33.setVisibility(View.INVISIBLE);
                email_images.set(8, null);

                return;

            default:
                break;
        }

        if (TabTimesheet.img_flag.equalsIgnoreCase("newpic")) {
            if (idd == R.id.img11) {
                openCamerGetImage();
                selcetedLay = "11";
            } else if (idd == R.id.img12) {
                openCamerGetImage();
                selcetedLay = "12";
            } else if (idd == R.id.img13) {
                openCamerGetImage();
                selcetedLay = "13";
            } else if (idd == R.id.img21) {
                openCamerGetImage();
                selcetedLay = "21";
            } else if (idd == R.id.img22) {
                openCamerGetImage();
                selcetedLay = "22";
            } else if (idd == R.id.img23) {
                openCamerGetImage();
                selcetedLay = "23";
            } else if (idd == R.id.img31) {
                openCamerGetImage();
                selcetedLay = "31";
            } else if (idd == R.id.img32) {
                openCamerGetImage();
                selcetedLay = "32";
            } else if (idd == R.id.img33) {
                openCamerGetImage();
                selcetedLay = "33";
            }
        } else if (TabTimesheet.img_flag.equalsIgnoreCase("upload")) {
            if (idd == R.id.img11) {
                callImageSelector();
                selcetedLay = "11";
            } else if (idd == R.id.img12) {
                callImageSelector();
                selcetedLay = "12";
            } else if (idd == R.id.img13) {
                callImageSelector();
                selcetedLay = "13";
            } else if (idd == R.id.img21) {
                callImageSelector();
                selcetedLay = "21";
            } else if (idd == R.id.img22) {
                callImageSelector();
                selcetedLay = "22";
            } else if (idd == R.id.img23) {
                callImageSelector();
                selcetedLay = "23";
            } else if (idd == R.id.img31) {
                callImageSelector();
                selcetedLay = "31";
            } else if (idd == R.id.img32) {
                callImageSelector();
                selcetedLay = "32";
            } else if (idd == R.id.img33) {
                callImageSelector();
                selcetedLay = "33";
            }
        }

    }

    public static void openGmailAppIntent(Context context, Intent intent) {
        final PackageManager pm = context.getPackageManager();
        final List<ResolveInfo> matches = pm.queryIntentActivities(intent, 0);
        ResolveInfo best = null;
        for (final ResolveInfo info : matches)
            if (info.activityInfo.packageName.endsWith(".gm")
                    || info.activityInfo.name.toLowerCase().contains("gmail"))
                best = info;
        if (best != null)
            intent.setClassName(best.activityInfo.packageName, best.activityInfo.name);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // startActivity(Intent
        // .createChooser(emailIntent, "Email to Send"));
        try {

            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            /*
             * AddPhotoAct.showMessageForValiDation(context,
             * AddPhotoAct.VALIDATION_NO_GMAIL_APP,
             * AddPhotoAct.iXPENSE_APP_FOLDER);
             */

        } catch (Exception e) {

        }

    }

    private File savebitmap(Bitmap bmp, String name) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        File file = new File(extStorageDirectory, name + ".png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, name + ".png");
        }

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    private void callImageSelector() {
        // TODO Auto-generated method stub
        TabPage.img_selec = "timesheet";
        here = "select_img";
       /* Intent ineee = new Intent(getApplicationContext(), ImageSelector.class);
        startActivity(ineee);*/

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 101);
    }


    private void openCamerGetImage() {
        // TODO Auto-generated method stub

        // Intent cameraIntent = new
        // Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        // startActivityForResult(cameraIntent, CAMERA_REQUEST);

        Intent i = new Intent(AddPhotoAct.this, CustomeCam.class);
        startActivityForResult(i, CAMERA_REQUEST);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        /*if (AddPhotoAct.btmp != null) {
            if (selcetedLay.equalsIgnoreCase("11")) {
                img11.setImageBitmap(AddPhotoAct.btmp);
                File imageFileToShare = new File(RETURN_URI);
                imageFileToShare.setReadable(true, false);
                Uri urii = Uri.fromFile(imageFileToShare);
                email_images.set(0, urii);
                System.out.println("" + urii);
            } else if (selcetedLay.equalsIgnoreCase("12")) {
                img12.setImageBitmap(AddPhotoAct.btmp);
                Uri urii = Uri.parse(RETURN_URI);
                email_images.set(1, urii);
                System.out.println("" + urii);
            } else if (selcetedLay.equalsIgnoreCase("13")) {
                img13.setImageBitmap(AddPhotoAct.btmp);
                Uri urii = Uri.parse(RETURN_URI);
                email_images.set(2, urii);
                System.out.println("" + urii);
            } else if (selcetedLay.equalsIgnoreCase("21")) {
                img21.setImageBitmap(AddPhotoAct.btmp);
                Uri urii = Uri.parse(RETURN_URI);
                email_images.set(3, urii);
                System.out.println("" + urii);
            } else if (selcetedLay.equalsIgnoreCase("22")) {
                img22.setImageBitmap(AddPhotoAct.btmp);
                Uri urii = Uri.parse(RETURN_URI);
                email_images.set(4, urii);
            } else if (selcetedLay.equalsIgnoreCase("23")) {
                img23.setImageBitmap(AddPhotoAct.btmp);
                Uri urii = Uri.parse(RETURN_URI);
                email_images.set(5, urii);
                System.out.println("" + urii);
            } else if (selcetedLay.equalsIgnoreCase("31")) {
                img31.setImageBitmap(AddPhotoAct.btmp);
                Uri urii = Uri.parse(RETURN_URI);
                email_images.set(6, urii);
            } else if (selcetedLay.equalsIgnoreCase("32")) {
                img32.setImageBitmap(AddPhotoAct.btmp);
                Uri urii = Uri.parse(RETURN_URI);
                email_images.set(7, urii);
            } else if (selcetedLay.equalsIgnoreCase("33")) {
                img33.setImageBitmap(AddPhotoAct.btmp);
                Uri urii = Uri.parse(RETURN_URI);
                email_images.set(8, urii);
                System.out.println("" + urii);
            }
        }*/
        here = "";

        actStopped = false;
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.e("AddPhotoAct", "OnStoppedCalled");
        actStopped = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // preventing default implementation previous to
            // android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static int getOrientation(Context context, Uri photoUri) {
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[]{MediaStore.Images.ImageColumns.ORIENTATION}, null, null, null);

        try {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0);
            } else {
                return -1;
            }
        } finally {
            cursor.close();
        }
    }

    private Bitmap ShrinkBitmap(String path, int width, int height) {
        // TODO Auto-generated method stub

        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;

        Bitmap bitmap = BitmapFactory.decodeFile(path, bmpFactoryOptions);

        int heightRatio = (int) Math.ceil(bmpFactoryOptions.outHeight / (float) height);
        int widthRatio = (int) Math.ceil(bmpFactoryOptions.outWidth / (float) width);

        if (heightRatio > 1 || widthRatio > 1) {
            if (heightRatio > widthRatio) {
                bmpFactoryOptions.inSampleSize = heightRatio;
            } else {
                bmpFactoryOptions.inSampleSize = widthRatio;
            }
        }

        bmpFactoryOptions.inJustDecodeBounds = false;

        bitmap = BitmapFactory.decodeFile(path, bmpFactoryOptions);

        return bitmap;
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }
}
