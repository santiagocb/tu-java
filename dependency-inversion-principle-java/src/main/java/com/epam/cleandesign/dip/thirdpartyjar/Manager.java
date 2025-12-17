package com.epam.cleandesign.dip.thirdpartyjar;

import java.util.List;

public interface Manager {

    NewsArticleTable save(NewsArticleTable item);

    NewsArticleTable get(Long id);

    void update(NewsArticleTable persistentObject);

    void delete(NewsArticleTable persistentObject);

    List<NewsArticleTable> getAll();
}
