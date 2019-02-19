package org.jenseigne;

import java.text.Collator;
import java.util.HashMap;
import java.util.Locale;

import org.jenseigne.dictionnaire.Dictionnaire;
import org.jenseigne.dictionnaire.MotIllustration;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class JEnseigneMain extends Activity implements OnInitListener,
		OnUtteranceCompletedListener, android.view.View.OnClickListener {

	private Dictionnaire dictionnaire = Dictionnaire.instance();

	private MotIllustration mot = dictionnaire.getRandom();

	private TextToSpeech myTts;

	private TextView tvQuestion;

	private boolean programmerUnNouveauMot = false;

	private ImageView imgView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		getWindow().setBackgroundDrawable(
				Drawable.createFromStream(JEnseigneMain.class
						.getResourceAsStream("res/background.png"),
						"res/background.png"));

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

		imgView = (ImageView) findViewById(R.id.ImageMot);

		System.out.println(metrics.heightPixels);
		imgView.setMaxHeight((int) ((metrics.heightPixels * 2) / 8));
		imgView.getLayoutParams().height = (int) ((metrics.heightPixels * 2) / 8);

		tvQuestion = (TextView) findViewById(R.id.question);
		tvQuestion.setGravity(Gravity.CENTER_HORIZONTAL);
		tvQuestion.setWidth(metrics.widthPixels);
		tvQuestion.setHeight((int) (metrics.heightPixels / 8));
		tvQuestion.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				(int) (metrics.heightPixels / 16));

		tvQuestion.getLayoutParams().height = (int) (metrics.heightPixels / 8);

		myTts = new TextToSpeech(this, this);
		myTts.setOnUtteranceCompletedListener(this);

		findViewById(R.id.tableButtons).setPadding(0, 0, 0, 0);

		setParameterRow(findViewById(R.id.TableRow01));
		setParameterRow(findViewById(R.id.TableRow02));
		setParameterRow(findViewById(R.id.TableRow03));
		setParameterRow(findViewById(R.id.TableRow04));

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
		if (myTts != null) {
			mot = dictionnaire.getRandom();

			tvQuestion.setTextColor(Color.BLACK);

			BitmapDrawable bmd;

			bmd = ImageUtil.loadBitmpaDrawable(mot, metrics.widthPixels,
					(int) (metrics.heightPixels * 2 / 8));

			imgView.setImageDrawable(bmd);

			String laQuestion = "Trouve la premiere lettre du mot "
					+ mot.getMot();

			montrerEtParler(laQuestion, false);
		}
	}

	public void onInit(int status) {
		nouveauMot();
		myTts.setOnUtteranceCompletedListener(this);
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

	public void montrerEtParler(String text, boolean good) {
		HashMap<String, String> speakParam = new HashMap<String, String>();
		speakParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, ""
				+ speechId);
		myTts.speak(text, TextToSpeech.QUEUE_ADD, speakParam);

		if (good == false) {
			tvQuestion.setTextColor(Color.BLACK);
			StringBuffer sb = new StringBuffer(mot.getMot());
			sb.replace(0, 1, "?");
			tvQuestion.setText(sb);
		} else {
			tvQuestion.setTextColor(Color.GREEN);
			tvQuestion.setText(mot.getMot());
		}
	}

	public class ChangeWordThread implements Runnable {

		public void run() {

			nouveauMot();

		}

	}

	public void onClick(View v) {
		System.out.println("onClick:" + v.getClass().getName());

		if (v instanceof Button) {
			Collator compareOperator = Collator.getInstance(Locale.FRENCH);
			compareOperator.setStrength(Collator.PRIMARY);
			Button b = (Button) v;
			if (compareOperator.compare(mot.getMot().substring(0, 1)
					.toUpperCase(), b.getText().toString().toUpperCase()) == 0) {
				programmerUnNouveauMot = true;

				myTts.stop();

				tvQuestion.setTextColor(Color.GREEN);
				tvQuestion.setText(mot.getMot().toUpperCase());

				montrerEtParler("Bien joué !", true);

			} else {
				tvQuestion.refreshDrawableState();

				montrerEtParler(
						"Non ! çà ne commence pas par un " + b.getText() + ", "
								+ mot.getMot() + " commence par "
								+ mot.getMot().substring(0, 1), false);

				tvQuestion.setText(mot.getMot().toUpperCase());
				tvQuestion.setTextColor(Color.RED);
			}
		}
	}

}
