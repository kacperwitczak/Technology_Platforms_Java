package com.example.Java_SpringData_JPA_Hibernate.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mage {
    @Id
    private String name;

    private int level;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private Tower tower;

    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
        tower.addMage(this);
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

}
