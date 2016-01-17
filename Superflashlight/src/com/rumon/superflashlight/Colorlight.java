package com.rumon.superflashlight;

import com.rumon.superfiashlight.R;
import com.rumon.superflashlight.ColorPickerDialog.OnColorChangedListener;
import com.rumon.widget.HideTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class Colorlight extends Buld implements OnColorChangedListener{
	protected int mCurrentColorLight = Color.RED;
	protected HideTextView mHideTextViewColorLight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHideTextViewColorLight = (HideTextView)findViewById(R.id.textview_hide_color_light);
	}

	@Override
	public void colorChanged(int color) {
		mUIcolorlight.setBackgroundColor(color);
		mCurrentColorLight = color;		
	}
	public void onClick_ShowColorPicker(View view)
	{
		new ColorPickerDialog(this, this, Color.RED).show();
	}
}
