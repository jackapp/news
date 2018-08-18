package com.am.news.dao;

import com.am.news.dao.entity.NewsArticle;
import com.am.news.entity.NewsArticleModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDAO<T> {
    Object create(T entity);

    void update(T entity);

    void delete(T entity);

    List<NewsArticleModel> findAllPaginated(Integer pageNo,Integer pageSize,String sortBy,String sortParams);

    int findAllRecords();
}
