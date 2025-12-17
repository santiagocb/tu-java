package com.epam.cleandesign.dip.api;

import com.epam.cleandesign.dip.dao.DAO;
import com.epam.cleandesign.dip.dao.NewsArticleDAO;
import com.epam.cleandesign.dip.publishing.Service;
import com.epam.cleandesign.dip.thirdpartyjar.NewsArticleTable;
import com.epam.cleandesign.dip.publishing.PublishService;

import java.util.List;

public class NewsBroadcaster {

    private DAO newsArticleDAO;
    private Service publishService;

    public NewsBroadcaster(DAO newsArticleDAO, Service publishService) {
        this.newsArticleDAO = newsArticleDAO;
        this.publishService = publishService;
    }

    public String broadcastNews(String newsType) {
        List<NewsArticleTable> newsArticles = newsArticleDAO.findByNewsType(newsType);
        return publishService.publishNews(newsType, newsArticles);
    }

}
