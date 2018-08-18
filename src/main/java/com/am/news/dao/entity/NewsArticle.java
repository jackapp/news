package com.am.news.dao.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "news_articles")
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class NewsArticle {

    @Id
    private String id;
    private String headline;
    private String sourceLink;
    private String publisher;
    private String baseUrl;
    private Date publishedDate;
    private String newsType;

}
