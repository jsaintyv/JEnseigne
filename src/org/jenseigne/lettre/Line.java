package org.jenseigne.lettre;

import java.util.List;

import android.graphics.Point;
import android.graphics.PointF;

public class Line extends GraphicPrimitive {

	public Line(float sourceX, float sourceY, float destinationX,
			float destinationY) {
		this.sourceX = sourceX;
		this.sourceY = sourceY;
		this.destinationX = destinationX;
		this.destinationY = destinationY;

	}

	public Line(PointF source, PointF destination) {
		this.sourceX = source.x;
		this.sourceY = source.y;
		this.destinationX = destination.x;
		this.destinationY = destination.y;

	}

	private float sourceX;
	private float sourceY;

	private float destinationX;
	private float destinationY;

	public float getSourceX() {
		return sourceX;
	}

	public void setSourceX(float sourceX) {
		this.sourceX = sourceX;
	}

	public float getSourceY() {
		return sourceY;
	}

	public void setSourceY(float sourceY) {
		this.sourceY = sourceY;
	}

	public float getDestinationX() {
		return destinationX;
	}

	public void setDestinationX(float destinationX) {
		this.destinationX = destinationX;
	}

	public float getDestinationY() {
		return destinationY;
	}

	public void setDestinationY(float destinationY) {
		this.destinationY = destinationY;
	}

	public void drawPoint(List<Point> listPoint, int width, int height) {
		Point src = new Point((int)(sourceX * width), (int)(sourceY * height));

		Point dst = new Point((int)(destinationX * width), (int)(destinationY * height));
		float xdiff = Math.abs(src.x - dst.x);		
		float ydiff = Math.abs(src.y - dst.y);
		
		PointF pf = null;		
		float xinc = 0;
		float yinc = 0;
			
		if(xdiff > ydiff) {
			
			
			xinc = src.x > dst.x ? -1 : 1;
			yinc = (dst.y - src.y) / xdiff;
									
		} else {					
			xinc = (dst.x - src.x) / ydiff;
			yinc = src.y > dst.y ? -1 : 1;
							
		}
		
		pf = new PointF(src);
				
		while (xdiff > ydiff ? pf.x != dst.x : pf.y != dst.y) {
			
			listPoint.add(new Point((int) pf.x, (int) pf.y));
			
			pf.x += xinc;
			pf.y += yinc;
		}
	}
	
	/*
	public void drawPoint(List<Point> listPoint, int width, int height) {

		PointF pSource = new PointF(sourceX * width, sourceY * height);

		PointF pDest = new PointF(destinationX * width, destinationY * height);

		float xinc = calculateInc(pSource.x, pDest.x);
		xinc = pSource.x < pDest.x ? xinc : -xinc;
		float yinc = calculateInc(pSource.y, pDest.y);
		yinc = pSource.y < pDest.y ? yinc : -yinc;

		System.out.println("D:" + listPoint.size());
		PointF index = new PointF(pSource.x, pSource.y);
		for (int x = 0; x < getAnimateStepCount(); x++) {
			listPoint.add(new Point((int) index.x, (int) index.y));
			index = new PointF(index.x, index.y);
			index.offset(xinc, yinc);
		}

		System.out.println("F:" + listPoint.size());
	}
	*/
}