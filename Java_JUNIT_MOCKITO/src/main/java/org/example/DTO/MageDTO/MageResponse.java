package org.example.DTO.MageDTO;

import org.example.Entities.Mage;

import java.util.Objects;

public class MageResponse {
    private String name;
    private int level;

    public MageResponse(String name, int level) {
        this.name = name;
        this.level = level;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MageResponse that = (MageResponse) o;
        return level == that.level && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }

    @Override
    public String toString() {
        return "MageResponse{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
