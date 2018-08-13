package com.highpots.DomParser.repository;

import com.highpots.DomParser.entity.WordCount;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WordPagingRepository extends PagingAndSortingRepository<WordCount, Long> {
}
