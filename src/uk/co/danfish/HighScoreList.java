package uk.co.danfish;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

public class HighScoreList extends ListActivity {
	private static final int CLEAR_HIGHSCORES_ID = Menu.FIRST;
	
	private HighScoresAdaptor mDbHelper;
	private Long mRowId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDbHelper = new HighScoresAdaptor(this);
		mDbHelper.open();
		mRowId = savedInstanceState != null ? savedInstanceState
				.getLong(HighScoresAdaptor.KEY_ROWID) : null;
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras
					.getLong(HighScoresAdaptor.KEY_ROWID) : null;
		}
		setContentView(R.layout.highscore_list);
		mDbHelper.open();
		fillData();
	}
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, CLEAR_HIGHSCORES_ID, 0, R.string.menu_clear_high_scores);
        return true;
    }
	
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
        case CLEAR_HIGHSCORES_ID:
            clearHighScores();
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
    
	private void clearHighScores() {
		mDbHelper.deleteAll();
	}

	private void fillData() {
		Cursor notesCursor = mDbHelper.fetchAllNotes();
		startManagingCursor(notesCursor);
		String[] from = new String[] {
				HighScoresAdaptor.KEY_DATE,  HighScoresAdaptor.KEY_NAME,HighScoresAdaptor.KEY_SCORE };
		int[] to = new int[] { R.id.hsrow_date, R.id.hsrow_name,
				R.id.hsrow_score };
		SimpleCursorAdapter scores = new SimpleCursorAdapter(this,
				R.layout.highscore_row, notesCursor, from, to);
		setListAdapter(scores);
	}
}
