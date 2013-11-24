package org.jenseigne;

import org.jenseigne.hiscore.Highscore;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DialogScore implements Runnable {
	private Dialog dialog;
	private Activity target;

	private Highscore highScore;

	public Highscore getHighScore() {
		return highScore;
	}

	private DisplayMetrics metrics;

	public DialogScore(Activity target, DisplayMetrics metrics,
			Context context, String appName) {
		this.target = target;
		this.metrics = metrics;

		dialog = new Dialog(target);
		dialog.setContentView(R.layout.score);
		dialog.setTitle("Terminé !");

		((LinearLayout) dialog.findViewById(R.id.dialogLayout))
				.setMinimumWidth((int) (metrics.widthPixels * 0.8));
		((LinearLayout) dialog.findViewById(R.id.dialogLayout))
				.setMinimumHeight((int) (metrics.heightPixels * 0.8));

		((LinearLayout) dialog.findViewById(R.id.dialogLayout))
				.setMinimumWidth((int) (metrics.widthPixels * 0.8));
		((LinearLayout) dialog.findViewById(R.id.dialogLayout))
				.setMinimumHeight((int) (metrics.heightPixels * 0.8));

		highScore = new Highscore(context, appName);
	}

	public void showScore(int idNom, int idScore, int index) {
		((TextView) dialog.findViewById(idNom)).setTextSize(
				TypedValue.COMPLEX_UNIT_PX, metrics.heightPixels / 22);
		((TextView) dialog.findViewById(idNom)).setText((index + 1) + "."
				+ getHighScore().getName(index));
		((TextView) dialog.findViewById(idScore)).setTextSize(
				TypedValue.COMPLEX_UNIT_PX, metrics.heightPixels / 22);
		((TextView) dialog.findViewById(idScore)).setText(""
				+ getHighScore().getScore(index));
	}

	public void setParameter(OnClickListener rejouer, OnClickListener retour,
			String score) {

		showScore(R.id.tvJoueur1, R.id.tvScore1, 0);
		showScore(R.id.tvJoueur2, R.id.tvScore2, 1);
		showScore(R.id.tvJoueur3, R.id.tvScore3, 2);
		showScore(R.id.tvJoueur4, R.id.tvScore4, 3);
		showScore(R.id.tvJoueur5, R.id.tvScore5, 4);
		showScore(R.id.tvJoueur6, R.id.tvScore6, 5);
		showScore(R.id.tvJoueur7, R.id.tvScore7, 6);
		showScore(R.id.tvJoueur8, R.id.tvScore8, 7);
		showScore(R.id.tvJoueur9, R.id.tvScore9, 8);
		showScore(R.id.tvJoueur10, R.id.tvScore10, 9);

		((Button) dialog.findViewById(R.id.btRejouer))
				.setOnClickListener(rejouer);
		((Button) dialog.findViewById(R.id.btRetour))
				.setOnClickListener(retour);

		TextView tv = (TextView) dialog.findViewById(R.id.score);
		tv.setTextSize(metrics.heightPixels / 15);
		tv.setGravity(Gravity.CENTER_HORIZONTAL);

		tv.setText(target.getString(R.string.score) + " " + score);

	}

	public void run() {
		dialog.show();
	}

	public void hide() {
		dialog.hide();

	}

}
