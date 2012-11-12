package csci498.wlandini.routeoptimizer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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
}
