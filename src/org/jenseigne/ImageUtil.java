package org.jenseigne;

import java.io.InputStream;

import org.jenseigne.dictionnaire.Dictionnaire;
import org.jenseigne.dictionnaire.MotIllustration;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;

public class ImageUtil {
	public static BitmapDrawable loadBitmpaDrawable(MotIllustration mot,
			int desiredWidth, int desiredHeight) {
		BitmapDrawable bmd;
		Bitmap bitmapOrg = BitmapFactory.decodeStream(Dictionnaire.class
				.getResourceAsStream("res/" + mot.getImageName()));

		bmd = loadBitmap(desiredWidth, desiredHeight, bitmapOrg, true);
		return bmd;
	}

	public static BitmapDrawable loadBitmpaDrawable(InputStream is,
			int desiredWidth, int desiredHeight, boolean proportional) {
		BitmapDrawable bmd;
		Bitmap bitmapOrg = BitmapFactory.decodeStream(is);

		bmd = loadBitmap(desiredWidth, desiredHeight, bitmapOrg, proportional);
		return bmd;
	}

	public static BitmapDrawable loadBitmap(int desiredWidth,
			int desiredHeight, Bitmap bitmapOrg, boolean proportional) {
		BitmapDrawable bmd;
		int width = bitmapOrg.getWidth();

		int height = bitmapOrg.getHeight();

		int newWidth = desiredWidth;
		int newHeight = desiredHeight;

		float scaleWidth = ((float) newWidth) / width;

		float scaleHeight = ((float) newHeight) / height;

		Matrix matrix = new Matrix();

		if (proportional) {
			if (scaleWidth < scaleHeight) {
				matrix.setScale(scaleWidth, scaleWidth);
			} else {
				matrix.setScale(scaleHeight, scaleHeight);
			}
		} else {
			matrix.setScale(scaleWidth, scaleHeight);
		}

		Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0,

		width, height, matrix, true);

		bmd = new BitmapDrawable(resizedBitmap);
		return bmd;
	}

}
