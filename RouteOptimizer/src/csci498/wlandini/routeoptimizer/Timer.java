package csci498.wlandini.routeoptimizer;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class Timer extends Activity {
	
	Chronometer chron;
	long referenceTime;
	boolean firstStart = true;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timer_view);
		
		chron = (Chronometer)findViewById(R.id.chronometer1);
		referenceTime = 0;
	}	
	
	public void startTimer(View v) {
		if (firstStart) {
			chron.setBase(SystemClock.elapsedRealtime());
			firstStart = false;
		} else {
			chron.setBase(chron.getBase() + SystemClock.elapsedRealtime() - referenceTime);
		}
		chron.start();
	}
	
	public void stopTimer(View v) {
		referenceTime = SystemClock.elapsedRealtime();
		chron.stop();
	}
	
	public void pauseTimer(View v) {
		referenceTime = SystemClock.elapsedRealtime();
		chron.stop();
	}
	
	
}
