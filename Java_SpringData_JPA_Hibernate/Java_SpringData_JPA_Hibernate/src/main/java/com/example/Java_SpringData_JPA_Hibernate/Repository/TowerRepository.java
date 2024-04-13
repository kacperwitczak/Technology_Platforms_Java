package com.example.Java_SpringData_JPA_Hibernate.Repository;

import com.example.Java_SpringData_JPA_Hibernate.Entities.Tower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TowerRepository extends JpaRepository<Tower, String> {

    @Query("SELECT t FROM Tower t WHERE t.height < ?1")
    List<Tower> findAllByHeightLessThan(int height);
}
