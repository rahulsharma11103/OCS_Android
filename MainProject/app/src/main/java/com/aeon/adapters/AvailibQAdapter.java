package com.aeon.adapters;

import java.util.ArrayList;
import java.util.List;

import com.aeon.asynctask.GetNextDates;
import com.aeon.constants.AvailabilityDateQ;
import com.aeon.myoncall.R;
import com.aeon.myoncall.SelectAvailibilty;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AvailibQAdapter extends BaseAdapter {

	ArrayList<AvailabilityDateQ> _data = new ArrayList<AvailabilityDateQ>();
	Context _c;
	public static String select_refresh = "";

	public AvailibQAdapter(ArrayList<AvailabilityDateQ> data, Context context) {
		_data = data;
		_c = context;
	}

	@Override
	public int getCount() {
		if (_data == null)
			return 0;
		else
			return _data.size();
	}

	@Override
	public Object getItem(int position) {
		if (_data == null)
			return null;
		else
			return _data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	static class ViewHolderr {
		protected static TextView text_name;
		protected static CheckBox chq_one;
		protected static CheckBox chq_two;
		protected static CheckBox chq_three;
		protected static CheckBox chq_four;
		protected static CheckBox chq_five;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		ViewHolderr viewHolderr = null;

		LayoutInflater vi = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = vi.inflate(R.layout.list_item_quick, null);

		RelativeLayout lay_l1 = (RelativeLayout) v.findViewById(R.id.lay_l1);
		RelativeLayout lay_l2 = (RelativeLayout) v.findViewById(R.id.lay_l2);
		RelativeLayout lay_l3 = (RelativeLayout) v.findViewById(R.id.lay_l3);
		RelativeLayout lay_l4 = (RelativeLayout) v.findViewById(R.id.lay_l4);
		RelativeLayout lay_l5 = (RelativeLayout) v.findViewById(R.id.lay_l5);
		RelativeLayout lay_l6 = (RelativeLayout) v.findViewById(R.id.lay_l6);
		int remain_one = 0;
		if (SelectAvailibilty.lay_width != 0) {
			if (SelectAvailibilty.lay_width > 500) {
				lay_l1.getLayoutParams().width = 175;
				remain_one = SelectAvailibilty.lay_width - 175;
			} else if (SelectAvailibilty.lay_width <= 500) {
				lay_l1.getLayoutParams().width = 145;
				remain_one = SelectAvailibilty.lay_width - 145;
			}
			int dith = remain_one / 5;
			lay_l2.getLayoutParams().width = dith;
			lay_l3.getLayoutParams().width = dith;
			lay_l4.getLayoutParams().width = dith;
			lay_l5.getLayoutParams().width = dith;
			lay_l6.getLayoutParams().width = dith;
		}

		ViewHolderr.text_name = (TextView) v.findViewById(R.id.textView1);
		ViewHolderr.chq_one = (CheckBox) v.findViewById(R.id.checkBox1);
		ViewHolderr.chq_two = (CheckBox) v.findViewById(R.id.checkBox2);
		ViewHolderr.chq_three = (CheckBox) v.findViewById(R.id.checkBox3);
		ViewHolderr.chq_four = (CheckBox) v.findViewById(R.id.checkBox4);
		ViewHolderr.chq_five = (CheckBox) v.findViewById(R.id.checkBox5);

		// select client please
		AvailabilityDateQ msg = _data.get(position);

		ViewHolderr.text_name.setText(msg.getDate_to());

		if (SelectAvailibilty.openation.equalsIgnoreCase("apply_fav")) {

			if (SelectAvailibilty.fav_check_onee.get(position).equalsIgnoreCase("AM")) {
				ViewHolderr.chq_one.setChecked(true);
				SelectAvailibilty.check_onee.set(position, "AM");
			}
			if (SelectAvailibilty.fav_chhck_twoo.get(position).equalsIgnoreCase("PM")) {
				ViewHolderr.chq_two.setChecked(true);
				SelectAvailibilty.chhck_twoo.set(position, "PM");
			}
			if (SelectAvailibilty.fav_check_three.get(position).equalsIgnoreCase("A/Night")) {
				ViewHolderr.chq_three.setChecked(true);
				SelectAvailibilty.check_three.set(position, "A/Night");
			}
			if (SelectAvailibilty.fav_check_four.get(position).equalsIgnoreCase("S/O")) {
				ViewHolderr.chq_four.setChecked(true);
				SelectAvailibilty.check_four.set(position, "S/O");
			}
			if (SelectAvailibilty.fav_check_five.get(position).equalsIgnoreCase("N/A")) {
				ViewHolderr.chq_five.setChecked(true);
				SelectAvailibilty.check_five.set(position, "N/A");
			}
		}
		if (select_refresh.equalsIgnoreCase("refresh")) {

			try {

				System.out.println("i call");
				if (SelectAvailibilty.check_onee.get(position).equalsIgnoreCase("AM")) {
					ViewHolderr.chq_one.setChecked(true);
					SelectAvailibilty.check_onee.set(position, "AM");
				} else {
					ViewHolderr.chq_one.setChecked(false);

				}
				if (SelectAvailibilty.chhck_twoo.get(position).equalsIgnoreCase("PM")) {
					ViewHolderr.chq_two.setChecked(true);
					SelectAvailibilty.chhck_twoo.set(position, "PM");
				} else {
					ViewHolderr.chq_two.setChecked(false);
				}
				if (SelectAvailibilty.check_three.get(position).equalsIgnoreCase("A/Night")) {
					ViewHolderr.chq_three.setChecked(true);
					SelectAvailibilty.check_three.set(position, "A/Night");
				} else {
					ViewHolderr.chq_three.setChecked(false);

				}
				if (SelectAvailibilty.check_four.get(position).equalsIgnoreCase("S/O")) {
					ViewHolderr.chq_four.setChecked(true);
					SelectAvailibilty.check_four.set(position, "S/O");
				} else {
					ViewHolderr.chq_four.setChecked(false);

				}
				if (SelectAvailibilty.check_five.get(position).equalsIgnoreCase("N/A")) {
					ViewHolderr.chq_five.setChecked(true);
					SelectAvailibilty.check_five.set(position, "N/A");
				} else {
					ViewHolderr.chq_five.setChecked(false);

				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			// select_refresh="";
		}

		viewHolderr.chq_one.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Object tag = ViewHolderr.chq_one.getTag();
				if (isChecked) {
					System.out.println("cehck 0ne--" + position);
					SelectAvailibilty.check_onee.set(position, "AM");

				} else {
					System.out.println("cehck remove 0ne--" + tag);
					Log.d("Checkbox", "removed " + position);
					SelectAvailibilty.check_onee.set(position, "");
					ViewHolderr.chq_one.setChecked(false);
				}
			}
		});
		ViewHolderr.chq_two.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Object tag = ViewHolderr.chq_two.getTag();
				if (isChecked) {
					System.out.println("cehck 0ne--" + position);
					SelectAvailibilty.chhck_twoo.set(position, "PM");
				} else {
					System.out.println("cehck remove 0ne--" + tag);
					Log.d("Checkbox", "removed " + position);
					SelectAvailibilty.chhck_twoo.set(position, "");
					ViewHolderr.chq_two.setChecked(false);
				}
			}
		});
		ViewHolderr.chq_three.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Object tag = ViewHolderr.chq_three.getTag();
				if (isChecked) {
					System.out.println("cehck 0ne--" + position);
					SelectAvailibilty.check_three.set(position, "A/Night");
				} else {
					System.out.println("cehck remove 0ne--" + tag);
					Log.d("Checkbox", "removed " + position);
					SelectAvailibilty.check_three.set(position, "");
					ViewHolderr.chq_three.setChecked(false);
				}

			}
		});

		ViewHolderr.chq_four.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Object tag = ViewHolderr.chq_four.getTag();
				if (isChecked) {
					System.out.println("cehck 0ne--" + position);
					SelectAvailibilty.check_four.set(position, "S/O");
				} else {
					System.out.println("cehck remove 0ne--" + tag);
					Log.d("Checkbox", "removed " + position);
					SelectAvailibilty.check_four.set(position, "");
					// ViewHolderr.chq_four.setChecked(false);
				}

			}
		});

		ViewHolderr.chq_five.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Object tag = ViewHolderr.chq_five.getTag();
				if (isChecked) {
					System.out.println("cehck 0ne--" + position);
					SelectAvailibilty.check_five.set(position, "N/A");
					SelectAvailibilty.check_three.set(position, "");
					SelectAvailibilty.check_four.set(position, "");
					SelectAvailibilty.chhck_twoo.set(position, "");
					SelectAvailibilty.check_onee.set(position, "");
					// ViewHolderr.chq_four.getTag()
					// notifyDataSetChanged();
					select_refresh = "refresh";
					int index = SelectAvailibilty.listscd.getFirstVisiblePosition();
					View v = SelectAvailibilty.listscd.getChildAt(0);
					int top = (v == null) ? 0 : v.getTop();
					SelectAvailibilty.RefreshUi(index, top);
					// ((AvailibQAdapter)SelectAvailibilty.listscd.getAdapter()).refresh(GetNextDates.arrayy);
				} else {
					System.out.println("cehck remove 0ne--" + tag);
					Log.d("Checkbox", "removed " + position);
					SelectAvailibilty.check_five.set(position, "");

				}

			}
		});
		v.setTag(viewHolderr);

		ViewHolderr.text_name.setTag(position);
		ViewHolderr.chq_five.setTag(position);
		ViewHolderr.chq_four.setTag(position);
		ViewHolderr.chq_three.setTag(position);
		ViewHolderr.chq_two.setTag(position);
		ViewHolderr.chq_one.setTag(position);

		return v;
	}

	// http://stackoverflow.com/questions/17292256/notifydatasetchanged-without-refreshing-the-ui

	public void refresh(List<AvailabilityDateQ> dataList) {
		/*
		 * dataList.clear(); mDataList.addAll(events);
		 */
		System.out.println("i call");

		notifyDataSetChanged();

	}

}