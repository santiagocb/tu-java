package com.epam.cleandesign.dip.publishing;

import com.epam.cleandesign.dip.thirdpartyjar.NewsArticleTable;

import java.util.List;

public interface Service {
    String publishNews(String newsType, List<NewsArticleTable> newsArticles);
}
