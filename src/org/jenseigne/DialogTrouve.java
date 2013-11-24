package org.jenseigne;

import java.util.HashMap;

import org.jenseigne.dictionnaire.MotIllustration;

import android.app.Activity;
import android.app.Dialog;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DialogTrouve implements Runnable {
	private Dialog dialog;
	private Activity target;
	private MotIllustration illustration;

	public MotIllustration getIllustration() {
		return illustration;
	}

	public void setIllustration(MotIllustration illustration) {
		this.illustration = illustration;
	}

	private TextToSpeech myTts;
	private int timeInSecond;
	private ImageView image;

	public DialogTrouve(Activity target, MotIllustration illustration,
			TextToSpeech myTts, int timeInSecond, DisplayMetrics metrics) {
		this.target = target;
		this.illustration = illustration;
		this.myTts = myTts;
		this.timeInSecond = timeInSecond;

		dialog = new Dialog(target);
		dialog.setContentView(R.layout.trouve);

		((LinearLayout) dialog.findViewById(R.id.dialogLayout))
				.setMinimumWidth((int) (metrics.widthPixels * 0.8));
		((LinearLayout) dialog.findViewById(R.id.dialogLayout))
				.setMinimumHeight((int) (metrics.heightPixels * 0.8));

		TextView tv = (TextView) dialog.findViewById(R.id.question);
		tv.setTextSize(metrics.heightPixels / 15);
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
	}

	public void run() {

		TextView tv = (TextView) dialog.findViewById(R.id.question);
		tv.setText(illustration.getMot().toUpperCase());

		target.runOnUiThread(new Sablier());

		image = (ImageView) dialog.findViewById(R.id.ImageMot);

		image.postDelayed(new FitImage(), 300);
		dialog.show();
	}

	public class FitImage implements Runnable {
		public void run() {
			dialog.show();
			if (image.getWidth() == 0) {
				image.postDelayed(new FitImage(), 300);
			} else {
				image.setImageDrawable(ImageUtil.loadBitmpaDrawable(
						illustration, image.getWidth(), image.getHeight()));

				HashMap<String, String> speakParam = new HashMap<String, String>();
				speakParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "");
				myTts.speak("Trouve le mot " + illustration.getMot(),
						TextToSpeech.QUEUE_ADD, speakParam);

				new Sablier().start();
			}
		}

	}

	public class Sablier extends Thread {
		public void run() {
			try {
				sleep(1000 * timeInSecond);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			target.runOnUiThread(new Runnable() {

				public void run() {
					dialog.hide();
				}
			});

		}
	}
}
