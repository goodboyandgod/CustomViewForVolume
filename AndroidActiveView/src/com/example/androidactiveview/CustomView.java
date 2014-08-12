package com.example.androidactiveview;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.Log;
import android.view.View;

public class CustomView extends View {

	private final int BACKGROUD = 0;
	private final int CHANGE = 1;

	private Paint paint;

	private int COUNT;

	private int step;
	private int padding;

	private int row;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	private int source_left;
	private int source_top;
	private int source_right;
	private int source_bottom;

	private int[][] colors;

	public int[][] getColors() {
		return colors;
	}

	public void setColors(int[][] colors) {
		this.colors = colors;
	}

	private int left;
	private int top;
	private int right;
	private int bottom;

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getSource_left() {
		return source_left;
	}

	public void setSource_left(int source_left) {
		this.source_left = source_left;
	}

	public int getSource_top() {
		return source_top;
	}

	public void setSource_top(int source_top) {
		this.source_top = source_top;
	}

	public int getSource_right() {
		return source_right;
	}

	public void setSource_right(int source_right) {
		this.source_right = source_right;
	}

	public int getSource_bottom() {
		return source_bottom;
	}

	public void setSource_bottom(int source_bottom) {
		this.source_bottom = source_bottom;
	}

	private int[] array;
	private int state;

	private int length;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public CustomView(Context context) {
		super(context);
	}

	public void init(int left, int top, int right, int bottom) {
		setSource_left(left);
		setSource_right(right);
		setSource_top(top);
		setSource_bottom(bottom);
		this.left = getSource_left();
		this.right = getSource_right();
		this.bottom = getSource_bottom();
		this.top = getSource_top();

	}

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		// 0X33A1A2A5 背景

		// 0XB3B500 最深
		synchronized (canvas) {

			switch (getState()) {

			case BACKGROUD:
				drawBackgroudView(canvas, getCOUNT());
				break;
			case CHANGE:
				drawFloatView(canvas, getColors());
				// drawFloatViewShader(canvas,getColors());

				break;
			}
		}

	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCOUNT() {
		return COUNT;
	}

	public void setCOUNT(int cOUNT) {
		COUNT = cOUNT;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	private void drawBackgroudView(Canvas canvas, int count) {

		for (int i = 0; i < 10; i++) {

			for (int j = 0; j < count; j++) {

				Rect rect1 = new Rect(left, top, right, bottom);

				canvas.drawRect(rect1, getPaint());
				int temp = bottom - top;
				bottom = top - getRow();
				top = bottom - temp;

			}
			int temp = right - left;
			left = right + getPadding();
			right = left + temp;
			top = getSource_top();
			bottom = getSource_bottom();
		}
		left = getSource_left();
		right = getSource_right();

	}

	private void drawFloatView(Canvas canvas, int[][] array) {

		// int [][] array = new int[10][10];
		for (int i = 0; i < 10; i++) {
			// 控制列数
			for (int j = 0; j < array[i].length; j++) {

				Log.d("length", array[i].length + "");
				Rect rect1 = new Rect(left, top, right, bottom);
				int colors[] = array[i];
				Log.d("颜色", colors[j] + "");
				getPaint().setColor(colors[j]);
				Log.d("画图", "画图");
				canvas.drawRect(rect1, getPaint());
				int temp = bottom - top;
				bottom = top - getRow();
				top = bottom - temp;
			}

			int temp = right - left;
			left = right + getPadding();
			right = left + temp;
			top = getSource_top();
			bottom = getSource_bottom();
		}
		left = getSource_left();
		right = getSource_right();
	}

	private void drawFloatViewShader(Canvas canvas, int[][] array) {

		// int [][] array = new int[10][10];
		for (int i = 0; i < 10; i++) {
			// 控制列数
			for (int j = 0; j < array[i].length; j++) {

				Log.d("length", array[i].length + "");
				Rect rect1 = new Rect(left, top, right, bottom);
				int colors[] = array[i];
				Log.d("颜色", colors[j] + "");
				LinearGradient lg = new LinearGradient(left, top, right,
						bottom, colors[j], colors[j], TileMode.REPEAT);
				// getPaint().setColor(colors[j]);
				getPaint().setShader(lg);
				Log.d("画图", "画图");
				canvas.drawRect(rect1, getPaint());
				int temp = bottom - top;
				bottom = top - getRow();
				top = bottom - temp;
			}

			int temp = right - left;
			left = right + getPadding();
			right = left + temp;
			top = getSource_top();
			bottom = getSource_bottom();
		}
		left = getSource_left();
		right = getSource_right();
	}

}
