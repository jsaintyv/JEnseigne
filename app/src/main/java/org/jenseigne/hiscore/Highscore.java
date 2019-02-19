package org.jenseigne.hiscore;

import android.content.Context;
import android.content.SharedPreferences;

public class Highscore {
	private SharedPreferences preferences;
	private String names[];
	private long score[];

	private String appName;

	public Highscore(Context context, String appName) {
		this.appName = appName;
		preferences = context.getSharedPreferences(appName + ".Highscore", 0);
		names = new String[10];
		score = new long[10];

		for (int x = 0; x < 10; x++) {
			names[x] = preferences.getString(appName + ".name" + x, "-");
			score[x] = preferences.getLong(appName + ".score" + x, 0);
		}

	}

	public String getName(int x) {
		// get the name of the x-th position in the Highscore-List
		return names[x];
	}

	public long getScore(int x) {
		// get the score of the x-th position in the Highscore-List
		return score[x];
	}

	public boolean inHighscore(long score) {
		// test, if the score is in the Highscore-List
		int position;
		for (position = 0; position < 10 && this.score[position] > score; position++)
			;

		if (position == 10)
			return false;
		return true;
	}

	public boolean addScore(String name, long score) {
		// add the score with the name to the Highscore-List
		int position;
		for (position = 0; position < 10 && this.score[position] > score; position++)
			;

		if (position == 10)
			return false;

		for (int x = 9; x > position; x--) {
			names[x] = names[x - 1];
			this.score[x] = this.score[x - 1];
		}

		this.names[position] = new String(name);
		this.score[position] = score;

		SharedPreferences.Editor editor = preferences.edit();
		for (int x = 0; x < 10; x++) {
			editor.putString(appName + ".name" + x, this.names[x]);
			editor.putLong(appName + ".score" + x, this.score[x]);
		}
		editor.commit();
		return true;

	}
}
