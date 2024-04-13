package com.example.Java_SpringData_JPA_Hibernate.Service;

import com.example.Java_SpringData_JPA_Hibernate.Entities.Tower;
import com.example.Java_SpringData_JPA_Hibernate.Repository.TowerRepository;

import java.util.List;
import java.util.Optional;

public class TowerService implements ITowerService{

    private final TowerRepository towerRepository;

    public TowerService(TowerRepository towerRepository) {
        this.towerRepository = towerRepository;
    }
    @Override
    public void addTower(Tower t) {
        towerRepository.save(t);
    }

    @Override
    public void updateTower(Tower t) {
        towerRepository.save(t);
    }

    @Override
    public void deleteTower(String id) {
        towerRepository.deleteById(id);
    }

    @Override
    public Tower getTower(String id) {
        Optional<Tower> optTower = towerRepository.findById(id);
        return optTower.orElse(null);
    }

    @Override
    public List<Tower> getAllTowers() {
        return towerRepository.findAll();
    }

    @Override
    public List<Tower> findAllByHeightLessThan(int height) {
        return towerRepository.findAllByHeightLessThan(height);
    }
}
