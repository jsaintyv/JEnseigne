package org.jenseigne.lettre;

import java.util.List;

import android.graphics.Point;

public class DrawJump extends GraphicPrimitive {

	public DrawJump() {	
	}

	public void drawPoint(List<Point> listPoint, int width, int height) {
		listPoint.add(ASymbol.RUPTURE_POINT);

	}
}