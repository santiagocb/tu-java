package com.epam.cleandesign.dip.thirdpartyjar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class EntityManager implements Manager {

    public EntityManager() {
    }

    public NewsArticleTable save(NewsArticleTable item) {
        return item;
    }

    public NewsArticleTable get(Long id) {
        return null;
    }

    public void update(NewsArticleTable persistentObject) {

    }

    public void delete(NewsArticleTable persistentObject) {

    }

    public List<NewsArticleTable> getAll() {
        List<NewsArticleTable> queryResult = new ArrayList<>();
        IntStream.rangeClosed(1, 3)
                .forEach(value -> {
                    NewsArticleTable article = new NewsArticleTable();
                    article.setId((long) value);
                    article.setHeadline("Headline " + value);
                    article.setDescription("News description " + value);
                    article.setNewsType("Regional");
                    queryResult.add(article);
                });
        IntStream.rangeClosed(4, 10)
                .forEach(value -> {
                    NewsArticleTable article = new NewsArticleTable();
                    article.setId((long) value);
                    article.setHeadline("Headline " + value);
                    article.setDescription("News description " + value);
                    article.setNewsType("National");
                    queryResult.add(article);
                });
        return queryResult;
    }
}
