package org.jenseigne.activity.drawletter;

import java.util.ArrayList;
import java.util.List;

import org.jenseigne.lettre.ASymbol;
import org.jenseigne.lettre.LetterAUpper;
import org.jenseigne.lettre.LetterBUpper;
import org.jenseigne.lettre.LetterCUpper;
import org.jenseigne.lettre.LetterDUpper;
import org.jenseigne.lettre.LetterEUpper;
import org.jenseigne.lettre.LetterFUpper;
import org.jenseigne.lettre.LetterGUpper;
import org.jenseigne.lettre.LetterHUpper;
import org.jenseigne.lettre.LetterIUpper;
import org.jenseigne.lettre.LetterJUpper;
import org.jenseigne.lettre.LetterKUpper;
import org.jenseigne.lettre.LetterLUpper;
import org.jenseigne.lettre.LetterMUpper;
import org.jenseigne.lettre.LetterNUpper;
import org.jenseigne.lettre.LetterOUpper;
import org.jenseigne.lettre.LetterPUpper;
import org.jenseigne.lettre.LetterQUpper;
import org.jenseigne.lettre.LetterRUpper;
import org.jenseigne.lettre.LetterSUpper;
import org.jenseigne.lettre.LetterTUpper;
import org.jenseigne.lettre.LetterUUpper;
import org.jenseigne.lettre.LetterVUpper;
import org.jenseigne.lettre.LetterWUpper;
import org.jenseigne.lettre.LetterXUpper;
import org.jenseigne.lettre.LetterYUpper;
import org.jenseigne.lettre.LetterZUpper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DevDessineLettreView extends View {

	public static final int COEF_MARQUE = 20;

	private ASymbol model;

	public ASymbol getModel() {
		return model;
	}

	public ASymbol tabLettre[] = new ASymbol[] { 
			new LetterAUpper(),
			new LetterBUpper(), new LetterCUpper(), new LetterDUpper(),
			new LetterEUpper(), new LetterFUpper(), new LetterGUpper(),
			new LetterHUpper(), new LetterIUpper(), new LetterJUpper(),
			new LetterKUpper(), new LetterLUpper(), new LetterMUpper(),
			new LetterNUpper(), new LetterOUpper(), new LetterPUpper(),
			new LetterQUpper(), new LetterRUpper(), new LetterSUpper(),
			new LetterTUpper(), new LetterUUpper(), new LetterVUpper(),
			new LetterWUpper(), new LetterXUpper(), new LetterYUpper(),
			new LetterZUpper() 			
			};
	
	private List<ASymbol> listCharacter = new ArrayList<ASymbol>(tabLettre.length);

	public DevDessineLettreView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		setFocusableInTouchMode(true);
		nextLettre();
	}

	public DevDessineLettreView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusableInTouchMode(true);
		nextLettre();
	}

	public DevDessineLettreView(Context context) {
		super(context);
		setFocusableInTouchMode(true);
		nextLettre();
	}

	private static BitmapDrawable bmdMotif;

	protected final static Bitmap getMotif(Canvas canvas) {
		if (bmdMotif == null) {
			Config config = Bitmap.Config.ARGB_8888;
			bmdMotif = new BitmapDrawable(Bitmap.createBitmap(
					canvas.getWidth() / 11, canvas.getHeight() / 11, config));
			Canvas cvMotif = new Canvas(bmdMotif.getBitmap());

			Paint paint = new Paint();

			paint.setColor(Color.BLUE);
			paint.setAlpha(255);

			cvMotif.drawCircle(
					cvMotif.getWidth() / 2,
					cvMotif.getHeight() / 2,
					cvMotif.getWidth() > cvMotif.getHeight() ? cvMotif
							.getHeight() / 2 : cvMotif.getWidth() / 2, paint);

		}
		return bmdMotif.getBitmap();
	}

	private static Bitmap bitmapRedPoint;

	protected final static Bitmap getRedPoint(Canvas canvas) {
		if (bitmapRedPoint == null) {
			Config config = Bitmap.Config.ARGB_8888;
			BitmapDrawable bmdRedPoint = new BitmapDrawable(
					Bitmap.createBitmap(canvas.getWidth() / 10,
							canvas.getHeight() / 10, config));
			Canvas cvMotif = new Canvas(bmdRedPoint.getBitmap());

			Paint paint = new Paint();

			paint.setColor(Color.RED);
			paint.setAlpha(255);

			cvMotif.drawCircle(
					cvMotif.getWidth() / 2,
					cvMotif.getHeight() / 2,
					cvMotif.getWidth() > cvMotif.getHeight() ? cvMotif
							.getHeight() / 2 : cvMotif.getWidth() / 2, paint);
			bitmapRedPoint = bmdRedPoint.getBitmap();
		}
		return bitmapRedPoint;
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
				int w = getWidth() / COEF_MARQUE;
				int h = getHeight() / COEF_MARQUE;

				int x1 = (int) (event.getX() - w);
				int y1 = (int) (event.getY() - h);

				canvas.drawBitmap(getMotif(canvas), x1, y1, paint);

				if (model.nearCurrentStep(x1, y1)) {
					model.nextAnimateStep();

					updateModelBitmap();

				}

			}
			postInvalidate();
		} catch (Throwable th) {
			th.printStackTrace();
		}
		return true;
	}

	private BitmapDrawable bmdLettre;

	private BitmapDrawable bmdDessin;

	private Paint paint = new Paint();

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		synchronized (this) {

			initBmdLettre();
			if (bmdDessin == null
					|| bmdDessin.getBitmap().getWidth() != getWidth()
					|| bmdDessin.getBitmap().getHeight() != getHeight()) {

				initBmd();

			}

			if (bmdLettre != null) {
				canvas.drawBitmap(bmdLettre.getBitmap(), 0, 0, paint);
			}
			canvas.drawBitmap(bmdDessin.getBitmap(), 0, 0, paint);

			Point lastPoint = model.getCurrentStepPoint();
			if (lastPoint != null) {
				Bitmap btPoint = getRedPoint(canvas);
				int dx = btPoint.getWidth() / 2;				
				canvas.drawBitmap(bitmapRedPoint, lastPoint.x - dx, lastPoint.y
						- dx, paint);
			}

		}
	}

	private void initBmdLettre() {
		if (bmdLettre == null) {
			updateModelBitmap();
		}

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

	public void nextLettre() {
		synchronized (this) {
			
			bmdDessin = null;
			bmdLettre = null;
			if(listCharacter.isEmpty()) {
				for (ASymbol symbol : tabLettre) {
					listCharacter.add(symbol);
				}
			}
			
			model = listCharacter.remove(listCharacter.size()-1);
			model.clearAnimateStep();
			System.out.println("Lettre " + model.getCharacter());
			postInvalidate();
		}
		updateModelBitmap();
	}

	public char getModelChar() {
		return model.getCharacter();
	}

	public void launchRetry() {
		nextLettre();
	}

	public void updateModelBitmap() {
		if (!updateRunning)
			new UpdateModdelBitmapThread().start();
	}

	private boolean updateRunning = false;

	private class UpdateModdelBitmapThread extends Thread {

		@Override
		public void run() {
			super.run();

			if (!updateRunning && getWidth() > 0 && getHeight() > 0) {
				updateRunning = true;
				try {
					synchronized (DevDessineLettreView.this) {
						Config config = Bitmap.Config.ARGB_8888;
						BitmapDrawable bmdTmp = new BitmapDrawable(
								Bitmap.createBitmap(getWidth(), getHeight(),
										config));
						Canvas canvas = new Canvas(bmdTmp.getBitmap());
						Paint paint = new Paint();

						paint.setColor(Color.WHITE);
						paint.setAlpha(255);
						canvas.drawRect(
								new Rect(0, 0, getWidth(), getHeight()), paint);
						
						
						paint.setColor(Color.BLUE);
						
						
						
						model.draw(canvas);

						bmdLettre = bmdTmp;
						postInvalidate();

					}
				} finally {
					updateRunning = false;
				}
			}
		}
		
		
	}
	
	public final void drawHLine(Canvas c,Paint p,float ratio) {
		float f = ratio * getHeight();
		c.drawLine(0, f, getWidth(), f, p);			
	}
	
	public final void drawVLine(Canvas c,Paint p,float ratio) {
		float f = ratio * getWidth();
		c.drawLine(f, 0, f, getHeight(), p);			
	}
}
