package org.example.Controllers;

import junit.framework.TestCase;
import org.example.DTO.MageDTO.MageResponse;
import org.example.Repositories.MageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MageControllerTest {

    private MageController mageController;
    private MageRepository mageRepository;

    @BeforeEach
    public void setUp() {
        mageRepository = Mockito.mock(MageRepository.class);
        mageController = new MageController(mageRepository);
    }

    @Test
    public void findTest_shouldFindWhenMageExists() {
        // Arrange
        String id = "ggg";
        int level = 1;
        MageResponse m = new MageResponse(id, level);
        Mockito.when(mageRepository.find(id)).thenReturn(Optional.of(new MageResponse(id, level)));

        // Act
        String result = mageController.find(id);

        // Assert
        assertEquals(m.toString(), result);
    }

    @Test
    public void findTest_shouldNotFindWhenMageDoesNotExist() {
        // Arrange
        String id = "ggg";
        Mockito.when(mageRepository.find(id)).thenReturn(Optional.empty());

        // Act
        String result = mageController.find(id);

        // Assert
        assertEquals("not found", result);
    }

    @Test
    public void deleteTest_shouldDeleteWhenMageExists() {
        // Arrange
        String id = "ggg";
        Mockito.doNothing().when(mageRepository).delete(id);

        // Act
        String result = mageController.delete(id);

        // Assert
        assertEquals("done", result);
    }

    @Test
    public void deleteTest_shouldNotDeleteWhenMageDoesNotExist() {
        // Arrange
        String id = "ggg";
        Mockito.doThrow(new IllegalArgumentException("Mage not found")).when(mageRepository).delete(id);

        // Act
        String result = mageController.delete(id);

        // Assert
        assertEquals("not found", result);
    }

    @Test
    public void saveTest_shouldSaveWhenMageDoesNotExist() {
        // Arrange
        String id = "ggg";
        int level = 1;
        Mockito.doNothing().when(mageRepository).save(Mockito.any());

        // Act
        String result = mageController.save(id, String.valueOf(level));

        // Assert
        assertEquals("done", result);
    }

    @Test
    public void saveTest_shouldNotSaveWhenMageExists() {
        // Arrange
        String id = "ggg";
        int level = 1;
        Mockito.doThrow(new IllegalArgumentException("Mage already exists")).when(mageRepository).save(Mockito.any());

        // Act
        String result = mageController.save(id, String.valueOf(level));

        // Assert
        assertEquals("bad request", result);
    }
}