package csci498.wlandini.routeoptimizer;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PathList extends ListActivity {
	
	Cursor c = null;
	PathAdapter adapter = null;
	SQLHelper helper = null;
	public final static String ID_EXTRA = "csci498.wlandini.routeoptimizer._ID";
	Intent i;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.path_list);

		helper = new SQLHelper(this);
		initList();
		i = new Intent(this, RouteOptimizer.class);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		
		helper.close();
		startActivity(i);
	}
	

	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		Intent i = new Intent(PathList.this, PathInfo.class);
		i.putExtra(ID_EXTRA, String.valueOf(id));
		startActivity(i);
	}


	@SuppressWarnings("deprecation")
	private void initList() {
		if (c != null) {
			stopManagingCursor(c);
			c.close();
		}

		c = helper.getAllPaths();
		startManagingCursor(c);
		adapter = new PathAdapter(c);
		setListAdapter(adapter);
	}

	class PathAdapter extends CursorAdapter {
		@SuppressWarnings("deprecation")
		PathAdapter(Cursor c) {
			super(PathList.this, c);
		}

		@Override
		public void bindView(View row, Context ctxt, Cursor c) {
			PathHolder holder = (PathHolder)row.getTag();
			holder.populateFrom(c, helper);
		}

		@Override
		public View newView(Context ctxt, Cursor c, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.row, parent, false);
			PathHolder holder = new PathHolder(row);
			row.setTag(holder);
			return row;
		}

	}

	static class PathHolder {
		
		private TextView start = null;
		private TextView finish = null;

		PathHolder(View row) {
			start = (TextView)row.findViewById(R.id.start);
			finish = (TextView)row.findViewById(R.id.finish);
		}

		void populateFrom(Cursor c, SQLHelper helper) {
			start.setText(helper.getStart(c));
			finish.setText(helper.getFinish(c));
		}
	}
}
