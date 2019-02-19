package org.jenseigne;

import org.jenseigne.activity.drawletter.DessineLesLettresActivity;
import org.jenseigne.activity.drawletter.DevDessineLettreActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class MenuActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		getWindow().setBackgroundDrawable(
				ImageUtil.loadBitmpaDrawable(MenuActivity.class
						.getResourceAsStream("res/background-accueil.png"),
						metrics.widthPixels, metrics.heightPixels, false));	
			
		ScrollView scroller = (ScrollView) findViewById(R.id.rollMenuButtonContainer);
		
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(scroller.getLayoutParams());
		param.setMargins(5, metrics.heightPixels/4, 5, 5);
		
		
		scroller.setLayoutParams(param);
	        
    }


	public void onClickPremiereLettre(View view) {
		try {
			Bundle bundle = new Bundle();

			// On envoi le message
			Intent defineIntent = new Intent(getApplicationContext(),
					JEnseigneMain.class);

			// on passe notre objet a notre activities
			defineIntent.putExtras(bundle);

			// on appelle notre activité
			startActivityForResult(defineIntent, 0);
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}

	public void onClickCourseAuxMots(View view) {
		try {
			Bundle bundle = new Bundle();

			// On envoi le message
			Intent defineIntent = new Intent(getApplicationContext(),
					CourseAuxMotsActivity.class);

			// on passe notre objet a notre activities
			defineIntent.putExtras(bundle);

			// on appelle notre activité
			startActivityForResult(defineIntent, 0);
		} catch (Throwable th) {
			th.printStackTrace();
		}

	}

	public void onClickMelimeloDeMots(View view) {
		try {
			Bundle bundle = new Bundle();

			// On envoi le message
			Intent defineIntent = new Intent(getApplicationContext(),
					MelimeloDeMotsActivity.class);

			// on passe notre objet a notre activities
			defineIntent.putExtras(bundle);

			// on appelle notre activité
			startActivityForResult(defineIntent, 0);
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}

	public void onClickDessineLesLettres(View view) {
		try {
			Bundle bundle = new Bundle();

			// On envoi le message
			Intent defineIntent = new Intent(getApplicationContext(),
					DessineLesLettresActivity.class);

			// on passe notre objet a notre activities
			defineIntent.putExtras(bundle);

			// on appelle notre activité
			startActivityForResult(defineIntent, 0);
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}

	public void onClickDevDessineLesLettres(View view) {
		try {
			Bundle bundle = new Bundle();

			// On envoi le message
			Intent defineIntent = new Intent(getApplicationContext(),
					DevDessineLettreActivity.class);

			// on passe notre objet a notre activities
			defineIntent.putExtras(bundle);

			// on appelle notre activité
			startActivityForResult(defineIntent, 0);
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}
}
