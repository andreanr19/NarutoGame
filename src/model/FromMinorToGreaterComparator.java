package model;

import java.util.Comparator;

public class FromMinorToGreaterComparator implements Comparator<Clan> {

	@Override
	public int compare(Clan c1, Clan c2) {
		if(c1.getSize()>c2.getSize()) return 1;
		if(c1.getSize()<c2.getSize()) return -1;
		return 0;
	}

}
