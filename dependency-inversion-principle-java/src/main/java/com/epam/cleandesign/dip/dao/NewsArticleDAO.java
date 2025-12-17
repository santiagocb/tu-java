package com.epam.cleandesign.dip.dao;

import com.epam.cleandesign.dip.thirdpartyjar.EntityManager;
import com.epam.cleandesign.dip.thirdpartyjar.Manager;
import com.epam.cleandesign.dip.thirdpartyjar.NewsArticleTable;

import java.util.List;
import java.util.stream.Collectors;

public class NewsArticleDAO implements DAO {
    private Manager entityManager;

    public NewsArticleDAO() {
    }

    public List<NewsArticleTable> findByNewsType(String newsType) {
        return entityManager.getAll()
                .stream()
                .filter(article -> newsType.equals(article.getNewsType()))
                .collect(Collectors.toList());
    }

    public void setEntityManager(Manager entityManager) {
        this.entityManager = entityManager;
    }
}
