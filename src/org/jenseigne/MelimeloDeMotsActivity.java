package org.jenseigne;

import java.util.HashMap;

import org.jenseigne.dictionnaire.MelimeloModel;
import org.jenseigne.dictionnaire.MelimeloModel.MotPosition;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MelimeloDeMotsActivity extends Activity implements OnInitListener,
		OnUtteranceCompletedListener, OnSelectWordListener {

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	private DialogTrouve dialogTrouve = null;

	private TextToSpeech myTts;

	private MelimeloView melimeloView;
	private MotPosition motRecherche;

	private TextView tvMotRecherche;

	private DisplayMetrics metrics;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.melimelo);

		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		getWindow().setBackgroundDrawable(
				Drawable.createFromStream(MelimeloDeMotsActivity.class
						.getResourceAsStream("res/background.png"),
						"res/background.png"));

		melimeloView = (MelimeloView) findViewById(R.id.melimeloView);

		tvMotRecherche = (TextView) findViewById(R.id.recherche);

		melimeloView.setOnSelectWordListener(this);

		myTts = new TextToSpeech(this, this);

		myTts.setOnUtteranceCompletedListener(this);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

	}

	public void onStart(int status) {

	}

	public void onUtteranceCompleted(String utteranceId) {

		System.out.println("Fin de phrase");

	}

	private int speechId = 0;
	private TextAlert askNom;
	private DialogScore dialog;

	public void parler(String text, boolean cut) {
		HashMap<String, String> speakParam = new HashMap<String, String>();
		speakParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, ""
				+ (speechId++));
		myTts.speak(text, cut ? TextToSpeech.QUEUE_FLUSH
				: TextToSpeech.QUEUE_ADD, speakParam);

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	public void nouveauMot() {

		motRecherche = melimeloView.getModel().getNext();
		if (motRecherche != null) {
			// Encore un mot

			tvMotRecherche.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					tvMotRecherche.getHeight() * 8 / 10);
			tvMotRecherche.setText(motRecherche.getMotIllustre().getMot()
					.toUpperCase());

			if (dialogTrouve == null) {
				dialogTrouve = new DialogTrouve(this,
						motRecherche.getMotIllustre(), myTts, 4, metrics);
			}
			dialogTrouve.setIllustration(motRecherche.getMotIllustre());
			runOnUiThread(dialogTrouve);

		} else {
			// Gagné !
			HashMap<String, String> speakParam = new HashMap<String, String>();
			speakParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "");
			myTts.speak("Gagné", TextToSpeech.QUEUE_ADD, speakParam);

			dialog = new DialogScore(MelimeloDeMotsActivity.this, metrics,
					getApplicationContext(), "Melimelo");

			final long score = melimeloView.getModel().getScore();
			if (dialog.getHighScore().inHighscore(score)) {
				askNom = new TextAlert(MelimeloDeMotsActivity.this,
						"Meilleurs score", "Entre ton nom",
						new OnClickListener() {
							public void onClick(View v) {
								dialog.getHighScore().addScore(
										askNom.getText(),
										melimeloView.getModel().getScore());
								dialog.setParameter(new OnClickRejouer(),
										new OnClickRetour(
												MelimeloDeMotsActivity.this,
												dialog), ""
												+ melimeloView.getModel()
														.getScore());
								dialog.run();
							}
						});
				askNom.show();
			} else {
				dialog.setParameter(new OnClickRejouer(), new OnClickRetour(
						MelimeloDeMotsActivity.this, dialog), ""
						+ melimeloView.getModel().getScore());
				dialog.run();
			}
		}
	}

	public class OnClickRejouer implements OnClickListener {
		public void onClick(View v) {
			dialog.hide();
			melimeloView.setModel(new MelimeloModel());
			nouveauMot();
		}

	}

	public void onInit(int status) {
		nouveauMot();
	}

	public void onSelectWord(MelimeloView view, Point begin, Point end,
			String selected) {

	}

	public boolean submitWord(View v) {

		if (motRecherche.getMotIllustre().getMot()
				.equals(melimeloView.getSelected())) {
			motRecherche.setFound(true);
			HashMap<String, String> speakParam = new HashMap<String, String>();
			speakParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "");
			myTts.speak("Bien joué", TextToSpeech.QUEUE_ADD, speakParam);
			nouveauMot();
		} else {
			HashMap<String, String> speakParam = new HashMap<String, String>();
			speakParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "");
			myTts.speak("Non, essaie encore !", TextToSpeech.QUEUE_ADD,
					speakParam);

			runOnUiThread(dialogTrouve);
		}

		return true;
	}

}
