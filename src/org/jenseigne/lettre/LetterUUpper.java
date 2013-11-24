package org.jenseigne.lettre;

import android.graphics.PointF;

public class LetterUUpper extends ASymbol {

	@Override
	public char getCharacter() {
		return 'U';
	}

	@Override
	public void initListGraphic() {
		PointF pfUpperRightCorner = new PointF(UPPER_RIGHT_CORNER.x + 0.1f,
				UPPER_RIGHT_CORNER.y);
		PointF pfUpperLeftCorner = new PointF(UPPER_LEFT_CORNER.x - 0.1f,
				UPPER_LEFT_CORNER.y);
		PointF pfBottomRightCorner = new PointF(BOTTOM_RIGHT_CORNER.x + 0.1f,
				BOTTOM_RIGHT_CORNER.y - 0.3f);
		PointF pfBottomLeftCorner = new PointF(BOTTOM_LEFT_CORNER.x - 0.1f,
				BOTTOM_LEFT_CORNER.y - 0.3f);
		PointF pfBottomMiddle = new PointF(MIDDLE.x,
				BOTTOM_LEFT_CORNER.y - 0.3f);
		append(new Line(pfUpperRightCorner, pfBottomRightCorner));
		append(new Arc(pfBottomMiddle,
				pfBottomMiddle.x - pfBottomRightCorner.x, 3.14f, 0f));
		append(new Line(pfBottomLeftCorner, pfUpperLeftCorner));

	}

	@Override
	public boolean isUpper() {
		return true;
	}

}
