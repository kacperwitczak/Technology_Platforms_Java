package com.example.Java_SpringData_JPA_Hibernate.DatabaseHelpers;

import com.example.Java_SpringData_JPA_Hibernate.Entities.Mage;
import com.example.Java_SpringData_JPA_Hibernate.Entities.Tower;
import com.example.Java_SpringData_JPA_Hibernate.Repository.MageRepository;
import com.example.Java_SpringData_JPA_Hibernate.Repository.TowerRepository;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    private final TowerRepository towerRepository;

    private final MageRepository mageRepository;


    public DatabaseSeeder (TowerRepository towerRepository, MageRepository mageRepository) {
        this.towerRepository = towerRepository;
        this.mageRepository = mageRepository;
    }

    public void seed() {
        Tower t1 = new Tower("Tower_1", 100);
        Tower t2 = new Tower("Tower_2", 200);

        towerRepository.save(t1);
        towerRepository.save(t2);

        Mage m1 = new Mage("Mage_1", 100, t1);
        Mage m2 = new Mage("Mage_2", 200, t2);
        Mage m3 = new Mage("Mage_3", 300, t1);
        Mage m4 = new Mage("Mage_4", 400, t2);
        Mage m5 = new Mage("Mage_5", 500, t1);
        Mage m6 = new Mage("Mage_6", 600, t2);
        Mage m7 = new Mage("Mage_7", 700, t1);
        Mage m8 = new Mage("Mage_8", 800, t2);

        mageRepository.save(m1);
        mageRepository.save(m2);
        mageRepository.save(m3);
        mageRepository.save(m4);
        mageRepository.save(m5);
        mageRepository.save(m6);
        mageRepository.save(m7);
        mageRepository.save(m8);

    }
}
