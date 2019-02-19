package org.jenseigne.activity.drawletter;

import java.util.HashMap;

import org.jenseigne.R;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.DisplayMetrics;
import android.view.View;

public class DevDessineLettreActivity extends Activity implements
		OnInitListener, OnUtteranceCompletedListener {

	private TextToSpeech myTts;
	private DevDessineLettreView dessineLettre;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.devdessineleslettres);

		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		myTts = new TextToSpeech(this, this);
		myTts.setOnUtteranceCompletedListener(this);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		dessineLettre = (DevDessineLettreView) findViewById(R.id.dessine);

		DessineLesLettresThread thread = new DessineLesLettresThread();
		thread.start();
	}

	public void onInit(int status) {
		if (myTts != null) {
			parler("Dessine la lettre " + dessineLettre.getModelChar(), true);
		}
	}

	public void onUtteranceCompleted(String utteranceId) {

	}

	private int speechId = 0;

	private DisplayMetrics metrics;

	public void parler(String text, boolean cut) {
		HashMap<String, String> speakParam = new HashMap<String, String>();
		speakParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, ""
				+ speechId);
		myTts.speak(text, cut ? TextToSpeech.QUEUE_FLUSH
				: TextToSpeech.QUEUE_ADD, speakParam);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	private boolean stopThread = false;
	private static final int SPEED_INIT = 100;
	private int speed = SPEED_INIT;

	private class DessineLesLettresThread extends Thread {

		@Override
		public void run() {
			super.run();

			while (stopThread == false) {

				try {
					sleep(speed);

					if (dessineLettre.getModel().isEnd()) {
						dessineLettre.nextLettre();
						parler("Dessine la lettre "
								+ dessineLettre.getModelChar(), true);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		stopThread = true;
	}

	@Override
	protected void onStop() {
		super.onStop();
		stopThread = true;
	}

	@Override
	protected void onRestart() {
		super.onRestart();

	}

}
