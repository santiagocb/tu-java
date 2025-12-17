package com.epam.engx.task3;

import com.epam.engx.task3.thirdpartyjar.Gift;
import com.epam.engx.task3.thirdpartyjar.GiftRepository;
import com.epam.engx.task3.thirdpartyjar.Person;
import com.epam.engx.task3.thirdpartyjar.Type;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class BirthdayGiftSelector {

    private static final int MAX_CHILD_AGE = 11;
    private static final int MAX_TEENAGER_AGE = 18;

    private final GiftRepository giftRepository;

    public BirthdayGiftSelector(GiftRepository giftRepository) {
        this.giftRepository = requireNonNull(giftRepository);
    }

    public Optional<Gift> selectGift(Person person) {
        List<Gift> gifts = getProperGifts(person.age());

        if (gifts.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(gifts.get(0));
    }

    private List<Gift> getProperGifts(int age) {
        // Handle negative age gracefully
        if (age < 0) {
            return List.of(); // No gifts are available for invalid age
        }
        List<Gift> gifts;
        if (age < MAX_CHILD_AGE) {
            gifts = giftRepository.findGiftsByType(Type.FOR_CHILD);
        } else if (age < MAX_TEENAGER_AGE) {
            gifts = giftRepository.findGiftsByType(Type.FOR_TEENAGER);
        } else {
            gifts = giftRepository.findGiftsByType(Type.FOR_ADULT);
        }
        return gifts;
    }

}
