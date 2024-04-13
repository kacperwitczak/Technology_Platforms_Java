package org.example.Entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Tower {
    @Id
    private String name;

    private int height;

    @OneToMany(mappedBy = "tower", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Mage> mages;

    public Tower(String name, int height) {
        this.name = name;
        this.height = height;
        mages = new ArrayList<>();
    }

    public Tower() {
        this.name = "";
        this.height = 0;
        mages = new ArrayList<>();
    }

    public void addMage(Mage mage) {
        mages.add(mage);
    }

    public void removeMage(Mage mage) {
        mages.remove(mage);
    }

    @Override
    public String toString() {
        return "Tower: " + name + " Height: " + height + " Mages count: " + mages.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tower tower = (Tower) obj;
        return Objects.equals(name, tower.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Mage> getMages() {
        return mages;
    }

    public void setMages(List<Mage> mages) {
        this.mages = mages;
    }
}
