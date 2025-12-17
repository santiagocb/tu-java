package com.epam.engx.task2;

import com.epam.engx.task2.thirdpartyjar.Flower;
import com.epam.engx.task2.thirdpartyjar.FlowerRepository;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class FlowerFinder {

    private final FlowerRepository flowerRepository;

    public FlowerFinder(FlowerRepository flowerRepository) {
        this.flowerRepository = requireNonNull(flowerRepository);
    }

    public List<Flower> findByColor(String color) {
        if (color == null) {
            throw new IllegalArgumentException("Color must not be null");
        }

        return flowerRepository.findAllFlowers().stream()
                .filter(flower -> flower.colors().contains(color))
                .toList();
    }
}
