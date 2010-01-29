package uk.co.danfish;

import java.util.ArrayList;
import java.util.List;

import uk.co.danfish.Card.Rank;
import uk.co.danfish.Card.Suit;

public class CardFactory {
	
	private static final List<Card> protoDeck = new ArrayList<Card>();

	// Initialize prototype deck
	static {
		for (Suit suit : Suit.values())
			for (Rank rank : Rank.values())
				protoDeck.add(new PlayableCard(rank, suit));
	}

	public static ArrayList<Card> newDeck() {
		return new ArrayList<Card>(protoDeck); // Return copy of prototype deck
	}
	
	public static Card fromInt(int val) {
		if (val<0)
			return NoCard.instance();
		Suit suit = Suit.values()[3 - val % 4];
		Rank rank = Rank.values()[12 - val / 4];
		return new PlayableCard(rank,suit);
	}
	
	public static int[] toInts(Card[] cardArr) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < cardArr.length; i++) {
			cards.add(cardArr[i]);
		}
		return toInts(cards);
	}

	public static int[] toInts(ArrayList<Card> arrayList) {
		int[] res = new int[25];
		for (int i = 0; i < arrayList.size(); i++) {
			res[i] = arrayList.get(i) == null ? -1 : arrayList.get(i).getVal();
		}
		return res;
	}

	public static Card[] fromInts(int[] ints) {
		Card[] res = new Card[ints.length];
		for (int i = 0; i < ints.length; i++) {
			res[i] = fromInt(ints[i]);
		}
		return res;
	}
}
