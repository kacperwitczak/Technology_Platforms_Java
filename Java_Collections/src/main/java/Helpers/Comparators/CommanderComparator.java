package Helpers.Comparators;

import Entities.Commander;

import java.util.Comparator;

//how to implement comparator
//https://www.geeksforgeeks.org/comparator-interface-java/
public class CommanderComparator implements Comparator<Commander> {

    @Override
    public int compare(Commander c1, Commander c2) {
        int c1Level = c1.getLevel();
        int c2Level = c2.getLevel();

        if (c1Level != c2Level) {
            return Integer.compare(c1Level, c2Level);
        }

        String c1Name = c1.getName();
        String c2Name = c2.getName();

        if (!c1Name.equals(c2Name)) {
            return c1Name.compareTo(c2Name);
        }

        return Double.compare(c1.getLevel(), c2.getLevel());
    }
}
