package uk.co.danfish;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class HighScoreList extends ListActivity {
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

	private void fillData() {
		Cursor notesCursor = mDbHelper.fetchAllNotes();
		startManagingCursor(notesCursor);
		String[] from = new String[] { HighScoresAdaptor.KEY_NAME,
				HighScoresAdaptor.KEY_DATE, HighScoresAdaptor.KEY_SCORE };
		int[] to = new int[] { R.id.hsrow_date, R.id.hsrow_name,
				R.id.hsrow_score };
		SimpleCursorAdapter scores = new SimpleCursorAdapter(this,
				R.layout.highscore_row, notesCursor, from, to);
		setListAdapter(scores);
	}
}
