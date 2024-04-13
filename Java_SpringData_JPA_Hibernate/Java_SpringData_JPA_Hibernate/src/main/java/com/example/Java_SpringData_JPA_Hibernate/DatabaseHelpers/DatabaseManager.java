package com.example.Java_SpringData_JPA_Hibernate.DatabaseHelpers;

import com.example.Java_SpringData_JPA_Hibernate.Entities.Mage;
import com.example.Java_SpringData_JPA_Hibernate.Entities.Tower;
import com.example.Java_SpringData_JPA_Hibernate.Repository.MageRepository;
import com.example.Java_SpringData_JPA_Hibernate.Repository.TowerRepository;
import com.example.Java_SpringData_JPA_Hibernate.Service.IMageService;
import com.example.Java_SpringData_JPA_Hibernate.Service.ITowerService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseManager {
    private final ITowerService towerService;

    private final IMageService mageService;

    public DatabaseManager(ITowerService towerService, IMageService mageService) {
        this.towerService = towerService;
        this.mageService = mageService;
    }


    public void printAll() {
        System.out.println("===================================");
        System.out.println("Printing database");
        List<Mage> mages = mageService.getAllMages();
        for (Mage m : mages) {
            System.out.println(m);
        }

        List<Tower> towers = towerService.getAllTowers();
        for (Tower t : towers) {
            System.out.println(t);
        }

        System.out.println("===================================");
    }
}
