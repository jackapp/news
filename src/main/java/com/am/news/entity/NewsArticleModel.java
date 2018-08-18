package com.am.news.entity;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class NewsArticleModel {

    private String id;
    private String headline;
    private String sourceLink;
    private String publisher;
    private String baseUrl;
    private Date publishedDate;
    private String newsType;

}