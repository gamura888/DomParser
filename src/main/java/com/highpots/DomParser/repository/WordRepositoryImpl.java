package com.highpots.DomParser.repository;

import com.highpots.DomParser.entity.WordCount;
import com.highpots.DomParser.model.PagingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class WordRepositoryImpl implements WordRepository {

    @Autowired
    private EntityManager em;

    @Override
    public PagingModel<WordCount> find(int page, int rows, String searchPhrase) {
        TypedQuery<WordCount> query = em.createQuery("select wc from WordCount wc where wc.name like :name", WordCount.class)
                .setParameter("name", "%" + searchPhrase + "%");

        long totalCount = count(searchPhrase);
        query.setFirstResult(page * rows - rows);

        query.setMaxResults(rows);

        return new PagingModel<WordCount>(page, rows, totalCount, query.getResultList());

    }

    @Override
    public long count(String searchPhrase) {
        return em.createQuery("select count(wc.id) from WordCount wc where wc.name like :name", Long.class)
                .setParameter("name", "%" + searchPhrase + "%")
                .getSingleResult();
    }
}
