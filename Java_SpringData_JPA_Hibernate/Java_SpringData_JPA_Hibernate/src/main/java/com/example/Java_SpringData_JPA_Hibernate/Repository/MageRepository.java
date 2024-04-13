package com.example.Java_SpringData_JPA_Hibernate.Repository;

import com.example.Java_SpringData_JPA_Hibernate.Entities.Mage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MageRepository extends JpaRepository<Mage, String> {
    @Query("SELECT m FROM Mage m WHERE m.level > ?1")
    List<Mage> findAllByLevelGreaterThan(int level);

    @Query("SELECT m FROM Mage m WHERE m.level > ?1 AND m.tower.name = ?2")
    List<Mage> findAllByLevelGreaterThanAndTowerEquals(int level, String towerName);
}
