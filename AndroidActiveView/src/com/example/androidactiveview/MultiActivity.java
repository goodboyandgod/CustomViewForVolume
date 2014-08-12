package com.example.androidactiveview;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MultiActivity extends Activity {

	private RelativeLayout multi;

	private RelativeLayout customBackgroud;

	private LinearLayout layout1;
	private LinearLayout layout2;

	private FrameLayout custom;
	private Paint paint;
	private CustomView view;

	private int[] array = { 4, 5, 8, 10, 4, 3, 1, 5, 9, 6 };

	private int[] color = { 0XFFB1B100, 0XFFC5C702, 0XFFDDE000, 0XFFF5F914,
			0XFFFEFE5B, 0XFFFCFF72, 0XFFFFFF8E, 0XFFFDFD9F, 0XFFFCFCB2,
			0XFFFEFECC };
	private int source_left;
	private int source_top;
	private int source_right;
	private int source_bottom;

	private int[][] colors;

	private int width;
	private int height;
	Random random = new Random();
	private Timer timer;
	private MTimer mTimer;

	private Handler handler = new Handler() {

		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);

			if (msg.what == 0) {
				colors = new int[10][10];
					for (int i = 0; i < 10; i++) {
						int count = random.nextInt(10);
						for (int k = 0; k < count; k++) {

							colors[i][k] = color[k];
						}
						Log.d("Array", colors[i][9]+"");
						view.setPaint(paint);
						// view.setCOUNT(count);
						view.setState(1);
						view.setColors(colors);
						view.invalidate();
					}
				}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mul_mix);
		paint = new Paint();
		paint.setAntiAlias(true);
		// paint.setColor(0X00000000);
		customBackgroud = (RelativeLayout) findViewById(R.id.custom_backgroud);

		colors = new int[10][10];
		for (int i = 0; i < 10; i++) {
			int count = random.nextInt(10);
			for (int j = 0; j < count; j++) {
				colors[i][j] = color[j];
			}
		}
		findView();

	}

	private void findView() {

		custom = (FrameLayout) this.findViewById(R.id.custom);
		multi = (RelativeLayout) this.findViewById(R.id.multi);
		layout1 = (LinearLayout) this.findViewById(R.id.volume_1);
		layout2 = (LinearLayout) this.findViewById(R.id.volume_draw_1);
		 addCustomView(layout1);
		addCustomViewForAbove(layout2);

	}

	private class MTimer extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			handler.sendEmptyMessage(0);
		}
	}

	private void addCustomView(LinearLayout layouts) {

		paint.setColor(0X33A1A2A5);

		CustomView view2 = new CustomView(this);
		// view2.init(source_left, source_top, source_right, source_bottom);
		view2.init(42, 300, 94, 315);
		view2.setPadding(10);
		view2.setRow(7);
		view2.setMinimumHeight(10);
		view2.setMinimumWidth(10);
		view2.setState(0);
		view2.setPaint(paint);
		view2.setCOUNT(10);
		layouts.addView(view2);
		view2.invalidate();

	}

	private void addCustomViewForAbove(LinearLayout layouts) {

		view = new CustomView(this);
		view.setMinimumHeight(10);
		view.setMinimumWidth(10);
		Log.d("竖屏", "竖屏");
		view.init(42, 300, 94, 315);
		view.setPadding(10);
		view.setRow(7);

		// paint.setColor(0XFFB3B500);
		view.setPaint(paint);
		view.setState(1);

		view.setColors(colors);
		// view.init(source_left, source_top, source_right, source_bottom);
		layouts.addView(view);
		view.invalidate();

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		width = multi.getWidth();
		height = multi.getHeight();
		source_left = multi.getLeft() + 15;
		source_top = multi.getTop() + height;
		source_right = source_left + getViewWidth();
		source_bottom = source_top + getViewHeight();

		// multi.invalidate();
		// Log.d("left", left + "");
		// Log.d("right", right + "");
		// Log.d("bottom", bottom + "");
		// Log.d("top", top + "");
		// Log.d("width", width + "");
		// Log.d("height", height + "");
		// Log.d("坐标X", multi.getX() + "");
		// Log.d("坐标Y", multi.getY() + "");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);

	}

	private int getViewWidth() {

		int step = 0;
		step = (width - 30 - 10 * 9) / 10;
		Log.d("width_step", step + "");
		return step;
	}

	private int getViewHeight() {

		int step = 0;
		step = (height - 15 - 15 * 9) / 10;
		Log.d("height_step", step + "");
		return step;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		timer = new Timer();
		mTimer = new MTimer();
		timer.schedule(mTimer, 1000, 100);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		if (timer != null)
			timer.cancel();
	}

}
