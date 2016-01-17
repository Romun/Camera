package com.rumon.superflashlight;

import com.rumon.superfiashlight.R;
import com.rumon.widget.HideTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class PoliceLight extends Colorlight {
	protected boolean mPoliceState;
	protected HideTextView mHideTextviewPoliceLight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHideTextviewPoliceLight = (HideTextView) findViewById(R.id.textview_hide_police_light);

	}

	class PoliceThread extends Thread {
		public void run() {
			mPoliceState = true;
			while(mPoliceState)
			{
				try {
					mHandler.sendEmptyMessage(Color.BLUE);
					sleep(100);
					mHandler.sendEmptyMessage(Color.BLACK);
					sleep(100);
					mHandler.sendEmptyMessage(Color.RED);
					sleep(100);
					mHandler.sendEmptyMessage(Color.BLACK);
					sleep(100);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message message) {
			int color = message.what;
			mUIPoliceLight.setBackgroundColor(color);
		}
	};
}
