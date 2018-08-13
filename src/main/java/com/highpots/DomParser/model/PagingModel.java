package com.highpots.DomParser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PagingModel<T> implements Serializable {
    @JsonProperty("current")
    private int pageNumber;
    @JsonProperty("rowCount")
    private int count;
    private long total;
    @JsonProperty("rows")
    private List<T> result;


    public PagingModel() {
        this.result=new ArrayList<>();
    }

    public PagingModel(int pageNumber, int count, long total, List<T> result) {
        this.pageNumber = pageNumber;
        this.count = count;
        this.total = total;
        this.result = result;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
