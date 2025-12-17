package com.epam.engx.task2;

import com.epam.engx.task2.thirdpartyjar.Flower;
import com.epam.engx.task2.thirdpartyjar.FlowerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FlowerFinderTest {

    @Test
    void findByColor_shouldReturnMatchingFlowers() {
        // Arrange: Mock FlowerRepository to return predefined flowers
        FlowerRepository mockFlowerRepository = Mockito.mock(FlowerRepository.class);

        Flower flower1 = new Flower(1L, "Rose", Set.of("red", "white"));
        Flower flower2 = new Flower(2L, "Tulip", Set.of("yellow", "pink"));
        Flower flower3 = new Flower(3L, "Daisy", Set.of("white", "yellow"));
        when(mockFlowerRepository.findAllFlowers()).thenReturn(List.of(flower1, flower2, flower3));

        FlowerFinder flowerFinder = new FlowerFinder(mockFlowerRepository);

        // Act: Find flowers by color "white"
        List<Flower> result = flowerFinder.findByColor("white");

        // Assert: Verify the returned list contains only the matching flowers
        assertEquals(List.of(flower1, flower3), result);

        // Verify the repository was called exactly once
        verify(mockFlowerRepository, times(1)).findAllFlowers();
    }

    @Test
    void findByColor_shouldReturnEmptyListIfNoFlowersMatch() {
        // Arrange: Mock FlowerRepository to return predefined flowers
        FlowerRepository mockFlowerRepository = Mockito.mock(FlowerRepository.class);

        Flower flower1 = new Flower(1L, "Rose", Set.of("red", "white"));
        Flower flower2 = new Flower(2L, "Tulip", Set.of("yellow", "pink"));
        when(mockFlowerRepository.findAllFlowers()).thenReturn(List.of(flower1, flower2));

        FlowerFinder flowerFinder = new FlowerFinder(mockFlowerRepository);

        // Act: Find flowers by color "blue" (no matches)
        List<Flower> result = flowerFinder.findByColor("blue");

        // Assert: Verify the returned list is empty
        assertEquals(List.of(), result);

        // Verify the repository was called exactly once
        verify(mockFlowerRepository, times(1)).findAllFlowers();
    }

    @Test
    void findByColor_shouldReturnEmptyListWhenRepositoryIsEmpty() {
        // Arrange: Mock FlowerRepository to return an empty list
        FlowerRepository mockFlowerRepository = Mockito.mock(FlowerRepository.class);
        when(mockFlowerRepository.findAllFlowers()).thenReturn(List.of());

        FlowerFinder flowerFinder = new FlowerFinder(mockFlowerRepository);

        // Act: Find flowers by any color in an empty repository
        List<Flower> result = flowerFinder.findByColor("white");

        // Assert: Verify the returned list is empty
        assertEquals(List.of(), result);

        // Verify the repository was called exactly once
        verify(mockFlowerRepository, times(1)).findAllFlowers();
    }

    @Test
    void findByColor_shouldThrowExceptionWhenColorIsNull() {
        // Arrange: Mock FlowerRepository to return predefined flowers
        FlowerRepository mockFlowerRepository = Mockito.mock(FlowerRepository.class);

        Flower flower1 = new Flower(1L, "Rose", Set.of("red", "white"));
        when(mockFlowerRepository.findAllFlowers()).thenReturn(List.of(flower1));

        FlowerFinder flowerFinder = new FlowerFinder(mockFlowerRepository);

        // Act & Assert: Verify method throws IllegalArgumentException for null color
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> flowerFinder.findByColor(null)
        );

        assertEquals("Color must not be null", exception.getMessage());
    }
}