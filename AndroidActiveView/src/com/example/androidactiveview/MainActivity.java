package com.example.androidactiveview;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap.Config;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity {

	private DisplayMetrics displayMetrics;

	private LinearLayout layout1;
	private LinearLayout layout2;

	private int width;
	private int height;

	private int left = 60;
	private int top = 90;
	private int right = 90;
	private int bottom = 100;

	Paint paint = new Paint();

	private MTimer mTimer;
	private Timer timer;

	private CustomView view2;

	private int[] array = { 4, 5, 8, 10, 4, 3, 1, 5, 9, 6, 7, 3, 9, 8, 2 };

	private Handler handler = new Handler() {

		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);

			if (msg.what == 0) {

				for (int j = 0; j < 100; j++) {

					for (int i = 0; i < 15; i++) {

						Random random = new Random();
						int count = random.nextInt(10);
						array[i] = count;
					}
					view2.setState(1);
//					paint.setColor(0XFFB3B500);
//				LinearGradient lg = new LinearGradient(x0, y0, x1, y1, color0, color1, tile)
//					paint.setShader(shader);
					view2.setPaint(paint);
					view2.setArray(array);
					view2.invalidate();
				}

			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		width = displayMetrics.widthPixels;
		height = displayMetrics.heightPixels;

		findView();

	}

	private void findView() {
		// view.setPaint(paint);
		// view.setRect(rect);
		layout1 = (LinearLayout) this.findViewById(R.id.volume);
		layout2 = (LinearLayout) this.findViewById(R.id.volume_draw);
		// view.setMinimumHeight(10);
		// view.setMinimumWidth(500);
		// view.invalidate();
		// layout.addView(view);

		paint.setColor(0XFFB3B500);
		paint.setAntiAlias(true);
//
//		addCustomView(layout1);
//		addCustomViewForAbove(layout2, 10);

	}
	private class MTimer extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub

//			handler.sendEmptyMessage(0);
		}

	}

	public void onButtonClick(View view){
		
		
		switch(view.getId()){
		
		case R.id.button:
			startActivity(new Intent(MainActivity.this,MultiActivity.class));
			break;
		}
		
	}
	
	private void addCustomView(LinearLayout layouts) {

		paint.setColor(0X33A1A2A5);

		CustomView view2 = new CustomView(this);
		view2.setMinimumHeight(50);
		view2.setMinimumWidth(100);
		view2.setState(0);
		view2.setPaint(paint);
		view2.setCOUNT(10);
		layouts.addView(view2);
		view2.invalidate();

	}

	private void addCustomViewForAbove(LinearLayout layouts, int count) {

		view2 = new CustomView(this);
		view2.setMinimumHeight(50);
		view2.setMinimumWidth(100);
		view2.setPaint(paint);
		view2.setCOUNT(count);
		view2.setState(1);
		view2.setArray(array);
		layouts.addView(view2);
		view2.invalidate();

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

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		timer = new Timer();
		mTimer = new MTimer();
		timer.schedule(mTimer, 1000, 100);
	}
}
