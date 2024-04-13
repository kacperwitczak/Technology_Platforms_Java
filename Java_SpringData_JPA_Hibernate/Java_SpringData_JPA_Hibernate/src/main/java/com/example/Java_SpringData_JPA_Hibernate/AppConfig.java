package com.example.Java_SpringData_JPA_Hibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Java_SpringData_JPA_Hibernate.Repository.MageRepository;
import com.example.Java_SpringData_JPA_Hibernate.Repository.TowerRepository;
import com.example.Java_SpringData_JPA_Hibernate.Service.IMageService;
import com.example.Java_SpringData_JPA_Hibernate.Service.ITowerService;
import com.example.Java_SpringData_JPA_Hibernate.Service.MageService;
import com.example.Java_SpringData_JPA_Hibernate.Service.TowerService;
import com.example.Java_SpringData_JPA_Hibernate.DatabaseHelpers.DatabaseSeeder;
import com.example.Java_SpringData_JPA_Hibernate.DatabaseHelpers.DatabaseManager;

@Configuration
@ComponentScan("Repository")
public class AppConfig {

    private final MageRepository mageRepository;
    private final TowerRepository towerRepository;

    @Autowired
    public AppConfig(MageRepository mageRepository, TowerRepository towerRepository) {
        this.mageRepository = mageRepository;
        this.towerRepository = towerRepository;
    }

    @Bean
    public ITowerService towerService() {
        return new TowerService(towerRepository);
    }

    @Bean
    public IMageService mageService() {
        return new MageService(mageRepository, towerService());
    }

    @Bean
    public DatabaseSeeder databaseSeeder() {
        return new DatabaseSeeder(towerRepository, mageRepository);
    }

    @Bean
    public DatabaseManager databaseManager() {
        return new DatabaseManager(towerService(), mageService());
    }
}
