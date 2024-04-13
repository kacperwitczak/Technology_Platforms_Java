package com.example.Java_SpringData_JPA_Hibernate.Service;

import com.example.Java_SpringData_JPA_Hibernate.Entities.Tower;

import java.util.List;

public interface ITowerService {
    void addTower(Tower t);
    void updateTower(Tower t);
    void deleteTower(String id);
    Tower getTower(String id);
    List<Tower> getAllTowers();

    List<Tower> findAllByHeightLessThan(int height);
}
