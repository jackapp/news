package com.am.news.dao;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class NewsArticleSearchRequest {
    private String sortBy;
    private String order;
}
