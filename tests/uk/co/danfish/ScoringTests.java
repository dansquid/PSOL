package uk.co.danfish;

import uk.co.danfish.Card.Rank;
import uk.co.danfish.Card.Suit;

public class ScoringTests {

	
	public static void main(String[] args) {
		try {
			Hand h = new Hand();
			h.add(new PlayableCard(Rank.ACE, Suit.CLUBS));
			h.add(new PlayableCard(Rank.KING, Suit.SPADES));
			h.add(new PlayableCard(Rank.KING, Suit.CLUBS));
			h.add(new PlayableCard(Rank.QUEEN, Suit.CLUBS));
			h.add(NoCard.instance());
			int score = h.score();
			if (score!=1)
				throw new Exception("wrong 1");
		} catch (Exception e) {
			System.out.println("FAIL:"+e);
		}
	}

}
