package org.example;

import Entities.Commander;
import Helpers.Enums.SortingOptions;

import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//https://www.geeksforgeeks.org/java-main-method-public-static-void-main-string-args/
public class Main {
    public static void main(String[] args) {

        SortingOptions sortOption = SortingOptions.NONE;

        if (args.length == 1) {
            try {
                int opt = Integer.parseInt(args[0]);
                sortOption = SortingOptions.values()[opt-1];
            } catch (NumberFormatException e) {
                System.out.println("Error! Running argument must be an integer between <1,3>!");
            }
        }

        Commander c0 = new Commander("COMMANDER",10,10, sortOption);
        Commander c1 = new Commander("a",9,11, sortOption);
        Commander c2 = new Commander("b",8,8, sortOption);
        Commander c3 = new Commander("c",7,7, sortOption);
        Commander c4 = new Commander("d",6,6, sortOption);
        Commander c5 = new Commander("e",5,5, sortOption);
        Commander c6 = new Commander("f",4,100, sortOption);
        Commander c7 = new Commander("g",3,2, sortOption);
        Commander c8 = new Commander("h",2,2, sortOption);

        c0.addArmy(c1);
        c1.addArmy(c2);
        c1.addArmy(c3);
        c2.addArmy(c4);
        c2.addArmy(c5);
        c3.addArmy(c6);
        c3.addArmy(c7);
        c1.addArmy(c8);

        System.out.println(c0.details());


        Map<Commander, Integer> stats = c0.generateStatistics(sortOption);
        stats.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}