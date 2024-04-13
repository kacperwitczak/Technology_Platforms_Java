package com.example.Java_SpringData_JPA_Hibernate;

import com.example.Java_SpringData_JPA_Hibernate.DatabaseHelpers.DatabaseManager;
import com.example.Java_SpringData_JPA_Hibernate.DatabaseHelpers.DatabaseSeeder;
import com.example.Java_SpringData_JPA_Hibernate.Entities.Mage;
import com.example.Java_SpringData_JPA_Hibernate.Entities.Tower;
import com.example.Java_SpringData_JPA_Hibernate.Service.IMageService;
import com.example.Java_SpringData_JPA_Hibernate.Service.ITowerService;
import com.example.Java_SpringData_JPA_Hibernate.Service.MageService;
import com.example.Java_SpringData_JPA_Hibernate.Service.TowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class JavaSpringDataJpaHibernateApplication implements CommandLineRunner {

	@Autowired
	private DatabaseSeeder databaseSeeder;

	@Autowired
	private DatabaseManager databaseManager;

	@Autowired
	private ITowerService towerService;

	@Autowired
	private IMageService mageService;

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringDataJpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
				towerService.addTower(t);
			} else if (input.equals("2")) {
				System.out.println("Enter mage name");
				String name = sc.nextLine();
				System.out.println("Enter mage level");
				int level = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter tower name");
				String towerName = sc.nextLine();
				Tower t = towerService.getTower(towerName);
				if (t == null) {
					System.out.println("Tower does not exist");
					continue;
				}
				Mage m = new Mage(name, level, t);
				mageService.addMage(m);
			} else if (input.equals("3")) {
				System.out.println("Enter tower name");
				String name = sc.nextLine();
				towerService.deleteTower(name);
			} else if (input.equals("4")) {
				System.out.println("Enter mage name");
				String name = sc.nextLine();
				mageService.deleteMage(name);
			} else if (input.equals("5")) {
				System.out.println("Enter level");
				int level = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter tower name");
				String towerName = sc.nextLine();
				System.out.println(mageService.findAllByLevelGreaterThanAndTowerEquals(level, towerName));
			} else if (input.equals("6")) {
				System.out.println("Enter level");
				int level = sc.nextInt();
				sc.nextLine();
				System.out.println(mageService.findAllByLevelGreaterThan(level));
			} else if (input.equals("7")) {
				System.out.println("Enter height");
				int height = sc.nextInt();
				sc.nextLine();
				System.out.println(towerService.findAllByHeightLessThan(height));
			} else if (input.equals("8")) {
				databaseManager.printAll();
			} else {
				System.out.println("Invalid input");
			}
		}
	}

}
