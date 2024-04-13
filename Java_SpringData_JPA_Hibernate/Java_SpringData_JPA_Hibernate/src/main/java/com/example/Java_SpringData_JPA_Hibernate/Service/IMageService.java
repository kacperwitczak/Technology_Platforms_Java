package com.example.Java_SpringData_JPA_Hibernate.Service;

import com.example.Java_SpringData_JPA_Hibernate.Entities.Mage;

import java.util.List;

public interface IMageService {
    void addMage(Mage m);
    void updateMage(Mage m);
    void deleteMage(String id);
    Mage getMage(String id);
    List<Mage> getAllMages();

    List<Mage> findAllByLevelGreaterThanAndTowerEquals(int level, String towerName);

    List<Mage> findAllByLevelGreaterThan(int level);



}
