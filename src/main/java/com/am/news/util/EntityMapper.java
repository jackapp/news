package com.am.news.util;

import com.am.news.dao.entity.NewsArticle;
import com.am.news.entity.NewsArticleModel;

public class EntityMapper {

    public static NewsArticleModel getNewsArticleModelFromEntity(NewsArticle newsArticle) {
        if (newsArticle!=null) {
            NewsArticleModel newsArticleModel = NewsArticleModel.builder().id(newsArticle.getId())
                    .newsType(newsArticle.getNewsType()).publishedDate(newsArticle.getPublishedDate())
                    .publisher(newsArticle.getPublisher()).headline(newsArticle.getHeadline())
                    .sourceLink(newsArticle.getSourceLink()).baseUrl(newsArticle.getBaseUrl()).build();

            return newsArticleModel;
        }
        return null;
    }

    public static NewsArticle getNewsArticleFromModel(NewsArticleModel newsArticleModel) {

        if (newsArticleModel!=null) {
            NewsArticle newsArticle = NewsArticle.builder().id(newsArticleModel.getId())
                    .newsType(newsArticleModel.getNewsType()).publishedDate(newsArticleModel.getPublishedDate())
                    .publisher(newsArticleModel.getPublisher()).headline(newsArticleModel.getHeadline())
                    .sourceLink(newsArticleModel.getSourceLink()).baseUrl(newsArticleModel.getBaseUrl()).build();

            return newsArticle;
        }
        return null;
    }
}
