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
	AlertDialog.Builder alertDialogBuilder;
	AlertDialog.Builder alertDialogBuilder2;
	EditText pathEdit;
	Editable startPathName;
	Editable endPathName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timer_view);
		
		pathEdit = new EditText(this);
		alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder2 = new AlertDialog.Builder(this);
		alertDialogBuilder2.setTitle("Ending Location");
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
		alertDialogBuilder2
			.setView(pathEdit)
			.setMessage("Enter the ending location of the path and hit OK to continue")
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					endPathName = pathEdit.getText();
					chron.setBase(SystemClock.elapsedRealtime());
					chron.stop();
				}
			})
			.setNegativeButton("Cancel", null);
		alertDialog = alertDialogBuilder.create();
		alertDialog2 = alertDialogBuilder2.create();
		chron = (Chronometer)findViewById(R.id.chronometer1);
		referenceTime = 0;
	}	
	
	public void startTimer(View v) {
		if (firstStart) {
			firstStart = false;
			alertDialog.show();
		} else {
			chron.setBase(chron.getBase() + SystemClock.elapsedRealtime() - referenceTime);
			chron.start();
		}
	}
	
	public void stopTimer(View v) {
		alertDialog2.show();
	}
	
	public void pauseTimer(View v) {
		referenceTime = SystemClock.elapsedRealtime();
		chron.stop();
	}
	
	
}
