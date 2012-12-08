package csci498.wlandini.routeoptimizer;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class PathInfo extends Activity {
	
	@Override 
	public void onCreate(Bundle SavedInstanceState) {
		 super.onCreate(SavedInstanceState);
		 setContentView(R.layout.path_info);
		 
		 TextView tv = (TextView)findViewById(R.id.time);
		 TextView tv2 = (TextView)findViewById(R.id.description);
		 SQLHelper helper = new SQLHelper(this);
	     String PathId = getIntent().getStringExtra(PathList.ID_EXTRA);
	     Cursor c = helper.getPathById(PathId);
	     c.moveToFirst();
	     double time = helper.getTime(c);
	     String description = helper.getDescription(c);
	     tv.setText(Double.toString(time));
	     tv2.setText(description);
	}
	
}
