package com.example.Java_Testing_API.Repositories;

import com.example.Java_Testing_API.Entities.Mage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMageRepository  extends JpaRepository<Mage, String> {
}
