package com.highpots.DomParser.service;

import com.highpots.DomParser.common.AppException;
import com.highpots.DomParser.entity.WordCount;
import com.highpots.DomParser.model.PagingModel;
import com.highpots.DomParser.repository.WordPagingRepository;
import com.highpots.DomParser.repository.WordRepository;
import com.highpots.DomParser.util.DomParserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class WordServiceImpl implements WordService {
    private static Logger log = Logger.getLogger(WordService.class.getName());

    @Autowired
    private WordPagingRepository wordPagingRepository;

    @Autowired
    private WordRepository wordRepository;


    @Override
    public void insertWord(WordCount word) throws AppException {
        try {
            wordPagingRepository.save(word);
        } catch (Throwable t) {
            log.log(Level.ALL, t.getMessage(), t);

            throw new AppException(t.getMessage());
        }
    }

    @Override
    public void insertWords(Set<WordCount> words) throws AppException {
        try {
            wordPagingRepository.saveAll(words);
        } catch (Throwable t) {
            log.log(Level.ALL, t.getMessage(), t);

            throw new AppException(t.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        wordPagingRepository.deleteAll();
    }

    @Override
    public List<WordCount> loadAll() {
        List<WordCount> result = new ArrayList<>();
        wordPagingRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    public PagingModel<WordCount> load(int page, int totalRows, String searchPhase) {
        if (searchPhase == null || searchPhase.trim().isEmpty()) {
            Page<WordCount> wordCountPage = wordPagingRepository.findAll(PageRequest.of(page, totalRows));
            return new PagingModel<WordCount>(wordCountPage.getNumber(), wordCountPage.getNumberOfElements(), wordCountPage.getTotalElements(), wordCountPage.getContent());
        } else {
            return wordRepository.find(page, totalRows, searchPhase);
        }

    }

    @Override
    public List<WordCount> parseAndSave(String url) throws Exception {
        deleteAll();

        List<WordCount> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : DomParserHelper.parse(url).entrySet()) {
            WordCount entity = new WordCount();
            entity.setName(entry.getKey());
            entity.setNumber(entry.getValue());
            insertWord(entity);
            result.add(entity);
        }

        return result;
    }

    @Override
    public long count() {
        return wordPagingRepository.count();
    }
}
