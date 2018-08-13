package com.highpots.DomParser.repository;

import com.highpots.DomParser.entity.WordCount;
import com.highpots.DomParser.model.PagingModel;

public interface WordRepository {
    PagingModel<WordCount> find(int page, int rows, String searchPhrase);

    long count(String searchPhrase);
}
