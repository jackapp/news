package com.am.news.dao;

import com.am.news.dao.entity.NewsArticle;
import com.am.news.entity.NewsArticleModel;
import com.am.news.util.EntityMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsDAOImpl  extends BaseDAOImpl implements NewsDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected NewsArticleModel getEntityModel(Object storeEntity) {
        return storeEntity instanceof NewsArticle ? EntityMapper.getNewsArticleModelFromEntity((NewsArticle) storeEntity) : null;
    }

    @Override
    protected NewsArticle getEntityFromModel(Object object) {
        return object instanceof NewsArticleModel ? EntityMapper.getNewsArticleFromModel((NewsArticleModel) object) : null;
    }

    @Override
    public List<NewsArticleModel> findAllPaginated(Integer pageNo, Integer pageSize, String sortBy, String sortParams) {
        return getEntityModelList(search(NewsArticleSearchRequest.builder().sortBy(sortBy).order(sortParams).build(),
                pageNo,pageSize));
    }

    @Override
    public int findAllRecords() {
        return getCountOfPaginatedQuery(null);
    }

    private List<NewsArticle> search(NewsArticleSearchRequest newsArticleSearchRequest,Integer pageNo,Integer pageSize) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsArticle> criteriaQuery = criteriaBuilder.createQuery(NewsArticle.class);
        Root<NewsArticle> newsArticleRoot = criteriaQuery.from(NewsArticle.class);
        List<Predicate> predicateList = new ArrayList<Predicate>();


        if (!StringUtils.isEmpty(newsArticleSearchRequest.getSortBy())) {
            if (StringUtils.equalsIgnoreCase(newsArticleSearchRequest.getOrder(),"asc")) {
                criteriaQuery.orderBy(criteriaBuilder.asc(newsArticleRoot.get(newsArticleSearchRequest.getSortBy())));
            } else if (StringUtils.equalsIgnoreCase(newsArticleSearchRequest.getSortBy(),"desc")) {
                criteriaQuery.orderBy(criteriaBuilder.desc(newsArticleRoot.get(newsArticleSearchRequest.getSortBy())));
            }

        }

        CriteriaQuery<NewsArticle> select = criteriaQuery.select(newsArticleRoot).where(predicateList.toArray(new Predicate[]{}));
        TypedQuery<NewsArticle> typedQuery = entityManager.createQuery(select);

        typedQuery.setFirstResult((pageNo - 1)*pageSize);
        typedQuery.setMaxResults(pageSize);
        return typedQuery.getResultList();
    }

    public int getCountOfPaginatedQuery(NewsArticleSearchRequest newsArticleSearchRequest) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder
                .createQuery(Long.class);
        Root<NewsArticle> newsArticleRoot = countQuery.from(NewsArticle.class);
        countQuery.select(criteriaBuilder
                .count(newsArticleRoot));
        Long count = entityManager.createQuery(countQuery)
                .getSingleResult();

        return count.intValue();
    }

    private List<NewsArticleModel> getEntityModelList(List<NewsArticle> kycCase) {

        List<NewsArticleModel> newsArticleModels = new ArrayList<>();
        for (NewsArticle iterator : kycCase) {
            NewsArticleModel newsArticleModel = getEntityModel(iterator);
            if (newsArticleModel != null) {
                newsArticleModels.add(newsArticleModel);
            }
        }
        return newsArticleModels;
    }
}
