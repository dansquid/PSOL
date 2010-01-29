package uk.co.danfish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlayableCard extends Card {
	

	private final Rank rank;
	private final Suit suit;

	protected PlayableCard(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank rank() {
		return rank;
	}

	public Suit suit() {
		return suit;
	}

	public String toString() {
		return rank + " of " + suit;
	}

	public int getVal() {
		return 51 - (rank.ordinal() * 4 + suit.ordinal());
	}

	public boolean isPlayable() {
		return true;
	}


}
