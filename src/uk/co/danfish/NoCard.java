package uk.co.danfish;

public class NoCard extends Card {

	private static NoCard theInstance;

	static {
		theInstance = new NoCard();
	}

	private NoCard() {
	}

	public static NoCard instance() {
		return theInstance;
	}

	public int getVal() {
		return -1;
	}

	public boolean isPlayable() {
		return false;
	}

	@Override
	Rank rank() {
		return null;
	}

	@Override
	Suit suit() {
		return null;
	}
	
	@Override
	public String toString() {
		return "<empty>";
	}

}
