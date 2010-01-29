package uk.co.danfish;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private Card[] cards = new Card[25];

	public Board() {
		for (int i = 0; i < cards.length; i++) {
			cards[i] = NoCard.instance();
		}
	}

	public Board(int[] ints) {
		initFromIntArray(ints);
	}

	private void initFromIntArray(int[] ints) {
		for (int i = 0; i < cards.length; i++) {
			if (i >= ints.length)
				break;
			cards[i] = CardFactory.fromInt(ints[i]);
		}
	}

	public int[] toInts() {
		int[] res = new int[cards.length];
		for (int i = 0; i < cards.length; i++) {
			res[i] = cards[i].getVal();
		}
		return res;
	}

	public Card atPos(int i) {
		return this.cards[i];
	}

	public void set(int pos, Card droppedCard) {
		this.cards[pos] = droppedCard;
	}

	public boolean isFull() {
		for (int i = 0; i < cards.length; i++) {
			if (!cards[i].isPlayable())
				return false;
		}
		return true;
	}

	public int score() {
		List<Hand> hands = new ArrayList<Hand>();
		for (int x = 0; x < 5; x++) {
			Hand h = new Hand();
			for (int y = 0; y < 5; y++) {
				h.add(cards[x*5+y]);
			}
			hands.add(h);
		}
		for (int y = 0; y < 5; y++) {
			Hand h = new Hand();
			for (int x = 0; x < 5; x++) {
				h.add(cards[x*5+y]);
			}
			hands.add(h);
		}
		int score = 0;
		for (Hand hand : hands) {
			score += hand.score();
		}
		return score;
	}

}
