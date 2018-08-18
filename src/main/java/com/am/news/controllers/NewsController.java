package com.am.news.controllers;

import com.am.news.dao.entity.NewsArticle;
import com.am.news.dto.NewsArticleResponse;
import com.am.news.entity.NewsArticleModel;
import com.am.news.service.NewsService;
import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/news/")
@Slf4j
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("bulletin")
    public NewsArticleResponse findAllNewsArticles(@RequestParam(value = "pageNo",required = false) Integer pageNo,
                                                   @RequestParam(value = "pageSize",required = false)Integer pageSize,
                                                   @RequestParam(value = "sortBy",required = false) String sortBy,
                                                   @RequestParam(value = "order",required = false) String order) {
        if (pageNo==null || pageNo<0) {
            pageNo=1;
        }
        if (pageSize==null || pageSize<0) {
            pageSize=10;
        }
        List<NewsArticleModel> newsArticleModels = newsService.fetchNewsArticle(pageNo,pageSize,sortBy,order);
        NewsArticleResponse newsArticleResponse = new NewsArticleResponse();
        newsArticleResponse.setData(newsArticleModels);
        int count = newsService.findAllNewsArticles();
        newsArticleResponse.setCurrentPage(pageNo);

        newsArticleResponse.setPageSize(newsArticleModels.size());
        int totalPages = count/pageSize + (count%pageSize==0?0:1);
        newsArticleResponse.setTotalPages(totalPages);
        newsArticleResponse.setTotalRecords(count);

        return newsArticleResponse;
    }

    @PostMapping("import")
    public void importCsv() {

        try {
            //csv file containing data
            String strFile = "/reports/news_bulletin.csv";
            CSVReader reader = new CSVReader(new FileReader(strFile));
            String[] newsBulletinCsv;
            int lineNumber = 0;
            while ((newsBulletinCsv = reader.readNext()) != null) {
                lineNumber++;

                String newsId=newsBulletinCsv[0];
                String headline=newsBulletinCsv[1];
                String fullUrl = newsBulletinCsv[2];
                String publisher = newsBulletinCsv[3];
                String newsType = newsBulletinCsv[4];
                String baseUrl = newsBulletinCsv[5];
                String timeStamp = newsBulletinCsv[6];

                NewsArticleModel newsArticleModel = NewsArticleModel.builder().newsType(newsType)
                        .id(newsId).baseUrl(baseUrl).headline(headline).publisher(publisher)
                        .publishedDate(new Date(Long.valueOf(timeStamp))).sourceLink(fullUrl).build();
                newsService.createNewArticle(newsArticleModel);
                log.info("Created a news article with news id:{}",newsArticleModel.getId());
            }
        } catch (IOException e) {
            log.error("Exception occured while reading from csv",e);
        }




        /*
        String csvFile = "/reports/news_bulletin.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {

                String[] newsBulletinCsv = line.split(cvsSplitBy);
                String newsId=newsBulletinCsv[0];
                String headline=newsBulletinCsv[1];
                String fullUrl = newsBulletinCsv[2];
                String publisher = newsBulletinCsv[3];
                String newsType = newsBulletinCsv[4];
                String baseUrl = newsBulletinCsv[5];
                String timeStamp = newsBulletinCsv[6];

                NewsArticleModel newsArticleModel = NewsArticleModel.builder().newsType(newsType)
                        .id(newsId).baseUrl(baseUrl).headline(headline).publisher(publisher)
                        .publishedDate(new Date(Long.valueOf(timeStamp))).sourceLink(fullUrl).build();
                newsService.createNewArticle(newsArticleModel);
                log.info("Created a news article with news id:{}",newsArticleModel.getId());

            }

        } catch (IOException e) {
            log.error("Exception occured while reading from csv",e);
        }
        */

    }
}
