package uk.co.danfish;

import java.util.ArrayList;
import java.util.List;

public final class CardR {
	private static List<Integer> c = new ArrayList<Integer>();
	static {
		c.add(R.drawable.card_01);
		c.add(R.drawable.card_02);
		c.add(R.drawable.card_03);
		c.add(R.drawable.card_04);
		c.add(R.drawable.card_05);
		c.add(R.drawable.card_06);
		c.add(R.drawable.card_07);
		c.add(R.drawable.card_08);
		c.add(R.drawable.card_09);
		c.add(R.drawable.card_10);
		c.add(R.drawable.card_11);
		c.add(R.drawable.card_12);
		c.add(R.drawable.card_13);
		c.add(R.drawable.card_14);
		c.add(R.drawable.card_15);
		c.add(R.drawable.card_16);
		c.add(R.drawable.card_17);
		c.add(R.drawable.card_18);
		c.add(R.drawable.card_19);
		c.add(R.drawable.card_20);
		c.add(R.drawable.card_21);
		c.add(R.drawable.card_22);
		c.add(R.drawable.card_23);
		c.add(R.drawable.card_24);
		c.add(R.drawable.card_25);
		c.add(R.drawable.card_26);
		c.add(R.drawable.card_27);
		c.add(R.drawable.card_28);
		c.add(R.drawable.card_29);
		c.add(R.drawable.card_30);
		c.add(R.drawable.card_31);
		c.add(R.drawable.card_32);
		c.add(R.drawable.card_33);
		c.add(R.drawable.card_34);
		c.add(R.drawable.card_35);
		c.add(R.drawable.card_36);
		c.add(R.drawable.card_37);
		c.add(R.drawable.card_38);
		c.add(R.drawable.card_39);
		c.add(R.drawable.card_40);
		c.add(R.drawable.card_41);
		c.add(R.drawable.card_42);
		c.add(R.drawable.card_43);
		c.add(R.drawable.card_44);
		c.add(R.drawable.card_45);
		c.add(R.drawable.card_46);
		c.add(R.drawable.card_47);
		c.add(R.drawable.card_48);
		c.add(R.drawable.card_49);
		c.add(R.drawable.card_50);
		c.add(R.drawable.card_51);
		c.add(R.drawable.card_52);
		c.add(R.drawable.card_53);
		c.add(R.drawable.card_54);
	}
	
	public static int getImageId(Card card) {
		int val = card.getVal();
		if (val<0)
			return R.drawable.blank;
		return c.get(card.getVal());
	}
}
