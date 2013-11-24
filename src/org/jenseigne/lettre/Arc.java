package org.jenseigne.lettre;

import java.util.List;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.FloatMath;

public class Arc extends GraphicPrimitive {

	private float centerX;
	private float centerY;
	private float rayon;
	private float beginAngle;
	private float endAngle;

	public Arc(PointF centre, float rayon, float beginAngleRadian,
			float endAngleRadian) {
		this.centerX = centre.x;
		this.centerY = centre.y;
		this.rayon = rayon;
		this.beginAngle = beginAngleRadian;
		this.endAngle = endAngleRadian;
	}

	public boolean inf(float dangle) {
		if (beginAngle < endAngle) {
			return dangle < endAngle;
		}
		return endAngle < dangle;
	}

	public float max(float dangle) {
		if (beginAngle < endAngle) {
			if (dangle > endAngle)
				return endAngle;
			return dangle;
		}
		if (endAngle > dangle)
			return endAngle;
		return dangle;
	}

	public float cos(float f) {
		return (float) FloatMath.cos(f);
	}

	public float sin(float f) {
		return (float) FloatMath.sin(f);
	}
	
	public boolean diffInt(float x,float y) {
		return (int)x != (int)y;
	}

	public void drawPoint(List<Point> listPoint, int width, int height) {
		float sourceX;
		float sourceY;
		

		float dangle = beginAngle;
		
		float nbPas=  2* Math.abs((rayon * width)*2 * ((float)Math.PI)); // 2 fois le périmetre 		
		
		float pasAngle = (endAngle - beginAngle) / nbPas; 
		
		float previousX=0;
		float previousY=0;

		while (inf(dangle)) {			
			sourceX = (centerX + cos(dangle) * rayon) * width;
			sourceY = (centerY + sin(dangle) * rayon) * height;
		
			if(diffInt(sourceX, previousX) || diffInt(sourceY, previousY)) {
				listPoint.add(new Point((int) sourceX, (int) sourceY));
				previousX = sourceX;
				previousY = sourceY;
			}

			dangle = dangle + pasAngle;
		}

	}
}