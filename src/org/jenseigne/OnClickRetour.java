/**
 * 
 */
package org.jenseigne;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickRetour implements OnClickListener {
	private DialogScore dialogToHide;
	private Activity sourceActivity;

	public OnClickRetour(Activity source, DialogScore dialogToHide) {
		this.sourceActivity = source;
		this.dialogToHide = dialogToHide;
	}

	public void onClick(View v) {
		dialogToHide.hide();
		try {
			Bundle bundle = new Bundle();

			// On envoi le message
			Intent defineIntent = new Intent(
					sourceActivity.getApplicationContext(), MenuActivity.class);

			// on passe notre objet a notre activities
			defineIntent.putExtras(bundle);

			// on appelle notre activité
			sourceActivity.startActivityForResult(defineIntent, 0);
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}

}