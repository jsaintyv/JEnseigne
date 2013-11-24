package org.jenseigne;

import org.jenseigne.dictionnaire.Dictionnaire;
import org.jenseigne.dictionnaire.MangeMotModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

public class MangeMotView extends View implements MangeMotModel.ChangeListener {

	private MangeMotModel model = new MangeMotModel();
	private BitmapDrawable bmd;

	public MangeMotModel getModel() {
		return model;
	}

	public void setModel(MangeMotModel model) {
		this.model = model;
		model.setListener(this);
	}

	public MangeMotView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		model.setListener(this);
	}

	public MangeMotView(Context context, AttributeSet attrs) {
		super(context, attrs);
		model.setListener(this);
	}

	public MangeMotView(Context context) {
		super(context);
		model.setListener(this);
	}

	private Paint paint = new Paint();

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		paint.setColor(Color.BLACK);

		paint.setTextSize(getHeight() / 6);

		int fullTextWidth = (int) paint.measureText(model.getMotIllustration()
				.getMot(), 0, model.getMotIllustration().getMot().length());

		String texteTape = model.getMotTape().toUpperCase();
		paint.setColor(Color.GREEN);

		int beginWidth = (getWidth() - fullTextWidth) / 2;

		canvas.drawText(texteTape, beginWidth, getHeight() / 4, paint);

		int texteTapeWidth = (int) paint.measureText(texteTape, 0,
				texteTape.length());

		paint.setColor(Color.BLACK);
		canvas.drawText(model.getMotRestant().toUpperCase(), beginWidth
				+ texteTapeWidth, getHeight() / 4, paint);

		paint.setTextSize(getHeight() / 10);

		canvas.drawText("Scores:" + model.getScore(), getWidth() / 2,
				getHeight() * 3 / 4, paint);

		canvas.drawText("Temps:" + model.getTime(), getWidth() / 2,
				getHeight(), paint);

		if (bmd != null) {
			canvas.drawBitmap(bmd.getBitmap(), (int) 0,
					(int) (getHeight() / 2), paint);
		}
	}

	public void onMangeMotModelChange(MangeMotModel model) {

		try {
			Bitmap bitmapOrg = BitmapFactory.decodeStream(Dictionnaire.class
					.getResourceAsStream("res/"
							+ getModel().getMotIllustration().getImageName()));

			int newWidth = getWidth() / 2;

			int newHeight = (int) (getHeight() / 2);

			bmd = ImageUtil.loadBitmap(newWidth, newHeight, bitmapOrg, true);
		} catch (Throwable th) {
			th.printStackTrace();
			bmd = null;
		}

	}
}
