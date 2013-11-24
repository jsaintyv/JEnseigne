package org.jenseigne;

import java.util.Iterator;

import org.jenseigne.dictionnaire.MelimeloModel;
import org.jenseigne.dictionnaire.MelimeloModel.MotPosition;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MelimeloView extends View {

	private MelimeloModel model = new MelimeloModel();
	private int caseLargeur;
	private int caseHauteur;
	private int paddingLargeur;
	private int paddingHauteur;

	private OnSelectWordListener onSelectWordListener;

	public OnSelectWordListener getOnSelectWordListener() {
		return onSelectWordListener;
	}

	public void setOnSelectWordListener(
			OnSelectWordListener onSelectWordListener) {
		this.onSelectWordListener = onSelectWordListener;
	}

	private Point begin;
	private Point end;

	public MelimeloModel getModel() {
		return model;
	}

	public void setModel(MelimeloModel model) {
		this.model = model;
		invalidate();
	}

	public MelimeloView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFocusableInTouchMode(true);
	}

	public MelimeloView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFocusableInTouchMode(true);
	}

	public MelimeloView(Context context) {
		super(context);
		setFocusableInTouchMode(true);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		try {
			System.out.println("Action:" + event.getAction());
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				begin = new Point();
				begin.x = (int) (event.getX() / caseLargeur);
				begin.y = (int) (event.getY() / caseHauteur);

				end = new Point(begin);
				setFocusableInTouchMode(true);

			}

			if (event.getAction() == MotionEvent.ACTION_MOVE
					|| event.getAction() == MotionEvent.ACTION_UP) {
				end = new Point();
				end.x = (int) (event.getX() / caseLargeur);
				end.y = (int) (event.getY() / caseHauteur);

				if (end.x > begin.x && (end.x - begin.x) > (end.y - begin.y)) {
					end.y = begin.y;
				} else {
					end.x = begin.x;
				}

			}

			if (event.getAction() == MotionEvent.ACTION_UP
					&& onSelectWordListener != null) {
				System.out.println("Mot selectionné:"
						+ model.getWord(begin, end));
				onSelectWordListener.onSelectWord(this, begin, end,
						model.getWord(begin, end));
			}

			postInvalidate();
		} catch (Throwable th) {
			th.printStackTrace();
		}
		return true;
	}

	public String getSelected() {
		try {
			return model.getWord(begin, end);
		} catch (Throwable th) {
			return "";
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Paint paint = new Paint();
		paint.setColor(Color.BLACK);

		caseLargeur = getWidth() / model.getLargeur();
		caseHauteur = getHeight() / model.getHauteur();

		paddingLargeur = caseLargeur / 10;
		paddingHauteur = caseHauteur / 10;

		paint.setTextSize(caseHauteur * 8 / 10);

		// Cadrillage
		for (int x = 0; x < model.getLargeur(); x++) {
			canvas.drawLine(x * caseLargeur, 0, x * caseLargeur, getHeight(),
					paint);
		}

		for (int y = 0; y < model.getLargeur(); y++) {
			canvas.drawLine(0, y * caseHauteur, getWidth(), y * caseHauteur,
					paint);
		}

		try {
			// Dessine les selections
			Iterator<MotPosition> itMot = model.getListeDeMots().iterator();
			while (itMot.hasNext()) {
				MelimeloModel.MotPosition mot = (MelimeloModel.MotPosition) itMot
						.next();
				if (mot.isFound()) {

					paint.setColor(mot.getColor());
					paint.setStyle(Style.STROKE);
					drawSelection(mot.getPosition(), mot.getEnd(), canvas,
							paint);

					paint.setAlpha(0x10);
					paint.setStyle(Style.FILL_AND_STROKE);
					drawSelection(mot.getPosition(), mot.getEnd(), canvas,
							paint);
					paint.setAlpha(0xFF);
				}
			}
		} catch (Throwable th) {
			System.out.println("Error lors du dessin des selections");
			th.printStackTrace();
		}

		try {
			paint.setColor(Color.GRAY);
			paint.setStyle(Style.FILL_AND_STROKE);

			if (begin != null && end != null)
				drawSelection(begin, end, canvas, paint);
		} catch (Throwable th) {
			th.printStackTrace();
		}

		try {
			paint.setColor(Color.BLACK);
			// Dessine les lettres
			for (int x = 0; x < model.getLargeur(); x++) {
				for (int y = 0; y < model.getLargeur(); y++) {

					String t = ("" + model.getCharAt(x, y)).toUpperCase();
					int texteTapeWidth = (int) paint.measureText(t, 0, 1);

					canvas.drawText(t, x * caseLargeur
							+ ((caseLargeur - texteTapeWidth) / 2), (y + 1)
							* caseHauteur - paddingHauteur, paint);
				}
			}

		} catch (Throwable th) {
			th.printStackTrace();
		}

	}

	public void drawSelection(Point to, Point at, Canvas canvas, Paint paint) {
		Point p1;
		Point p2;
		if (to.y == at.y) {
			// Horizontale
			if (to.x < at.x) {
				p1 = to;
				p2 = at;
			} else {
				p1 = at;
				p2 = to;
			}

			canvas.drawRect(
					new Rect(p1.x * caseLargeur + paddingLargeur, p1.y
							* caseHauteur + paddingHauteur, (p2.x + 1)
							* caseLargeur - paddingLargeur, (p2.y + 1)
							* caseHauteur - paddingHauteur), paint);

		} else if (to.x == at.x) {
			// Verticale
			if (to.y < at.y) {
				p1 = to;
				p2 = at;
			} else {
				p1 = at;
				p2 = to;
			}

			canvas.drawRect(
					new Rect(p1.x * caseLargeur + paddingLargeur, p1.y
							* caseHauteur + paddingHauteur, (p2.x + 1)
							* caseLargeur - paddingLargeur, (p2.y + 1)
							* caseHauteur - paddingHauteur), paint);

		}
	}
}
