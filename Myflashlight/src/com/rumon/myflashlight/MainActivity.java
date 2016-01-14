package com.rumon.myflashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private boolean isopent = false;
	private Camera camera=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn1 =(Button) findViewById(R.id.btn1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

			if(!isopent){
					startPreview();
					isopent=true;
				}else{
					stopPreview();
					isopent=false;
				}
				
				
			}
		});
	}
	private void startPreview(){
		camera = Camera.open();
		try {
			//camera.setPreviewDisplay(cameraPreview.getHolder());			
			//camera.setDisplayOrientation(90);
			 Parameters params = camera.getParameters();
             params.setFlashMode(Parameters.FLASH_MODE_TORCH);
             camera.setParameters(params);
			camera.startPreview();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void stopPreview(){
		camera.stopPreview();
		camera.release();
		 camera = null;
	}
	
	
	
	
}
