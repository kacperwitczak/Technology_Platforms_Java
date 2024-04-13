package org.example.Entities;

import javax.persistence.*;

import java.util.Objects;

@Entity
public class Mage {
    @Id
    private String name;

    private int level;

    @ManyToOne(cascade= CascadeType.REFRESH)
    private Tower tower;

    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
        tower.addMage(this);
    }

    public Mage() {
        this.name = "";
        this.level = 0;
        this.tower = null;
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", tower=" + tower.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return Objects.equals(name, mage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }
}

