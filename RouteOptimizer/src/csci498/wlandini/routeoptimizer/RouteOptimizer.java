package csci498.wlandini.routeoptimizer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class RouteOptimizer extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_optimizer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_route_optimizer, menu);
        return true;
    }
    
    public void startNewPath(View view) {
    	Intent i = new Intent(this, Timer.class);
    	startActivity(i);
    }
    
    public void showListOfPaths(View view) {
    	Intent i = new Intent(this, PathList.class);
    	startActivity(i);
    }
}
