package uk.co.danfish;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.ImageView;

public class CardSlot extends ImageView{

	private DropSurface surface;
	private int pos;
	private Card card;
	
	public CardSlot(Context context,DropSurface surface,int pos) {
		super(context);
		this.surface = surface;
		this.pos = pos;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (this.card.isPlayable())
			return false;
		if (!surface.dropped(this.getPos()))
			return false;
		return super.onTouchEvent(event);
	}

	public int getPos() {
		return pos;
	}

	public void setCard(Card card) {
		this.card = card;
		this.setImageResource(CardR.getImageId(this.card));
	}

}
