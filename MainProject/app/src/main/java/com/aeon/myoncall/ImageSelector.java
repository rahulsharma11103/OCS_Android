package com.aeon.myoncall;

import com.aeon.asynctask.AsyncGetFolders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ImageSelector extends BaseActivity {
	public static final String ACTION_CLOSE = "com.aeon.myoncall.ACTION_CLOSE";
	public static ListView list_folders;
	Button btn_ucancls;
	private FirstReceiver firstReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setScreenName("ImageSelector Screen");
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_selector);
		
		IntentFilter filter = new IntentFilter(ACTION_CLOSE);
		firstReceiver = new FirstReceiver();
		registerReceiver(firstReceiver, filter);
		list_folders = (ListView) findViewById(R.id.list_folders);
		btn_ucancls = (Button) findViewById(R.id.btn_ucancls);

		AsyncGetFolders getFolders = new AsyncGetFolders(ImageSelector.this);
		getFolders.execute("");

		list_folders.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
				// TODO Auto-generated method stub

				TextView text_foldername = (TextView) v.findViewById(R.id.txt_one);
				TextView text_imgpath = (TextView) v.findViewById(R.id.txt_two);
				TextView text_folderpath = (TextView) v.findViewById(R.id.txt_three);

				String flderpath = text_folderpath.getText().toString();
				String folder_name = text_foldername.getText().toString();
				System.out.println("here is open folder==>>" + flderpath);

				// TabPage.img_selection="updatee";
				Intent ibb = new Intent(getApplicationContext(), ImageGridLayout.class);
				ibb.putExtra("folders", folder_name);
				ibb.putExtra("folde_path", flderpath);
				startActivity(ibb);

			}
		});

		btn_ucancls.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	class FirstReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.e("FirstReceiver", "FirstReceiver");
			if (intent.getAction().equals(ACTION_CLOSE)) {
				ImageSelector.this.finish();
			}
		}
	}
}
