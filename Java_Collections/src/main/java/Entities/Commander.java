package Entities;

import Helpers.Comparators.CommanderComparator;
import Helpers.Enums.SortingOptions;

import java.util.*;
import java.util.stream.Collectors;

public class Commander implements Comparable<Commander> {
    private String name;
    private int level;
    private double power;
    private Set<Commander> army;

    public Commander(String name, int level, double power, SortingOptions opt) {
        this.name = name;
        this.level = level;
        this.power = power;
        switch (opt) {
            case NONE -> this.army = new HashSet<Commander>();
            case NATURAL -> this.army = new TreeSet<Commander>();
            case ALTERNATIVE -> this.army = new TreeSet<Commander>(new CommanderComparator());
        }
    }

    public void addArmy(Commander c) {
        army.add(c);
    }

    @Override
    public String toString() {
        return "Commander{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", power=" + power +
                '}';
    }

    public String details() {
        return this.detailsHelper(1);
    }
    private String detailsHelper(int degree) {
        String indentation = "-".repeat(degree);

        String armyString = army.stream().map(commander -> commander.detailsHelper(degree+1)).collect(Collectors.joining("\n"));

        return indentation + this.toString() +
                (army.isEmpty() ? "" : "\n" + armyString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commander commander)) return false;
        return level == commander.level && Double.compare(power, commander.power) == 0 && Objects.equals(name, commander.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, power);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setArmy(Set<Commander> army) {
        this.army = army;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getPower() {
        return power;
    }

    public Set<Commander> getArmy() {
        return army;
    }

    @Override
    public int compareTo(Commander o) {
        if (this.equals(o)) {
            return 0;
        }

        if (!this.name.equals(o.name)) {
            return this.name.compareTo(o.name);
        }

        if (this.level != o.level) {
            return Integer.compare(this.level, o.level);
        }

        return Double.compare(this.power,o.power);
    }

    public Map<Commander, Integer> generateStatistics(SortingOptions opt) {
        Map<Commander, Integer> stats = switch (opt) {
            case NONE -> new HashMap<Commander, Integer>();
            case NATURAL -> new TreeMap<Commander, Integer>();
            case ALTERNATIVE -> new TreeMap<Commander, Integer>(new CommanderComparator());
        };

        generateStatisticsHelper(this, stats);

        return stats;
    }

    public void generateStatisticsHelper(Commander commander, Map<Commander,Integer> stats) {
        //Stats already counted for this mage
        if (stats.containsKey(commander)) {
            return;
        }

        int apprenticeCount = 0;
        for (var c : commander.army) {
            generateStatisticsHelper(c, stats);
            apprenticeCount += 1 + stats.get(c);
        }

        stats.put(commander, apprenticeCount);
    }
}
