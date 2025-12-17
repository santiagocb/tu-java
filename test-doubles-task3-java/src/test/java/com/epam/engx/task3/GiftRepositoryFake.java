package com.epam.engx.task3;

import com.epam.engx.task3.thirdpartyjar.Gift;
import com.epam.engx.task3.thirdpartyjar.GiftRepository;
import com.epam.engx.task3.thirdpartyjar.Type;

import java.util.ArrayList;
import java.util.List;

public class GiftRepositoryFake implements GiftRepository {

    private final List<Gift> gifts = new ArrayList<>();

    public void addGift(Gift gift) {
        gifts.add(gift);
    }

    @Override
    public List<Gift> findGiftsByType(Type type) {
        return gifts.stream()
                .filter(gift -> gift.type() == type)
                .toList();
    }
}