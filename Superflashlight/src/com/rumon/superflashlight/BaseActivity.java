package com.rumon.superflashlight;

import com.rumon.superfiashlight.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.provider.Settings.SettingNotFoundException;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class BaseActivity extends Activity {

	protected enum UIType {
		UI_TYPE_MAIN, UI_TYPE_FLASHLIGHT, UI_TYPE_WARNINGLIGHT, UI_TYPE_MORSE, UI_TYPE_BLUB, UI_TYPE_COLOR, UI_TYPE_POLICE, UI_TYPE_SETTINGS
	}
	
	protected int mCurrentWarningLightInterval = 500;
	protected int mCurrentPoliceLightInterval = 100;
	
	protected SeekBar mSeekBarWarningLight;
	protected SeekBar mSeekBarPoliceLight;
	protected Button mButtonAddShortcut;
	protected Button mButtonRemoveShortcut;
	
	protected int mFinishCount = 0;

	protected SharedPreferences mSharedPreferences;

	protected ImageView mImageViewFlashlight;
	protected ImageView mImageViewFlashlightController;
	protected ImageView mImageViewWarningLight1;
	protected ImageView mImageViewWarningLight2;
	protected EditText mEditTextMorseCode;
	protected ImageView mImageViewBuld;
	
	
	protected Camera mCamera;
	protected Parameters mParameters;
	
	protected FrameLayout mUIflashlight;
	protected LinearLayout mUImain;
	protected LinearLayout mUIwarning;
	protected LinearLayout mUImorse;
	protected FrameLayout mUIbuld;
	protected FrameLayout mUIcolorlight;
	protected FrameLayout mUIPoliceLight;
	protected LinearLayout mUISettings;

	protected UIType mCurrentUIType = UIType.UI_TYPE_FLASHLIGHT;
	protected UIType mLastUIType = UIType.UI_TYPE_FLASHLIGHT;
	
	protected int mDefaultScreenBrightness;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mUIflashlight = (FrameLayout) findViewById(R.id.framelayout_flashlight);
		mUImain = (LinearLayout) findViewById(R.id.linearLayout_main);
		mUIwarning=(LinearLayout) findViewById(R.id.linearlayout_warning_light);
		mUImorse=(LinearLayout)findViewById(R.id.linearlayout_morse);
		mUIbuld=(FrameLayout) findViewById(R.id.framelayout_bulb);
		mUIcolorlight=(FrameLayout) findViewById(R.id.framelayout_colorlight);
		mUIPoliceLight = (FrameLayout)findViewById(R.id.framelayout_police_light);
		mUISettings = (LinearLayout) findViewById(R.id.linearlayout_settings);
		
		mImageViewFlashlight = (ImageView) findViewById(R.id.imageview_flashlight);
		mImageViewFlashlightController = (ImageView) findViewById(R.id.imageview_flashlight_controller);
		mImageViewWarningLight1=(ImageView) findViewById(R.id.imageview_warning_light1);
		mImageViewWarningLight2=(ImageView) findViewById(R.id.imageview_warning_light2);
		mEditTextMorseCode = (EditText)findViewById(R.id.editext_morse_code);
		mImageViewBuld = (ImageView) findViewById(R.id.imageview_bulb);
		mDefaultScreenBrightness  = getScreenBrightness();
		
		mSeekBarPoliceLight = (SeekBar) findViewById(R.id.seekbar_police_light);
		mSeekBarWarningLight = (SeekBar) findViewById(R.id.seekbar_warning_light);

		mButtonAddShortcut = (Button) findViewById(R.id.button_add_shortcut);
		mButtonRemoveShortcut = (Button) findViewById(R.id.button_remove_shortcut);

		mSharedPreferences = getSharedPreferences("config",
				Context.MODE_PRIVATE);
		
		mCurrentWarningLightInterval = mSharedPreferences.getInt(
				"warning_light_interval", 200);
		mCurrentPoliceLightInterval = mSharedPreferences.getInt(
				"police_light_interval", 100);

		mSeekBarPoliceLight.setProgress(mCurrentPoliceLightInterval - 50);
		mSeekBarWarningLight.setProgress(mCurrentWarningLightInterval - 100);
	}

	protected void hideAllui() {
		mUImain.setVisibility(View.GONE);
		mUIflashlight.setVisibility(View.GONE);	
		mUIwarning.setVisibility(View.GONE);
		mUImorse.setVisibility(View.GONE);
		mUIbuld.setVisibility(View.GONE);
		mUIcolorlight.setVisibility(View.GONE);
		mUIPoliceLight.setVisibility(View.GONE);
		mUISettings.setVisibility(View.GONE);
	}
	
	//设置屏幕亮度
	protected void  screenBrightness(float value) {
		try {
			WindowManager.LayoutParams layout =getWindow().getAttributes();
			layout.screenBrightness=value;
			getWindow().setAttributes(layout);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	protected int  getScreenBrightness() {
		int value =0;
		try {
			value =android.provider.Settings.System.getInt(getContentResolver(),
					android.provider.Settings.System.SCREEN_BRIGHTNESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value ;
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		mFinishCount = 0;
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public void finish() {
		mFinishCount++;
		if (mFinishCount == 1) {
			Toast.makeText(this, "再按一次退出！", Toast.LENGTH_LONG).show();
		} else if (mFinishCount == 2) {
			super.finish();
		}
	}
}
