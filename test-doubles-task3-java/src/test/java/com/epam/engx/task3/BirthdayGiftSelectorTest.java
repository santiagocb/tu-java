package com.epam.engx.task3;

import com.epam.engx.task3.thirdpartyjar.Gift;
import com.epam.engx.task3.thirdpartyjar.Person;
import com.epam.engx.task3.thirdpartyjar.Type;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BirthdayGiftSelectorTest {

    @Test
    void selectGift_shouldReturnGiftForChild() {
        // Arrange: Set up the GiftRepositoryFake
        GiftRepositoryFake giftRepositoryFake = new GiftRepositoryFake();
        Gift gift1 = new Gift(1L, "Toy Car", Type.FOR_CHILD);
        giftRepositoryFake.addGift(gift1);

        BirthdayGiftSelector giftSelector = new BirthdayGiftSelector(giftRepositoryFake);

        Person child = new Person(1L, "Alice", 8); // Child

        // Act: Call the method
        Optional<Gift> selectedGift = giftSelector.selectGift(child);

        // Assert: Verify the proper gift is returned
        assertTrue(selectedGift.isPresent());
        assertEquals(gift1, selectedGift.get());
    }

    @Test
    void selectGift_shouldReturnGiftForTeenager() {
        // Arrange: Set up the GiftRepositoryFake
        GiftRepositoryFake giftRepositoryFake = new GiftRepositoryFake();
        Gift gift1 = new Gift(2L, "Book", Type.FOR_TEENAGER);
        giftRepositoryFake.addGift(gift1);

        BirthdayGiftSelector giftSelector = new BirthdayGiftSelector(giftRepositoryFake);

        Person teenager = new Person(2L, "Bob", 15); // Teenager

        // Act: Call the method
        Optional<Gift> selectedGift = giftSelector.selectGift(teenager);

        // Assert: Verify the proper gift is returned
        assertTrue(selectedGift.isPresent());
        assertEquals(gift1, selectedGift.get());
    }

    @Test
    void selectGift_shouldReturnGiftForAdult() {
        // Arrange: Set up the GiftRepositoryFake
        GiftRepositoryFake giftRepositoryFake = new GiftRepositoryFake();
        Gift gift1 = new Gift(3L, "Watch", Type.FOR_ADULT);
        giftRepositoryFake.addGift(gift1);

        BirthdayGiftSelector giftSelector = new BirthdayGiftSelector(giftRepositoryFake);

        Person adult = new Person(3L, "Charlie", 25); // Adult

        // Act: Call the method
        Optional<Gift> selectedGift = giftSelector.selectGift(adult);

        // Assert: Verify the proper gift is returned
        assertTrue(selectedGift.isPresent());
        assertEquals(gift1, selectedGift.get());
    }

    @Test
    void selectGift_shouldReturnEmptyOptionalIfNoGiftsAvailable() {
        // Arrange: Set up the GiftRepositoryFake
        GiftRepositoryFake giftRepositoryFake = new GiftRepositoryFake();

        BirthdayGiftSelector giftSelector = new BirthdayGiftSelector(giftRepositoryFake);

        Person adult = new Person(4L, "Dave", 30); // Adult

        // Act: Call the method
        Optional<Gift> selectedGift = giftSelector.selectGift(adult);

        // Assert: Verify no gift is returned
        assertFalse(selectedGift.isPresent());
    }

    @Test
    void selectGift_shouldReturnEmptyOptionalForNegativeAge() {
        // Arrange: Set up the GiftRepositoryFake
        GiftRepositoryFake giftRepositoryFake = new GiftRepositoryFake();

        BirthdayGiftSelector giftSelector = new BirthdayGiftSelector(giftRepositoryFake);

        Person personWithNegativeAge = new Person(5L, "Eve", -5);

        // Act
        Optional<Gift> selectedGift = giftSelector.selectGift(personWithNegativeAge);

        // Assert
        assertFalse(selectedGift.isPresent());
    }
}