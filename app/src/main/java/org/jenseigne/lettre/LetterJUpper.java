package org.jenseigne.lettre;

import android.graphics.PointF;

public class LetterJUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'J';
	}

	@Override
	public void initListGraphic() {
		PointF upperLeft = new PointF(UPPER_LEFT_CORNER.x - 0.1f,
				UPPER_LEFT_CORNER.y);
		PointF p = new PointF(BOTTOM_LEFT_CORNER.x - 0.1f,
				BOTTOM_LEFT_CORNER.y - 0.2f);
		PointF p2 = new PointF(MIDDLE.x, p.y);
		append(new Line(upperLeft, p));
		append(new Arc(p2, p.x - p2.x, 0f, 3.14f));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
