package com.am.news.dto;

import com.am.news.dao.entity.NewsArticle;
import com.am.news.entity.NewsArticleModel;
import lombok.Data;

import java.util.List;

@Data
public class NewsArticleResponse {

    private int currentPage;
    private int pageSize;
    private int totalPages;
    private int totalRecords;
    private List<NewsArticleModel> data;
}
