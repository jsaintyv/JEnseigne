package org.jenseigne.activity.drawletter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DessineLettreView extends View implements IDessineLettreView {

	private float blanc = 0;

	private BitmapDrawable bmdDessin;

	private BitmapDrawable bmdLettre;

	private float complete = 0;

	private float error = 0;

	private Paint paint = new Paint();

	private char[] tabLettre = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };

	private char model = tabLettre[(int) (tabLettre.length * Math.random())];

	public DessineLettreView(Context context) {
		super(context);
		setFocusableInTouchMode(true);
	}

	public DessineLettreView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusableInTouchMode(true);
	}

	public DessineLettreView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFocusableInTouchMode(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jenseigne.activity.drawletter.IDessineLettreView#getCompleted()
	 */
	@Override
	public float getCompleted() {
		return complete * 100 / blanc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jenseigne.activity.drawletter.IDessineLettreView#getError()
	 */
	@Override
	public float getError() {
		return error * 100 / ((getWidth() * getHeight()) - blanc);
	}

	public char getModel() {
		return model;
	}

	@Override
	public char getModelChar() {

		return getModel();
	}

	private void initBmd() {
		Config config = Bitmap.Config.ARGB_8888;
		bmdDessin = new BitmapDrawable(Bitmap.createBitmap(getWidth(),
				getHeight(), config));
		Canvas canvas = new Canvas(bmdDessin.getBitmap());
		Paint paint = new Paint();

		paint.setColor(Color.WHITE);
		paint.setAlpha(0);
		canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);
	}

	private void initBmdLettre() {
		Config config = Bitmap.Config.ARGB_8888;
		bmdLettre = new BitmapDrawable(Bitmap.createBitmap(getWidth(),
				getHeight(), config));
		Canvas canvas = new Canvas(bmdLettre.getBitmap());
		Paint paint = new Paint();

		paint.setColor(Color.BLACK);
		paint.setAlpha(255);
		canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);

		paint.setColor(Color.WHITE);

		paint.setStrokeWidth(getWidth() / 10);
		paint.setTextSize((getHeight() * 80) / 100);

		int texteTapeWidth = (int) paint.measureText("" + model, 0, 1);

		canvas.drawText("" + model, (getWidth() - texteTapeWidth) / 2,
				(getHeight() * 90 / 100), paint);

		blanc = 0;
		complete = 0;
		error = 0;
		for (int x = 0; x < bmdLettre.getBitmap().getWidth(); x++)
			for (int y = 0; y < bmdLettre.getBitmap().getHeight(); y++) {
				if (bmdLettre.getBitmap().getPixel(x, y) == Color.WHITE) {
					blanc += 1;
				}
			}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jenseigne.activity.drawletter.IDessineLettreView#nextLettre()
	 */
	@Override
	public void nextLettre() {
		model = tabLettre[(int) (tabLettre.length * Math.random())];
		complete = 0;
		error = 0;
		bmdDessin = null;
		bmdLettre = null;
		postInvalidate();

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (bmdLettre == null) {
			initBmdLettre();
		}

		if (bmdDessin == null || bmdDessin.getBitmap().getWidth() != getWidth()
				|| bmdDessin.getBitmap().getHeight() != getHeight()) {

			initBmd();

		}

		canvas.drawBitmap(bmdLettre.getBitmap(), 0, 0, paint);
		canvas.drawBitmap(bmdDessin.getBitmap(), 0, 0, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		try {
			if (bmdDessin != null) {
				Canvas canvas = new Canvas(bmdDessin.getBitmap());
				Paint paint = new Paint();

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					setFocusableInTouchMode(true);

				}

				if (event.getAction() == MotionEvent.ACTION_MOVE
						|| event.getAction() == MotionEvent.ACTION_UP) {

				}

				if (event.getAction() == MotionEvent.ACTION_UP) {

				}

				paint.setColor(Color.BLUE);
				paint.setAlpha(255);
				int w = getWidth() / 10;
				int h = getHeight() / 10;

				int x1 = (int) (event.getX() - w / 2);
				int y1 = (int) (event.getY() - h / 2);
				int x2 = (int) (event.getX() + w / 2);
				int y2 = (int) (event.getY() + h / 2);

				for (int x = x1; x < x2; x++)
					for (int y = y1; y < y2; y++) {
						if (bmdDessin.getBitmap().getPixel(x, y) == Color.BLUE) {
							if (bmdLettre.getBitmap().getPixel(x, y) == Color.WHITE) {
								complete -= 1;
							} else {
								error -= 1;
							}
						}
					}

				canvas.drawRect(new Rect(x1, y1, x2, y2), paint);

				for (int x = x1; x < x2; x++)
					for (int y = y1; y < y2; y++) {
						if (bmdLettre.getBitmap().getPixel(x, y) == Color.WHITE) {
							complete += 1;

						} else {
							error += 1;
						}
					}

			}
			postInvalidate();
		} catch (Throwable th) {
			th.printStackTrace();
		}
		return true;
	}

	public void setModel(char model) {
		this.model = model;
		bmdDessin = null;
		bmdLettre = null;
		invalidate();
	}

	@Override
	public void launchRetry() {
		setModel(model);

	}
}
