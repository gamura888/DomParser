package com.highpots.DomParser.util;

import com.highpots.DomParser.common.AppException;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.HashMap;
import java.util.Map;

/**
 * helper class for parsing html DOM
 */

public class DomParserHelper {

    public static Map<String, Integer> parse(String url) throws Exception {
        Map<String, Integer> result = new HashMap<>();

        if (url == null || url.trim().isEmpty()) {
            throw new AppException("Url not spceified");
        } else if (!new UrlValidator().isValid(url)) {
            throw new AppException("Url is not valid!");
        }

        Document doc = null;
        doc = Jsoup.connect(url).get();

        parseAllTextNodes(doc.body(), result);

        return result;
    }


    private static void parseAllTextNodes(Node element, Map<String, Integer> result) {
        for (Node n : element.childNodes()) {
            if (n instanceof TextNode && !((TextNode) n).isBlank()) {
                String text = ((TextNode) n).text();
                String[] words = text.trim().split("[^\\w]");
                if (words.length > 0) {
                    for (String wotd : words) {
                        if (!wotd.trim().isEmpty()) {
                            int counter = result.get(wotd) == null ? 0 : result.get(wotd);
                            result.put(wotd, ++counter);
                        }
                    }
                }
            } else {
                parseAllTextNodes(n, result);
            }
        }
    }

}
