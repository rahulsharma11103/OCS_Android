package com.aeon.adapters;

import java.util.ArrayList;

import com.aeon.constants.ImageFolders;
import com.aeon.myoncall.MyApplication;
import com.aeon.myoncall.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FoldeListAdapter extends BaseAdapter {

	ArrayList<ImageFolders> _data = new ArrayList<ImageFolders>();
	Context _c;
	ImageLoader imageLoader;
	DisplayImageOptions options;
	
	public FoldeListAdapter(ArrayList<ImageFolders> data, Context context) {
		_data = data;
		_c = context;
		
		imageLoader = MyApplication.getImageLoader();
		options = MyApplication.getImageOption();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) _c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_frnd, null);
		}

		TextView text_name = (TextView) v.findViewById(R.id.txt_one);
		TextView text_date = (TextView) v.findViewById(R.id.txt_two);
		TextView text_desc = (TextView) v.findViewById(R.id.txt_three);
		ImageView img_folder = (ImageView) v.findViewById(R.id.img_folder);
		TextView txt_count = (TextView) v.findViewById(R.id.txt_count);

		// font and set marquee also here
		// txt_list_discription.setTypeface(type_normal);

		// select client please
		ImageFolders msg = _data.get(position);

		text_date.setText(msg.getFolder_img_path());
		text_name.setText(msg.getFolder_name());
		text_desc.setText(msg.getFolder_path());

		String img_path = msg.getFolder_img_path();
		System.out.println("image pathh----->>" + img_path);
		//Bitmap bmp = BitmapFactory.decodeFile(img_path);
		//img_folder.setImageBitmap(bmp);
		
		imageLoader.displayImage("file://" + img_path, img_folder,
				options);
		
		txt_count.setText(msg.getImagescounr());
		// text_name.setBackgroundColor(Color.BLUE);
		// text_Url.setText(msg.getSpinner_Url());

		return v;
	}

}
