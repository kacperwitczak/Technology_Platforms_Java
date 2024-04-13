package com.example.Java_SpringData_JPA_Hibernate.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tower {
    @Id
    private String name;

    private int height;

    @OneToMany(mappedBy = "tower", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Mage> mages;

    public Tower(String name, int height) {
        this.name = name;
        this.height = height;
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
}
