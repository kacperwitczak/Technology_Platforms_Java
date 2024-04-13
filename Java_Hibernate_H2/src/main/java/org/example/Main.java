package org.example;

import org.example.Database.DatabaseManager;
import org.example.Database.DatabaseSeeder;
import org.example.Entities.Mage;
import org.example.Entities.Tower;
import org.example.Repositories.MageRepository;
import org.example.Repositories.TowerRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        TowerRepository towerRepository = new TowerRepository(sessionFactory);
        MageRepository mageRepository = new MageRepository(sessionFactory);

        DatabaseManager databaseManager = new DatabaseManager(towerRepository, mageRepository);
        DatabaseSeeder databaseSeeder = new DatabaseSeeder(towerRepository, mageRepository);
        databaseSeeder.seed();
        databaseManager.printAll();

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!input.equals("-q")) {
            System.out.println("Enter -q to quit");
            System.out.println("1. Add Tower");
            System.out.println("2. Add Mage");
            System.out.println("3. Delete Tower");
            System.out.println("4. Delete Mage");
            System.out.println("5. findAllByLevelGreaterThanAndTowerEquals");
            System.out.println("6. findAllByLevelGreaterThan");
            System.out.println("7. findAllByHeightLessThan");
            System.out.println("8. Print All");

            input = sc.nextLine();
            if (input.equals("-q")) {
                break;
            } else if (input.equals("1")) {
                System.out.println("Enter tower name");
                String name = sc.nextLine();
                System.out.println("Enter tower height");
                int height = sc.nextInt();
                sc.nextLine();
                Tower t = new Tower(name, height);
                towerRepository.saveTower(t);
            } else if (input.equals("2")) {
                System.out.println("Enter mage name");
                String name = sc.nextLine();
                System.out.println("Enter mage level");
                int level = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter tower name");
                String towerName = sc.nextLine();
                Tower t = towerRepository.findTower(towerName);
                //should be in service layer
                if (t == null) {
                    System.out.println("Tower does not exist");
                    continue;
                }
                Mage m = new Mage(name, level, t);
                mageRepository.saveMage(m);
            } else if (input.equals("3")) {
                System.out.println("Enter tower name");
                String name = sc.nextLine();
                towerRepository.deleteTower(name);
            } else if (input.equals("4")) {
                System.out.println("Enter mage name");
                String name = sc.nextLine();
                mageRepository.deleteMage(name);
            } else if (input.equals("5")) {
                System.out.println("Enter level");
                int level = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter tower name");
                String towerName = sc.nextLine();
                System.out.println(mageRepository.findAllByLevelGreaterThanAndTowerEquals(level, towerName));
            } else if (input.equals("6")) {
                System.out.println("Enter level");
                int level = sc.nextInt();
                sc.nextLine();
                System.out.println(mageRepository.findAllByLevelGreaterThan(level));
            } else if (input.equals("7")) {
                System.out.println("Enter height");
                int height = sc.nextInt();
                sc.nextLine();
                System.out.println(towerRepository.findAllByHeightLessThan(height));
            } else if (input.equals("8")) {
                databaseManager.printAll();
            } else {
                System.out.println("Invalid input");
            }
        }

        sessionFactory.close();
    }
}