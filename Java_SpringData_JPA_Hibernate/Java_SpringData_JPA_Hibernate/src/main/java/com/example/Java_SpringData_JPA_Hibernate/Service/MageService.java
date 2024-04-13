package com.example.Java_SpringData_JPA_Hibernate.Service;

import com.example.Java_SpringData_JPA_Hibernate.Entities.Mage;
import com.example.Java_SpringData_JPA_Hibernate.Entities.Tower;
import com.example.Java_SpringData_JPA_Hibernate.Repository.MageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MageService implements IMageService{

    private final ITowerService towerService;
    private final MageRepository mageRepository;

    public MageService(MageRepository mageRepository, ITowerService towerService) {
        this.mageRepository = mageRepository;
        this.towerService = towerService;
    }

    @Override
    public void addMage(Mage m) {
        mageRepository.save(m);
    }

    @Override
    public void updateMage(Mage m) {
        mageRepository.save(m);
    }

    @Override
    public void deleteMage(String id) {
        System.out.println("Deleting mage with id: " + id);
        Mage mage = mageRepository.findById(id).orElse(null);
        assert mage != null;
        Tower dbTower = towerService.getTower(mage.getTower().getName());
        assert dbTower != null;
        dbTower.removeMage(mage);
        towerService.updateTower(dbTower);
        mageRepository.deleteById(id);
    }

    @Override
    public Mage getMage(String id) {
        Optional<Mage> optMage = mageRepository.findById(id);
        return optMage.orElse(null);
    }

    @Override
    public List<Mage> getAllMages() {
        return mageRepository.findAll();
    }

    @Override
    public List<Mage> findAllByLevelGreaterThanAndTowerEquals(int level, String towerName) {
        return mageRepository.findAllByLevelGreaterThanAndTowerEquals(level, towerName);
    }

    @Override
    public List<Mage> findAllByLevelGreaterThan(int level) {
        return mageRepository.findAllByLevelGreaterThan(level);
    }
}
