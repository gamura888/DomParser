package com.highpots.DomParser.util;

import com.highpots.DomParser.entity.WordCount;
import com.highpots.DomParser.model.WordCountMetaModel;

import java.util.*;

public class ModelHelper {

    public static List<WordCountMetaModel> convert(Collection<WordCount> entities) {
        List<WordCountMetaModel> result = new ArrayList<>();

        ClassUtil<WordCountMetaModel, WordCount> classUtil = new ClassUtil<>();

        entities.forEach(e -> result.add(classUtil.toModel(new WordCountMetaModel(), e)));

        return result;
    }


}
