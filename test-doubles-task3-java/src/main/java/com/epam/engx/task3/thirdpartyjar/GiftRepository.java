package com.epam.engx.task3.thirdpartyjar;

import java.util.List;

public interface GiftRepository {

    List<Gift> findGiftsByType(Type type);

}
