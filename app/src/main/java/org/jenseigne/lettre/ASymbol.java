package org.jenseigne.lettre;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;

public abstract class ASymbol {

	public static final PointF BOTTOM_RIGHT_CORNER = new PointF(0.1f, 0.9f);
	public static final PointF BOTTOM_MIDDLE_RIGHT = new PointF(0.3f, 0.9f);
	public static final PointF BOTTOM_MIDDLE_LEFT = new PointF(0.7f, 0.9f);
	public static final PointF BOTTOM_MIDDLE = new PointF(0.5f, 0.9f);
	public static final PointF MIDDLE_BOTTOM_MIDDLE = new PointF(0.5f, 0.70f);
	public static final PointF BOTTOM_LEFT_CORNER = new PointF(0.9f, 0.9f);
	public static final PointF RIGHT_MIDDLE = new PointF(0.1f, 0.5f);
	public static final PointF MIDDLE = new PointF(0.5f, 0.5f);
	public static final PointF LEFT_MIDDLE = new PointF(0.9f, 0.5f);
	public static final PointF UPPER_RIGHT_CORNER = new PointF(0.1f, 0.1f);
	public static final PointF UPPER_MIDDLE = new PointF(0.5f, 0.1f);
	public static final PointF MIDDLE_UPPER_MIDDLE = new PointF(0.5f, 0.3f);
	public static final PointF UPPER_LEFT_CORNER = new PointF(0.9f, 0.1f);
	public static final PointF MIDDLE_RIGHT_MIDDLE = new PointF(0.3f, 0.5f);
	public static final PointF MIDDLE_LEFT_MIDDLE = new PointF(0.7f, 0.5f);

	private int animateStep = 5;
	private int animateStepInc = 5;

	private Paint paintLine = new Paint();

	public ArrayList<GraphicPrimitive> listGraphic = new ArrayList<GraphicPrimitive>();

	public ASymbol() {
		initListGraphic();
			

		paintLine.setColor(Color.BLACK);
		paintLine.setAlpha(255);

	}

	public void append(GraphicPrimitive graphic) {
		listGraphic.add(graphic);
	}

	private static BitmapDrawable bmdMotif;
	private static final int RATIO_MOTIF = 20;
	public static final Point RUPTURE_POINT = new Point(-1, -1);

	protected final static Bitmap getMotif(Canvas canvas) {
		if (bmdMotif == null) {			
			Config config = Bitmap.Config.ARGB_8888;
			bmdMotif = new BitmapDrawable(Bitmap.createBitmap(canvas.getWidth()
					/ RATIO_MOTIF, canvas.getHeight() / RATIO_MOTIF, config));
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

	private Point[] tabPoint = null;
	private boolean end;

	public void drawModel(Canvas canvasLettre) {
		synchronized (this) {
			generateTabPoint(canvasLettre);

			int previousIdx = 0;
			int nextIdx;
			Point previous = tabPoint[previousIdx];
			Bitmap motif = getMotif(canvasLettre);
			int dxMotif = -motif.getWidth() / 2;
			int dyMotif = -motif.getHeight() / 2;

			for (nextIdx = 0; nextIdx < tabPoint.length; nextIdx += 1) {
				Point next = tabPoint[nextIdx];

				if (previous != null && next != null
						& previous != RUPTURE_POINT && next != RUPTURE_POINT) {
					drawPencil(canvasLettre, previous, next);
					/*
					 * canvas.drawLine(previous.x, previous.y, next.x, next.y,
					 * paintLine);
					 */
				}

				previous = next;
			}
		}
	}

	public void drawCompleted(Canvas canvasCompleted) {
		synchronized (this) {
			/*
			generateTabPoint(canvasCompleted);

			int previousIdx = 0;
			int nextIdx;
			Point previous = tabPoint[previousIdx];
			Bitmap motif = getMotif(canvasCompleted);
			int dxMotif = -motif.getWidth() / 2;
			int dyMotif = -motif.getHeight() / 2;

			for (nextIdx = 0; nextIdx < tabPoint.length; nextIdx += 1) {
				Point next = tabPoint[nextIdx];

				if (previous != null && previous != RUPTURE_POINT
						&& nextIdx < getAnimateStep()) {
					canvasCompleted.drawBitmap(motif, previous.x + dxMotif,
							previous.y + dyMotif, paintLine);
				}

				previous = next;
			}
			*/
		}
	}

	private void generateTabPoint(Canvas canvas) {
		if (tabPoint == null) {
			animateStepInc = Math.min(canvas.getWidth(),canvas.getHeight())/10;
			ArrayList<Point> listPointToDraw = new ArrayList<Point>();
			Iterator<GraphicPrimitive> it = listGraphic.iterator();
			while (it.hasNext()) {
				GraphicPrimitive graphicPrimitive = (GraphicPrimitive) it
						.next();
				graphicPrimitive.drawPoint(listPointToDraw,
						canvas.getWidth(), canvas.getHeight());
			}

			tabPoint = listPointToDraw
					.toArray(new Point[getAnimateStepCount()]);
		}
	}

	static final int nbPoint = 20;
	static final int max = nbPoint * 2 + 1;
	static Point[] pointHLineWidth = null;
	static Point[] pointVLineWidth = null;
	
	
	protected static BitmapDrawable bmdHPencil = null; 
	protected final static Bitmap getHPencil(Canvas canvas) {
		if (bmdHPencil == null) {			
			Config config = Bitmap.Config.ARGB_8888; 
			bmdHPencil = new BitmapDrawable(Bitmap.createBitmap((int)(canvas.getWidth()/30), 2, config));
			Canvas cvMotif = new Canvas(bmdHPencil.getBitmap());
			Paint paint = new Paint();
			paint.setColor(Color.BLACK);
			paint.setAlpha(255);
					
			cvMotif.drawLine(0, 0, cvMotif.getWidth()-1, 0, paint);
			cvMotif.drawLine(0, 1, cvMotif.getWidth()-1, 1, paint);

		}
		return bmdHPencil.getBitmap();
	}
	
	protected static BitmapDrawable bmdVPencil = null; 
	protected final static Bitmap getVPencil(Canvas canvas) {
		if (bmdVPencil == null) {			
			Config config = Bitmap.Config.ARGB_8888;
			bmdVPencil = new BitmapDrawable(Bitmap.createBitmap(2, canvas.getHeight()/30, config));
			Canvas cvMotif = new Canvas(bmdVPencil.getBitmap());
			Paint paint = new Paint();
			paint.setColor(Color.BLACK);
			paint.setAlpha(255);
					
			cvMotif.drawLine(0, 0, 0, cvMotif.getHeight()-1, paint);
			cvMotif.drawLine(1, 0, 1, cvMotif.getHeight()-1, paint);

		}
		return bmdVPencil.getBitmap();
	}

	public void drawPencil(Canvas c, Point src, Point dst) {
		float xdiff = Math.abs(src.x - dst.x);
		float ydiff = Math.abs(src.y - dst.y);		
		
		if (ydiff > xdiff) {
			Bitmap bitmap = getHPencil(c);
			c.drawBitmap(bitmap, src.x - bitmap.getWidth()/2, src.y,paintLine);
		} else {
			Bitmap bitmap = getVPencil(c);
			c.drawBitmap(bitmap, src.x, src.y - bitmap.getHeight()/2,paintLine);
		}
	}
	
	public int getAnimateStep() {
		return animateStep;
	}

	public final int nextAnimateStep() {

		animateStep = animateStep + animateStepInc;
		
		if (animateStep > getAnimateStepCount()) {
			animateStep = getAnimateStepCount();

		}
		System.out.println("\n");

		return animateStep;
	}



	public abstract char getCharacter();

	public abstract void initListGraphic();

	public abstract boolean isUpper();

	public final int getAnimateStepCount() {
		if(tabPoint == null) return 0;		
		return tabPoint.length;
		
	}

	public void clearAnimateStep() {
		synchronized (this) {
			animateStep = 5;
			end = false;
		}

	}

	public boolean nearCurrentStep(int x1, int y1) {
		synchronized (this) {
			if (tabPoint != null) {

				Bitmap motif = getMotif(null);
				int currentStep = getAnimateStep();
				int maxStep = getAnimateStepCount() - 1;

				if (currentStep == getAnimateStep()) {
					currentStep = getAnimateStep() - 1;
				}

				int dw = motif.getWidth() * 2;
				int dy = motif.getHeight() * 2;

				for (int x = 0; x < animateStepInc; x++) {
					if (currentStep - x >= 0) {
						Point point = tabPoint[currentStep - x];
						if (point == null) {
							System.out.println("Point null !!! pour "
									+ getCharacter() + " au  "
									+ (currentStep - x));
						} else {
							if (between(point.x - dw, x1, point.x + dw)
									&& between(point.y - dy, y1, point.y + dy)) {
								if(currentStep > (3*maxStep/4))
									System.out.println("Test end: " + currentStep + " / " + maxStep);
								if (currentStep == maxStep && x <= 1) {
									end = true;
								}

								return true;

							}
						}
					}
				}
			}
		}
		return false;
	}

	private final boolean between(int min, int middle, int max) {
		if (min <= middle && max >= middle)
			return true;
		return false;
	}

	public boolean isEnd() {
		synchronized (this) {
			return end;
		}
	}

	public Point getCurrentStepPoint() {
		try {
			return tabPoint[getAnimateStep() - 1];
		} catch (RuntimeException e) {
			return null;
		}
	}
}
