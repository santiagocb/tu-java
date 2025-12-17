package com.epam.cleandesign.dip;

import com.epam.cleandesign.dip.api.NewsBroadcaster;
import com.epam.cleandesign.dip.dao.NewsArticleDAO;
import com.epam.cleandesign.dip.publishing.PublishService;
import com.epam.cleandesign.dip.thirdpartyjar.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewsBroadcasterTest {

    private NewsBroadcaster newsBroadcaster;
    private PublishService publishService;

    private EntityManager entityManager;
    private NewsArticleDAO newsArticleDAO;

    @BeforeEach
    public void setUp() {
        entityManager = new EntityManager();
        newsArticleDAO = new NewsArticleDAO();
        newsArticleDAO.setEntityManager(entityManager);

        publishService = new PublishService();
        
        newsBroadcaster = new NewsBroadcaster(newsArticleDAO, publishService);
    }

    @Test
    public void shouldPublishRegionalNews() {
        String regional = newsBroadcaster.broadcastNews("Regional");
        assertTrue(regional.startsWith("Regional News:"));
        assertTrue(regional.contains("<"));
        assertTrue(regional.contains(">"));
    }

    @Test
    public void shouldPublishNationalNews() {
        String national = newsBroadcaster.broadcastNews("National");
        assertTrue(national.startsWith("National News:"));
        assertFalse(national.contains("<"));
        assertFalse(national.contains(">"));
        assertTrue(national.contains(" -- "));
    }
}