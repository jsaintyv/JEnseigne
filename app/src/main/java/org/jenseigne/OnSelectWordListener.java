package org.jenseigne;

import android.graphics.Point;

public interface OnSelectWordListener {
	public void onSelectWord(MelimeloView view, Point begin, Point end,
			String selected);
}
