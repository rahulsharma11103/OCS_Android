package com.aeon.myoncall;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.aeon.adapters.MyGridAdapter;
import com.aeon.constants.BitmapHelper;
import com.aeon.constants.GridViewItem;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageGridLayout extends BaseActivity implements OnItemClickListener {

	TextView txt_foldname;
	GridView gridView;
	List<GridViewItem> gridItems;

	Button btn_ucanclgrd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setScreenName("ImageGrid Screen");
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.img_grid_lay);

		txt_foldname = (TextView) findViewById(R.id.txt_foldname);
		gridView = (GridView) findViewById(R.id.gridView);
		btn_ucanclgrd = (Button) findViewById(R.id.btn_ucanclgrd);
		if (TabPage.img_selec.equalsIgnoreCase("update")) {
			// TabPage.img_selec="timesheet";
			Bundle getuserID = getIntent().getExtras();
			String fld_name = getuserID.getString("folders");
			String fld_path = getuserID.getString("folde_path");
			txt_foldname.setText(fld_name);

			setGridAdapter(fld_path);
		} else if (TabPage.img_selec.equalsIgnoreCase("timesheet")) {
			Bundle getuserID = getIntent().getExtras();
			String fld_name = getuserID.getString("folders");
			String fld_path = getuserID.getString("folde_path");
			txt_foldname.setText(fld_name);

			setGridAdapter(fld_path);
		}

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> convertView, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				TextView text = (TextView) arg1.findViewById(R.id.textView);
				ImageView imageView = (ImageView) arg1.findViewById(R.id.imageView);

				String img_selected = text.getText().toString();
				System.out.println("here_i select-->>" + img_selected);
				String im_uri = text.getText().toString();

				if (AddPhotoAct.here.equalsIgnoreCase("select_img")) {
					AddPhotoAct.RETURN_URI = img_selected;
				}

				getBitmap(im_uri);
				Intent myIntent = new Intent(ImageSelector.ACTION_CLOSE);
				sendBroadcast(myIntent);
				finish();
			}
		});
		btn_ucanclgrd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myIntent = new Intent(ImageSelector.ACTION_CLOSE);
				sendBroadcast(myIntent);
				finish();
			}
		});
	}

	public Bitmap getCroppedBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap
				.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		// canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2,
				paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		// Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
		// return _bmp;
		return output;
	}

	public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);

		// "RECREATE" THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		return resizedBitmap;
	}

	private void setGridAdapter(String path) {
		// Create a new grid adapter
		gridItems = createGridItems(path);
		MyGridAdapter adapter = new MyGridAdapter(this, gridItems);

		// Set the grid adapter
		GridView gridView = (GridView) findViewById(R.id.gridView);
		gridView.setAdapter(adapter);
		// Set the onClickListener
		gridView.setOnItemClickListener(this);
	}

	private List<GridViewItem> createGridItems(String directoryPath) {
		List<GridViewItem> items = new ArrayList<GridViewItem>();

		// List all the items within the folder.
		File[] files = new File(directoryPath).listFiles(new ImageFileFilter());
		for (File file : files) {

			// Add the directories containing images or sub-directories
			if (file.isDirectory() && file.listFiles(new ImageFileFilter()).length > 0) {

				items.add(new GridViewItem(file.getAbsolutePath(), true, null));
			}
			// Add the images
			else {
				Bitmap image = BitmapHelper.decodeBitmapFromFile(file.getAbsolutePath(), 50, 50);
				items.add(new GridViewItem(file.getAbsolutePath(), false, image));
			}
		}

		return items;
	}

	private boolean isImageFile(String filePath) {
		if (filePath.endsWith(".jpg") || filePath.endsWith(".png"))
		// Add other formats as desired
		{
			return true;
		}
		return false;
	}

	private class ImageFileFilter implements FileFilter {

		@Override
		public boolean accept(File file) {
			if (file.isDirectory()) {
				return true;
			} else if (isImageFile(file.getAbsolutePath())) {
				return true;
			}
			return false;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub

		if (gridItems.get(position).isDirectory()) {
			setGridAdapter(gridItems.get(position).getPath());
		} else {
			// Display the image
		}

	}

	private Bitmap getBitmap(String path) {
		try {
			int inWidth = 0;
			int inHeight = 0;

			InputStream in = new FileInputStream(path);

			// decode image size (decode metadata only, not the whole image)
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(in, null, options);
			in.close();
			in = null;

			// save width and height
			inWidth = options.outWidth;
			inHeight = options.outHeight;

			// decode full image pre-resized
			in = new FileInputStream(path);
			options = new BitmapFactory.Options();
			// calc rought re-size (this is no exact resize)
			options.inSampleSize = Math.max(inWidth / 420, inHeight / 420);
			// decode full image
			Bitmap roughBitmap = BitmapFactory.decodeStream(in, null, options);

			// calc exact destination size
			Matrix m = new Matrix();
			RectF inRect = new RectF(0, 0, roughBitmap.getWidth(), roughBitmap.getHeight());
			RectF outRect = new RectF(0, 0, 440, 440);
			m.setRectToRect(inRect, outRect, Matrix.ScaleToFit.FILL);
			float[] values = new float[9];
			m.getValues(values);

			// resize bitmap
			if (TabPage.img_selec.equalsIgnoreCase("update")) {

				TabPage.bitmap_prof = Bitmap.createScaledBitmap(roughBitmap,
						(int) (roughBitmap.getWidth() * values[0]),
						(int) (roughBitmap.getHeight() * values[4]), true);

			} else if (TabPage.img_selec.equalsIgnoreCase("timesheet")) {

				AddPhotoAct.btmp = Bitmap.createScaledBitmap(roughBitmap,
						(int) (roughBitmap.getWidth() * values[0]),
						(int) (roughBitmap.getHeight() * values[4]), true);
			}

		} catch (IOException e) {
			Log.e("Image", e.getMessage(), e);
		}
		return null;

	}

	/*
	 * public static Bitmap decodeSampledBitmapFromFile(String filename, int
	 * reqWidth, int reqHeight, ImageCache cache) {
	 * 
	 * // First decode with inJustDecodeBounds=true to check dimensions final
	 * BitmapFactory.Options options = new BitmapFactory.Options();
	 * options.inJustDecodeBounds = true; BitmapFactory.decodeFile(filename,
	 * options);
	 * 
	 * // Calculate inSampleSize options.inSampleSize =
	 * calculateInSampleSize(options, reqWidth, reqHeight);
	 * 
	 * // If we're running on Honeycomb or newer, try to use inBitmap if
	 * (Utils.hasHoneycomb()) { addInBitmapOptions(options, cache); }
	 * 
	 * // Decode bitmap with inSampleSize set options.inJustDecodeBounds =
	 * false; return BitmapFactory.decodeFile(filename, options); }
	 */
}
