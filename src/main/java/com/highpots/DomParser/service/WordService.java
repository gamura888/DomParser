package com.highpots.DomParser.service;

import com.highpots.DomParser.common.AppException;
import com.highpots.DomParser.entity.WordCount;
import com.highpots.DomParser.model.PagingModel;
import com.highpots.DomParser.model.WordCountMetaModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface WordService {
    void insertWord(WordCount word) throws AppException;

    void insertWords(Set<WordCount> words) throws AppException;

    void deleteAll();

    List<WordCount> loadAll();

    PagingModel<WordCount> load(int page, int totalRows, String searchPhase);

    List<WordCount> parseAndSave(String url) throws Exception;

    long count();

}
