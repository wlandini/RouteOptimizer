package csci498.wlandini.routeoptimizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

public class Timer extends Activity {
	
	Chronometer chron;
	long referenceTime;
	boolean firstStart = true;
	AlertDialog alertDialog;
	AlertDialog alertDialog2;
	AlertDialog alertDialog3;
	AlertDialog.Builder alertDialogBuilder;
	AlertDialog.Builder alertDialogBuilder2;
	AlertDialog.Builder alertDialogBuilder3;
	EditText pathEdit;
	EditText pathEdit2;
	EditText pathEdit3;
	Editable startPathName;
	Editable endPathName;
	Editable description;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timer_view);
		
		pathEdit = new EditText(this);
		pathEdit2 = new EditText(this);
		pathEdit3 = new EditText(this);
		alertDialogBuilder3 = new AlertDialog.Builder(this);
		alertDialogBuilder3.
		setTitle("Description")
		.setView(pathEdit3)
		.setMessage("Enter a description of the path")
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				description = pathEdit3.getText();
			}
		})
		.setNegativeButton("Cancel", null);
		chron = (Chronometer)findViewById(R.id.chronometer1);
		referenceTime = 0;
	}	
	
	public void startTimer(View v) {
		if (firstStart) {
			firstStart = false;
			alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setTitle("Starting Location");
			alertDialogBuilder
				.setView(pathEdit)
				.setMessage("Enter the starting location of the path and hit OK to start timer")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						startPathName = pathEdit.getText();
						chron.setBase(SystemClock.elapsedRealtime());
						chron.start();
					}
				})
				.setNegativeButton("Cancel", null);
			alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		} else {
			chron.setBase(chron.getBase() + SystemClock.elapsedRealtime() - referenceTime);
			chron.start();
		}
	}
	
	public void stopTimer(View v) {
		chron.stop();
		chron.setBase(SystemClock.elapsedRealtime());
		alertDialogBuilder2 = new AlertDialog.Builder(this);
		alertDialogBuilder2.setTitle("Ending Location");
		alertDialogBuilder2
		.setView(pathEdit2)
		.setMessage("Enter the ending location of the path and hit OK to continue")
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				endPathName = pathEdit2.getText();
				alertDialog3 = alertDialogBuilder3.create();
				alertDialog3.show();
			}
		})
		.setNegativeButton("Cancel", null);
		alertDialog2 = alertDialogBuilder2.create();
		alertDialog2.show();
	}
	
	public void pauseTimer(View v) {
		referenceTime = SystemClock.elapsedRealtime();
		chron.stop();
	}
	
	
}
