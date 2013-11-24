package org.jenseigne;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class TextAlert {

	private EditText input;
	private AlertDialog.Builder alert;

	private final OnClickListener onTerminate;

	public TextAlert(Context context, String titre, String message,
			OnClickListener onTerminate) {
		alert = new AlertDialog.Builder(context);
		this.onTerminate = onTerminate;
		alert.setTitle(titre);
		alert.setMessage(message);

		// Set an EditText view to get user input
		input = new EditText(context);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				TextAlert.this.onTerminate.onClick(input);
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						TextAlert.this.onTerminate.onClick(input);
					}
				});

	}

	public void show() {
		alert.show();
	}

	public String getText() {
		return input.getText().toString();
	}

}
