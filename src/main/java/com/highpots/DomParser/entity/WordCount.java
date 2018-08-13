package com.highpots.DomParser.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "WordCount")
@Table(name = "SYS_WORDS_COUNT_TABLE")
public class WordCount {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer number;

    public WordCount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordCount)) return false;
        WordCount wordCount = (WordCount) o;
        return Objects.equals(getId(), wordCount.getId()) &&
                Objects.equals(getName(), wordCount.getName()) &&
                Objects.equals(getNumber(), wordCount.getNumber());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getNumber());
    }
}
