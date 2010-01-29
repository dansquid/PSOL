package uk.co.danfish;

import java.util.Stack;

import android.content.Context;
import android.widget.ImageView;

public class CardSource extends ImageView  {

	private Stack<Card> pack;

	public CardSource(Context context, Stack<Card> pack) {
		super(context);
		this.setPack(pack);
	}

	public void refreshImage() {
		this.setImageResource(CardR.getImageId(getPack().peek()));
	}

	public void setPack(Stack<Card> pack) {
		this.pack = pack;
		this.refreshImage();
	}

	private Stack<Card> getPack() {
		return pack;
	}

}
