package uk.co.danfish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Card {

	public enum Rank {
		DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
	}

	public enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}

	abstract int getVal();

	abstract boolean isPlayable();

	abstract Rank rank();

	abstract Suit suit();

	public boolean sameSuitAs(Card other)
	{
		if (!isPlayable()|| !other.isPlayable())
			return false;
		return this.suit()==other.suit();
	}

	public boolean sameRankAs(Card other) {

		if (!isPlayable()|| !other.isPlayable())
			return false;
		return this.rank()==other.rank();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
