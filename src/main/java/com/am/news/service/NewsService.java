package com.am.news.service;

import com.am.news.dao.entity.NewsArticle;
import com.am.news.dto.NewsArticleResponse;
import com.am.news.entity.NewsArticleModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface NewsService {

    List<NewsArticleModel> fetchNewsArticle(Integer pageNo, Integer pageSize, String sortBy, String order);
    int findAllNewsArticles();
    NewsArticleModel createNewArticle(NewsArticleModel newsArticleModel);
}
