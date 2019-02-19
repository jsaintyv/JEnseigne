package org.jenseigne;

import java.util.HashMap;

import org.jenseigne.dictionnaire.Dictionnaire;
import org.jenseigne.dictionnaire.MangeMotModel;
import org.jenseigne.dictionnaire.MotIllustration;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CourseAuxMotsActivity extends Activity implements OnInitListener,
		OnUtteranceCompletedListener, android.view.View.OnClickListener {

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		stopThread = true;
	}

	private boolean stopThread = false;
	private Dictionnaire dictionnaire = Dictionnaire.instance();

	private MotIllustration mot = dictionnaire.getRandom();

	private TextToSpeech myTts;

	private boolean programmerUnNouveauMot = false;

	private MangeMotView mangeMotView;

	private int dureePartie = 60;

	private static final int SPEED_INIT = 300;
	private int speed = SPEED_INIT;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.courseauxmots);

		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		getWindow().setBackgroundDrawable(
				Drawable.createFromStream(CourseAuxMotsActivity.class
						.getResourceAsStream("res/background.png"),
						"res/background.png"));

		mangeMotView = (MangeMotView) findViewById(R.id.mangeMotView);

		setParameterButton(((Button) findViewById(R.id.A)));
		setParameterButton(((Button) findViewById(R.id.B)));
		setParameterButton(((Button) findViewById(R.id.C)));
		setParameterButton(((Button) findViewById(R.id.D)));
		setParameterButton(((Button) findViewById(R.id.E)));
		setParameterButton(((Button) findViewById(R.id.F)));
		setParameterButton(((Button) findViewById(R.id.G)));
		setParameterButton(((Button) findViewById(R.id.H)));
		setParameterButton(((Button) findViewById(R.id.I)));
		setParameterButton(((Button) findViewById(R.id.J)));
		setParameterButton(((Button) findViewById(R.id.K)));
		setParameterButton(((Button) findViewById(R.id.L)));
		setParameterButton(((Button) findViewById(R.id.M)));
		setParameterButton(((Button) findViewById(R.id.N)));
		setParameterButton(((Button) findViewById(R.id.O)));
		setParameterButton(((Button) findViewById(R.id.P)));
		setParameterButton(((Button) findViewById(R.id.Q)));
		setParameterButton(((Button) findViewById(R.id.R)));
		setParameterButton(((Button) findViewById(R.id.S)));
		setParameterButton(((Button) findViewById(R.id.T)));
		setParameterButton(((Button) findViewById(R.id.U)));
		setParameterButton(((Button) findViewById(R.id.V)));
		setParameterButton(((Button) findViewById(R.id.W)));
		setParameterButton(((Button) findViewById(R.id.X)));
		setParameterButton(((Button) findViewById(R.id.Y)));
		setParameterButton(((Button) findViewById(R.id.Z)));

		System.out.println(metrics.heightPixels);

		mangeMotView.setMinimumHeight((int) ((metrics.heightPixels * 3) / 8));
		mangeMotView.measure(metrics.widthPixels,
				((metrics.heightPixels * 3) / 8));

		myTts = new TextToSpeech(this, this);
		myTts.setOnUtteranceCompletedListener(this);

		findViewById(R.id.tableButtons).setPadding(0, 0, 0, 0);

		setParameterRow(findViewById(R.id.TableRow01));
		setParameterRow(findViewById(R.id.TableRow02));
		setParameterRow(findViewById(R.id.TableRow03));
		setParameterRow(findViewById(R.id.TableRow04));

		mangeMotView.getLayoutParams().height = metrics.heightPixels * 3 / 8;

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	public void onInit(int status) {
		if (myTts != null) {
			parler("Tu as "
					+ dureePartie
					+ " secondes pour taper le plus de mots et faire le meilleur score",
					true);
			rejouer();
		}
	}

	public void setParameterButton(Button button) {
		button.setWidth((int) ((metrics.widthPixels) / 7));
		button.setHeight((int) ((metrics.heightPixels / 8)));
		button.setOnClickListener(this);
		button.setPadding(0, 0, 0, 0);
		button.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				(int) (metrics.heightPixels / 16));

	}

	public void setParameterRow(View v) {
		v.setMinimumHeight((int) ((metrics.heightPixels / 8)));
		v.setPadding(0, 0, 0, 0);
	}

	private void nouveauMot() {

		mot = dictionnaire.getRandom();

		mangeMotView.getModel().setMotIllustration(mot);

		parler(mot.getMot(), false);

	}

	public void onUtteranceCompleted(String utteranceId) {

		System.out.println("Fin de phrase");
		if (programmerUnNouveauMot) {
			programmerUnNouveauMot = false;
			runOnUiThread(new ChangeWordThread());
		}

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
		rejouer();
	}

	private class MangeMotThread extends Thread {

		@Override
		public void run() {
			super.run();

			while (stopThread == false) {
				try {
					sleep(speed);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				runOnUiThread(new Runnable() {

					public void run() {

						mangeMotView.invalidate();

					}
				});

				if (mangeMotView.getModel().getTime() > dureePartie) {
					termine();
					stopThread = true;
				}
			}
		}

	}

	public class ChangeWordThread implements Runnable {
		public void run() {
			nouveauMot();
		}
	}

	public void onClick(View v) {

		if (v instanceof Button) {

			Button b = (Button) v;
			if (mangeMotView.getModel().isNext(b.getText().toString())) {
				mangeMotView.refreshDrawableState();
				parler(b.getText().toString(), false);
				mangeMotView.getModel().incScore();
				mangeMotView.refreshDrawableState();
				if (mangeMotView.getModel().fin()) {
					programmerUnNouveauMot = true;
					myTts.setOnUtteranceCompletedListener(this);
					parler("Bien joué", true);

				}

			} else {
				mangeMotView.getModel().incFautes();
				parler("Non", false);
			}
		}
	}

	private DialogScore dialog;

	private class OnClickRejouer implements OnClickListener {

		public void onClick(View v) {
			dialog.hide();

			rejouer();

		}

	}

	private void termine() {

		parler("La partie est terminé, ton score est de "
				+ mangeMotView.getModel().getScore(), true);
		stopThread = true;

		runOnUiThread(new Runnable() {
			TextAlert askNom;

			public void run() {

				dialog = new DialogScore(CourseAuxMotsActivity.this, metrics,
						getApplicationContext(), "CoursesAuxMots");

				final long score = mangeMotView.getModel().getScore();
				if (dialog.getHighScore().inHighscore(score)) {
					askNom = new TextAlert(CourseAuxMotsActivity.this,
							"Meilleurs score", "Entre ton nom",
							new OnClickListener() {
								public void onClick(View v) {
									dialog.getHighScore().addScore(
											askNom.getText(),
											mangeMotView.getModel().getScore());
									dialog.setParameter(new OnClickRejouer(),
											new OnClickRetour(
													CourseAuxMotsActivity.this,
													dialog), ""
													+ mangeMotView.getModel()
															.getScore());
									dialog.run();
								}
							});
					askNom.show();
				} else {
					dialog.setParameter(new OnClickRejouer(),
							new OnClickRetour(CourseAuxMotsActivity.this,
									dialog), ""
									+ mangeMotView.getModel().getScore());
					dialog.run();
				}

			}
		});

	}

	private void rejouer() {
		mangeMotView.setModel(new MangeMotModel());
		nouveauMot();
		stopThread = false;
		new MangeMotThread().start();
	}
}
