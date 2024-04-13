package org.example.Repositories;

import org.example.DTO.MageDTO.MageRequest;
import org.example.DTO.MageDTO.MageResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MageRepositoryTest {

    private MageRepository mageRepository;

    @BeforeEach
    public void setUp() {
        mageRepository = new MageRepository();
    }

    @Test
    public void saveTest_shouldNotSaveWhenMageExists() {
        //Arange
        MageRequest mageRequest = new MageRequest("ggg", 1);
        mageRepository.save(mageRequest);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> mageRepository.save(mageRequest));
    }

    @Test
    public void saveTest_shouldSaveWhenMageDoesNotExists() {
        //Arange
        MageRequest mageRequest = new MageRequest("ggg", 1);

        //Act & Assert
        assertDoesNotThrow(() -> mageRepository.save(mageRequest));
    }

    @Test
    public void deleteTest_shouldNotDeleteWhenIdDoesNotExists() {
        // Arrange
        String id = "ggg";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> mageRepository.delete(id));
    }

    @Test
    public void deleteTest_shouldDeleteWhenIdDoesExists() {
        // Arrange
        MageRequest mageRequest = new MageRequest("ggg", 1);
        mageRepository.save(mageRequest);
        String idToFind = "ggg";

        // Act & Assert
        assertDoesNotThrow(() -> mageRepository.delete(idToFind));
    }

    @Test
    public void findTest_shouldNotFindWhenMageDoesNotExists() {
        // Arrange
        String id = "ggg";

        // Act
        Optional<MageResponse> result = mageRepository.find(id);

        // Assert
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void findTest_shouldFindWhenMageDoesExists() {
        // Arrange
        String id = "ggg";
        MageRequest mageRequest = new MageRequest(id, 1);
        mageRepository.save(mageRequest);

        // Act
        Optional<MageResponse> result = mageRepository.find(id);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(mageRequest.getName(), result.get().getName());
        Assertions.assertEquals(mageRequest.getLevel(), result.get().getLevel());
    }


}