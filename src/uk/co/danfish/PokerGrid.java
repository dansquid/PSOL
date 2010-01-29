package uk.co.danfish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import android.app.Activity;
import android.graphics.Canvas.VertexMode;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class PokerGrid extends Activity implements DropSurface {

	public static Board board;
	public static Stack<Card> pack;
	static {
		newTable();
	}

	private static void newTable() {
		board = new Board();
		pack = new Stack<Card>();
		ArrayList<Card> deck = CardFactory.newDeck();
		Collections.shuffle(deck);
		pack.addAll(deck);
	}

	public CardSlot[] slots;
	private CardSource source;
	private TextView tv;
	public PokerGrid() {
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			restoreState(savedInstanceState);
		}
		if (slots == null) {
			slots = new CardSlot[25];
			for (int i = 0; i < slots.length; i++) {
				CardSlot iv = new CardSlot(this, this, i);
				iv.setScaleType(ScaleType.FIT_XY);
				iv.setAdjustViewBounds(true);
				iv.setMaxHeight(64);
				iv.setMaxWidth(48);
				slots[i] = iv;
			}
		}
		LinearLayout llv = new LinearLayout(this);
		llv.setOrientation(LinearLayout.VERTICAL);
		LinearLayout ll = new LinearLayout(this);
		llv.addView(ll);
		TableLayout t = new TableLayout(this);
		t.setPadding(10, 10, 10, 10);
		int cardslot = 0;
		for (int i = 0; i < 5; i++) {
			TableRow r = new TableRow(this);
			r.setMinimumHeight(64);
			t.addView(r);
			for (int j = 0; j < 5; j++) {
				r.addView(slots[cardslot]);
				cardslot++;
			}
		}
		ll.addView(t);

		Button newgameButton = new Button(this);
		newgameButton.setText("New Game");
		newgameButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				startNewGame();
			}
		});
		llv.addView(newgameButton);
		TextView tv = new TextView(this);
		tv.setText("play!");
		llv.addView(tv);
		
		source = new CardSource(this, pack);
		ll.addView(source);

		setContentView(llv);
	}

	private void restoreState(Bundle savedInstanceState) {
		int[] boardIntArray = savedInstanceState.getIntArray("BOARD");
		board = new Board(boardIntArray);
		
		Card[] pArr = CardFactory.fromInts(savedInstanceState
				.getIntArray("PACK"));
		pack.clear();
		for (int i = 0; i < pArr.length; i++) {
			pack.add(pArr[i]);
		}

	}

	private void startNewGame() {
		newTable();
		setupTable();
	}

	private void setupTable() {
		
		for (int i = 0; i < slots.length; i++) {
			CardSlot iv = slots[i];
			Card c = board.atPos(i);
			iv.setCard(c);
		}
		source.setPack(pack);
		for (int i = 0; i < 23; i++) {
			dropped(i);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		try {
			outState.putIntArray("PACK", CardFactory
					.toInts(new ArrayList<Card>(pack)));
			outState.putIntArray("BOARD", board.toInts());
		} catch (Exception ex) {
			Log.e("BOARD", "Error saving state:" + ex);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		setupTable();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		setupTable();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		restoreState(savedInstanceState);
	}

	public boolean dropped(int pos) {
		Card droppedCard = pack.pop();
		slots[pos].setCard(droppedCard);
		board.set(pos,droppedCard);
		source.refreshImage();
		if (board.isFull()) {
			Log.i("BOARD", "FINSHED.");
			tv.setText("Last score: "+board.score());
			newTable();
			setupTable();
		}
		return true;
	}
}