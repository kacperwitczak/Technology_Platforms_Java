package org.example.Database;


import org.example.Entities.Mage;
import org.example.Entities.Tower;
import org.example.Repositories.MageRepository;
import org.example.Repositories.TowerRepository;

import java.util.List;

public class DatabaseManager {
    private final TowerRepository towerRepository;

    private final MageRepository mageRepository;

    public DatabaseManager(TowerRepository towerRepository, MageRepository mageRepository) {
        this.towerRepository = towerRepository;
        this.mageRepository = mageRepository;
    }


    public void printAll() {
        System.out.println("===================================");
        System.out.println("Printing database");
        List<Mage> mages = mageRepository.findAll();
        for (Mage m : mages) {
            System.out.println(m);
        }

        List<Tower> towers = towerRepository.findAll();
        for (Tower t : towers) {
            System.out.println(t);
        }

        System.out.println("===================================");
    }
}
