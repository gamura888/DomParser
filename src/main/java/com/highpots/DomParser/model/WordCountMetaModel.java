package com.highpots.DomParser.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WordCountMetaModel implements Serializable {
    private Long id;
    private String name;
    private Integer number;

    public WordCountMetaModel() {
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
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WordCountMetaModel)) {
            return false;
        }

        WordCountMetaModel model = (WordCountMetaModel) o;

        if (model.getId() == this.getId() && model.getName().equals(this.getName())) {
            return true;
        }

        return false;
    }
}
