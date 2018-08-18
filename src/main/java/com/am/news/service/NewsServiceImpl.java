package com.am.news.service;

import com.am.news.dao.NewsDAO;
import com.am.news.dao.entity.NewsArticle;
import com.am.news.dto.NewsArticleResponse;
import com.am.news.entity.NewsArticleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;

    @Override
    public List<NewsArticleModel> fetchNewsArticle(Integer pageNo, Integer pageSize, String sortBy, String order) {
        return newsDAO.findAllPaginated(pageNo, pageSize, sortBy, order);
    }

    @Override
    public int findAllNewsArticles() {
        return newsDAO.findAllRecords();
    }

    @Override
    public NewsArticleModel createNewArticle(NewsArticleModel newsArticleModel) {
        return (NewsArticleModel) newsDAO.create(newsArticleModel);
    }
}
