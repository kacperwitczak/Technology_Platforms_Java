package org.example.Database;

import org.example.Entities.Mage;
import org.example.Entities.Tower;
import org.example.Repositories.MageRepository;
import org.example.Repositories.TowerRepository;

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

        towerRepository.saveTower(t1);
        towerRepository.saveTower(t2);

        Mage m1 = new Mage("Mage_1", 100, t1);
        Mage m2 = new Mage("Mage_2", 200, t2);
        Mage m3 = new Mage("Mage_3", 300, t1);
        Mage m4 = new Mage("Mage_4", 400, t2);
        Mage m5 = new Mage("Mage_5", 500, t1);
        Mage m6 = new Mage("Mage_6", 600, t2);
        Mage m7 = new Mage("Mage_7", 700, t1);
        Mage m8 = new Mage("Mage_8", 800, t2);

        mageRepository.saveMage(m1);
        mageRepository.saveMage(m2);
        mageRepository.saveMage(m3);
        mageRepository.saveMage(m4);
        mageRepository.saveMage(m5);
        mageRepository.saveMage(m6);
        mageRepository.saveMage(m7);
        mageRepository.saveMage(m8);


    }
}

