package com.rumon.superflashlight;

import com.rumon.superfiashlight.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Settings {

	public void onclick_toflashlight(View view) {
		hideAllui();
		mUIflashlight.setVisibility(View.VISIBLE);
		mLastUIType = UIType.UI_TYPE_FLASHLIGHT;
		mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
	}

	public void onclick_towarninglight(View view) {
		hideAllui();
		mUIwarning.setVisibility(View.VISIBLE);
		mLastUIType = UIType.UI_TYPE_WARNINGLIGHT;
		mCurrentUIType = mLastUIType;
		screenBrightness(1f);
		new WarningLightThread().start();
	}
	
	public void onClick_ToBulb(View view){
		hideAllui();
		mUIbuld.setVisibility(View.VISIBLE);
		mHideTextViewBulb.hide();
		mHideTextViewBulb.setTextColor(Color.RED);
		mLastUIType=UIType.UI_TYPE_BLUB;
		mCurrentUIType=mLastUIType;
	}

	public void onclick_tomorse(View view) {
		hideAllui();
		mUImorse.setVisibility(View.VISIBLE);
		mLastUIType = UIType.UI_TYPE_MORSE;
		mCurrentUIType = mLastUIType;
	}
	
	public void onClick_ToColor(View view){
		hideAllui();
		mUIcolorlight.setVisibility(View.VISIBLE);
		mLastUIType=UIType.UI_TYPE_COLOR;
		screenBrightness(1f);
		mCurrentUIType=mLastUIType;
		
	}
	public void onClick_ToPolice(View view)
    {
		hideAllui();
    	mUIPoliceLight.setVisibility(View.VISIBLE);
    	screenBrightness(1f);
    	mLastUIType = UIType.UI_TYPE_POLICE;
    	mCurrentUIType = mLastUIType;
    	mHideTextviewPoliceLight.hide();
    	new PoliceThread().start();
    }
	
	public void onClick_ToSettings(View view) {
		hideAllui();
		mUISettings.setVisibility(View.VISIBLE);
		mLastUIType = UIType.UI_TYPE_SETTINGS;
		mCurrentUIType = mLastUIType;

	}

	public void onclick_controller(View view) {
		hideAllui();
		if (mCurrentUIType != UIType.UI_TYPE_MAIN) {
			mUImain.setVisibility(View.VISIBLE);
			mCurrentUIType = UIType.UI_TYPE_MAIN;
			mWarningLightFlicker = false;
			screenBrightness(mDefaultScreenBrightness / 255f);
			if (mBulbCrossFadeFlag)
				mDrawable.reverseTransition(0);
			mBulbCrossFadeFlag = false;
			
			mSharedPreferences
										.edit()
										.putInt("warning_light_interval",
												mCurrentWarningLightInterval)
										.putInt("police_light_interval",
												mCurrentPoliceLightInterval).commit();
		} else {
			switch (mLastUIType) {
			case UI_TYPE_FLASHLIGHT:
				mUIflashlight.setVisibility(View.VISIBLE);
				screenBrightness(1f);
				mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
				break;
			case UI_TYPE_WARNINGLIGHT:
				mUIwarning.setVisibility(View.VISIBLE);
				mCurrentUIType = UIType.UI_TYPE_WARNINGLIGHT;
				new WarningLightThread().start();
				break;
			case UI_TYPE_MORSE:
				mUImorse.setVisibility(View.VISIBLE);
				mCurrentUIType = UIType.UI_TYPE_MORSE;
				break;
			case UI_TYPE_BLUB:
				mUIbuld.setVisibility(View.VISIBLE);
				mCurrentUIType=UIType.UI_TYPE_BLUB;
				break;
			case UI_TYPE_POLICE:
				mUIPoliceLight.setVisibility(View.VISIBLE);
				mCurrentUIType = UIType.UI_TYPE_POLICE;
				new PoliceThread().start();
				break;
			case UI_TYPE_SETTINGS:
				mUISettings.setVisibility(View.VISIBLE);
				mCurrentUIType = UIType.UI_TYPE_SETTINGS;
				break;

			default:
				break;
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
