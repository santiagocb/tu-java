package com.epam.cleandesign.dip.dao;

import com.epam.cleandesign.dip.thirdpartyjar.Manager;
import com.epam.cleandesign.dip.thirdpartyjar.NewsArticleTable;

import java.util.List;

public interface DAO {

    List<NewsArticleTable> findByNewsType(String newsType);

    void setEntityManager(Manager entityManager);
}
