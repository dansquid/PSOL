package uk.co.danfish;

import java.util.ArrayList;
import java.util.Iterator;

import android.util.Log;

import uk.co.danfish.Card.Rank;

public class Hand extends ArrayList<Card> {

	public int score() {
		String hand = this.toString();
		int res = iscore();
		return res;
	}

	private int iscore() {
		if (strait() & sameSuit()) {
			if (minRankOrdinal(false) == Rank.TEN.ordinal())
				return 30;
			return 30;
		}

		if (ofAKind(4) != null)
			return 16;

		if (strait())
			return 12;

		if (ofAKind(3) != null && ofAKind(2) != null)
			return 10;

		if (ofAKind(3) != null)
			return 6;

		if (sameSuit())
			return 5;

		Rank r = ofAKind(2);

		if (r != null) {
			if (ofAKind(2, r) != null) {
				return 3;
			}
			return 1;
		}
		return 0;
	}

	private Rank ofAKind(int needed) {
		return ofAKind(needed, null);
	}

	private Rank ofAKind(int needed, Rank excluding) {
		if (needed < 2)
			return null;
		for (int i = 0; i <= this.size() - needed; i++) {
			Rank ri = get(i).rank();
			if (excluding == ri)
				continue;
			int found = 1;
			for (int j = i + 1; j < this.size(); j++) {
				if (get(i).sameRankAs(get(j))) {
					found++;
					if (found >= needed)
						return get(i).rank();
				}
			}
		}
		return null;
	}

	public boolean strait() {
		for (Card card : this) {
			if (!card.isPlayable())
				return false;
		}
		if (!allDifferentRank())
			return false;
		if (maxRankOrdinal(false) - minRankOrdinal(false) == 4)
			return true;
		if (maxRankOrdinal(true) - minRankOrdinal(true) == 4)
			return true;
		return false;
	}

	public int minRankOrdinal(boolean acehigh) {
		int min = 13;
		for (Card card : this) {
			int ord = card.rank().ordinal();
			if (acehigh && ord == 0)
				ord = 13;
			if (ord < min)
				min = ord;
		}
		return min;
	}

	public int maxRankOrdinal(boolean acehigh) {
		int max = 0;
		for (Card card : this) {
			int ord = card.rank().ordinal();
			if (acehigh && ord == 0)
				ord = 13;
			if (ord > max)
				max = ord;
		}
		return max;
	}

	private boolean allDifferentRank() {
		for (int i = 0; i < this.size() - 1; i++) {
			for (int j = i + 1; j < this.size(); j++) {
				if (get(i).sameRankAs(get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean sameSuit() {
		Card first = this.get(0);
		for (int i = 1; i < this.size(); i++) {
			if (!first.sameSuitAs(this.get(i)))
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card c : this) {
			sb.append("|");
			sb.append(c.toString());
		}
		sb.append("|");
		return sb.toString();
	}
}
