package com.aeon.myoncall;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserUpdate extends BaseActivity implements OnClickListener {

	TextView txt_uploadpic;
	EditText edt_oncalid, edt_fname, edt_lastname, edt_emaill;
	Button btn_update, btn_backusr;
	ImageView img_user;
	SharedPreferences sh_Pref;
	Editor toEdit;

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (TabPage.bitmap_prof != null) {
			img_user.setImageBitmap(null);
			img_user.destroyDrawingCache();
			img_user.setBackgroundResource(0);

			// img_user.setImageBitmap(getCroppedBitmap(TabPage.bitmap_prof));
			img_user.setImageBitmap(TabPage.bitmap_prof);
		} else {
			Bitmap mm = UserUpdate.getImageBitmap(getApplicationContext(), "user_prof", null);
			if (mm != null) {
				img_user.setBackgroundResource(0);
				img_user.setImageBitmap(UserUpdate.getImageBitmap(getApplicationContext(),
						"user_prof", null));
			} else {
				img_user.setImageResource(R.drawable.user_pic);
			}
		}

		/*
		 * Bitmap mm=UserUpdate.getImageBitmap(getApplicationContext(),
		 * "user_prof", null); if(mm!=null){ img_user.setBackgroundResource(0);
		 * img_user
		 * .setImageBitmap(UserUpdate.getImageBitmap(getApplicationContext(),
		 * "user_prof", null)); }else{
		 * img_user.setImageResource(R.drawable.user_pic); }
		 */

	}

	/*
	 * public static Bitmap getCroppedBitmap(Bitmap bitmap) {
	 * 
	 * final int width = bitmap.getWidth(); final int height =
	 * bitmap.getHeight(); final Bitmap outputBitmap =
	 * Bitmap.createBitmap(width, height, Config.ARGB_8888);
	 * 
	 * final Path path = new Path(); path.addCircle( (float)(width / 2) ,
	 * (float)(height / 2) , (float) Math.min(width, (height / 2)) ,
	 * Path.Direction.CCW);
	 * 
	 * final Canvas canvas = new Canvas(outputBitmap); canvas.clipPath(path);
	 * canvas.drawBitmap(bitmap, 0, 0, null); return outputBitmap; }
	 */

	public static Bitmap getCroppedBitmap(Bitmap bitmap) {
		Bitmap output;

		if (bitmap.getWidth() > bitmap.getHeight()) {
			output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Config.ARGB_8888);
		} else {
			output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Config.ARGB_8888);
		}

		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		float r = 0;

		if (bitmap.getWidth() > bitmap.getHeight()) {
			r = bitmap.getHeight() / 2;
		} else {
			r = bitmap.getWidth() / 2;
		}

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawCircle(r, r, r, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/*
	 * public static Bitmap getCroppedBitmap(Bitmap bitmap) { Bitmap output =
	 * Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
	 * Config.ARGB_8888); Canvas canvas = new Canvas(output);
	 * 
	 * final int color = 0xff424242; final Paint paint = new Paint(); final Rect
	 * rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	 * 
	 * paint.setAntiAlias(true); canvas.drawARGB(0, 0, 0, 0);
	 * paint.setColor(color); // canvas.drawRoundRect(rectF, roundPx, roundPx,
	 * paint); canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
	 * bitmap.getWidth() / 2, paint); paint.setXfermode(new
	 * PorterDuffXfermode(Mode.SRC_IN)); canvas.drawBitmap(bitmap, rect, rect,
	 * paint); //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
	 * //return _bmp; return output; }
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setScreenName("UserUpdate Screen");
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_update);
		
		init();

		CallDialogue("333");

		if (TabPage.bitmap_prof != null) {
			img_user.setBackgroundResource(0);
		} else {
			img_user.setImageResource(R.drawable.user_pic);
		}

	}

	private void init() {
		// TODO Auto-generated method stub

		sh_Pref = getSharedPreferences("register_details", MODE_PRIVATE);
		edt_oncalid = (EditText) findViewById(R.id.edt_oncalid);
		edt_fname = (EditText) findViewById(R.id.edt_fname);
		edt_lastname = (EditText) findViewById(R.id.edt_lastname);
		edt_emaill = (EditText) findViewById(R.id.edt_emaill);
		btn_update = (Button) findViewById(R.id.btn_update);
		txt_uploadpic = (TextView) findViewById(R.id.txt_uploadpic);
		btn_backusr = (Button) findViewById(R.id.btn_backusr);
		txt_uploadpic.setOnClickListener(this);
		btn_update.setOnClickListener(this);
		btn_backusr.setOnClickListener(this);
		img_user = (ImageView) findViewById(R.id.img_user);

		toEdit = sh_Pref.edit();
		String usercallid = sh_Pref.getString("on_call_id", null);
		String fname = sh_Pref.getString("first_name", null);
		String lname = sh_Pref.getString("last_name", null);
		String emailss = sh_Pref.getString("email_id", null);

		edt_oncalid.setText(usercallid);
		edt_fname.setText("" + fname);
		edt_lastname.setText("" + lname);
		edt_emaill.setText("" + emailss);

		// String user_profilepc = sh_Pref.getString("on_call_profile", null);

		Bitmap mm = getImageBitmap(getApplicationContext(), "user_prof", null);
		if (mm != null) {
			img_user.setBackgroundResource(0);
			img_user.setImageBitmap(getImageBitmap(getApplicationContext(), "user_prof", null));
		} else {
			img_user.setImageResource(R.drawable.user_pic);
		}
		// img_user.setImageBitmap(getImageBitmap(getApplicationContext(),
		// "user_prof", null));

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		if (id == R.id.btn_update) {

			String iddd = edt_oncalid.getText().toString();
			String fnamee = edt_fname.getText().toString();
			String lnamess = edt_lastname.getText().toString();
			String emaill = edt_emaill.getText().toString();
			if (!iddd.equalsIgnoreCase("") && !fnamee.equalsIgnoreCase("")
					&& !lnamess.equalsIgnoreCase("") && !emaill.equalsIgnoreCase("")) {
				// update preff

				toEdit = sh_Pref.edit();
				toEdit.putString("on_call_id", iddd);
				toEdit.putString("first_name", fnamee);
				toEdit.putString("last_name", lnamess);
				toEdit.putString("email_id", emaill);
				toEdit.putString("login_flag", "true");
				toEdit.commit();

				CallDialogue("Update");

				TabPage.bitmap_user_profreal = null;
				img_user.buildDrawingCache();

				TabPage.bitmap_user_profreal = img_user.getDrawingCache();
				// saveToInternalSorage(TabPage.bitmap_user_profreal);

				saveImage(getApplicationContext(), TabPage.bitmap_user_profreal, "user_prof", null);

				TabUser.img_userrr.setImageBitmap(null);
				TabUser.img_userrr.destroyDrawingCache();

				img_user.destroyDrawingCache();
				TabUser.img_userrr.setImageBitmap(TabPage.bitmap_prof);
				if (TabPage.update_selection.equalsIgnoreCase("user")) {
					TabPage.tabHost.setCurrentTab(0);
				} else if (TabPage.update_selection.equalsIgnoreCase("more")) {
					TabPage.tabHost.setCurrentTab(4);
				}

			} else {
				CallDialogue("1");
			}

		} else if (id == R.id.txt_uploadpic) {
			TabPage.img_selec = "update";
			Intent ineee = new Intent(getApplicationContext(), ImageSelector.class);
			startActivity(ineee);

		} else if (id == R.id.btn_backusr) {
			if (TabPage.update_selection.equalsIgnoreCase("user")) {
				TabPage.tabHost.setCurrentTab(0);
			} else if (TabPage.update_selection.equalsIgnoreCase("more")) {
				TabPage.tabHost.setCurrentTab(4);
			}

		}
	}

	private void CallDialogue(String here) {

		try {
			final Dialog Alert_dialogg22;
			Alert_dialogg22 = new Dialog(UserUpdate.this);
			Alert_dialogg22.requestWindowFeature(Window.FEATURE_NO_TITLE);
			Alert_dialogg22.setContentView(R.layout.dialog_error);
			Alert_dialogg22.setCancelable(false);

			TextView txt_msg = (TextView) Alert_dialogg22.findViewById(R.id.shshs);
			Button btn_okksss = (Button) Alert_dialogg22.findViewById(R.id.btn_ok);
			TextView txt_errr = (TextView) Alert_dialogg22.findViewById(R.id.txt_errr);

			if (here.equalsIgnoreCase("1")) {
				txt_msg.setText("You must complete all fields");
				txt_errr.setText("Error");
			} else if (here.equalsIgnoreCase("Update")) {
				txt_msg.setText("Information has been updated.");
				txt_errr.setText("Updated");
			} else if (here.equalsIgnoreCase("333")) {
				txt_msg.setText("Please fill in all information.");
				txt_errr.setText("Alert");
			}

			Alert_dialogg22.show();

			btn_okksss.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Alert_dialogg22.dismiss();
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static Bitmap getImageBitmap(Context context, String name, String extension) {
		name = name + "." + extension;
		try {
			FileInputStream fis = context.openFileInput(name);
			Bitmap b = BitmapFactory.decodeStream(fis);
			// b=getCroppedBitmap(b);
			fis.close();
			return b;
		} catch (Exception e) {
		}
		return null;
	}

	public static void saveImage(Context context, Bitmap b, String name, String extension) {
		name = name + "." + extension;
		FileOutputStream out;
		try {
			out = context.openFileOutput(name, Context.MODE_PRIVATE);
			b.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
